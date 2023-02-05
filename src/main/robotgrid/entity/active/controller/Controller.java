package robotgrid.entity.active.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import robotgrid.entity.active.ActiveEntity;
import robotgrid.server.Server;
import robotgrid.utils.Result;

public class Controller implements Runnable {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static Map<String, Controller> _ALL_CONTROLLERS = new HashMap<>();

    // Static initializer =====================================================
    // Static methods =========================================================

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

    protected Thread _thread;
    
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Controller(final String name) {
        this.name = name;
        _thread = new Thread(this);
        _thread.start();
        _ALL_CONTROLLERS.put(name, this);
    }

    public Controller setEntity(final ActiveEntity entity) {
        _entity = entity;
        return this;
    }

    // Instance methods =======================================================

    public ActiveEntity entity() {
        return _entity;
    }

    public boolean isOn() {
        return _isOn;
    }

    public void powerOn() {
        _isOn = true;
    }

    public void powerOff() {
        _isOn = false;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (_commandQ.isEmpty()) {
                Server.THE_SERVER.programComplete(this);
            }
            Command command = _commandQ.deq();
            Result<Void, String> result = command.execute(this);
            Server.THE_SERVER.commandComplete(this, command, result);
        }
        _isOn = false;
    }

    public boolean sendCommand(final CommandHandler command) {
        if (_isOn) {
            synchronized (_commandQ) {
                _commandQ.enq(command);
            }
            return true;
        }
        return false;
    }

    public void terminate() {
        _thread.interrupt();
    }

}
