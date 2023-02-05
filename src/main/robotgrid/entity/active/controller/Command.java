package robotgrid.entity.active.controller;

import java.util.Arrays;

import robotgrid.server.Server;
import robotgrid.utils.Result;
import robotgrid.utils.UID;

public class Command {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected String string;
    public final UID uid = new UID();

    protected Controller _controller;
    protected CommandHandler _handler;
    protected String[] _arguments;
    protected Result<Void, String> _result;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Command(final String string) {
        this.string = string;
    }

    // Instance methods =======================================================

    public boolean validate() {
        String[] parts = string.split(" ");
        if (parts.length < 2) {
            _result = new Result.Failure<>("no command action specified");
            return false;
        }
        String controllerName = parts[0];
        _controller = Controller.lookup(controllerName);
        if (_controller == null) {
            _result = new Result.Failure<>("controller '" + controllerName + "' not found");
            return false;
        }
        String commandName = parts[1];
        _handler = _controller.locateCommandHandler(commandName);
        if (_handler == null) {
            _result = new Result.Failure<>("command '" + commandName + "' not found for controller '" + controllerName + "'");
            return false;
        }
        _arguments = Arrays.copyOfRange(parts, 2, parts.length);
        return true;
    }

    public void sendToController() {
        _controller.sendCommand(this);
    }

    public void execute() {
        _handler.execute(this);
        Server.THE_SERVER.reportCommandResult(this);
    }

    public String[] arguments() {
        return _arguments;
    }

    public Controller controller() {
        return _controller;
    }

    public CommandHandler handler() {
        return _handler;
    }

    public Result<Void, String> result() {
        return _result;
    }

    public void setResult(final Result<Void, String> result) {
        _result = result;
    }

    @Override
    public String toString() {
        return string;
    }

}
