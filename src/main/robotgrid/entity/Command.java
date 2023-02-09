package robotgrid.entity;

import java.util.Arrays;
import java.util.Optional;

import robotgrid.utils.UID;

public class Command {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    public final String string;
    public final IOContext ioContext;
    public final UID uid;

    protected Entity _entity;
    protected CommandHandler _handler;
    protected String[] _arguments;

    protected Optional<String> _errorMessage = Optional.empty();

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Command(final String string, final IOContext ioContext) {
        this.string = string;
        this.ioContext = ioContext;
        this.uid = new UID();
    }

    // Instance methods =======================================================

    public boolean validate() {
        String[] parts = string.split(" ");
        if (parts.length < 2) {
            ioContext.commandError("No command action specified");
            return false;
        }
        String entityName = parts[0];
        _entity = Entity.lookup(entityName);
        if (_entity == null) {
            ioContext.commandError("Entity '" + entityName + "' not found");
            return false;
        }
        String commandName = parts[1];
        _handler = _entity.locateCommandHandler(commandName);
        if (_handler == null) {
            ioContext.commandError("Command '" + commandName + "' not found for entity '" + entityName + "'");
            return false;
        }
        _arguments = Arrays.copyOfRange(parts, 2, parts.length);
        return true;
    }

    public void execute() {
        System.out.println("Command.execute handler = " + _handler);
        _handler.execute(this);
    }

    public String[] arguments() {
        return _arguments;
    }

    public Entity entity() {
        return _entity;
    }

    public CommandHandler handler() {
        return _handler;
    }

    public Optional<String> errorMessage() {
        return _errorMessage;
    }

    public void setErrorMessage(final String message) {
        _errorMessage = Optional.of(message);
    }

    @Override
    public String toString() {
        return string;
    }

}
