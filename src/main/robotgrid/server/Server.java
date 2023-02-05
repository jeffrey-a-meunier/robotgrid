package robotgrid.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import robotgrid.entity.active.controller.Command;
import robotgrid.entity.active.controller.Controller;
import robotgrid.utils.Logger;
import robotgrid.utils.Result;

public class Server {

    // Static inner classes ===================================================
    // Static variables =======================================================

    public static final Server THE_SERVER = new Server();

    protected static final int _COMMAND_PORT = 43210;
    protected static final int _INFO_PORT = _COMMAND_PORT + 1;

    private Logger _logger = new Logger(Server.class, Logger.Level.All);

    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup() {
        // Do nothing. This is here to force the static THE_SERVER variable to
        // be initialized.
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected ServerSocket _commandServerSocket;
    protected Thread _commandThread;
    protected PrintWriter _commandSocketPrintWriter;

    protected ServerSocket _infoServerSocket;
    protected Thread _infoThread;
    protected PrintWriter _infoSocketPrintWriter;
    // protected Queue<String> _infoStringQueue = new LinkedBlockingQueue<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Server() {
        try {
            _commandServerSocket = new ServerSocket(_COMMAND_PORT);
            _infoServerSocket = new ServerSocket(_INFO_PORT);
        }
        catch (final Exception exn) {
            _logger.fatal("Constructor caught exception ", exn.toString());
        }
        _startThreads();
    }

    // Instance methods =======================================================

    // TODO refactor this after it is verified to work
    public void reportCommandResult(final Command command) {
        Result<Void, String> result = command.result();
        if (result.isSuccess) {
            if (command.handler().isImmediate()) {
                sendCommandReply("OK " + command.uid + " " + command);
            }
            else {
                sendInfo("OK " + command.uid + " " + command);
            }
        }
        else {
            if (command.handler().isImmediate()) {
                sendCommandReply("ERROR " + command.uid + " " + command + " " + result);
            }
            else {
                sendInfo("OK " + command.uid + " " + command + " " + result);
            }
        }
    }

    public void controllerNotFound(final String controllerName) {
        sendCommandReply("ERROR Controller not found: " + controllerName);
    }

    public void commandNotFound(final String commandString) {
        sendCommandReply("ERROR Command not found: " + commandString);
    }

    public void commandInvalid(final Command command) {
        sendCommandReply("ERROR Command '" + command + "' is invalid: " + command.result());
    }

    // public void commandAdded(final Command command) {
    //     sendCommandReply("OK Command added: " + command);
    // }

    public void commandComplete(final Controller controller, final Command command) {
        sendInfo("OK Command complete: " + controller + " " + command + " " + command.result());
    }

    public void programComplete(final Controller controller) {
        sendInfo("OK Program complete " + controller);
    }

    public void sendCommandReply(final String replyString) {
        if (_commandSocketPrintWriter == null) {
            _logger.debug("Command reply: ", replyString);
        }
        else {
            synchronized (_commandSocketPrintWriter) {
                _commandSocketPrintWriter.println(replyString);
                _commandSocketPrintWriter.flush();
            }
        }
    }

    public void sendInfo(final String infoString) {
        if (_infoSocketPrintWriter != null) {
            synchronized (_infoSocketPrintWriter) {
                _infoSocketPrintWriter.println(infoString);
                _infoSocketPrintWriter.flush();
            }
        }
    }

    public void terminate() {
        try {
            _commandServerSocket.close();
        }
        catch (final IOException exn) {
            _logger.error("terminate() caught exception trying to close command server socket ", exn.toString());
        }
        try {
            _infoServerSocket.close();
        }
        catch (final IOException exn) {
            _logger.error("terminate() caught exception trying to close info server socket ", exn.toString());
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
        _logger.info("Command handler listening on port ", _COMMAND_PORT);
        try {
            while (!_commandThread.isInterrupted()) {
                Socket clientSocket = _commandServerSocket.accept();
                _logger.info("Command handler got socket ", clientSocket);
                _handleCommandSocket(clientSocket);
            }
        }
        catch (final IOException exn) {
            _logger.error("_commandThreandler caught exception ", exn.toString());
        }
    }

    protected void _infoThreadHandler() {
        _logger.info("Info handler listening on port ", _INFO_PORT);
        try {
            while (!_commandThread.isInterrupted()) {
                Socket clientSocket = _infoServerSocket.accept();
                _logger.info("Info handler got socket ", clientSocket);
                _handleInfoSocket(clientSocket);
            }
        }
        catch (final IOException exn) {
            _logger.error("_commandThreadHandler caught exception ", exn.toString());
        }
    }

    public void handleCommandString(final String commandString) {
        Command command = new Command(commandString);
        if (!command.validate()) {
            Server.THE_SERVER.commandInvalid(command);
            return;
        }
        command.sendToController();
        // Server.THE_SERVER.commandAdded(command);
    }

    protected void _handleCommandSocket(final Socket clientSocket) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            _commandSocketPrintWriter = new PrintWriter(clientSocket.getOutputStream());
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
            _logger.error("exception while closing client command socket ", exn);
        }
    }

    protected void _handleInfoSocket(final Socket clientSocket) {
        try {
            _infoSocketPrintWriter = new PrintWriter(clientSocket.getOutputStream());
        }
        catch (final IOException exn) {
            _logger.error("exception while handling client info socket ", exn);
        }
    }

    protected Controller _locateController(final String controllerName) {
        return Controller.lookup(controllerName);
    }

}
