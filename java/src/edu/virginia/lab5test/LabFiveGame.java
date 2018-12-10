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
    Sprite levelThree;
    Sprite planetOne;
    Sprite planetThree;
    Sprite planetFour;
    Sprite whiteDwarf;
    Sprite sun;
    Sprite moon;
    Sprite spaceShip;
    Sprite spaceShip2;
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
    int level = 3;
    final Point INITIALSHIPPOSITION = new Point(150,800);
    final Point INITIALPIVOT = new Point(30, 26);
    final Point CENTER = new Point(350, 350);
    final Point STAGERIGHT = new Point(1000, 0);

    private void initSprites() {
        levelOne = new Sprite("backgroundLevelOne", "spaceBackground.jpg");
        levelTwo = new Sprite("backgroundLevelTwo", "spaceBackground.jpg");
        levelThree = new Sprite("backgroundLevelThree", "white.jpg");
        planetOne = new Sprite("planetOne", "planet1.png");
        planetThree = new Sprite("planetThree", "planet3.png");
        planetFour = new Sprite("planetFour", "planet4.png");
        whiteDwarf = new Sprite("whiteDwarf", "whiteDwarf.png");
        moon = new Sprite("moon", "moon.png");
        sun = new Sprite("sun", "sun.png");
        spaceShip = new Sprite("spaceship", "spaceship.png");
        spaceShip2 = new Sprite("spaceship2", "spaceship.png");
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

    }

    private void initLevelThree() {


        // Set spaceship at bottom left corner
        spaceShip2.setPosition(INITIALSHIPPOSITION);
        spaceShip2.setMass(1);
        spaceShip2.initializeCollisionHitbox();
        resetWhiteDot();

        planetThree.setPosition(new Point(700, 700));
        planetThree.setMass(3);
        planetThree.setCenter(new Point(820, 820));
        planetThree.initializeCollisionHitbox();
        moon.setPosition(new Point(600, 600));
        moon.initializeCollisionHitbox();

        planetFour.setPosition(new Point(50, 400));
        planetFour.setMass(3);
        planetFour.setCenter(new Point(137, 465));
        planetFour.initializeCollisionHitbox();

        whiteDwarf.setPosition(new Point(500, 250));
        whiteDwarf.setMass(7);
        whiteDwarf.setCenter(new Point(537, 287));
        whiteDwarf.initializeCollisionHitbox();

        target.setPosition(new Point(700, 50));
        target.initializeCollisionHitbox();

        levelThree.addChild(spaceShip2);
        //levelThree.addChild(whiteTrajectoryDot);
        levelThree.addChild(planetThree);
        levelThree.addChild(moon);
        levelThree.addChild(planetFour);
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
        //initLevelTwo();
        initLevelThree();
        messageTimer = 0;
        didWin = false;
        launched = false;


    }


    private void collisionReset(Sprite spaceShip) {
        System.out.println("Collided");
        currentMessage = loseMessage;
        currentMessage.setPosition(CENTER);
        spaceShip.setPosition(INITIALSHIPPOSITION);
        spaceShip.setPivotPoint(INITIALPIVOT);
        spaceShip.initializeCollisionHitbox();
        launched = false;

        messageTimer = 100;
        resetWhiteDot();
    }


    public void handleSpaceshipPostLaunch() {
        Sprite ship = null;
        if (level == 1) {
            ship = spaceShip;
            spaceShip.updatePositionWithGravity(planetOne);
            spaceShip.updatePositionWithGravity(sun);

            if(spaceShip.collidesWith(planetOne) || spaceShip.collidesWith(sun)) {
                collisionReset(spaceShip);
            }
        }
        else if (level == 3) {
            ship = spaceShip2;
            spaceShip2.updatePositionWithGravity(planetFour);
            spaceShip2.updatePositionWithGravity(whiteDwarf);
            spaceShip2.updatePositionWithGravity(planetThree);


            if(spaceShip2.collidesWith(planetThree) || spaceShip2.collidesWith(whiteDwarf) || spaceShip2.collidesWith(planetFour) || spaceShip2.collidesWith(moon)) {
                collisionReset(spaceShip2);
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
        if (level == 3)
            spaceShip = spaceShip2;

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
            } else if (level == 3) {
                System.out.println("correct level");
                whiteTrajectoryDot.updatePositionWithGravity(planetThree);
                whiteTrajectoryDot.updatePositionWithGravity(planetFour);
                whiteTrajectoryDot.updatePositionWithGravity(whiteDwarf);
            }
            whiteDotTimer -= 1;
        } else
            resetWhiteDot();

        if (whiteTrajectoryDot.collidesWith(planetOne) || whiteTrajectoryDot.collidesWith(sun)) {
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
