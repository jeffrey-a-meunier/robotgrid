package robotgrid.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import robotgrid.entity.active.controller.Command;
import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;
import robotgrid.entity.active.robot.MobileRobot;
import robotgrid.scene.Direction;
import robotgrid.scene.Grid;
import robotgrid.server.Server;
import robotgrid.utils.Logger;
import robotgrid.utils.Result;

public class WorldController extends Controller {

    // Static inner classes ===================================================

    public static class Exit extends CommandHandler {
        public Exit() { setImmeidate(true); }
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
            _logger.info("World exiting");
            World.THE_WORLD.exit();
            return new Result.Success<Void, String>();
        }
    }

    public static class ListCommands extends CommandHandler {
        public ListCommands() { setImmeidate(true); }
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
            Collection<Controller> allControllers = Controller.controllers();
            List<String> commandStrings = new ArrayList<>();
            for (Controller controller1 : allControllers) {
                String controllerName = controller1.name;
                Set<String> controllerCommandNames = controller1.commandNames();
                for (String commandName : controllerCommandNames) {
                    commandStrings.add(controllerName + ' ' + commandName);
                }
            }
            Collections.sort(commandStrings);
            Server.THE_SERVER.sendCommandReply("" + commandStrings.size());
            for (String commandString : commandStrings) {
                Server.THE_SERVER.sendCommandReply(commandString);
            }
            return new Result.Success<Void, String>();
        }
    }

    public static class ListControllers extends CommandHandler {
        public ListControllers() { setImmeidate(true); }
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
            List<String> allNames = new ArrayList<>(Controller.names());
            Collections.sort(allNames);
            Server.THE_SERVER.sendCommandReply("" + allNames.size());
            for (String name : allNames) {
                Server.THE_SERVER.sendCommandReply(name);
            }
            return new Result.Success<Void, String>();
        }
    }

    public static class NewMobileRobot extends CommandHandler {
        public NewMobileRobot() { setImmeidate(true); }
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
            _logger.debug("WorldController.NewMobileRobot got here, args = ", Arrays.toString(arguments));
            String name = getStringArg(arguments, 0);
            if (name == null) {
                _logger.debug("WorldController.NewMobileRobot name not supplied");
                return new Result.Failure<Void,String>("Name not supplied");
            }
            int row = getIntArg(arguments, 1, 0);
            int col = getIntArg(arguments, 2, 0);
            Direction heading = getDirectionArg(arguments, 3, Direction.North);
            MobileRobot robot = (MobileRobot)new MobileRobot(name)
                .setDirection(heading)
                ;
            Grid grid = World.THE_WORLD.currentScene().grid();
            if (grid.addEntity(row, col, robot)) {
                return new Result.Success<Void, String>();
            }
            return new Result.Failure<Void,String>("Unable to add MobileRobot to grid at " + row + ", " + col);
        }
    }

    // Static variables =======================================================

    private static Logger _logger = new Logger(WorldController.class, Logger.Level.All);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public WorldController() {
        super("World");
        addCommandHandler("Exit", new Exit());
        addCommandHandler("ListCommands", new ListCommands());
        addCommandHandler("ListControllers", new ListControllers());
        addCommandHandler("NewMobileRobot", new NewMobileRobot());
        powerOn();
    }

    // Instance methods =======================================================

    @Override
    public void powerOff() {
    }

    @Override
    public boolean sendCommand(final Command command) {
        _logger.debug("WorldController.sendCommand got command ", command);
        command.execute();
        return true;
    }

}
