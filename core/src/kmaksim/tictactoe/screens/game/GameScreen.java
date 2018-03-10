package kmaksim.tictactoe.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;

import kmaksim.tictactoe.MyGdxGametictactoe;
import kmaksim.tictactoe.config.GameConfig;
import kmaksim.tictactoe.screens.menuScreens.MenuScreen;

/**
 * Created by Maksim1 on 03.02.2018.
 */

public class GameScreen implements Screen {

    private MyGdxGametictactoe game;
    private AssetManager assetManager;

    private GameController controller;
    private GameRenderer renderer;



    public GameScreen(MyGdxGametictactoe game) {
        this.game = game;
        this.assetManager = game.getAssetManager();


    }

    @Override
    public void show() {
        controller = new GameController(game, renderer);
        renderer = new GameRenderer(game.getBatch(), assetManager, controller);




    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        renderer.render(delta);

        if(controller.checWin() && controller.getCooldown() < 0){

            setScreen();
        }

        if(controller.chekDraw() && controller.getCooldown() < 0){

            setScreen();
        }

    }

    public void setScreen(){
        game.setScreen(new MenuScreen(game));
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();

    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
