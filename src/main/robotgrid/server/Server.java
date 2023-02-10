package robotgrid.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import robotgrid.entity.Command;
import robotgrid.entity.Entity;
import robotgrid.utils.Logger;

public class Server {

    // Static inner classes ===================================================
    // Static variables =======================================================

    public static final Server THE_SERVER = new Server();

    protected static final int _COMMAND_PORT = 43210;  // TODO read from config file
    protected static final int _INFO_PORT = _COMMAND_PORT + 1;  // TODO read from config file

    private static Logger _LOGGER = new Logger(Server.class, Logger.Level.All);

    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup() {
        // Do nothing. Calling this method forces the static THE_SERVER
        // variable to be initialized.
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected ServerSocket _commandServerSocket;
    protected Thread _commandThread;
    protected PrintWriter _commandSocketPrintWriter;

    protected ServerSocket _infoServerSocket;
    protected Thread _infoThread;
    protected PrintWriter _infoSocketPrintWriter;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Server() {
        try {
            _commandServerSocket = new ServerSocket(_COMMAND_PORT);
            _infoServerSocket = new ServerSocket(_INFO_PORT);
        }
        catch (final Exception exn) {
            _LOGGER.fatal("Constructor caught exception ", exn.toString());
        }
        _startThreads();
    }

    // Instance methods =======================================================

    public void terminate() {
        try {
            _commandServerSocket.close();
        }
        catch (final IOException exn) {
            _LOGGER.error("terminate() caught exception trying to close command server socket ", exn.toString());
        }
        try {
            _infoServerSocket.close();
        }
        catch (final IOException exn) {
            _LOGGER.error("terminate() caught exception trying to close info server socket ", exn.toString());
        }
        _commandThread.interrupt();
        _infoThread.interrupt();
    }

    protected void _startThreads() {
        _commandThread = new Thread() {
            @Override
            public void run() {
                _commandThreadHandler();
            }
        };
        _commandThread.start();
        // TODO I think the infoThread is no longer needed; the _infoSocketPrintWriter can be used
        // to write directly to the client.
        _infoThread = new Thread() {
            @Override
            public void run() {
                _infoThreadHandler();
            }
        };
        _infoThread.start();
    }

    protected void _commandThreadHandler() {
        _LOGGER.info("Command handler listening on port ", _COMMAND_PORT);
        try {
            while (!_commandThread.isInterrupted()) {
                Socket clientSocket = _commandServerSocket.accept();
                _LOGGER.info("Command handler got socket ", clientSocket);
                _handleCommandSocket(clientSocket);
            }
        }
        catch (final IOException exn) {
            _LOGGER.error("_commandThreandler caught exception ", exn.toString());
        }
    }

    protected void _infoThreadHandler() {
        _LOGGER.info("Info handler listening on port ", _INFO_PORT);
        try {
            while (!_commandThread.isInterrupted()) {
                Socket clientSocket = _infoServerSocket.accept();
                _LOGGER.info("Info handler got socket ", clientSocket);
                _handleInfoSocket(clientSocket);
            }
        }
        catch (final IOException exn) {
            _LOGGER.error("_commandThreadHandler caught exception ", exn.toString());
        }
    }

    public void handleCommandString(final String commandString) {
        Command command = new Command(commandString);
        if (command.validate()) {
            if (command.handler().isImmediate()) {
                command.handler().execute(command);
                Client.COMMAND_REPLY.showResult(command);
            }
            else {
                Entity entity = command.entity();
                if (entity.isOn()) {
                    command.entity().sendCommand(command);
                    Client.COMMAND_REPLY.commandStarted(command);
                }
                else {
                    Client.COMMAND_REPLY.entityIsPoweredOff(command);
                }
            }
        }
    }

    protected void _handleCommandSocket(final Socket clientSocket) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            _commandSocketPrintWriter = new PrintWriter(clientSocket.getOutputStream());
            Client.setCommandReplyChannel(_commandSocketPrintWriter);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                line = line.strip();
                if (line.length() == 0) {
                    continue;
                }
                handleCommandString(line);
            }
            clientSocket.close();
        }
        catch (final IOException exn) {
            _LOGGER.error("exception while closing client command socket ", exn);
        }
        finally {
            Client.setCommandReplyChannel(null);
        }
    }

    protected void _handleInfoSocket(final Socket clientSocket) {
        try {
            _infoSocketPrintWriter = new PrintWriter(clientSocket.getOutputStream());
            Client.setInfoChannel(_infoSocketPrintWriter);
            // TODO how to detect when the client closes this connection?
            // Client.setInfoChannel(null);
        }
        catch (final IOException exn) {
            _LOGGER.error("exception while handling client info socket ", exn);
        }
    }

}
