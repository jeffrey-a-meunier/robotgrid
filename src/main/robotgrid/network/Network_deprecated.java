package robotgrid.network;

import java.util.HashMap;
import java.util.Map;

@Deprecated  // <- until I can decide if I really want to use it
public class Network_deprecated {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static Map<String, Network_deprecated> _NETWORKS = new HashMap<>();

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected String _name;
    protected Map<String, NetworkMessageHandler_deprecated> _subscribers = new HashMap<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Network_deprecated(final String name) {
        _name = name;
    }

    // Instance methods =======================================================

    public void sendMessage(final String receiverName, final Message message) {
        NetworkMessageHandler_deprecated receiver = _subscribers.get(receiverName);
        if (receiver != null) {
            receiver.handleMessage(message);
        }
    }

    public void subscribe(final NetworkMessageHandler_deprecated subscriber) {
        _subscribers.put(subscriber.name(), subscriber);
    }

    public void unsubscribe(final NetworkMessageHandler_deprecated subscriber) {
        _subscribers.remove(subscriber.name());
    }

}
