package survival.main.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;
import java.util.HashMap;

public class Sound {

    Clip clip;
    HashMap<String, URL> map = new HashMap<>();

    public Sound() {
        // Sound Effects
        map.put("punch", getClass().getResource("/sounds/effects/punch.wav"));
        map.put("sword", getClass().getResource("/sounds/effects/sword.wav"));
        map.put("hit", getClass().getResource("/sounds/effects/hit.wav"));

        // Music
        map.put("background", getClass().getResource("/sounds/background/background_one.wav"));
    }

    public void setFile(String key) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(map.get(key));
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {

        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }


    public float getVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void setVolume(float volume) {
        if(volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume is not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

}
