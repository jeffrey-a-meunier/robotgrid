package robotgrid.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import robotgrid.device.Command;
import robotgrid.utils.Logger;

public class Server {

    // Static inner classes ===================================================
    // Static variables =======================================================

    public static final Server THE_SERVER = new Server();

    protected static final int _COMMAND_PORT = 43210;  // TODO read from config file
    protected static final int _FEEDBACK_PORT = _COMMAND_PORT + 1;  // TODO read from config file

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

    protected ServerSocket _feedbackServerSocket;
    protected Thread _feedbackThread;
    protected PrintWriter _feedbackSocketPrintWriter;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Server() {
        try {
            _commandServerSocket = new ServerSocket(_COMMAND_PORT);
            _feedbackServerSocket = new ServerSocket(_FEEDBACK_PORT);
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
            _feedbackServerSocket.close();
        }
        catch (final IOException exn) {
            _LOGGER.error("terminate() caught exception trying to close feedback server socket ", exn.toString());
        }
        _commandThread.interrupt();
        _feedbackThread.interrupt();
    }

    protected void _startThreads() {
        _commandThread = new Thread() {
            @Override
            public void run() {
                _commandThreadHandler();
            }
        };
        _commandThread.start();
        // TODO I think the _feedbackThread is no longer needed; the _feedbackSocketPrintWriter can be used
        // to write directly to the client.
        _feedbackThread = new Thread() {
            @Override
            public void run() {
                _feedbackThreadHandler();
            }
        };
        _feedbackThread.start();
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

    protected void _feedbackThreadHandler() {
        _LOGGER.info("Feedback thread handler listening on port ", _FEEDBACK_PORT);
        try {
            while (!_commandThread.isInterrupted()) {
                Socket clientSocket = _feedbackServerSocket.accept();
                _LOGGER.info("Feedback thread handler got socket ", clientSocket);
                _handleFeedbackSocket(clientSocket);
            }
        }
        catch (final IOException exn) {
            _LOGGER.error("_feedbackThreadHandler caught exception ", exn.toString());
        }
    }

    public void handleCommandString(final String commandString) {
        Command command = new Command(commandString);
        command.performLifecycle();
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

    protected void _handleFeedbackSocket(final Socket clientSocket) {
        try {
            _feedbackSocketPrintWriter = new PrintWriter(clientSocket.getOutputStream());
            Client.setFeedbackChannel(_feedbackSocketPrintWriter);
            // TODO how to detect when the client closes this connection?
            // Client.setInfoChannel(null);
        }
        catch (final IOException exn) {
            _LOGGER.error("exception while handling client info socket ", exn);
        }
    }

}
