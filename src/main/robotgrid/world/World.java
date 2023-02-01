package robotgrid.world;

import java.util.HashMap;
import java.util.Scanner;

import processing.core.PApplet;
import processing.event.MouseEvent;
import robotgrid.scene.Scene;
import robotgrid.server.Server;
import robotgrid.server.commands.WorldCreate;
import robotgrid.server.commands.WorldCreateGrid;
import robotgrid.utils.Logger;
import robotgrid.server.CommandHandlerReistry;

public class World extends PApplet {

    // Static inner classes ===================================================
    // Static variables =======================================================

    public static World THE_WORLD;

    public static float SIMULATION_SPEED = 1.0f;
    public static final Scanner KEYBOARD = new Scanner(System.in);  // for debugging

    private static Logger _logger = new Logger(World.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected HashMap<String, Scene> _scenes = new HashMap<>();
    protected Scene _currentScene = null;

    protected int _dragOffsetX, _dragOffsetY;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public World() {
        THE_WORLD = this;
        WorldSetup.setup(this);
        CommandHandlerReistry.THE_REGISTRY.register(new WorldCreate(this), "World", "Create");
        CommandHandlerReistry.THE_REGISTRY.register(new WorldCreateGrid(this), "World", "Create", "Grid");
        _logger.info("Server created: ", Server.THE_SERVER);
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

    public void setCurrentScene(final String sceneName) {
        Scene scene = _scenes.get(sceneName);
        if (scene == null) {
            _logger.fatal("Scene not found: '", sceneName, "'");
        }
        else {
            _currentScene = scene;
        }
    }

    public void settings() {
        size(WorldSetup.getWorldWidth(), WorldSetup.getWorldHeight());
    }

    public void setup() {
        background(0, 0, 0);
        stroke(255);
        strokeWeight(10);
    }

}