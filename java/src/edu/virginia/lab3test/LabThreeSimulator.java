package edu.virginia.lab3test;

import edu.virginia.engine.display.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Point;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.DisplayObject;

public class LabThreeSimulator extends Game {
    Sprite center = new Sprite("Center", "blank.png", new Point(400,400));
    Sprite sun = new Sprite("Sun", "sun-adj.jpg", new Point(-400, -400));

    Sprite planetOne = new Sprite("Planet 1", "planet1.png", new Point(500, 500));
    Sprite moonOne = new Sprite("Moon 1", "moon-adj.png", new Point( -30,-30));

    Sprite planetTwo = new Sprite("Planet 2", "planet2.png", new Point(100, 100));
    Sprite moonTwo = new Sprite("Moon 2", "moon-adj.png", new Point( -30,-30));


    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public LabThreeSimulator() {
        super("Lab Three Test Game", 900, 900);

        moonOne.setPivotPoint(new Point(80, 80));
        planetOne.addChild(moonOne);
        planetOne.setPivotPoint(new Point(planetOne.getPivotPoint().x - 100, planetOne.getPivotPoint().y - 100));

        moonTwo.setPivotPoint(new Point(80, 80));
        planetTwo.addChild(moonTwo);
        planetTwo.setPivotPoint(new Point(planetTwo.getPivotPoint().x + 300, planetTwo.getPivotPoint().y + 300));
        sun.addChild(planetOne);
        sun.addChild(planetTwo);
        center.addChild(sun);
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);



        /* Make sure sun is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        if (center != null) center.update(pressedKeys);



        /* Q & W Zoom in and out */
        if (pressedKeys.contains(KeyEvent.VK_Q)) {
            center.setScaleX(center.getScaleX() + 0.1);
            center.setScaleY(center.getScaleY() + 0.1);

        }

        if (pressedKeys.contains(KeyEvent.VK_W)) {
            if (center.getScaleX() - 0.1 >= 0 || center.getScaleY() - 0.1 >= 0) {
                center.setScaleX(center.getScaleX() - 0.1);
                center.setScaleY(center.getScaleY() -  0.1);
            }

        }

        /* Up, down, left, right */
        if(pressedKeys.contains(KeyEvent.VK_UP)) {
            center.setPosition(new Point(center.getPosition().x, center.getPosition().y - 2));
        }
        if(pressedKeys.contains(KeyEvent.VK_DOWN)) {
            center.setPosition(new Point(center.getPosition().x, center.getPosition().y + 2));
        }
        if(pressedKeys.contains(KeyEvent.VK_LEFT)) {
            center.setPosition(new Point(center.getPosition().x - 2, center.getPosition().y));
        }
        if(pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            center.setPosition(new Point(center.getPosition().x + 2, center.getPosition().y));
        }

        /* A and S Rotation */
        if(pressedKeys.contains(KeyEvent.VK_A)) {
            center.setRotation(center.getRotation() + 5.0f);
        }
        if(pressedKeys.contains(KeyEvent.VK_S)) {
            center.setRotation(center.getRotation() - 5.0f);
        }
        moonTwo.setRotation(moonTwo.getRotation() + 0.5f);
        moonOne.setRotation(moonOne.getRotation() + 3.0f);
        planetOne.setRotation(planetOne.getRotation() + 1.0f);
        planetTwo.setRotation(planetTwo.getRotation() + 3.0f);
    }




    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure sun gets drawn to
     * the screen, we need to make sure to override this method and call sun's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);

        /* Same, just check for null in case a frame gets thrown in before Sun is initialized */
        if(center != null) center.draw(g);
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        LabThreeSimulator game = new LabThreeSimulator();
        game.start();
    }
}
