package robotgrid.network;

import java.util.Optional;

public class Message {

    // Static inner classes ===================================================

    public static enum Status {
        Normal("Normal"),
        Interrupted("Interrupted");

        public final String name;

        private Status(final String name) {
            this.name = name;
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    Optional<String> _payload = Optional.empty();
    protected Status _status = Status.Normal;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Message(final String payload) {
        _payload = Optional.of(payload);
        _status = Status.Normal;
    }

    public Message(final Status status) {
        _status = status;
    }

    // Instance methods =======================================================

    public boolean isInterrupted() {
        return _status == Status.Interrupted;
    }

    public boolean isNormal() {
        return _status == Status.Normal;
    }

    public Optional<String> payload() {
        return _payload;
    }

    public Status status() {
        return _status;
    }

    @Override
    public String toString() {
        return "Message{" + _payload + ", " + _status + '}';
    }

}
