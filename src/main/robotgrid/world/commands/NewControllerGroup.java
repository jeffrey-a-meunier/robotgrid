package robotgrid.world.commands;

import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;
import robotgrid.entity.active.controller.ControllerGroup;
import robotgrid.utils.Result;

public class NewControllerGroup extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public NewControllerGroup() {
        setImmeidate(true);
    }

    // Instance methods =======================================================

    @Override
    protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
        String name = getStringArg(arguments, 0);
        if (name == null) {
            return new Result.Failure<Void,String>("Name not supplied");
        }
        new ControllerGroup(name);
        return new Result.Success<Void, String>();
    }

}
