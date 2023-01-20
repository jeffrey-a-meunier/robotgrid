package robotgrid.network;

@Deprecated  // <- until I can decide if I really want to use it
public interface NetworkMessageHandler_deprecated {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    public String name();
    public void handleMessage(final Message message);

    /**
     * The network interface allows any device to be powered on or off through
     * the network interface itself.
     */
    public void powerOn();
    public void powerOff();

}
