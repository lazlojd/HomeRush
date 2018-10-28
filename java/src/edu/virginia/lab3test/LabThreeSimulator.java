package edu.virginia.lab3test;

import edu.virginia.engine.display.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Point;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.DisplayObject;

public class LabThreeSimulator extends Game {

    /* Create a sprite object for our game. We'll use sun */
    Sprite sun = new Sprite("Sun", "sun.png", new Point(50, 50));
    Sprite planet_1 = new Sprite("Planet 1", "planet1.png", new Point(10, 10));
    //Sprite planet_2 = new Sprite("Planet 2", "planet2.png", new Point(10, 10));
    Sprite moon_1 = new Sprite("Moon 1", "moon.png", new Point(10,10));
    //Sprite moon_2 = new Sprite("Moon 2", "moon.png", new Point(10,10));

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public LabThreeSimulator() {

        super("Lab Three Test Game", 1000, 600);
        planet_1.addChild(moon_1);
        sun.addChild(planet_1);
        System.out.println(planet_1.localToGlobal(new Point(10,10)).toString());
        System.out.println(moon_1.localToGlobal(new Point(10,10)).toString());
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);



        /* Make sure sun is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        if (sun != null) sun.update(pressedKeys);



        /* Q & W Zoom in and out */
        if (pressedKeys.contains(KeyEvent.VK_Q)) {
            sun.setScaleX(sun.getScaleX() + 0.1);
            sun.setScaleY(sun.getScaleY() + 0.1);

        }

        if (pressedKeys.contains(KeyEvent.VK_W)) {
            if (sun.getScaleX() - 0.1 >= 0 || sun.getScaleY() - 0.1 >= 0) {
                sun.setScaleX(sun.getScaleX() - 0.1);
                sun.setScaleY(sun.getScaleY() -  0.1);
            }

        }

        /* Up, down, left, right */
        if(pressedKeys.contains(KeyEvent.VK_UP)) {
            sun.setPosition(new Point(sun.getPosition().x, sun.getPosition().y - 5));
        }
        if(pressedKeys.contains(KeyEvent.VK_DOWN)) {
            sun.setPosition(new Point(sun.getPosition().x, sun.getPosition().y + 5));
        }
        if(pressedKeys.contains(KeyEvent.VK_LEFT)) {
            sun.setPosition(new Point(sun.getPosition().x - 5, sun.getPosition().y));
        }
        if(pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            sun.setPosition(new Point(sun.getPosition().x + 5, sun.getPosition().y));
        }


        /* A and S Rotation */
        if(pressedKeys.contains(KeyEvent.VK_A)) {
            sun.setRotation(sun.getRotation() + 5.0f);
        }
        if(pressedKeys.contains(KeyEvent.VK_S)) {
            sun.setRotation(sun.getRotation() - 5.0f);
        }
    }




    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure sun gets drawn to
     * the screen, we need to make sure to override this method and call sun's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);

        /* Same, just check for null in case a frame gets thrown in before Sun is initialized */
        if(sun != null) sun.draw(g);
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        DisplayObject test = new DisplayObjectContainer("test");
        ((DisplayObjectContainer) test).getChildren();
        LabThreeSimulator game = new LabThreeSimulator();
        game.start();


    }
}
