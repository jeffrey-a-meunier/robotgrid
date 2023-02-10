package robotgrid.server;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import robotgrid.entity.Command;
import robotgrid.entity.Entity;

public class ClientChannel {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static final Object _CLIENT_MUTEX = new Object();
    protected static final String _PREFIX = ":";

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected final PrintWriter _pw;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ClientChannel(final PrintWriter pw) {
        _pw = pw;
    }

    // Instance methods =======================================================

    public void commandSuccess(final Command command) {
        write(_PREFIX + "OK [", command, "]");
    }

    public void commandError(final Command command, final String message) {
        write(_PREFIX, "ERROR [", command, "] ", message);
    }

    public void commandStarted(final Command command) {
        write(_PREFIX, "STARTED [", command, "]");
    }

    public void entityIsPoweredOff(final Command command) {
        write(_PREFIX, "OFF ", command.entity().name);
    }

    public void error(final String message) {
        write(_PREFIX, "ERROR ", message);
    }

    public void notice(final String message) {
        write(_PREFIX, "NOTICE ", message);
    }

    public void payloadNotice(final Entity entity, final Entity payload) {
        String payloadString = payload == null ? "None" : payload.name;
        write(_PREFIX, "PAYLOAD ", entity.name, ' ', payloadString);
    }

    public void showResult(final Command command) {
        Optional<String> errorMessage = command.errorMessage();
        if (errorMessage.isEmpty()) {
            write(_PREFIX, "OK " + command.uid + " [" + command.string + ']');
        }
        else {
            write(_PREFIX, "ERROR " + command.errorMessage().get());
        }
    }

    public  void write(final Object ... values) {
        synchronized (_CLIENT_MUTEX) {
            for (Object value : values) {
                _pw.print("" + value);
            }
            _pw.println();
            _pw.flush();
        }
    }

    public  void writeLines(final List<String> lines) {
        synchronized (_CLIENT_MUTEX) {
            for (String line : lines) {
                _pw.println("" + line);
            }
            _pw.flush();
        }
    }

}