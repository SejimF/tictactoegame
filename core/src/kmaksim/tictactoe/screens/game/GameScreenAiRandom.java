package kmaksim.tictactoe.screens.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;

import kmaksim.tictactoe.MyGdxGametictactoe;
import kmaksim.tictactoe.screens.menuScreens.MenuScreen;

/**
 * Created by Maksim1 on 10.03.2018.
 */

public class GameScreenAiRandom implements Screen{

    private MyGdxGametictactoe game;
    private AssetManager assetManager;

    private GameController controller;
    private GameRenderer renderer;



    public GameScreenAiRandom(MyGdxGametictactoe game) {
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

        renderer.renderAiRandom(delta);
        controller.update(delta);

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
