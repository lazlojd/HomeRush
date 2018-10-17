package edu.virginia.lab2test;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Animation;
import edu.virginia.engine.display.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Graphics;

public class LabTwoGame extends Game {

    AnimatedSprite mario = new AnimatedSprite("Mario", "mario_jump_0.png", new Point(100, 100));
    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public LabTwoGame() {
        super("Lab 2 Test Game", 500, 300);
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);

        Animation jump = new Animation("jump", 0, 1);
        Animation run = new Animation("run", 2, 3);
        ArrayList<Animation> animations = new ArrayList<Animation>();
        animations.add(jump);
        animations.add(run);
        mario.setAnimations(animations);


        /* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        if (mario != null) mario.update(pressedKeys);

        /* Up, left, right */
        if (pressedKeys.contains(KeyEvent.VK_UP)) {
            mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y - 5));
            mario.animate("jump");
            if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
                mario.stopAnimation();
            }
        }

        if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
            mario.setPosition(new Point(mario.getPosition().x - 5, mario.getPosition().y));
            mario.animate("run");
            if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
                mario.stopAnimation();
            }
        }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            mario.setPosition(new Point(mario.getPosition().x + 5, mario.getPosition().y));
            mario.animate("run");
            if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
                mario.stopAnimation();
            }
        }

        /* Plus and minus */
        if (pressedKeys.contains(KeyEvent.VK_PLUS)) {
            mario.setAnimationSpeed(mario.getAnimationSpeed() + 50);
        }
        if (pressedKeys.contains(KeyEvent.VK_MINUS)) {
            mario.setAnimationSpeed(mario.getAnimationSpeed() - 50);
        }

        /* Stops animation */
        if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
            mario.stopAnimation();
        }

    }


    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
     * the screen, we need to make sure to override this method and call mario's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);

        /* Same, just check for null in case a frame gets thrown in before Mario is initialized */
        if(mario != null) mario.draw(g);
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        LabTwoGame game = new LabTwoGame();
        game.start();

    }
}


