package edu.virginia.lab5test;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.SoundManager;
import edu.virginia.engine.display.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LabFiveGame extends Game {
    Sprite background = new Sprite("bg", "blank.png");
    Sprite mario = new Sprite("Mario", "mario.png");
    Sprite bowser = new Sprite("Bowser", "bowser.png");
    Sprite luigi = new Sprite("Luigi", "luigi.png");
    Sprite winScreen = new Sprite("win", "winner.jpeg");
    Sprite mushroomScore0 = new Sprite("mushroomS0", "mushroom.png");
    Sprite mushroomScore1 = new Sprite("mushroomS1", "mushroom.png");
    Sprite mushroomScore2 = new Sprite("mushroomS2", "mushroom.png");
    Sprite mushroomScore3 = new Sprite("mushroomS3", "mushroom.png");
    Sprite mushroomScore4 = new Sprite("mushroomS4", "mushroom.png");
    SoundManager soundManager;
    private int visibilityBlocker = 10;
    private int score = 5;
    private boolean reverseMotion = false;
    private boolean didWin = false;
    private String currentlyPlaying;

    public LabFiveGame() {
        super("Lab Five Test Game", 900, 900);
        background.addChild(luigi);
        background.addChild(mario);
        background.addChild(bowser);
        background.addChild(mushroomScore0);
        background.addChild(mushroomScore1);
        background.addChild(mushroomScore2);
        background.addChild(mushroomScore3);
        background.addChild(mushroomScore4);
        background.addChild(winScreen);
        winScreen.setPosition(new Point(-300,300));
        winScreen.setVisible(false);
        // The position setting below are all acting as offsets off the previous mushroom
        // very weird!
        mushroomScore0.setPosition(new Point(600, 10));
        mushroomScore1.setPosition(new Point(50, 0));
        mushroomScore2.setPosition(new Point(50, 0));
        mushroomScore3.setPosition(new Point(50, 0));
        mushroomScore4.setPosition(new Point(50, 0));




        luigi.setPosition(new Point(600,600));
        bowser.setPosition(new Point(300,300));
//        mushroomScore0.initializeRectangleHitbox();
        mario.initializeRectangleHitbox();
        luigi.initializeRectangleHitbox();
        bowser.initializeRectangleHitbox();
        soundManager = new SoundManager();
        soundManager.LoadSoundEffect("bowserCollision", "dead.wav");
        soundManager.LoadSoundEffect("luigiCollision", "win.wav");
        soundManager.LoadMusic("bgMusic", "game_music.wav");
        this.currentlyPlaying = "bgMusic";
        soundManager.PlayMusic("bgMusic");
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);
        if (this.didWin) {
            soundManager.PlaySoundEffect("luigiCollision");
            this.didWin = false;
            winScreen.setVisible(false);
        }
        visibilityBlocker--;
        if (visibilityBlocker == Integer.MAX_VALUE)
            visibilityBlocker = 10;

        if(mario.collidesWith(bowser) || bowser.collidesWith((mario)))
        {
            score -= 1;
            System.out.println("removing mushroomS" + score);
            background.removeChild("mushroomS" + (score));
            soundManager.PlaySoundEffect("bowserCollision");
            soundManager.PlayMusic(this.currentlyPlaying);
            mario.setPosition(new Point(0,0));
            mario.initializeRectangleHitbox();
        }

        if(mario.collidesWith(luigi))
        {
            winScreen.setVisible(true);
            this.didWin = true;
            //soundManager.PlaySoundEffect("luigiCollision");
            mario.setPosition(new Point(0,0));
            mario.initializeRectangleHitbox();


        }

        /* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        if (mario != null) mario.update(pressedKeys);



        if (pressedKeys.contains(KeyEvent.VK_V)) {
            /* visibilityBlocker only allows visibility to change once every 10 iterations of the loop
             * This prevents V key events that last more than one iteration from producing
             * no effect on the sprite
             */
            if (visibilityBlocker <= 0) {
                mario.setVisible(!mario.getVisible());
                visibilityBlocker = 10;
            }
            System.out.println(mario.getVisible());
        }

        if (pressedKeys.contains(KeyEvent.VK_Z)) {
            Float alpha = mario.getAlpha();
            if (alpha + 0.01f <= 1) {
                mario.setAlpha(mario.getAlpha() + 0.01f);
            }

            System.out.println(mario.getAlpha());
            System.out.println("-------------");

        }

        if (pressedKeys.contains(KeyEvent.VK_X)) {
            Float alpha = mario.getAlpha();
            if (alpha - 0.01f >= 0) {
                mario.setAlpha(mario.getAlpha() - 0.01f);
            }
            System.out.println(mario.getAlpha());
            System.out.println("-------------");
        }

        if (pressedKeys.contains(KeyEvent.VK_A)) {
            mario.setScaleX(mario.getScaleX() + 0.1);
            mario.setScaleY(mario.getScaleY() + 0.1);
            mario.updateHitbox(0.1);


        }

        if (pressedKeys.contains(KeyEvent.VK_S)) {
            if (mario.getScaleX() - 0.1 >= 0 || mario.getScaleY() - 0.1 >= 0) {
                mario.setScaleX(mario.getScaleX() - 0.1);
                mario.setScaleY(mario.getScaleY() -  0.1);
                mario.updateHitbox(0.1);
            }

        }

        /* Up, down, left, right */
        if(pressedKeys.contains(KeyEvent.VK_UP)) {
            mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y - 5));
            mario.updateHitbox(0, -5);
        }
        if(pressedKeys.contains(KeyEvent.VK_DOWN)) {
            mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y + 5));
            mario.updateHitbox(0,5);
        }
        if(pressedKeys.contains(KeyEvent.VK_LEFT)) {
            mario.setPosition(new Point(mario.getPosition().x - 5, mario.getPosition().y));
            mario.updateHitbox(-5,0);
        }
        if(pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            mario.setPosition(new Point(mario.getPosition().x + 5, mario.getPosition().y));
            mario.updateHitbox(5,0);
        }

        /* I,J,K,L Pivot Point */
        if(pressedKeys.contains(KeyEvent.VK_I)) {
            mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y - 5));
        }
        if(pressedKeys.contains(KeyEvent.VK_J)) {
            mario.setPivotPoint(new Point(mario.getPivotPoint().x - 5, mario.getPivotPoint().y));
        }
        if(pressedKeys.contains(KeyEvent.VK_K)) {
            mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y + 5));
        }
        if(pressedKeys.contains(KeyEvent.VK_L)) {
            mario.setPivotPoint(new Point(mario.getPivotPoint().x + 5, mario.getPivotPoint().y));
        }

        /* W and Q Rotation */
        if(pressedKeys.contains(KeyEvent.VK_W)) {
            mario.setRotation(mario.getRotation() + 5.0f);
            mario.updateHitbox(5.0f);
        }
        if(pressedKeys.contains(KeyEvent.VK_Q)) {
            mario.setRotation(mario.getRotation() - 5.0f);
            mario.updateHitbox(-5.0f);
        }
        int y = bowser.getPosition().y;
        if (reverseMotion) {
            bowser.setPosition(new Point(bowser.getPosition().x, y - 5));
            bowser.updateHitbox(0,-5);
            if (y <= 0)
                reverseMotion = !reverseMotion;
        }
        else {
            bowser.setPosition(new Point(bowser.getPosition().x, y + 5));
            bowser.updateHitbox(0 ,5);
            if (y >= 800)
                reverseMotion = !reverseMotion;
        }
    }
    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure sun gets drawn to
     * the screen, we need to make sure to override this method and call sun's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);

        /* Same, just check for null in case a frame gets thrown in before the sprites is initialized */
          if (background != null) background.draw(g);
//        if(mario != null) mario.draw(g);
//        if(luigi != null) luigi.draw(g);
//        if(bowser != null) bowser.draw(g);
//        if(mushroomScore0 != null) mushroomScore0.draw(g);
//        if(mushroomScore0 != null) mushroomScore0.draw(g);

    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        LabFiveGame game = new LabFiveGame();
        game.start();
    }
}
