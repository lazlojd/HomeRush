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
    Sprite whiteTrajectoryDot;
    boolean launched;
    int justRotated = 10;
    int whiteDotTimer = 50;

    private void initSprites() {
        levelOne = new Sprite("backgroundLevelOne", "spaceBackground.jpg");
        planetOne = new Sprite("planetOne", "planet1.png");
        sun = new Sprite("sun", "sun.png");
        spaceShip = new Sprite("spaceship", "spaceship.png");
        whiteTrajectoryDot = new Sprite("whiteDot", "whiteDot.png");

    }

    private void initLevelOne() {
        // Set spaceship at bottom left corner
        spaceShip.setPosition(new Point(150,800));
        spaceShip.setMass(1);

        // Set pivot point to be center of spaceship
        spaceShip.setPivotPoint(new Point(30, 26));

        resetWhiteDot();
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
        levelOne.addChild(whiteTrajectoryDot);
        levelOne.addChild(planetOne);
        //levelOne.addChild(sun);

        launched = false;
    }

    private void resetWhiteDot() {
        whiteTrajectoryDot.setPosition(new Point(spaceShip.getPosition().x, spaceShip.getPosition().y));
        whiteTrajectoryDot.setMass(1);
        whiteTrajectoryDot.setPivotPoint(new Point(spaceShip.getPivotPoint().x, spaceShip.getPivotPoint().y));
        whiteTrajectoryDot.resetVelocity();
        whiteDotTimer = 50;
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
                    whiteTrajectoryDot.setPosition(new Point(spaceShip.getPosition().x, spaceShip.getPosition().y));
                    justRotated = 2;
                }
                /* left to move Spaceship position left */
                if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                    spaceShip.setPosition(new Point(spaceShip.getPosition().x - 10, spaceShip.getPosition().y));
                    whiteTrajectoryDot.setPosition(new Point(spaceShip.getPosition().x, spaceShip.getPosition().y));
                    justRotated = 2;
                }
                /* Q to rotate Spaceship right */
                if (pressedKeys.contains(KeyEvent.VK_W)) {
                    spaceShip.setRotation(spaceShip.getRotation() + 45.0f);
                    whiteTrajectoryDot.setRotation(spaceShip.getRotation());
                    justRotated = 10;
                }
                /* W to rotate Spaceship right */
                if (pressedKeys.contains(KeyEvent.VK_Q)) {
                    spaceShip.setRotation(spaceShip.getRotation() - 45.0f);
                    whiteTrajectoryDot.setRotation(spaceShip.getRotation());
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
            // White trajectory dot control
            if (whiteDotTimer != 0) {
                whiteTrajectoryDot.updatePositionWithGravity(planetOne);
                whiteDotTimer -= 1;
            } else
                resetWhiteDot();

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
