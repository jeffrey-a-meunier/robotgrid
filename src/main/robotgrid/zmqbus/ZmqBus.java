package robotgrid.zmqbus;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ZmqBus {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected int _port;
    protected ZContext _context;
    protected ZMQ.Socket _pubSocket;
    protected ZMQ.Socket _subSocket;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ZmqBus(final int port) {
        _port = port;
        _context = new ZContext();
        _pubSocket = _context.createSocket(SocketType.PUB);
        _pubSocket.bind("tcp://*:" + port);
        _subSocket = _context.createSocket(SocketType.SUB);
        _subSocket.bind("tcp://*:" + (port + 1));
    }

    // Instance methods =======================================================

    public void publish(final String name, final String message) {
        _pubSocket.sendMore(name);
        _pubSocket.send(message);
    }

    public void subscribe(final String name) {
        _subSocket.subscribe(name.getBytes(ZMQ.CHARSET));
    }

}
