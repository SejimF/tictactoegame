package kmaksim.tictactoe.screens.menuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import kmaksim.tictactoe.MyGdxGametictactoe;
import kmaksim.tictactoe.assets.AssetDescriptors;
import kmaksim.tictactoe.assets.RegionNames;
import kmaksim.tictactoe.common.Game_Manager;
import kmaksim.tictactoe.screens.game.GameScreen;
import kmaksim.tictactoe.screens.game.GameScreenAiRandom;

/**
 * Created by Maksim1 on 03.02.2018.
 */

public class MenuScreen extends MenuScreenBase{


    private Sound click;
    private Music themeMusic;


    public MenuScreen(MyGdxGametictactoe game){
        super(game);
    }

    @Override
    protected Actor createUi() {
        Table table = new Table();



        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY);
        Skin uiSkin = assetManager.get(AssetDescriptors.UI_SKIN);
        click = assetManager.get(AssetDescriptors.CLICK);
        themeMusic = assetManager.get(AssetDescriptors.THEME_MUSIC);
        themeMusic.setVolume(Game_Manager.INSTANCE.getMusicString());
        themeMusic.play();


        TextureRegion background = gameplayAtlas.findRegion(RegionNames.BACKGROUND);
        table.setBackground(new TextureRegionDrawable(background));

        TextButton play = new TextButton("1 VS 1", uiSkin);
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
             play();
            }
        });

        TextButton playAi = new TextButton("VS AI", uiSkin);
        playAi.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                aiMenu();
            }
        });


        TextButton options = new TextButton("OPTIONS", uiSkin);
        options.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                options();
            }
        });

        final TextButton exitGame = new TextButton("EXIT", uiSkin);
        exitGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                exit();
            }
        });

         TextButton copyright = new TextButton("SejimF  2018", uiSkin);


        table.add(play).pad(20).row();
        table.add(playAi).pad(20).row();
        table.add(options).pad(40).row();
        table.add(exitGame).padBottom(180).row();
        table.add(copyright);
        table.center();
        table.setFillParent(true);
        table.pack();

        return table;
    }



    protected void click(){
        if(Game_Manager.INSTANCE.soundCheked()) {
            click.play();
        }
    }

    private void play(){
        click();
        game.setScreen(new GameScreen(game));

    }

    private void aiMenu(){
        game.setScreen(new AiPlayMenu(game));
    }

    private void options(){
        click();
        game.setScreen(new OptionsScreen(game));
    }

    private void exit(){
        click();
        Gdx.app.exit();

    }
    }
