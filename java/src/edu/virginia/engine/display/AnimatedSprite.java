package edu.virginia.engine.display;

import edu.virginia.engine.util.GameClock;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.lang.Thread;

public class AnimatedSprite extends Sprite {

    static final int DEFAULT_ANIMATION_SPEED = 1000;

    private ArrayList<Animation> animations;
    private Boolean playing;
    private String fileName;
    private ArrayList<BufferedImage> frames;
    private Integer currentFrame;
    private Integer startFrame;
    private Integer endFrame;
    private Integer animationSpeed;
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


    public void setFrames(ArrayList<BufferedImage> frames) {
        this.frames = frames;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setEndFrame(Integer endFrame) {
        this.endFrame = endFrame;
    }

    public void setStartFrame(Integer startFrame) {
        this.startFrame = startFrame;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void setAnimations(ArrayList<Animation> animations) {
        this.animations = animations;
    }

    public void setAnimationSpeed(Integer animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public Integer getStartFrame() {
        return startFrame;
    }

    public Integer getEndFrame() {
        return endFrame;
    }

    public Boolean getPlaying() {
        return playing;
    }

    public String getFileName() {
        return fileName;
    }

    public GameClock getGameClock() {
        return gameClock;
    }

    public ArrayList<Animation> getAnimations() {
        return animations;
    }

    public void draw(Graphics g) {
        currentFrame = startFrame;
        while (currentFrame <= endFrame) {
            this.setImage(frames.get(currentFrame));
            super.draw(g);
            this.gameClock.resetGameClock();
            currentFrame++;
            try {
                Thread.sleep(DEFAULT_ANIMATION_SPEED);
            } catch (InterruptedException e) {
                System.err.println("Sleep interrupted");
            }

        }


    }


}