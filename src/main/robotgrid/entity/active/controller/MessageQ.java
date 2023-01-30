package robotgrid.entity.active.controller;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class MessageQ {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Queue<Message> _msgq = new LinkedList<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    /**
     * Returns null if the waiting thread gets interrupted.
     */
    public Message deq() {
        synchronized (_msgq) {
            while (_msgq.isEmpty()) {
                try {
                    _msgq.wait();
                }
                catch (final InterruptedException exn) {
                    return new Message(null, Message.Status.Interrupted);
                }
            }
            return _msgq.remove();
        }
    }

    public void enq(final Message message) {
        synchronized (_msgq) {
            _msgq.add(message);
            _msgq.notify();
        }
    }

    public synchronized Optional<Message> poll() {
        Message message = null;;
        synchronized (_msgq) {
            message = _msgq.poll();
        }
        if (message == null) {
            return Optional.empty();
        }
        return Optional.of(message);
    }

}
