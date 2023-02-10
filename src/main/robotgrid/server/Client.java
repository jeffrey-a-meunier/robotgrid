package robotgrid.server;

import java.io.PrintWriter;

public class Client {

    // Static inner classes ===================================================
    // Static variables =======================================================

    public static ClientChannel COMMAND_REPLY = new ClientChannel(new PrintWriter(System.out));
    public static ClientChannel INFO = new ClientChannel(new PrintWriter(System.out));

    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setCommandReplyChannel(final PrintWriter pw) {
        if (pw == null) {
            COMMAND_REPLY = new ClientChannel(new PrintWriter(System.out));
        }
        else {
            COMMAND_REPLY = new ClientChannel(pw);
        }
    }

    public static void setInfoChannel(final PrintWriter pw) {
        if (pw == null) {
            INFO = new ClientChannel(new PrintWriter(System.out));
        }
        else {
            INFO = new ClientChannel(pw);
        }
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
