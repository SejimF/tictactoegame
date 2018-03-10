package kmaksim.tictactoe.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import kmaksim.tictactoe.MyGdxGametictactoe;
import kmaksim.tictactoe.assets.AssetDescriptors;
import kmaksim.tictactoe.assets.RegionNames;
import kmaksim.tictactoe.config.GameConfig;
import kmaksim.tictactoe.util.GdxUtils;

/**
 * Created by Maksim1 on 03.02.2018.
 */

public abstract class GameBaseScreen extends ScreenAdapter{
    protected final MyGdxGametictactoe game;
    protected final AssetManager assetManager;

    private Viewport viewport;
    private Stage stage;

    public GameBaseScreen(MyGdxGametictactoe game) {
        this.game = game;
        this.assetManager = game.getAssetManager();
    }

    @Override
    public void show() {
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGTH);
        stage = new Stage(viewport, game.getBatch());
        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY);

        Gdx.input.setInputProcessor(stage);

        stage.addActor(createUi());

        TextureRegion playerNull = gameplayAtlas.findRegion(RegionNames.PNULL);

    }

    protected abstract Actor createUi();

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {
        GdxUtils.ClearScreen();

        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
