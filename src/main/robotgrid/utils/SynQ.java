package robotgrid.utils;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class SynQ<T> {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Queue<T> _q = new LinkedList<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    /**
     * Returns null if the waiting thread gets interrupted.
     */
    public T deq() {
        synchronized (_q) {
            while (_q.isEmpty()) {
                try {
                    _q.wait();
                }
                catch (final InterruptedException exn) {
                    return null;
                }
            }
            return _q.remove();
        }
    }

    public void enq(final T message) {
        synchronized (_q) {
            _q.add(message);
            _q.notify();
        }
    }

    public boolean isEmpty() {
        synchronized (_q) {
            return _q.size() == 0;
        }
    }

    public synchronized Optional<T> poll() {
        T elem = null;;
        synchronized (_q) {
            elem = _q.poll();
        }
        if (elem == null) {
            return Optional.empty();
        }
        return Optional.of(elem);
    }

}
