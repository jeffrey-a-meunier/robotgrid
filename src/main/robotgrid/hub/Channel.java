package robotgrid.hub;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Channel {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected String _name;
    protected Optional<Message> _message = Optional.empty();
    protected Object _messageMutex = new Object();
    protected Set<Subscriber> _subscribers = new HashSet<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Channel(final String name) {
        _name = name;
    }

    // Instance methods =======================================================

    public void clear() {
        synchronized (_messageMutex) {
            _message = Optional.empty();
        }
    }

    public Optional<Message> read() {
        return _message;
    }

    public void write(final Message message) {
        synchronized (_messageMutex) {
            _message = Optional.of(message);
            _notifySubscribers(Action.Write);
        }
    }

    public void trigger() {
        _notifySubscribers(Action.Trigger);
    }

    public void subscribe(final Subscriber subscriber) {
        synchronized (_subscribers) {
            _subscribers.add(subscriber);
        }
    }

    public void unsubscribe(final Subscriber subscriber) {
        synchronized (_subscribers) {
            _subscribers.remove(subscriber);
        }
    }

    @Override
    public String toString() {
        return "Channel{" + _name + ", " + _message + '}';
    }

    protected void _notifySubscribers(final Action action) {
        for (Subscriber subscriber : _subscribers) {
            subscriber.notify(this, action);
        }
    }

}
