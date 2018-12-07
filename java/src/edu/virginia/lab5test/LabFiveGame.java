package edu.virginia.lab5test;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.SoundManager;
import edu.virginia.engine.display.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LabFiveGame extends Game {

    Sprite levelOne;
    Sprite planetOne;
    Sprite sun;
    Sprite spaceShip;

    private void initSprites() {
        levelOne = new Sprite("backgroundLevelOne", "spaceBackground.jpg");
        planetOne = new Sprite("planetOne", "planet1.png");
        sun = new Sprite("sun", "sun.png");
        spaceShip = new Sprite("spaceship", "spaceship.png");

    }

    private void initLevelOne() {
        // Set spaceship at bottom left corner
        spaceShip.setPosition(new Point(150,800));
        spaceShip.setMass(1);
        // Center planet vertically and place on right side of screen
        planetOne.setPosition(new Point(100, 400));
        // We want spaceship to gravitate toward center of planet, not upper left corner
        planetOne.setCenter(new Point(150, 350));
        planetOne.setMass(10);
        // Center sun horizontally and place on left side of screen
        sun.setPosition(new Point(200, 400));

        levelOne.addChild(spaceShip);
        levelOne.addChild(planetOne);
        //levelOne.addChild(sun);
    }

    public LabFiveGame() {
        super("Lab Five Test Game", 900, 900);
        //System.out.println("mush0 pos");
        initSprites();
        initLevelOne();

    }



    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     */
    @Override
    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);

        // This applies gravitational affects from planet one to spaceship one
        spaceShip.updatePosition(planetOne);





    }



    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure sun gets drawn to
     * the screen, we need to make sure to override this method and call sun's draw method.
     */
    @Override
    public void draw(Graphics g) {
        super.draw(g);

        if (levelOne != null) levelOne.draw(g);

    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     */
    public static void main(String[] args) {
        LabFiveGame game = new LabFiveGame();
        game.start();
    }
}
