package edu.virginia.lab4test;

import edu.virginia.engine.display.*;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;


import edu.virginia.engine.display.Game;

public class LabFourGame extends Game {
    Sprite mario = new Sprite("Mario", "mario.png");
    Sprite bowser = new Sprite("Bowser", "bowser.png");
    Sprite luigi = new Sprite("Luigi", "luigi.png");
    private int visibilityBlocker = 10;
    private int score = 0;

    public LabFourGame() {
        super("Lab Four Test Game", 900, 900);
        luigi.setPosition(new Point(600,600));
        bowser.setPosition(new Point(300,300));
        mario.initializeRectangleHitbox();
        luigi.initializeRectangleHitbox();
        bowser.initializeRectangleHitbox();
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

        visibilityBlocker--;
        if (visibilityBlocker == Integer.MAX_VALUE)
            visibilityBlocker = 10;

        if(mario.collidesWith(bowser))
        {

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
    }
    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure sun gets drawn to
     * the screen, we need to make sure to override this method and call sun's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);

        /* Same, just check for null in case a frame gets thrown in before the sprites is initialized */
        if(mario != null) mario.draw(g);
        if(luigi != null) luigi.draw(g);
        if(bowser != null) bowser.draw(g);
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        LabFourGame game = new LabFourGame();
        game.start();
    }
}
