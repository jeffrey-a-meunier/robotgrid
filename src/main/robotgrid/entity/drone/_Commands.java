package robotgrid.entity.drone;

import robotgrid.entity.Command;
import robotgrid.entity.CommandHandler;
import robotgrid.scene.Direction;

class _Commands {

    // Static inner classes ===================================================

    protected static class _Drop extends CommandHandler {
        @Override
        public void execute(final Command command) {
            Drone drone = (Drone)command.entity();
            drone.drop();
        }
    }

    protected static class _PickUp extends CommandHandler {
        @Override
        public void execute(final Command command) {
            Drone drone = (Drone)command.entity();
            drone.pickUp();
        }
    }

    protected static class _Move extends CommandHandler {
        @Override
        public void execute(final Command command) {
            Drone drone = (Drone)command.entity();
            Direction direction = getDirectionArg_required(command, "heading", 0);
            drone.move(direction);
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final Drone drone) {
        drone.addCommandHandler("Drop", new _Drop());
        drone.addCommandHandler("PickUp", new _PickUp());
        drone.addCommandHandler("Move", new _Move());

    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================


}
