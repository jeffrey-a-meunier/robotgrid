package robotgrid.hub;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import robotgrid.logger.Logger;

public class Hub implements Runnable {

    // Static inner classes ===================================================

    public static enum Result {
        Success, NoSuchChannel;
    }

    // Static variables =======================================================

    protected static Map<String, Channel> _ALL_CHANNELS = new HashMap<>();

    private Logger _logger = new Logger(Hub.class, Logger.Level.All);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Thread _thread;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Hub(final boolean startImmediately) {
        if (startImmediately) {
            start();
        }
    }

    // Instance methods =======================================================

    // Channel management -----------------------------------------------------

    public void addChannel(final String name) {
        synchronized (_ALL_CHANNELS) {
            _addChannel_nosync(name);
        }
        _logger.debug("addChannel() added channel ", name);
    }

    protected Channel _addChannel_nosync(final String name) {
        Channel channel = new Channel(name);
        _ALL_CHANNELS.put(name, channel);
        return channel;
    }

    public List<String> channelNames() {
        Set<String> channelNames;
        synchronized (_ALL_CHANNELS) {
            channelNames = _ALL_CHANNELS.keySet();
        }
        List<String> sortedNames = new ArrayList<>(channelNames);
        Collections.sort(sortedNames);
        return sortedNames;
    }

    // Messaging --------------------------------------------------------------

    public Optional<Message> read(final String channelName) {
        Channel channel;
        synchronized (_ALL_CHANNELS) {
            channel = _ALL_CHANNELS.get(channelName);
        }
        if (channel == null) {
            return Optional.empty();
        }
        return channel.read();
    }

    public Result write(final String channelName, final Message message) {
        Channel channel;
        synchronized (_ALL_CHANNELS) {
            channel = _ALL_CHANNELS.get(channelName);
        }
        if (channel == null) {
            return Result.NoSuchChannel;
        }
        channel.write(message);
        return Result.Success;
    }

    public Result trigger(final String channelName) {
        Channel channel;
        synchronized (_ALL_CHANNELS) {
            channel = _ALL_CHANNELS.get(channelName);
        }
        if (channel == null) {
            return Result.NoSuchChannel;
        }
        channel.trigger();
        return Result.Success;
    }

    // Subscription management ------------------------------------------------

    public boolean subscribe(final String channelName, final Subscriber subscriber, final boolean createChannel) {
        Channel channel = null;
        synchronized (_ALL_CHANNELS) {
            channel = _ALL_CHANNELS.get(channelName);
            if (channel == null && createChannel) {
               channel = _addChannel_nosync(channelName);
            }
        }
        if (channel == null) {
            return false;
        }
        channel.subscribe(subscriber);
        return true;
    }

    // Hub management ---------------------------------------------------------

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
        }
    }

    public void start() {
        _thread = new Thread(this);
        _thread.start();
    }

    public void terminate() {
        _thread.interrupt();
    }

}
