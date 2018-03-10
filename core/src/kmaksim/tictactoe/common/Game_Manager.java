package kmaksim.tictactoe.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import kmaksim.tictactoe.MyGdxGametictactoe;
import kmaksim.tictactoe.config.GameConfig;

/**
 * Created by Maksim1 on 08.03.2018.
 */

public class Game_Manager {



    public static final Game_Manager INSTANCE = new Game_Manager();

    private static final String MUSIC_VOLUME = "musicvolume";
    private static final String SOUNDS_VOLUME = "SOUND_VOLUME";

    private Preferences PREF;
    private float music;
    private float sounds;
    private boolean musCheked;
    private boolean soundCheked;

    protected Preferences getPREF(){
        if(PREF == null) {
            PREF = Gdx.app.getPreferences(GameConfig.GAME_NAME);
        }
        return PREF;
    }

    private Game_Manager(){
            getPREF();
            music = PREF.getFloat(MUSIC_VOLUME, 1);
            sounds = PREF.getFloat(SOUNDS_VOLUME, 1);
        }

    public boolean musicCheked(){
        if(music == 1){
            return true;
        }else

        return  false;
    }

    public boolean soundCheked(){
        if(sounds == 1){
            return true;
        }else
            return  false;
    }

    public void changeOnOffMusic(int onOff){

        PREF.putFloat(MUSIC_VOLUME, onOff);
        PREF.flush();
    }

    public float getMusicString() {
        return music;
    }

    public float getSoundString() {
        return sounds;
    }

    public void  changeOnOffSounds(int onOff){


        PREF.putFloat(SOUNDS_VOLUME, onOff);
        PREF.flush();
    }

}
