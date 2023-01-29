package robotgrid.zmqbus;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import robotgrid.entity.active.controller.Controller;
import robotgrid.logger.Logger;

public class ZmqBus {

    // Static inner classes ===================================================
    // Static variables =======================================================

    private static Logger _logger = new Logger(ZmqBus.class, Logger.Level.Info);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected int _port;
    protected ZContext _context;
    protected ZMQ.Socket _socket;
    protected Thread _thread;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ZmqBus(final int port) {
        _port = port;
        _context = new ZContext();
        _socket = _context.createSocket(SocketType.PAIR);
        _socket.bind("tcp://*:" + port);
        _logger.info("ZmqBus listening on port " + port + " for socket type PAIR");
        _startThread();
    }

    // Instance methods =======================================================

    protected void _startThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("ZmqBus._startThread.run() waiting for message");
                    byte[] incoming = _socket.recv();
                    String messageString = new String(incoming, ZMQ.CHARSET);
                    System.out.println("ZmqBus got '" + messageString + "'");
                    Controller.deliverMessage(messageString);
                }
            }
        };
        Thread _thread = new Thread(runnable);
        _thread.start();
    }

}
