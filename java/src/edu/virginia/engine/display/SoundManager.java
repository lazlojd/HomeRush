package edu.virginia.engine.display;

import javax.sound.sampled.*;
import java.util.HashMap;
import java.io.*;

public class SoundManager {
    private HashMap<String, File> soundEffects;
    private Clip clip;
    /**
    private AudioFileFormat generateAudioFile(String fileName) {
        return new AudioFileFormat(new AudioFileFormat.Type(, "wav"));
    } **/
    public SoundManager() {
       this.soundEffects = new HashMap<String, File>();
       try {
           this.clip = AudioSystem.getClip();
       } catch (LineUnavailableException e) {
           System.err.println(e);
       }

    }

    public void LoadSoundEffect(String id, String fileName) {
        if (!this.soundEffects.containsKey(id)) {
            String file = ("resources" + File.separator + fileName);
            File fileObj = new File(file);
            this.soundEffects.put(id, fileObj);
                //AudioInputStream gameSound = AudioSystem.getAudioInputStream(fileObj);


//            AudioFileFormat audioFile = AudioSystem.getAudioFileFormat(new File(file));

        }


    }

    public void PlaySoundEffect(String id) {
        File audioFile = this.soundEffects.get(id);
        try {
            AudioInputStream gameSound = AudioSystem.getAudioInputStream(audioFile);
            clip.open(gameSound);
            clip.start();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void LoadMusic(String id, String filename) {

    }

    public void PlayMusic(String id) {

    }

}
