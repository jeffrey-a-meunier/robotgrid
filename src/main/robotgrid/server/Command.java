package robotgrid.server;

import java.util.ArrayList;
import java.util.List;

/**
 * Every command must be a string of the form
 * "<receiver> <verb> [<arguments>]"
 */
public class Command {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected CommandUID _uid = new CommandUID();
    protected String _receiver;
    protected String _verb;
    protected List<String> _arguments = new ArrayList<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Command(final String string) {
        _parse(string);
    }

    // Instance methods =======================================================

    public String receiver() {
        return _receiver;
    }

    public String verb() {
        return _verb;
    }

    public List<String> arguments() {
        return _arguments;
    }

    final void execute() {
        CommandHandler handler = CommandHandlerReistry.THE_REGISTRY.get(_receiver, _verb);
        // handleCommand should not return until the command is complete
        handler.handleCommand(this);
        _complete();
    }

    /**
     * Under no circumstances should a Command instance be used after this
     * method is called.
     */
    protected void _complete() {
        String message = "Command complete " + _uid;
        Server.THE_SERVER.sendInfo(message);
        _clearMemberVariables();
    }

    protected boolean _parse(final String commandString) {
        String[] parts = commandString.split(" ");
        if (parts.length < 2) {
            return false;
        }
        _receiver = parts[0];
        _verb = parts[1];
        for (int n=2; n<parts.length; n++) {
            _arguments.add(parts[n]);
        }
        return true;
    }

    /**
     * Assist the memory manager.
     */
    protected void _clearMemberVariables() {
        _uid = null;
        _receiver = null;
        _verb = null;
        _arguments = null;
    }

}
