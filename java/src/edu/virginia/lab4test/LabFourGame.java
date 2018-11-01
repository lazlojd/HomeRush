package edu.virginia.lab4test;

import edu.virginia.engine.display.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Point;

public class LabFourGame extends Game {
    Sprite mario = new Sprite("Mario", "mario.png");
    private int visibilityBlocker = 10;

    public LabFourGame() {
        super("Lab Four Test Game", 900, 900);

    }

    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

        visibilityBlocker--;
        if (visibilityBlocker == Integer.MAX_VALUE)
            visibilityBlocker = 10;

        /* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        if (mario != null) mario.update(pressedKeys);


        if (pressedKeys.contains(KeyEvent.VK_V)) {
            /* visibilityBlocker only allows visbility to change once every 10 iterations of the loop
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

        }

        if (pressedKeys.contains(KeyEvent.VK_S)) {
            if (mario.getScaleX() - 0.1 >= 0 || mario.getScaleY() - 0.1 >= 0) {
                mario.setScaleX(mario.getScaleX() - 0.1);
                mario.setScaleY(mario.getScaleY() -  0.1);
            }

        }

        /* Up, down, left, right */
        if(pressedKeys.contains(KeyEvent.VK_UP)) {
            mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y - 5));
        }
        if(pressedKeys.contains(KeyEvent.VK_DOWN)) {
            mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y + 5));
        }
        if(pressedKeys.contains(KeyEvent.VK_LEFT)) {
            mario.setPosition(new Point(mario.getPosition().x - 5, mario.getPosition().y));
        }
        if(pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            mario.setPosition(new Point(mario.getPosition().x + 5, mario.getPosition().y));
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
        }
        if(pressedKeys.contains(KeyEvent.VK_Q)) {
            mario.setRotation(mario.getRotation() - 5.0f);
        }


    }

}
