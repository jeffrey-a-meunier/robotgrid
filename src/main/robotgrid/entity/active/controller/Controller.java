package robotgrid.entity.active.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import robotgrid.entity.active.ActiveEntity;
import robotgrid.entity.active.controller.commands.Info;
import robotgrid.entity.active.controller.commands.ListCommands;
import robotgrid.entity.active.controller.commands.Power;
import robotgrid.entity.active.controller.commands.PowerOff;
import robotgrid.entity.active.controller.commands.PowerOn;
import robotgrid.server.Server;
import robotgrid.utils.SynQ;

@Deprecated
public class Controller implements Runnable {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static Map<String, Controller> _ALL_CONTROLLERS = new HashMap<>();

    // Static initializer =====================================================
    // Static methods =========================================================

    public static Collection<Controller> controllers() {
        return _ALL_CONTROLLERS.values();
    }

    public static Set<String> names() {
        return _ALL_CONTROLLERS.keySet();
    }

    public static Controller lookup(final String name) {
        return _ALL_CONTROLLERS.get(name);
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================

    public final String name;

    protected ActiveEntity _entity;
    protected SynQ<Command> _commandQ = new SynQ<>();
    protected boolean _isOn = false;

    protected Map<String, CommandHandler> _handlers = new HashMap<>();
    protected Thread _thread;
    
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Controller(final String name) {
        this(name, true);
    }

    public Controller(final String name, boolean addPowerCommands) {
        this.name = name;
        _ALL_CONTROLLERS.put(name, this);
        addCommandHandler("Info", new Info());
        addCommandHandler("ListCommands", new ListCommands(_handlers));
        if (addPowerCommands) {
            addCommandHandler("Power", new Power());
            addCommandHandler("PowerOn", new PowerOn());
            addCommandHandler("PowerOff", new PowerOff());
        }
        else {
            powerOn();
        }
    }

    public Controller setEntity(final ActiveEntity entity) {
        assert entity != null;
        _entity = entity;
        return this;
    }

    public Controller addCommandHandler(final String name, final CommandHandler handler) {
        _handlers.put(name, handler);
        return this;
    }

    // Instance methods =======================================================

    public Set<String> commandNames() {
        return _handlers.keySet();
    }

    public ActiveEntity entity() {
        return _entity;
    }

    public boolean isOn() {
        return _isOn;
    }

    public CommandHandler locateCommandHandler(final String commandName) {
        return _handlers.get(commandName);
    }

    public void powerOn() {
        _thread = new Thread(this);
        _thread.start();
        _isOn = true;
    }

    public void powerOff() {
        _isOn = false;
        _thread.interrupt();
        _commandQ.clear();
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (_commandQ.isEmpty()) {
                // Server.THE_SERVER.programComplete(this);
            }
            Command command = _commandQ.deq();
            if (command == null) {
                break;
            }
            command.execute();
            //Server.THE_SERVER.commandComplete(this, command);
        }
        _isOn = false;
    }

    public boolean sendCommand(final Command command) {
        // if (command.handler().isImmediate()) {
        //     command.execute();
        //     return true;
        // }
        // if (!_isOn){ 
            return false;
        // }
        // synchronized (_commandQ) {
        //     _commandQ.enq(command);
        // }
        // return true;
    }

    public void terminate() {
        _thread.interrupt();
    }

}
