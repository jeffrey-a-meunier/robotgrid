package robotgrid.entity;

import java.util.List;
import java.util.Optional;

import robotgrid.server.Client;
import robotgrid.utils.SynQ;

public abstract class PoweredEntity extends Entity implements Runnable {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected boolean _isOn = false;
    protected SynQ<Command> _commandQ = new SynQ<>();
    protected Thread _thread;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public PoweredEntity(final String name, final int height) {
        super(name, height);
        PoweredEntity_Commands.setup(this);
    }

    // Instance methods =======================================================

    public void info(final List<String> infoStrings) {
        super.info(infoStrings);
        infoStrings.add("Power=" + (_isOn ? "On" : "Off"));
    }

    @Override
    public boolean isOn() {
        return _isOn;
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

    @Override
    public void run() {
        while (!_thread.isInterrupted()) {
            Command command = _commandQ.deq();
            if (command == null) {
                // a null command means that the thread has been interrupted
                break;
            }
            command.execute();
            Optional<String> errorMessage = command.errorMessage();
            if (errorMessage.isEmpty()) {
                Client.INFO.commandSuccess(command);
            }
            else {
                Client.INFO.commandError(command, errorMessage.get());
            }
        }
    }

    @Override
    public void sendCommand(final Command command) {
        _commandQ.enq(command);
    }

}
