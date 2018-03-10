package kmaksim.tictactoe.screens.loading;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import kmaksim.tictactoe.MyGdxGametictactoe;
import kmaksim.tictactoe.assets.AssetDescriptors;
import kmaksim.tictactoe.config.GameConfig;
import kmaksim.tictactoe.screens.game.GameScreen;
import kmaksim.tictactoe.screens.game.GameScreenAiRandom;
import kmaksim.tictactoe.screens.menuScreens.MenuScreen;
import kmaksim.tictactoe.util.GdxUtils;

/**
 * Created by Maksim1 on 25.01.2018.
 */

public class LoadingScreen extends ScreenAdapter {

    // == Constanr ==

    private static final float PROGRESS_BAR_WIDTH = GameConfig.HUD_WIDTH / 2f;
    private static final float PROGRESS_BAR_HEIGTH = 60;

    // == atributes ==

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private float progress;
    private float waitTime = 0.75f;
    private boolean changeScreen;

    private final MyGdxGametictactoe game;
    private final AssetManager assetManager;

    // == constructor ==

    public LoadingScreen(MyGdxGametictactoe game) {
        this.game = game;
        assetManager = game.getAssetManager();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGTH, camera);
        renderer = new ShapeRenderer();

        assetManager.load(AssetDescriptors.UI_SKIN);
        assetManager.load(AssetDescriptors.GAMEPLAY);
        assetManager.load(AssetDescriptors.FONT);
        assetManager.load(AssetDescriptors.THEME_MUSIC);
        assetManager.load(AssetDescriptors.X_SOUND);
        assetManager.load(AssetDescriptors.O_SOUND);
        assetManager.load(AssetDescriptors.CLICK);
        assetManager.load(AssetDescriptors.WIN);
        assetManager.load(AssetDescriptors.DRAW);


    }

    @Override
    public void render(float delta) {
        update(delta);

        GdxUtils.ClearScreen();
        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        draw();

        renderer.end();

        if(changeScreen){
            game.setScreen(new GameScreenAiRandom(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }



    @Override
    public void hide() {
        //NOTE: SCREENS DON"T DISPOSE AUTOMATICLE, DISPOSE ON HIDE
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    // == private methods ===

    private void update(float delta){


        // progress betwen 0 and 1
        progress = assetManager.getProgress();

        // update returns true when all assets are loaded

        if(assetManager.update()){
            waitTime -= delta;

            if(waitTime <= 0){
                changeScreen = true;
            }
        }

    }

    private void draw(){

        float progressBarX = (GameConfig.HUD_WIDTH - PROGRESS_BAR_WIDTH) / 2f;
        float progressBarY = (GameConfig.HUD_HEIGTH - PROGRESS_BAR_HEIGTH) / 2f;

        renderer.rect(progressBarX, progressBarY,
                progress * PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGTH);
    }

    private static void waitMillis(long millis){
        try{
            Thread.sleep(millis);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
