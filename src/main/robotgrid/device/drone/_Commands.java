package robotgrid.device.drone;

import robotgrid.device.Command;
import robotgrid.device.CommandHandler;
import robotgrid.scene.Direction;

class _Commands {

    // Static inner classes ===================================================

    protected static class _Drop extends CommandHandler {
        @Override
        public void execute(final Command command) {
            Drone drone = (Drone)command.device();
            drone.drop();
        }
    }

    protected static class _PickUp extends CommandHandler {
        @Override
        public void execute(final Command command) {
            Drone drone = (Drone)command.device();
            drone.pickUp();
        }
    }

    protected static class _Move extends CommandHandler {
        protected Direction _direction;
        public _Move(final Direction heading) {
            _direction = heading;
        }
        @Override
        public void execute(final Command command) {
            Drone drone = (Drone)command.device();
            drone.move(_direction);
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final Drone drone) {
        drone.addCommandHandler("Drop", new _Drop());
        drone.addCommandHandler("PickUp", new _PickUp());
        drone.addCommandHandler("MoveNorth", new _Move(Direction.North));
        drone.addCommandHandler("MoveEast", new _Move(Direction.East));
        drone.addCommandHandler("MoveSouth", new _Move(Direction.South));
        drone.addCommandHandler("MoveWest", new _Move(Direction.West));

    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================


}
