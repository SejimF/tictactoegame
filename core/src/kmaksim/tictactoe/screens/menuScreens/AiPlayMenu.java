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

import kmaksim.tictactoe.MyGdxGametictactoe;
import kmaksim.tictactoe.assets.AssetDescriptors;
import kmaksim.tictactoe.assets.RegionNames;
import kmaksim.tictactoe.common.Game_Manager;
import kmaksim.tictactoe.screens.game.GameScreen;
import kmaksim.tictactoe.screens.game.GameScreenAiRandom;

/**
 * Created by Maksim1 on 10.03.2018.
 */

public class AiPlayMenu extends MenuScreenBase{

    private Music themeMusic;
    private Sound click;



    public AiPlayMenu(MyGdxGametictactoe game){
        super(game);
    }

    @Override
    protected Actor createUi() {
        Table table = new Table();



        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY);
        Skin uiSkin = assetManager.get(AssetDescriptors.UI_SKIN);
        click = assetManager.get(AssetDescriptors.CLICK);


        themeMusic = assetManager.get(AssetDescriptors.THEME_MUSIC);
        themeMusic.isLooping();


        TextureRegion background = gameplayAtlas.findRegion(RegionNames.BACKGROUND);
        table.setBackground(new TextureRegionDrawable(background));

        TextButton play = new TextButton("EASY", uiSkin);
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                easy();
            }
        });



        final TextButton back = new TextButton("BACK", uiSkin);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                back();
            }
        });




        table.add(play).pad(20).row();
        table.add(back).padBottom(20).row();
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

    private void easy(){
        click();
        game.setScreen(new GameScreenAiRandom(game));
    }

    private void back(){
        click();
        game.setScreen(new MenuScreen(game));

    }
}
