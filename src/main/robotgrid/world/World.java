package robotgrid.world;

import java.util.HashMap;
import java.util.Properties;

import processing.core.PApplet;
import processing.event.MouseEvent;
import robotgrid.scene.Cell;
import robotgrid.scene.Grid;
import robotgrid.scene.Scene;
import robotgrid.utils.Logger;
import robotgrid.utils.Result;
import robotgrid.server.Server;

public class World extends PApplet {

    // Static inner classes ===================================================
    // Static variables =======================================================

    public static final String CONFIG_FILE = "config.props";
    public static final String STARTUP_SCRIPT_FILE = "startup.txt";
    public static World THE_WORLD;

    public static float SIMULATION_SPEED = 1.0f;
    // public static final Scanner KEYBOARD = new Scanner(System.in);  // for debugging

    private static Logger _logger = new Logger(World.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected String _name;
    protected HashMap<String, Scene> _scenes = new HashMap<>();
    protected Scene _currentScene = null;
    protected int _worldWidth;
    protected int _worldHeight;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public World() {
        THE_WORLD = this;
        Result<Properties, String> result = ConfigFile.read(CONFIG_FILE);
        Properties properties;
        if (result.isSuccess) {
            properties = result.successValue();
            Grid grid = _createGridFromProperties(properties);
            Scene scene1 = new Scene(this);
            scene1.setGrid(grid);
            String gridName = grid.name();
            addScene(grid.name(), scene1);
            setCurrentScene(gridName);
        }
        // WorldSetup.setup(this);
        CommandSetup.setup();
        Server.setup();
        ScriptFile.run(STARTUP_SCRIPT_FILE);
    }

    // Instance methods =======================================================

    public void addScene(final String sceneName, final Scene scene) {
        _scenes.put(sceneName, scene);
    }
    
    @Override
    public void draw() {
        if (_currentScene != null) {
            _currentScene.draw(this);
        }
    }

    public void setCurrentScene(final String sceneName) {
        Scene scene = _scenes.get(sceneName);
        if (scene == null) {
            _logger.fatal("Scene not found: '", sceneName, "'");
        }
        else {
            _currentScene = scene;
        }
    }

    public Scene currentScene() {
        return _currentScene;
    }

    public void settings() {
        size(_worldWidth, _worldHeight);
    }

    public void setup() {
        background(0, 0, 0);
        stroke(255);
        strokeWeight(10);
        surface.setTitle(_name);
    }

    protected Grid _createGridFromProperties(final Properties properties) {
        _name = properties.getProperty("name", "World");
        int nRows = Integer.parseInt(properties.getProperty("nRows", "9"));
        int nCols = Integer.parseInt(properties.getProperty("nCols", "9"));
        int cellSize = Integer.parseInt(properties.getProperty("cellSize", "50"));
        _logger.info("Got config properties nRows=", nRows, ", nCols=", nCols, ", cellSize=", cellSize);
        _worldWidth = cellSize * nCols;
        _worldHeight = cellSize * nRows;
        Cell.setSize(cellSize);
        Grid grid = new Grid(nRows, nCols, cellSize, cellSize);
        return grid;
    }

    // Keyboard and mouse events ----------------------------------------------

    // TODO handle these?

    @Override
    public void keyPressed() {
    }

    @Override
    public void keyReleased() {
    }

    @Override
    public void keyTyped() {
    }

    @Override
    public void mouseClicked(final MouseEvent mev) {
    }

    @Override
    public void mouseDragged() {
    }
    
    @Override
    public void mouseMoved() {
    }

    @Override
    public void mousePressed(final MouseEvent mev) {
    }

    @Override
    public void mouseReleased() {
    }

    @Override
    public void mouseWheel(final MouseEvent mev) {
    }

}