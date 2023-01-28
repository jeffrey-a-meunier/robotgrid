package robotgrid.entity.active.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import robotgrid.entity.active.ActiveEntity;
import robotgrid.network.Message;
import robotgrid.network.MessageQ;
import robotgrid.world.World;

public class Controller implements Runnable {
    
    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    /**
     * This is a support method for the ArticulatedRobotController and the
     * MobileRobotController classes.
     */
    protected static CommandResult _afterDelay(final int ms, Supplier<CommandResult> action) {
        try {
            Thread.sleep(ms);
        }
        catch (final InterruptedException exn) {
            return new CommandResult.Failure("Robot motion interrupted");
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
        World.ZMQ_BUS.subscribe(_name);
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

    public CommandResult execute(final String[] parts) {
        String opcode = parts[0];
        ICommand command = _commands.get(opcode);
        if (command != null) {
            return execute(command, parts);
        }
        return new CommandResult.NotImplemented(opcode, this);
    }

    public CommandResult execute(final ICommand command, final String[] parts) {
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
        while (!Thread.currentThread().isInterrupted()) {
            Message message = _msgq.deq();
            _handleCommand(message);
        }
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

    // public synchronized void sendMessage(final Message message) {
    //     _msgq.enq(message);
    // }

    protected void _handleCommand(final Message command) {
        String[] parts = command.parts();
        if (parts != null && parts.length > 0) {
            execute(parts);
        }
    }

}
