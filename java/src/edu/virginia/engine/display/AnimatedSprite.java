package edu.virginia.engine.display;

import edu.virginia.engine.util.GameClock;

import java.util.ArrayList;
import java.awt.*;

public class AnimatedSprite extends Sprite {

    static final int DEFAULT_ANIMATION_SPEED = 1;

    private ArrayList<AnimatedSprite> animations;
    private Boolean playing;
    private String fileName;
    private ArrayList<DisplayObject> frames;
    private Integer currentFrame;
    private Integer startFrame;
    private Integer endFrame;
    private Float animationSpeed;
    private GameClock gameClock;



    public AnimatedSprite(String id, String fileName, Point pos) {
        super(id);
        this.fileName = fileName;
        this.position = pos;
        this.gameClock = new GameClock();
    }


    public void initGameClock() {
        if (gameClock == null)
            this.gameClock = new GameClock();
    }

    public void setAnimations(ArrayList<AnimatedSprite> animations) {
        this.animations = animations;
    }

    public void setAnimationSpeed(Float animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public void draw() {
        
    }


}