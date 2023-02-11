package robotgrid.entity;

import java.util.Arrays;
import java.util.Optional;

import robotgrid.entity.abstractEntity.AbstractEntity;
import robotgrid.server.Client;
import robotgrid.utils.UID;

public class Command {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    public final String string;
    public final UID uid;

    protected String _entityName;
    protected AbstractEntity _entity;
    protected String _commandName;
    protected CommandHandler _handler;
    protected String[] _arguments;
    protected Optional<String> _errorMessage = Optional.empty();

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Command(final String string) {
        this.string = string;
        this.uid = new UID();
    }

    public Command copyWithEntityName(final String name) {
        String commandString = name + " " + _commandName + " " + String.join(" ", _arguments);
        return new Command(commandString);
    }

    // Instance methods =======================================================

    public void performLifecycle() {
        if (validate()) {
            if (_handler.isImmediate()) {
                _handler.execute(this);
                Client.COMMAND_REPLY.showResult(this);
            }
            else {
                if (_entity.isOn()) {
                    _entity.sendCommand(this);
                    Client.COMMAND_REPLY.commandStarted(this);
                }
                else {
                    Client.COMMAND_REPLY.entityIsPoweredOff(this);
                }
            }
        }
    }

    public boolean validate() {
        String[] parts = string.split(" ");
        if (parts.length < 2) {
            Client.COMMAND_REPLY.error("No command action specified");
            return false;
        }
        _entityName = parts[0];
        _entity = AbstractEntity.lookup(_entityName);
        if (_entity == null) {
            Client.COMMAND_REPLY.error("Entity '" + _entityName + "' not found");
            return false;
        }
        _commandName = parts[1];
        _handler = _entity.locateCommandHandler(_commandName);
        if (_handler == null) {
            Client.COMMAND_REPLY.error("Command '" + _commandName + "' not found for entity '" + _entityName + "'");
            return false;
        }
        _arguments = Arrays.copyOfRange(parts, 2, parts.length);
        return true;
    }

    public void execute() {
        _handler.execute(this);
    }

    public String[] arguments() {
        return _arguments;
    }

    public String commandName() {
        return _commandName;
    }

    public AbstractEntity entity() {
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
