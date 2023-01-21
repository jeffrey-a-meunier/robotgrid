package robotgrid.network;

import java.util.Arrays;

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

    protected String[] _parts;
    protected Status _status = Status.Normal;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Message(final String ... parts) {
        this(parts, Status.Normal);
    }

    public Message(final String[] parts, final Status status) {
        _parts = parts;
        _status = Status.Normal;
    }

    public Message setStatus(final Status status) {
        _status = status;
        return this;
    }

    // Instance methods =======================================================

    public boolean isInterrupted() {
        return _status == Status.Interrupted;
    }

    public boolean isNormal() {
        return _status == Status.Normal;
    }

    public String[] parts() {
        return _parts;
    }

    public Status status() {
        return _status;
    }

    @Override
    public String toString() {
        return "Message{" + Arrays.toString(_parts) + ", " + _status + '}';
    }

}
