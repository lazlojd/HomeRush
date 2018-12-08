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
    boolean launched;
    int justRotated = 10;

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
        Point pos = spaceShip.getPosition();
        // Set pivot point to be center of spaceship
        spaceShip.setPivotPoint(new Point(30, 26));
        /*NOTE: position of planet will not govern what spaceship is attracted to. The
        * CENTER of the planet does*/
        planetOne.setPosition(new Point(600, 400));
        // We want spaceship to gravitate toward center of planet, not upper left corner
        planetOne.setCenter(new Point(650, 450));
        planetOne.setMass(10);
        // Center sun horizontally and place on left side of screen
        sun.setPosition(new Point(200, 400));
        sun.setMass(11);
        sun.setCenter(new Point(250, 450));

        levelOne.addChild(spaceShip);
        levelOne.addChild(planetOne);
        //levelOne.addChild(sun);

        launched = false;
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

        if (!launched) {

            if (justRotated == 0) {
                /* left to move Spaceship position left */
                if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                    spaceShip.setPosition(new Point(spaceShip.getPosition().x + 10, spaceShip.getPosition().y));
                    justRotated = 2;
                }
                /* left to move Spaceship position left */
                if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                    spaceShip.setPosition(new Point(spaceShip.getPosition().x - 10, spaceShip.getPosition().y));
                    justRotated = 2;
                }
                /* Q to rotate Spaceship right */
                if (pressedKeys.contains(KeyEvent.VK_W)) {
                    spaceShip.setRotation(spaceShip.getRotation() + 45.0f);
                    justRotated = 10;
                }
                /* W to rotate Spaceship right */
                if (pressedKeys.contains(KeyEvent.VK_Q)) {
                    spaceShip.setRotation(spaceShip.getRotation() - 45.0f);
                    justRotated = 10;
                }
            } else {
                // wait 10 frames before accepting another user input
                justRotated -= 1;
            }
            // space to launch
            if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
                spaceShip.launch();
                launched = true;
            }
            System.out.println(spaceShip.getRotation());
        } else {
            // This applies gravitational affects from planet one to spaceship one
            spaceShip.updatePositionWithGravity(planetOne);
            //spaceShip.updatePositionWithGravity(sun);
            //spaceShip.updatePosition();
        }






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
