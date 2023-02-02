package robotgrid.hub;

@Deprecated
public interface Subscriber {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    /**
     * This method is called when a new message has been added to the channel.
     * A subscribers should not spend a lot of time handing this method, as
     * this method is called from the Hub's main thread.
     */
    public default void notify(final Channel channel, final Action action) {
        switch (action) {
            case Read:
                notifyRead(channel);
                break;
            case Write:
                notifyWrite(channel);
                break;
            case Trigger:
                notifyTrigger(channel);
                break;
        }
    }

    public void notifyRead(final Channel channel);
    public void notifyWrite(final Channel channel);
    public void notifyTrigger(final Channel channel);

}
