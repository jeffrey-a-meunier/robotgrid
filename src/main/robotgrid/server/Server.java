package robotgrid.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import robotgrid.entity.active.controller.Controller;
import robotgrid.logger.Logger;

public class Server {

    // Static inner classes ===================================================
    // Static variables =======================================================

    public static final Server THE_SERVER = new Server();

    protected static final int _COMMAND_PORT = 43210;
    protected static final int _INFO_PORT = _COMMAND_PORT + 1;

    private Logger _logger = new Logger(Server.class, Logger.Level.Debug);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected ServerSocket _commandServerSocket;
    protected ServerSocket _infoServerSocket;
    protected Thread _commandThread;
    protected Thread _infoThread;
    protected Queue<String> _infoStringQueue = new LinkedBlockingQueue<>();

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

    public void sendInfo(final String infoString) {
        synchronized (_infoStringQueue) {
            if (_infoStringQueue.offer(infoString)) {
                _infoStringQueue.notify();
            }
            else {
                _logger.error("_infoStringQueue has reached its capacity");
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

    protected void _handleCommandSocket(final Socket clientSocket) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                line = line.strip();
                if (line.length() == 0) {
                    continue;
                }
                Controller.deliverMessage(line);
            }
            clientSocket.close();
        }
        catch (final IOException exn) {
            _logger.error("exception while closing client command socket ", exn);
        }
    }

    protected void _handleInfoSocket(final Socket clientSocket) {
        try {
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream());
            while (!_infoThread.isInterrupted()) {
                synchronized (_infoStringQueue) {
                    while (_infoStringQueue.isEmpty()) {
                        _infoStringQueue.wait();
                    }
                    String string = _infoStringQueue.poll();
                    if (string != null) {
                        pw.println(string);
                        pw.flush();
                    }
                }
            }
            clientSocket.close();
        }
        catch (final IOException exn) {
            _logger.error("exception while handling client info socket ", exn);
        }
        catch (final InterruptedException exn) {
            _logger.error("exception while waiting on input queue ", exn);
        }
    }

}
