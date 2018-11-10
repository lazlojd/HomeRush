package edu.virginia.engine.display;

import javax.sound.sampled.*;
import java.util.*;
import java.io.*;

public class SoundManager {
    private HashMap<String, File> soundEffects;
    private HashMap<String, File> music;
    private Clip clip;
    private boolean playing;
    private String currentlyPlaying;

    public SoundManager() {
        this.soundEffects = new HashMap<String, File>();
        this.music = new HashMap<String, File>();
        try {
            this.clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            System.err.println(e);
        }
        playing = false;

    }

    public void endSounds() {
        clip.stop();
    }

    private void loadSound(String id, String fileName, HashMap<String, File> hMap) {
        String file = ("resources" + File.separator + fileName);
        File fileObj = new File(file);
        hMap.put(id, fileObj);
        System.out.println(soundEffects.get(id));
    }

    public void LoadSoundEffect(String id, String fileName) {
        if (!this.music.containsKey(id)) {
            loadSound(id, fileName, soundEffects);

            //AudioInputStream gameSound = AudioSystem.getAudioInputStream(fileObj);


//            AudioFileFormat audioFile = AudioSystem.getAudioFileFormat(new File(file));

        }


    }

    public void PlaySoundEffect(String id) {
        File audioFile = this.soundEffects.get(id);
        try {
            AudioInputStream gameSound = AudioSystem.getAudioInputStream(audioFile);
            clip.close();
            clip.open(gameSound);
            clip.start();

            //clip.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        while(clip.getMicrosecondLength() != clip.getMicrosecondPosition()) {

        }
        if (playing)
            this.PlayMusic(this.currentlyPlaying);

    }

    public void LoadMusic(String id, String fileName) {
            loadSound(id, fileName, this.music);
    }

    public void PlayMusic(String id) {
        //if only one music clip, clip won't be in a map
        // so just play it
        this.currentlyPlaying = id;
        File audioFile = this.music.get(id);
        try {
            AudioInputStream gameSound = AudioSystem.getAudioInputStream(audioFile);
            clip.close();
            clip.open(gameSound);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            //clip.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        playing = true;
//        int len = music.size();
//        if (len == 1) {
//            clip.start();
//        } else if (len > 1) {
//            ArrayList<File> audiofiles = (ArrayList<File>) music.values();
//            try {
//                AudioInputStream mergedMusic = AudioSystem.getAudioInputStream(audiofiles.get(0));
//                int size = audiofiles.size();
//                for (int i = 1; i < size; i++) {
//                    AudioInputStream next = AudioSystem.getAudioInputStream(audiofiles.get(i));
//                    mergedMusic = new AudioInputStream(
//                            new SequenceInputStream(mergedMusic, next),
//                            mergedMusic.getFormat(),
//                            mergedMusic.getFrameLength() + next.getFrameLength());
//                }
//                clip.open(mergedMusic);
//                clip.start();
//            } catch (Exception e) {
//                System.err.println(e);
//            }
//        }
//
//        try {
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
//        } catch (Exception e) {
//            System.err.println((e));
//        }
    }

}
