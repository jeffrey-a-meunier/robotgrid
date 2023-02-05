package robotgrid.entity.active.controller;

import robotgrid.utils.Result;
import robotgrid.utils.UID;

public class Command {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    public final String[] commandParts;
    public final UID uid = new UID();

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Command(final String[] commandParts) {
        this.commandParts = commandParts;
    }

    // Instance methods =======================================================

    public Result<Void, String> execute(final Controller controller) {
        // TODO
        return new Result.Failure<>("not implemented");
    }

}
