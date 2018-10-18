package edu.virginia.lab3test;

import edu.virginia.engine.display.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LabThreeSimulator extends Game {

    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

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

        /* W and Q Rotation */
        if(pressedKeys.contains(KeyEvent.VK_A)) {
            mario.setRotation(mario.getRotation() + 5.0f);
        }
        if(pressedKeys.contains(KeyEvent.VK_S)) {
            mario.setRotation(mario.getRotation() - 5.0f);
        }
}
