package kmaksim.tictactoe.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import kmaksim.tictactoe.MyGdxGametictactoe;
import kmaksim.tictactoe.assets.AssetDescriptors;
import kmaksim.tictactoe.assets.RegionNames;
import kmaksim.tictactoe.config.GameConfig;
import kmaksim.tictactoe.entity.Background;
import kmaksim.tictactoe.entity.Button;
import kmaksim.tictactoe.entity.CellBase;
import kmaksim.tictactoe.entity.Player;
import kmaksim.tictactoe.util.GdxUtils;
import kmaksim.tictactoe.util.ViewportUtils;
import kmaksim.tictactoe.util.debug.DebugCameraController;

/**
 * Created by Maksim1 on 01.03.2018.
 */

public class GameRenderer implements Disposable {

    private static final Logger log = new Logger(GameRenderer.class.getName(), Logger.DEBUG);

    // == attributes ==

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;
    private MyGdxGametictactoe game;

    private OrthographicCamera hudCamera;
    private Viewport hudViewport;

    private final SpriteBatch batch;
    private BitmapFont font;
    private DebugCameraController debugCameraController;
    private final AssetManager assetManager;
    private GameController controller;
    private TextureRegion backgroundRegion, x_Region, o_Region, nullRegion;
    private final GlyphLayout glyphLayout = new GlyphLayout();
    private Button backButton;


    public GameRenderer(SpriteBatch batch, AssetManager assetManager, GameController controller) {
        this.batch = batch;
        this.assetManager = assetManager;
        this.controller = controller;

        init();
    }


    // init
    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGTH, camera);
        renderer = new ShapeRenderer();

        hudCamera = new OrthographicCamera();
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGTH, hudCamera);


        font = assetManager.get(AssetDescriptors.FONT);

        TextureAtlas gamePlayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY);
        backgroundRegion = gamePlayAtlas.findRegion(RegionNames.BACKGROUND);

        x_Region = gamePlayAtlas.findRegion(RegionNames.PX);
        o_Region = gamePlayAtlas.findRegion(RegionNames.P0);
        nullRegion = gamePlayAtlas.findRegion(RegionNames.PNULL);

        backButton = new Button();


        font.getData().markupEnabled = true;
        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);


    }

    // == Public methods ==


    public void render(float delta) {

//Camera control is not in alive chek, cuz we wan't to have ability to controll camera
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        GdxUtils.ClearScreen();

        renderGameplay(delta);

        renderUi();

        if(!controller.checWin()) {
            checkTouch();
        }
    }

    public void renderAiRandom(float delta){
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        GdxUtils.ClearScreen();

        renderGameplay(delta);

        renderUi();

        if(!controller.checWin() && controller.getCurrentPlayer().getName().contains("X")) {
            checkTouch();

        }else if(!controller.checWin() && controller.getCurrentPlayer().getName().contains("O") && !controller.chekDraw()){
            boolean touched = false;
            controller.aiMoveEasy();
            controller.changeCurrentPlayer();
            touched = true;
        }
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
        ViewportUtils.debugPixelPerUnit(viewport);
    }


    @Override
    public void dispose() {

    }

    // == private methods ==

    private void renderDebug(float delta) {

        ViewportUtils.drawGrid(viewport, renderer);
    }


    private void renderGameplay(float delta) {
        viewport.apply();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // == draw backgrounds ==

        Background background = controller.getBackground();

        batch.draw(backgroundRegion,
                background.getX(), background.getY(),
                background.getWidth(), background.getHeigth());

        // == draw buttons ==


        for (CellBase cell : controller.getCells()) {
            TextureRegion textureRegion;
            if (cell.getTag().contains("Cell_X")) {
                textureRegion = x_Region;
            } else if (cell.getTag().contains("Cell_O")) {
                textureRegion = o_Region;
            } else
                textureRegion = nullRegion;

            batch.draw(textureRegion,
                    cell.getX(), cell.getY(),
                    cell.getWidth(), cell.getHeight());
        }
        batch.end();
    }

    private void renderUi() {
        hudViewport.apply();
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();

        String currentPlayer = "Current Player: " + controller.getCurrentPlayer().getName();
        glyphLayout.setText(font, currentPlayer);

        font.draw(batch, currentPlayer,
                10,
                GameConfig.HUD_HEIGTH - 680
        );

        if (controller.checWin()) {

            String winPlayer = "Win of player: " + controller.getDisplayPlayer();
            glyphLayout.setText(font, winPlayer);
            Color oldColor = font.getColor().cpy();
            float oldx= font.getScaleX();
            float oldy = font.getScaleY();
            font.getData().setScale(1.5f, 1.5f);
            font.setColor(Color.RED);
            font.draw(batch, winPlayer,
                    20,
                    GameConfig.HUD_HEIGTH - 200

            );


            String restartingIn = "Back to menu: " + MathUtils.round(controller.getCooldown());
            glyphLayout.setText(font, restartingIn);
            font.setColor(Color.BLUE);
            font.draw(batch, restartingIn,
                    20,
                    GameConfig.HUD_HEIGTH - 450

            );
            font.getData().setScale(oldx, oldy);
            controller.resetTime(Gdx.graphics.getDeltaTime());
            font.setColor(oldColor);
        }

        if(controller.chekDraw()){
            String draw = "DRAW";
            glyphLayout.setText(font, draw);
            Color oldColor = font.getColor().cpy();
            float oldx= font.getScaleX();
            float oldy = font.getScaleY();
            font.getData().setScale(1.5f, 1.5f);
            font.setColor(Color.RED);
            font.draw(batch, draw,
                    160,
                    GameConfig.HUD_HEIGTH - 200

            );


            String restartingIn = "Back to menu: " + MathUtils.round(controller.getCooldown());
            glyphLayout.setText(font, restartingIn);
            font.setColor(Color.BLUE);
            font.draw(batch, restartingIn,
                    20,
                    GameConfig.HUD_HEIGTH - 450

            );
            font.getData().setScale(oldx, oldy);
            controller.resetTime(Gdx.graphics.getDeltaTime());
            font.setColor(oldColor);
        }


        batch.end();


    }

    public void PlayerMove(){
        if(controller.getCurrentPlayer().getName().contains("X")){

        }
    }

    private void checkTouch() {

        if (Gdx.input.isTouched()) {

            Vector2 screenTouch = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            Vector2 worldTouch = viewport.unproject(new Vector2(screenTouch));
            System.out.println("screeTouch= " + screenTouch);
            System.out.println("worldTouch= " + worldTouch);


            boolean touched = false;

            log.debug("state" + touched);


            for (int index = 0; index < controller.getCells().length; index++) {

                if (worldTouch.x > controller.getCells()[index].getX() &&
                        worldTouch.x < controller.getCells()[index].getX() + GameConfig.BUTTON_WIDTH &&
                        touched == false && controller.getCells()[index].getTag().contains("Cell_null")) {

                    if (worldTouch.y > controller.getCells()[index].getY() &&
                            worldTouch.y < controller.getCells()[index].getY() + GameConfig.BUTTON_WIDTH && touched == false && controller.getBoard().getCells()[index].getTag().contains("Cell_null")) {
                        controller.setCellByPlayer(index, controller.getCurrentPlayer().getButton());
                        controller.changeCurrentPlayer();
                        controller.debugCells();
                        touched = true;

                    }

                }
            }
        }

    }

}
