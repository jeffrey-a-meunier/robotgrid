package robotgrid.entity.active.controller;

import java.util.HashSet;
import java.util.Set;

import robotgrid.entity.active.ActiveEntity;

public class ControllerGroup extends Controller {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Set<Controller> _controllers = new HashSet<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ControllerGroup(String name) {
        super(name);
    }

    public ControllerGroup add(final Controller controller) {
        _controllers.add(controller);
        return this;
    }

    public ControllerGroup add(final ActiveEntity entity) {
        return add(entity.controller());
    }

    // Instance methods =======================================================

    @Override
    public void powerOn() {
        super.powerOn();
        for (Controller controller : _controllers) {
            controller.powerOn();
        }
    }

    @Override
    public void powerOff() {
        super.powerOff();
        for (Controller controller : _controllers) {
            controller.powerOff();
        }
    }

    @Override
    public synchronized void sendMessage(final Message message) {
        for (Controller controller : _controllers) {
            controller.sendMessage(message);
        }
    }

}
