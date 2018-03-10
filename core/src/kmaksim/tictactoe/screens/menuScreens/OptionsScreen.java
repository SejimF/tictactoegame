package kmaksim.tictactoe.screens.menuScreens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import kmaksim.tictactoe.MyGdxGametictactoe;
import kmaksim.tictactoe.assets.AssetDescriptors;
import kmaksim.tictactoe.assets.RegionNames;
import kmaksim.tictactoe.common.Game_Manager;

/**
 * Created by Maksim1 on 08.03.2018.
 */

public class OptionsScreen extends MenuScreenBase {

    private MenuScreen menuScreen;
    private Music themeMusic;
    private CheckBox musicChek, soundChek;
    private Sound click;


    public OptionsScreen(MyGdxGametictactoe game) {
        super(game);
    }

    @Override
    protected Actor createUi() {
        Table table = new Table();

        click = assetManager.get(AssetDescriptors.CLICK);


        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY);
        Skin uiSkin = assetManager.get(AssetDescriptors.UI_SKIN);
        themeMusic = assetManager.get(AssetDescriptors.THEME_MUSIC);

        musicChek = new CheckBox("Music on/off", uiSkin);
        soundChek = new CheckBox("Sound mode", uiSkin);

//        themeMusic.setVolume(1);




        TextureRegion background = gameplayAtlas.findRegion(RegionNames.BACKGROUND);
        table.setBackground(new TextureRegionDrawable(background));

        musicChek.setChecked(Game_Manager.INSTANCE.musicCheked());
        soundChek.setChecked(Game_Manager.INSTANCE.soundCheked());

        ChangeListener listener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                musicOnOff();
            }
        };
        ChangeListener listener2 = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                soundOnOff();
            }
        };
        musicChek.addListener(listener);
        soundChek.addListener(listener2);

//        TextButton play = new TextButton("MUSIC ON/OFF", uiSkin);
//        play.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                musicOnOff();
//
//            }
//        });
//
//        TextButton options = new TextButton("SOUND ON/OFF", uiSkin);
//        options.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                soundOnOff();
//
//            }
//        });

        final TextButton exitGame = new TextButton("MENU", uiSkin);
        exitGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                back();
            }
        });


        table.add(musicChek).pad(20).row();
        table.add(soundChek).pad(20).row();
        table.add(exitGame);
        table.center();
        table.setFillParent(true);
        table.pack();

        return table;
    }

    private void musicOnOff(){
        clickSound();
        if(Game_Manager.INSTANCE.getMusicString() == 1){
            Game_Manager.INSTANCE.changeOnOffMusic(0);
        }else if(Game_Manager.INSTANCE.getMusicString() == 0){
            Game_Manager.INSTANCE.changeOnOffMusic(1);
        }
    }

    private void soundOnOff(){
        clickSound();
        if(Game_Manager.INSTANCE.getSoundString()== 1){
            Game_Manager.INSTANCE.changeOnOffSounds(0);
        }else if(Game_Manager.INSTANCE.getSoundString() == 0){
            Game_Manager.INSTANCE.changeOnOffSounds(1);
        }
    }

    private void back(){
        clickSound();
        game.setScreen(new MenuScreen(game));
    }

    private void clickSound(){
        if(Game_Manager.INSTANCE.soundCheked()) {
            click.play();
        }
    }


}
