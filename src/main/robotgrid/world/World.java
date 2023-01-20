package robotgrid.world;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import processing.core.PApplet;
import processing.event.MouseEvent;
import robotgrid.entity.active.IUpdateSubscriber;
import robotgrid.logger.Logger;
import robotgrid.scene.Scene;

public class World extends PApplet {

    // Static inner classes ===================================================
    // Static variables =======================================================

    public static World THE_WORLD = null;

    protected Set<IUpdateSubscriber> _UPDATE_SUBSCRIBERS = new HashSet<>();

    private static Logger _logger = new Logger(World.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected HashMap<String, Scene> _scenes = new HashMap<>();
    protected Scene _currentScene = null;

    protected int _windowWidth = 1024;
    protected int _windowHeight = 768;

    protected int _lastTickTime;
    protected int _dragOffsetX, _dragOffsetY;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public World() {
        assert(THE_WORLD == null);
        THE_WORLD = this;
        WorldSetup.setup(this);
    }

    // Instance methods =======================================================

    public void addScene(final String sceneName, final Scene scene) {
        _scenes.put(sceneName, scene);
    }
    
    // This method gets called repeatedly by the Processing engine. We will
    // use this method call as a call to both the world update() method and the
    // world draw() method.
    public void draw() {
        int currentTime = millis();
        int elapsedTime = currentTime - _lastTickTime;
        update(elapsedTime);
        if (_currentScene != null) {
            _currentScene.draw(this);
        }
        _lastTickTime = currentTime;
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
        size(_windowWidth, _windowHeight);
    }

    public void setup() {
        background(0, 0, 0);
        stroke(255);
        strokeWeight(10);
        //frame.setResizable(true);
    }

    protected void update(final int elapsedTime) {
        for (IUpdateSubscriber subscriber : _UPDATE_SUBSCRIBERS) {
            subscriber.update(elapsedTime);
        }
    }

}