package kmaksim.tictactoe.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import kmaksim.tictactoe.MyGdxGametictactoe;
import kmaksim.tictactoe.assets.AssetDescriptors;
import kmaksim.tictactoe.common.Game_Manager;
import kmaksim.tictactoe.config.GameConfig;
import kmaksim.tictactoe.entity.AiBase;
import kmaksim.tictactoe.entity.AiEasy;
import kmaksim.tictactoe.entity.Background;
import kmaksim.tictactoe.entity.Board;
import kmaksim.tictactoe.entity.CellBase;
import kmaksim.tictactoe.entity.Cell_O;
import kmaksim.tictactoe.entity.Cell_X;
import kmaksim.tictactoe.entity.Player;

/**
 * Created by Maksim1 on 03.02.2018.
 */

public class GameController {

    private static final Logger log = new Logger(GameController.class.getName(), Logger.DEBUG);

    private float cooldown;

    private MyGdxGametictactoe game;
    private AssetManager assetManager;
    private GameRenderer renderer;
    private Viewport viewport;
    private Board board;
    private Player playerOne, playerTwo, currentPlayer, displayPlayer;
    private OrthographicCamera camera;


    private CellBase [] cells;
    private int count;
    private Sound clickX, clickO;
    private Music win, draw;
    private AiEasy aiEasy;
//    private Music musicTheme;


    private Background background;

    // == constructors ==

    public GameController(MyGdxGametictactoe game, GameRenderer renderer) {
        this.game = game;
        this.assetManager = game.getAssetManager();
        this.renderer = renderer;
        init();

    }

    // == init ==
    private void init() {

        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGTH, camera);

        clickO = assetManager.get(AssetDescriptors.O_SOUND);
        clickX = assetManager.get(AssetDescriptors.X_SOUND);

        win = assetManager.get(AssetDescriptors.WIN);
        draw = assetManager.get(AssetDescriptors.DRAW);

        // init board
        board = new Board();

//        musicTheme = assetManager.get(AssetDescriptors.THEME_MUSIC);


        playerOne = new Player(new Cell_X(), "X");
        playerTwo = new Player(new Cell_O(), "O");
        displayPlayer = playerOne;
        currentPlayer = changeCurrentPlayer();

        cooldown = GameConfig.RESTART_COOLDAWN;

        cells = board.getCells();

        aiEasy = new AiEasy(cells, new Cell_O());

        count = 0;



        // == background ==

        background = new Background();
        background.setPosition(0, 0);
        background.setSize(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGTH);




    }

    // == public methods ==

    public Background getBackground() {
        return background;
    }

    public void update(float delta) {
        playWinSound();
    }

    public void resetTime(float delta){
        cooldown -= delta;
    }

    public Board getBoard(){return board;}

    public CellBase[] getCells() {
        return cells;
    }



    public Player changeCurrentPlayer(){
        if(currentPlayer == playerOne){

            displayPlayer = playerOne;

            currentPlayer = playerTwo;

        } else {

            displayPlayer = playerTwo;

            currentPlayer = playerOne;

        }



        return currentPlayer;}

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public String getDisplayPlayer() {
        return displayPlayer.getName();
    }

    public void setCellByPlayer(int index, CellBase cell){

        countIncreaser();


        if(cell.getTag().contains("Cell_X")){

            cell = new Cell_X();
            if(Game_Manager.INSTANCE.soundCheked()) {
                clickX.play();
            }

        }else {
            cell = new Cell_O();
            if(Game_Manager.INSTANCE.soundCheked()) {
                clickO.play();
            }
        }

        float newX = cells[index].getX();
        float newY = cells[index].getY();

        cells[index] = cell;

        cells[index].setPosition(newX, newY);

    }

    public boolean checWin(){
        int xWin = -30;
        int oWin = 30;

        if(isWin(xWin)){
//            log.debug("x win");

            return true;

        }else if(isWin(oWin)){
//            log.debug("O win");

            return true;
        }



//        log.debug("NOPE");
        return  false;
    }

    public boolean chekDraw(){

        if(draw(count)){
//            log.debug("DRAW");
            if(Game_Manager.INSTANCE.soundCheked()) {
                draw.play();
            }
            return true;
        }

        return false;
    }

    public float getCooldown() {
        return cooldown;
    }

    public void aiMoveEasy(){

        aiEasy.setCells(cells);

        setCellByPlayer(aiEasy.easyMove(), aiEasy.getCell());

    }

    // == private methods ==

    private boolean isWin(int win) {

        win = win;

        for (int i = 0; i < 7; i += 3) {

            if (getCells()[i].getPoint() + getCells()[i + 1].getPoint() + getCells()[i + 2].getPoint() == win) {
//                log.debug("row");
                return true;
            }

        }

        for (int i = 0; i <= 2; i++) {
            if (getCells()[i].getPoint() + getCells()[i + 3].getPoint() + getCells()[i + 6].getPoint() == win) {
//                log.debug("col");
                return true;
            }
        }

        if(getCells()[0].getPoint() + getCells()[4].getPoint() + getCells()[8].getPoint() == win){
//            log.debug("Side from left to rigth");
            return true;
        }else if(getCells()[2].getPoint() + getCells()[4].getPoint() + getCells()[6].getPoint() == win){
//            log.debug("Side from rigth to left");
            return true;
        }



        return false;
    }

    private void countIncreaser(){
        count++;
    }

    private boolean draw(int counter){
        if(!checWin()) {
            if (counter == 9) {
                return true;
            }
        }
        return false;
    }

    private void  playWinSound(){
        if(checWin() == true){
            if(Game_Manager.INSTANCE.soundCheked()) {
                win.play();
            }
        }
    }

    private boolean isEmptyPlaceAvaible(){


        boolean isDraw = false;

        for(int i = 0; i < cells.length; i++){
            if(getCells()[i].getTag()== "Cell_null"){

                isDraw = false;
            }else{ isDraw = true;}

        }

        return isDraw;
    }

}