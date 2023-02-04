package robotgrid.entity.active.controller;

import robotgrid.entity.active.ActiveEntity;
import robotgrid.server.Server;
import robotgrid.utils.Result;

public class Controller2 implements Runnable {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    public final ActiveEntity entity;
    public final String name;

    protected SynQ<Command2> _commandQ = new SynQ<>();
    protected boolean _isOn = false;

    protected Thread _thread;
    
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Controller2(final ActiveEntity entity) {
        this.entity = entity;
        this.name = entity.name;
    }

    // Instance methods =======================================================

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
        while (!Thread.currentThread().isInterrupted()) {
            if (_commandQ.isEmpty()) {
                Server.THE_SERVER.programComplete(this);
            }
            Command2 command = _commandQ.deq();
            Result<Void, String> result = command.execute(entity);
            Server.THE_SERVER.commandComplete(this, command, result);
        }
        _isOn = false;
    }

    public boolean sendCommand(final Command2 commandHandler) {
        if (!_isOn) {
            return false;
        }
        synchronized (_commandQ) {
            _commandQ.enq(commandHandler);
        }
        return true;
    }

}
