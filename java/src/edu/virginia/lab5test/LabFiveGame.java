package edu.virginia.lab5test;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.SoundManager;
import edu.virginia.engine.display.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LabFiveGame extends Game {

    Sprite levelOne;
    Sprite levelTwo;
    Sprite levelThree;
    Sprite planetOne;
    Sprite planetTwo;
    Sprite planetThree;
    Sprite planetFour;
    Sprite planetFive;
    Sprite whiteDwarf;
    Sprite asteroid;
    Sprite sun;
    Sprite moon;
    AnimatedSprite spaceShip;
    AnimatedSprite spaceShip2;
    AnimatedSprite spaceShip3;
    Sprite whiteTrajectoryDot;
    Sprite target;
    Sprite reset;
    Sprite winMessage;
    Sprite loseMessage;
    Sprite currentMessage;
    AnimatedSprite currentShip;
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
        levelTwo = new Sprite("backgroundLevelTwo", "spaceBackground.jpg");
        levelThree = new Sprite("backgroundLevelThree", "spaceBackground.jpg");
        planetOne = new Sprite("planetOne", "planet1.png");
        planetTwo = new Sprite("planetTwo", "planet2.png");
        planetThree = new Sprite("planetThree", "planet3.png");
        planetFour = new Sprite("planetFour", "planet4.png");
        planetFive = new Sprite("planetFive", "planet5.png");
        whiteDwarf = new Sprite("whiteDwarf", "whiteDwarf.png");
        asteroid = new Sprite("asteroid", "asteroid.png");
        moon = new Sprite("moon", "moon.png");
        sun = new Sprite("sun", "sun.png");
        spaceShip = new AnimatedSprite("spaceship", "spaceship.png");
        spaceShip2 = new AnimatedSprite("spaceship2", "spaceship.png");
        spaceShip3 = new AnimatedSprite("spaceship3", "spaceship.png");
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

        // Center sun horizontally and place on left side of screen
        sun.setPosition(new Point(100, 200));
        sun.setMass(10);
        sun.setCenter(new Point(sun.getPosition().x - 50, sun.getPosition().y - 50));
        sun.initializeCollisionHitbox();


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
    }

    private  void initLevelTwo() {
        spaceShip2.setPosition(INITIALSHIPPOSITION);
        spaceShip2.setMass(1);
        spaceShip2.initializeCollisionHitbox();
        resetWhiteDot();

        planetTwo.setPosition(new Point(700, 700));
        planetTwo.setMass(3);
        planetTwo.setCenter(new Point(820, 820));
        planetTwo.initializeCollisionHitbox();

        planetThree.setPosition(new Point(25, 400));
        planetThree.setMass(5);
        planetThree.setCenter(new Point(137, 465));
        planetThree.initializeCollisionHitbox();

        asteroid.setPosition(new Point(-50, -150));
        asteroid.setMass(1);
        asteroid.setCenter(new Point(asteroid.getPosition().x + 25, asteroid.getPosition().y + 25));
        asteroid.initializeCollisionHitbox();

        target.setPosition(new Point(700, 50));
        target.initializeCollisionHitbox();

        levelTwo.addChild(spaceShip2);
        //levelTwo.addChild(whiteTrajectoryDot);
        levelTwo.addChild(asteroid);
        levelTwo.addChild(planetTwo);
        levelTwo.addChild(planetThree);
        levelTwo.addChild(target);
        levelTwo.addChild(winMessage);
        levelTwo.addChild(loseMessage);
        levelTwo.addChild(whiteTrajectoryDot);
        levelTwo.addChild(reset);
    }

    private void initLevelThree() {


        // Set spaceship at bottom left corner
        spaceShip3.setPosition(INITIALSHIPPOSITION);
        spaceShip3.setMass(1);
        spaceShip3.initializeCollisionHitbox();
        resetWhiteDot();

        planetFour.setPosition(new Point(700, 700));
        planetFour.setMass(3);
        planetFour.setCenter(new Point(820, 820));
        planetFour.initializeCollisionHitbox();
        moon.setPosition(new Point(600, 600));
        moon.initializeCollisionHitbox();

        planetFive.setPosition(new Point(50, 400));
        planetFive.setMass(3);
        planetFive.setCenter(new Point(137, 465));
        planetFive.initializeCollisionHitbox();

        whiteDwarf.setPosition(new Point(500, 250));
        whiteDwarf.setMass(7);
        whiteDwarf.setCenter(new Point(537, 287));
        whiteDwarf.initializeCollisionHitbox();

        target.setPosition(new Point(700, 50));
        target.initializeCollisionHitbox();

        levelThree.addChild(spaceShip3);
        //levelThree.addChild(whiteTrajectoryDot);
        levelThree.addChild(planetFour);
        levelThree.addChild(moon);
        levelThree.addChild(planetFive);
        levelThree.addChild(whiteDwarf);
        levelThree.addChild(target);
        levelThree.addChild(winMessage);
        levelThree.addChild(loseMessage);
        levelThree.addChild(whiteTrajectoryDot);
        levelThree.addChild(reset);
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
        initLevelThree();
        messageTimer = 0;
        didWin = false;
        launched = false;
    }


    private void collisionReset(AnimatedSprite spaceShip) {
        System.out.println("Collided");
        spaceShip.animate("explode");
        currentMessage = loseMessage;
        currentMessage.setPosition(CENTER);
        currentShip = spaceShip;
        messageTimer = 100;

    }

    private void collisionResetPosition(AnimatedSprite spaceShip) {
        spaceShip.setPosition(INITIALSHIPPOSITION);
        spaceShip.setPivotPoint(INITIALPIVOT);
        spaceShip.initializeCollisionHitbox();
        launched = false;


        resetWhiteDot();
    }


    public void handleSpaceshipPostLaunch() {
        if (messageTimer != 0)
            return;
        Sprite ship = null;
        if (level == 1) {
            ship = spaceShip;
            spaceShip.updatePositionWithGravity(planetOne);
            spaceShip.updatePositionWithGravity(sun);

            if(spaceShip.collidesWith(planetOne) || spaceShip.collidesWith(sun)) {
                collisionReset(spaceShip);
            }
        }
        else if (level == 2) {
            ship = spaceShip2;
            if((asteroid.getPosition().x) > 1000) {
                asteroid.setPosition(new Point(150,50));
                asteroid.initializeCollisionHitbox();
            }
            else {
                asteroid.setPosition(new Point(asteroid.getPosition().x + 2, asteroid.getPosition().y + 2));
                asteroid.setCenter(new Point(asteroid.getPosition().x + 25, asteroid.getPosition().y + 25));
                asteroid.initializeCollisionHitbox();
            }
            spaceShip2.updatePositionWithGravity(planetTwo);
            spaceShip2.updatePositionWithGravity(planetThree);
            spaceShip2.updatePositionWithGravity(asteroid);


            if(spaceShip2.collidesWith(planetTwo) || spaceShip2.collidesWith(planetThree) || spaceShip2.collidesWith(asteroid)) {
                collisionReset(spaceShip2);
            }
        }
        else if (level == 3) {
            ship = spaceShip3;
            spaceShip3.updatePositionWithGravity(whiteDwarf);
            spaceShip3.updatePositionWithGravity(planetFour);
            spaceShip3.updatePositionWithGravity(planetFive);


            if (spaceShip3.collidesWith(planetFour) || spaceShip3.collidesWith(planetFive) || spaceShip3.collidesWith(whiteDwarf) || spaceShip3.collidesWith(moon)) {
                collisionReset(spaceShip3);
            }
        }


        // The ship should reset when the spaceship collides with either the planet or the sun



        if(ship.collidesWith(target)) {
            System.out.println("Victory!");
            currentMessage = winMessage;
            currentMessage.setPosition(CENTER);
            ship.setPosition(INITIALSHIPPOSITION);
            ship.setPivotPoint(INITIALPIVOT);
            ship.initializeCollisionHitbox();
            launched = false;
            messageTimer = 100;
            resetWhiteDot();
        }

    }

    public void handleSpaceshipPreLaunch(ArrayList<Integer> pressedKeys) {
        if(level == 2)
            spaceShip = spaceShip2;
            if((asteroid.getPosition().x) > 1000) {
                asteroid.setPosition(new Point(150,50));
                asteroid.initializeCollisionHitbox();
            }
            else {
                asteroid.setPosition(new Point(asteroid.getPosition().x + 2, asteroid.getPosition().y + 2));
                asteroid.setCenter(new Point(asteroid.getPosition().x + 25, asteroid.getPosition().y + 25));
                asteroid.initializeCollisionHitbox();
            }

        if (level == 3)
            spaceShip = spaceShip3;

        if (justRotated == 0) {
            /* right to move Spaceship position right */
            if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                spaceShip.setPosition(new Point(spaceShip.getPosition().x + 10, spaceShip.getPosition().y));
                whiteTrajectoryDot.setPosition(new Point(spaceShip.getPosition().x, spaceShip.getPosition().y));
                spaceShip.updateHitbox(+10, 0);
                whiteTrajectoryDot.updateHitbox(+10, 0);
                justRotated = 2;
            }
            /* left to move Spaceship position left */
            if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                spaceShip.setPosition(new Point(spaceShip.getPosition().x - 10, spaceShip.getPosition().y));
                whiteTrajectoryDot.setPosition(new Point(spaceShip.getPosition().x, spaceShip.getPosition().y));
                spaceShip.updateHitbox(-10, 0);
                whiteTrajectoryDot.updateHitbox(-10, 0);
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
            if (level == 1) {
                whiteTrajectoryDot.updatePositionWithGravity(planetOne);
                whiteTrajectoryDot.updatePositionWithGravity(sun);
            }
            if (level == 2) {
                whiteTrajectoryDot.updatePositionWithGravity(planetTwo);
                whiteTrajectoryDot.updatePositionWithGravity(planetThree);
                whiteTrajectoryDot.updatePositionWithGravity(asteroid);
            }
            else if (level == 3) {
                //System.out.println("correct level");
                whiteTrajectoryDot.updatePositionWithGravity(planetFour);
                whiteTrajectoryDot.updatePositionWithGravity(planetFive);
                whiteTrajectoryDot.updatePositionWithGravity(whiteDwarf);
            }
            whiteDotTimer -= 1;
        } else
            resetWhiteDot();

        if (whiteTrajectoryDot.collidesWith(planetOne) || whiteTrajectoryDot.collidesWith(sun)) {
            resetWhiteDot();
        }

        if (whiteTrajectoryDot.collidesWith(planetTwo) || whiteTrajectoryDot.collidesWith(planetThree)) {
            resetWhiteDot();
        }

        if (whiteTrajectoryDot.collidesWith(planetFour) || whiteTrajectoryDot.collidesWith(planetFive) || whiteTrajectoryDot.collidesWith(whiteDwarf) || whiteTrajectoryDot.collidesWith(moon)) {
            resetWhiteDot();
        }

        if (whiteTrajectoryDot.collidesWith(target)) {
            resetWhiteDot();
        }
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

                System.out.println("next level:" + level + 1);
                if (currentMessage.getId() == "win")
                    level += 1;
                else {
                    currentShip.stopAnimation(0);
                    collisionResetPosition(currentShip);
                }

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
            handleSpaceshipPreLaunch(pressedKeys);
            //System.out.println(spaceShip.getRotation());
        } else {
            handleSpaceshipPostLaunch();
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

        if (levelThree != null && level == 3) levelThree.draw(g);

        if (level == 4) winMessage.draw(g);


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
