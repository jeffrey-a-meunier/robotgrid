package robotgrid.hub;

/**
 * Implementers are encouraged to override and specialize this class.
 */
public class Message {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected String _receiver;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Message(final String receiver) {
        _receiver = receiver;
    }

    // Instance methods =======================================================


    public String receiver() {
        return _receiver;
    }

    @Override
    public String toString() {
        return "Message{" + _receiver + '}';
    }

}
