package robotgrid.entity2;

import java.util.List;
import java.util.Optional;

import robotgrid.server.Server;

public class IOContext {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static final String _PREFIX = ":";

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected final Server _server;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public IOContext(final Server server) {
        _server = server;
    }

    // Instance methods =======================================================

    // TODO these methods are kid of a mess, must be cleaned up

    // command channel methods ------------------------------------------------

    public void commandStarted(final Command command) {
        _commandReply(_PREFIX + "STARTED " + command.uid + " [" + command.string + ']');
    }

    public void commandSuccessOrfailure(final Command command) {
        Optional<String> errorMessage = command.errorMessage();
        if (errorMessage.isEmpty()) {
            _commandSuccess(command);
        }
        else {
            _commandError(command);
        }
    }

    protected void _commandSuccess(final Command command) {
        _commandReply(_PREFIX + "OK " + command.uid + " [" + command.string + ']');
    }

    protected void _commandError(final Command command) {
        _commandReply(_PREFIX + "ERROR " + command.errorMessage().get());
    }

    public void commandError(final String reply) {
        _commandReply(_PREFIX + "ERROR " + reply);
    }

    public void commandNotice(final String string) {
        _info(_PREFIX + "NOTICE " + string);
    }

    public void commandStrings(final String ... strings) {
        for (String string : strings) {
            _commandReply(string);
        }
    }

    public void commandStrings(final List<String> strings) {
        for (String string : strings) {
            _commandReply(string);
        }
    }

    // info channel methods ---------------------------------------------------

    public void infoStrings(final String ... strings) {
        for (String string : strings) {
            _info(string);
        }
    }

    public void infoStrings(final List<String> strings) {
        for (String string : strings) {
            _info(string);
        }
    }

    public void infoSuccess(final Command command) {
        _info(_PREFIX + "OK " + command.uid + " [" + command.string + ']');
    }

    public void infoNotify(final String string) {
        _info(_PREFIX + "NOTICE " + string);
    }

    public void commandNotify(final String string) {
        _commandReply(_PREFIX + "NOTICE " + string);
    }

    public void infoError(final Command command, final String reason) {
        _info(_PREFIX + "ERROR " + command.uid + " [" + command.string + "] " + reason);
    }

    // protected methods ------------------------------------------------------

    protected void _commandReply(final String reply) {
        _server.sendCommandReply(reply);
    }

    protected void _info(final String message) {
        System.out.println("IOContext._info sending message [" + message + "]");
        _server.sendInfoMessage(message);
    }

}
