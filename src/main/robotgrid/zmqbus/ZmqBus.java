package robotgrid.zmqbus;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import robotgrid.entity.active.controller.Controller;

public class ZmqBus implements Runnable {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected ZContext _context;
    protected ZMQ.Socket _socket;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ZmqBus() {
        _context = new ZContext();
        _socket = _context.createSocket(SocketType.REP);
    }

    // Instance methods =======================================================


    public void test1() {
        // Socket to talk to clients
        _socket.bind("tcp://*:5555");
        while (!Thread.currentThread().isInterrupted()) {
            // Block until a message is received
            byte[] reply = _socket.recv(0);

            // Print the message
            System.out.println("Received: [" + new String(reply, ZMQ.CHARSET) + "]");

            // Send a response
            String response = "Hello, world!";
            _socket.send(response.getBytes(ZMQ.CHARSET), 0);
        }
    }

    @Override
    public void run() {
    }

    public void subscribe(final Controller controller) {
        // TODO
        String name = controller.name();
    }

}
