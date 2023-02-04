package robotgrid.server.commands.controller;

import robotgrid.entity.active.controller.ControllerGroup;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.server.CommandHandlerRegistry;
import robotgrid.utils.Result;
import robotgrid.world.World;

/**
 * Command:
 * new MobileRobot <x> <y> <heading> <name>
 */
public class CreateControllerGroup extends CommandHandler {

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
    public Result<Void, String> handleCommand(final Command command) {
        String name = getArg(command, 0, null);
        if (name == null) {
            name = ControllerGroup.class.getSimpleName() + (_NEXT_ID++);
        }
        ControllerGroup group = new ControllerGroup(name);
        _registerCommands(group);
        return new Result.Success<Void, String>();
    }

    protected void _registerCommands(final ControllerGroup group) {
        CommandHandlerRegistry registry = CommandHandlerRegistry.THE_REGISTRY;
        String name = group.name();
        registry.register(new ControllerGroupAdd(group, name, "add"));
    }
}
