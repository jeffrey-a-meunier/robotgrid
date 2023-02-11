package robotgrid.world.commands;

import robotgrid.entity.Command;
import robotgrid.entity.CommandHandler;
import robotgrid.entity.group.Group;

public class CreateGroup extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CreateGroup() {
        setImmeidate(true);
    }

    // Instance methods =======================================================

    @Override
    public void execute(Command command) {
        String[] args = command.arguments();
        try {
            String name = getStringArg("name", args, 0);
            if (name == null) {
                command.setErrorMessage("Group name required");
                return;
            }
            new Group(name);
        }
        catch (final ArgumentException exn) {
            command.setErrorMessage("Argument exception: " + exn);
        }
    }

}
