package robotgrid.entity.active.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import robotgrid.entity.active.ActiveEntity;
import robotgrid.server.Server;
import robotgrid.utils.Logger;
import robotgrid.utils.Result;

public class Controller implements Runnable {
    
    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static Map<String, Controller> _ALL_CONTROLLERS = new HashMap<>();

    private static Logger _logger = new Logger(Controller.class, Logger.Level.Info);

    // Static initializer =====================================================
    // Static methods =========================================================

    public static boolean deliverMessage(final String messageString) {
        String[] parts = messageString.split(" ");
        if (parts.length == 0) {
            _logger.error("deliverMessage() got an empty message");
            Server.THE_SERVER.sendInfo("Command string is empty");
            return false;
        }
        String controllerName = parts[0];
        Controller controller = _ALL_CONTROLLERS.get(controllerName);
        if (controller == null) {
            _logger.error("deliverMessage() could not find controller named '", controllerName, "'");
            Server.THE_SERVER.sendInfo("ERROR controller not found: " + controllerName);
            return false;
        }
        Message message = new Message(Arrays.copyOfRange(parts, 1, parts.length));
        controller.sendMessage(message);
        return true;
    }

    /**
     * This is a support method for the ArticulatedRobotController and the
     * MobileRobotController classes.
     */
    @Deprecated
    protected static Result<Void, String> _afterDelay(final int ms, Supplier<Result.Failure<Void, String>> action) {
        try {
            Thread.sleep(ms);
        }
        catch (final InterruptedException exn) {
            return new Result.Failure<Void, String>("Robot motion interrupted");
        }
        return action.get();
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected String _name;
    protected Map<String, ICommand> _commands = new HashMap<>();
    protected ActiveEntity _entity;
    protected MessageQ _msgq = new MessageQ();
    protected boolean _isOn = false;
    protected Thread _thread;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Controller(final String name) {
        _name = name;
        _ALL_CONTROLLERS.put(name, this);
        _logger.info("added new controller " + this);
    }

    public Controller installCommand(final String opcode, final ICommand command) {
        _commands.put(opcode, command);
        return this;
    }

    public Controller setEntity(final ActiveEntity entity) {
        _entity = entity;
        return this;
    }

    // Instance methods =======================================================

    public ActiveEntity entity() {
        return _entity;
    }

    public Result<Void, String> execute(final String[] parts) {
        String commandName = parts[0];
        ICommand command = _commands.get(commandName);
        if (command != null) {
            return execute(command, parts);
        }
        return new Result.Failure<>("Command '" + commandName + " not implemented in controller " + this);
    }

    public Result<Void, String> execute(final ICommand command, final String[] parts) {
        return command.execute(this, parts);
    }

    public boolean isOn() {
        return _isOn;
    }

    public String name() {
        return _name;
    }

    public void powerOn() {
        _thread = new Thread(this);
        _thread.start();
    }

    public void powerOff() {
        if (_thread != null) {
            _thread.interrupt();
        }
        _thread = null;
    }

    public void run() {
        _isOn = true;
        String commandCompleteString = _name + " command complete";
        while (!Thread.currentThread().isInterrupted()) {
            Message message = _msgq.deq();
            _handleCommand(message);
            Server.THE_SERVER.sendInfo(commandCompleteString);
        }
        Server.THE_SERVER.sendInfo(_name + " program complete");
        _isOn = false;
    }

    /**
     * The messageString is allowed to contain arguments separated by spaces.
     * "Command arg1 arg2"
     */
    public synchronized Controller sendCommands(final String ... commandStrings) {
        for (String commandString : commandStrings) {
            String[] parts = commandString.split(" ", 0);
            _msgq.enq(new Message(parts));
        }
        return this;
    }

    public synchronized void sendMessage(final Message message) {
        _msgq.enq(message);
    }

    public void terminate() {
        _thread.interrupt();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' + _name + '}';
    }

    protected void _handleCommand(final Message command) {
        String[] parts = command.parts();
        if (parts != null && parts.length > 0) {
            execute(parts);
        }
    }

}
