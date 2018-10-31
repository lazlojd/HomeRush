package edu.virginia.lab3test;

import edu.virginia.engine.display.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Point;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.DisplayObject;

public class LabThreeSimulator extends Game {

    /* Create a sprite object for our game. We'll use sun
    *  With a 900 X 900 JFrame and a 50, 50 offset, the origin
    *  point is effectively (-150, -150) and the right-bottom most point
    *  is (650, 628). The y value of the second point is approximated.
    *  It is unclear why it too shouldn't be 650
    * */
//
//    Sprite sun = new Sprite("Sun", "sun-debug.png", new Point(0, 0));
////    Sprite planet_1 = new Sprite("Planet 1", "planet1-adj.jpg", new Point(250, 529));
//    Sprite planet_1 = new Sprite("Planet 1", "planet1-debug.png", new Point(500, 500));
//    //Sprite planet_2 = new Sprite("Planet 2", "planet2.png", new Point(10, 10));
//    Sprite moon_1 = new Sprite("Moon 1", "moon1-debug.png", new Point( 0,0));
//    //Sprite moon_2 = new Sprite("Moon 2", "moon.png", new Point(10,10));

    Sprite sun = new Sprite("Sun", "sun-adj.jpg", new Point(50, 50));
    Sprite planetOne = new Sprite("Planet 1", "planet1.png", new Point(500, 500));
    Sprite moonOne = new Sprite("Moon 1", "moon-adj.png", new Point( -30,-30));

    Sprite planetTwo = new Sprite("Planet 2", "planet2.png", new Point(100, 100));
    Sprite moonTwo = new Sprite("Moon 2", "moon-adj.png", new Point( -30,-30));

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public LabThreeSimulator() {
        super("Lab Three Test Game", 900, 900);

//        Sprite sun = new Sprite("Sun", "sun-debug.png", new Point(0, 0));
//        Sprite planetOne = new Sprite("Planet 1", "planet1-debug.png", new Point(500, 500));
//        Sprite moonOne = new Sprite("Moon 1", "moon1-debug.png", new Point( 0,0));

        moonOne.setPivotPoint(new Point(80, 80));
        planetOne.addChild(moonOne);
        planetOne.setPivotPoint(new Point(planetOne.getPivotPoint().x - 100, planetOne.getPivotPoint().y - 100));

        moonTwo.setPivotPoint(new Point(80, 80));
        planetTwo.addChild(moonTwo);
        planetTwo.setPivotPoint(new Point(planetTwo.getPivotPoint().x + 300, planetTwo.getPivotPoint().y + 300));
        sun.addChild(planetOne);
        sun.addChild(planetTwo);
        //System.out.println(planetOne.localToGlobal(new Point(10,10)).toString());
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
            sun.setPosition(new Point(sun.getPosition().x, sun.getPosition().y - 1));
        }
        if(pressedKeys.contains(KeyEvent.VK_DOWN)) {
            sun.setPosition(new Point(sun.getPosition().x, sun.getPosition().y + 1));
        }
        if(pressedKeys.contains(KeyEvent.VK_LEFT)) {
            sun.setPosition(new Point(sun.getPosition().x - 1, sun.getPosition().y));
        }
        if(pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            sun.setPosition(new Point(sun.getPosition().x + 1, sun.getPosition().y));
        }


        /* A and S Rotation */
        if(pressedKeys.contains(KeyEvent.VK_A)) {
            sun.setRotation(sun.getRotation() + 5.0f);
        }
        if(pressedKeys.contains(KeyEvent.VK_S)) {
            sun.setRotation(sun.getRotation() - 5.0f);
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
