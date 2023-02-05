package robotgrid.server.commands.controller;

import robotgrid.entity.active.controller.ControllerGroup;
import robotgrid.server.Command_deprecated;
import robotgrid.server.CommandHandler_deprecated;
import robotgrid.server.CommandHandlerRegistry_deprecated;
import robotgrid.utils.Result;
import robotgrid.world.World;

public class CreateControllerGroup extends CommandHandler_deprecated {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static int _NEXT_ID = 1;

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected World _world;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CreateControllerGroup(String ... commandParts) {
        super(commandParts);
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(final Command_deprecated command) {
        String name = getArg(command, 0, null);
        if (name == null) {
            name = ControllerGroup.class.getSimpleName() + (_NEXT_ID++);
        }
        ControllerGroup group = new ControllerGroup(name);
        _registerCommands(group);
        return new Result.Success<Void, String>();
    }

    protected void _registerCommands(final ControllerGroup group) {
        CommandHandlerRegistry_deprecated registry = CommandHandlerRegistry_deprecated.THE_REGISTRY;
        String name = group.name;
        registry.register(new AddController(group, name, "add"));
        registry.register(new PowerOn(group, name, "power", "on"));
        registry.register(new PowerOff(group, name, "power", "off"));
    }
}
