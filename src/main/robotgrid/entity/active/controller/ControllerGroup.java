package robotgrid.entity.active.controller;

import java.util.HashSet;
import java.util.Set;

import robotgrid.entity.active.ActiveEntity;
import robotgrid.server.Server;
import robotgrid.utils.Result;

@Deprecated
public class ControllerGroup extends Controller {

    // Static inner classes ===================================================

    public class Add extends CommandHandler {
        public Add() { setImmeidate(true); }
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] args) {
            for (String controllerName : args) {
                Controller controller1 = Controller.lookup(controllerName);
                if (controller1 == null) {
                    // Server.THE_SERVER.controllerNotFound(controllerName);
                }
                else {
                    add(controller1);
                }
            }
            return new Result.Success<>();
        }
    
    }

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
        addCommandHandler("Add", new Add());
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

}
