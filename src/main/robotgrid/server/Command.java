package robotgrid.server;

import java.util.Arrays;

import robotgrid.utils.UID;

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

    protected UID _uid = new UID();
    protected String[] _parts;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Command(final String string) {
        _parts = string.split(" ");
    }

    // Instance methods =======================================================

    public CommandHandler getHandler() {
        return CommandHandlerRegistry.THE_REGISTRY.get(_parts);
    }

    @Deprecated
    final CommandResult execute() {
        CommandHandler handler = CommandHandlerRegistry.THE_REGISTRY.get(_parts);
        // handleCommand does not return until the command is complete
        CommandResult res = handler.handleCommand(this);
        _complete();
        return res;
    }

    public String[] parts() {
        return _parts;
    }

    @Override
    public String toString() {
        return "Command{" + Arrays.toString(_parts) + ", " + _uid + '}';
    }

    public UID uid() {
        return _uid;
    }

    /**
     * Under no circumstances should a Command instance be used after this
     * method is called.
     */
    @Deprecated
    protected void _complete() {
        String message = "Command complete " + this;
        Server.THE_SERVER.sendInfo(message);
        _clearMemberVariables();
    }

    /**
     * Assist the memory manager.
     */
    @Deprecated
    protected void _clearMemberVariables() {
        _uid = null;
        _parts = null;
    }

}
