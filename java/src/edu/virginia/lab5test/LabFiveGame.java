package edu.virginia.lab5test;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.SoundManager;
import edu.virginia.engine.display.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LabFiveGame extends Game {

    Sprite levelOne;
    Sprite levelTwo;
    Sprite planetOne;
    Sprite sun;
    Sprite spaceShip;
    Sprite whiteTrajectoryDot;
    Sprite target;
    Sprite reset;
    Sprite winMessage;
    Sprite loseMessage;
    Sprite currentMessage;
    boolean launched;
    int justRotated = 10;
    int whiteDotTimer = 50;
    int messageTimer = 0;
    boolean didWin;
    int level = 1;
    final Point INITIALSHIPPOSITION = new Point(150,800);
    final Point INITIALPIVOT = new Point(30, 26);
    final Point CENTER = new Point(350, 350);
    final Point STAGERIGHT = new Point(1000, 0);

    private void initSprites() {
        levelOne = new Sprite("backgroundLevelOne", "spaceBackground.jpg");
        planetOne = new Sprite("planetOne", "planet1.png");
        sun = new Sprite("sun", "sun.png");
        spaceShip = new Sprite("spaceship", "spaceship.png");
        whiteTrajectoryDot = new Sprite("whiteDot", "whiteDot.png");
        target = new Sprite("target", "target.png");
        reset = new Sprite("reset", "reset.png");
        winMessage = new Sprite("win", "winner.jpg");
        loseMessage = new Sprite("loser", "lose.png");
    }

    private void initLevelOne() {
        // Set spaceship at bottom left corner
        spaceShip.setPosition(new Point(150,800));
        spaceShip.setMass(1);
        spaceShip.initializeCollisionHitbox();

        // Set pivot point to be center of spaceship
        spaceShip.setPivotPoint(new Point(30, 26));

        resetWhiteDot();
        /*NOTE: position of planet will not govern what spaceship is attracted to. The
        * CENTER of the planet does*/
        planetOne.setPosition(new Point(600, 750));
        // We want spaceship to gravitate toward center of planet, not upper left corner
        planetOne.setCenter(new Point(planetOne.getPosition().x - 50, planetOne.getPosition().y - 50));
        planetOne.setMass(5);
        planetOne.initializeCollisionHitbox();
        planetOne.initializeGravityHitbox();
        // Center sun horizontally and place on left side of screen
        sun.setPosition(new Point(100, 200));
        sun.setMass(10);
        sun.setCenter(new Point(sun.getPosition().x - 50, sun.getPosition().y - 50));
        sun.initializeCollisionHitbox();
        sun.initializeGravityHitbox();

        target.setPosition(new Point(700, 50));
        target.initializeCollisionHitbox();


        winMessage.setPosition(STAGERIGHT);

        loseMessage.setPosition(STAGERIGHT);


        levelOne.addChild(target);
        levelOne.addChild(spaceShip);
        levelOne.addChild(whiteTrajectoryDot);
        levelOne.addChild(planetOne);
        levelOne.addChild(loseMessage);
        levelOne.addChild(sun);
        levelOne.addChild(reset);
        levelOne.addChild(winMessage);



        messageTimer = 0;
        didWin = false;
        launched = false;
    }

    private  void initLevelTwo() {
        levelTwo = new Sprite("backgroundLevelTwo", "spaceBackground.jpg");
    }

    private void resetWhiteDot() {
        whiteTrajectoryDot.setPosition(new Point(spaceShip.getPosition().x, spaceShip.getPosition().y));
        whiteTrajectoryDot.setMass(1);
        whiteTrajectoryDot.setPivotPoint(new Point(spaceShip.getPivotPoint().x, spaceShip.getPivotPoint().y));
        whiteTrajectoryDot.setRotation(spaceShip.getRotation());
        whiteTrajectoryDot.initializeCollisionHitbox();
        whiteTrajectoryDot.resetVelocity();
        whiteTrajectoryDot.launch();
        whiteDotTimer = 20;
    }

    public LabFiveGame() {
        super("Lab Five Test Game", 900, 900);
        initSprites();
        initLevelOne();
        initLevelTwo();
    }



    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     */
    @Override
    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);

        if (messageTimer > 0) {
            messageTimer -= 1;
            if (messageTimer == 0) {
                currentMessage.setPosition(STAGERIGHT);
                if (currentMessage.getId() == "win")
                    level += 1;
            }
            return;
        }

        if (pressedKeys.contains((KeyEvent.VK_R))) {
            System.out.println("RESET");
            spaceShip.setPosition(INITIALSHIPPOSITION);
            spaceShip.setPivotPoint(INITIALPIVOT);
            spaceShip.initializeCollisionHitbox();
            launched = false;
            resetWhiteDot();
        }

        if (!launched) {

            if (justRotated == 0) {
                /* right to move Spaceship position right */
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
                whiteTrajectoryDot.updatePositionWithGravity(sun);
                whiteDotTimer -= 1;
            } else
                resetWhiteDot();

            if (whiteTrajectoryDot.collidesWith(planetOne) || whiteTrajectoryDot.collidesWith(sun)) {
                resetWhiteDot();
            }

            if (whiteTrajectoryDot.collidesWith(target)) {

                resetWhiteDot();
            }

            //System.out.println(spaceShip.getRotation());
        } else {

            // This applies gravitational effects from planet one to spaceship when the spaceship hits the gravitational field
            spaceShip.updatePositionWithGravity(planetOne);


            spaceShip.updatePositionWithGravity(sun);

            // The ship should reset when the spaceship collides with either the planet or the sun
            if(spaceShip.collidesWith(planetOne) || spaceShip.collidesWith(sun)) {
                System.out.println("Collided");
                currentMessage = loseMessage;
                currentMessage.setPosition(CENTER);
                spaceShip.setPosition(new Point(150,800));
                spaceShip.setPivotPoint(new Point(30, 26));
                spaceShip.initializeCollisionHitbox();
                launched = false;

                messageTimer = 100;
                resetWhiteDot();
            }

            if(spaceShip.collidesWith(target)) {
                System.out.println("Victory!");
                currentMessage = winMessage;
                currentMessage.setPosition(CENTER);
                spaceShip.setPosition(new Point(150,800));
                spaceShip.setPivotPoint(new Point(30, 26));
                spaceShip.initializeCollisionHitbox();
                launched = false;
                messageTimer = 100;
                resetWhiteDot();
            }



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

        if (levelOne != null && level == 1) levelOne.draw(g);

        if (levelTwo != null && level == 2) levelTwo.draw(g);


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
