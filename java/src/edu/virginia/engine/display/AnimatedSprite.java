package edu.virginia.engine.display;

import edu.virginia.engine.util.GameClock;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.*;

public class AnimatedSprite extends Sprite {

    //Information about all animations
    static final int DEFAULT_ANIMATION_SPEED = 1000;
    private ArrayList<Animation> animations;
    private ArrayList<BufferedImage> frames;
    //Information about current animation playing
    private Boolean playing;
<<<<<<< HEAD
    private String fileName;
    private ArrayList<BufferedImage> frames;
=======
>>>>>>> 136146f17c022e37575a9f90f9de35cbcd4438c9
    private Integer currentFrame;
    private Integer startFrame;
    private Integer endFrame;
    private Integer animationSpeed;
    private GameClock gameClock;



    public AnimatedSprite(String id, String fileName, Point pos) {
        super(id, fileName);
        this.position = pos;
        this.animationSpeed = DEFAULT_ANIMATION_SPEED;
        this.playing = false;
        initGameClock();
        initializeFrames();
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

    public Integer getAnimationSpeed() { return this.animationSpeed; }

    public void setAnimationSpeed(Integer animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

<<<<<<< HEAD
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

=======
    public void draw(Graphics g) {
        currentFrame = startFrame;
        super.draw(g);
        if(this.playing) {
            if(this.gameClock.getElapsedTime() > this.animationSpeed) {
                super.draw(g);

            }
            //Once image is drawn, next frame is set and clock is reset
            currentFrame++;
            this.setImage(this.frames.get(currentFrame));
            this.gameClock.resetGameClock();
            //Goes back to beginning of animation once end of animation is hit
            if(currentFrame > endFrame) {
                currentFrame = startFrame;
            }
>>>>>>> 136146f17c022e37575a9f90f9de35cbcd4438c9
        }
    }

    /* Krishan */
    public void initializeFrames() {
        frames = new ArrayList<>();
        this.frames.add(readImage("mario_jump_0.png"));
        this.frames.add(readImage("mario_jump_1.png"));
        this.frames.add(readImage("mario_run_0.png"));
        this.frames.add(readImage("mario_run_1.png"));
    }

    /* Krishan */
    public Animation getAnimation(String id) {
        for(int i = 0; i < this.animations.size(); i++) {
            if(this.animations.get(i).getId() == id) {
                return this.animations.get(i);
            }
        }
        return null;
    }


    /* Partner 2 - Part 3 and 4 */
    public void animate(Animation animateObject) {
        this.startFrame = animateObject.getStartFrame();
        this.endFrame = animateObject.getEndFrame();
        animate(startFrame, endFrame);
    }
    public void animate(String id) {
        Animation animateObject = getAnimation(id);
        animate(animateObject);
    }
    public void animate(Integer startFrame, Integer endFrame) {
        this.startFrame = startFrame;
        this.endFrame = endFrame;
        this.playing = true;
    }

    public void stopAnimation(Integer frameNumber) {
        this.playing = false;
        this.setImage(this.frames.get(frameNumber));
    }
    public void stopAnimation() {
        stopAnimation(this.startFrame);
    }
}