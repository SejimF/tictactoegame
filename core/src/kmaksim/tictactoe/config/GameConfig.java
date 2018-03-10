package kmaksim.tictactoe.config;

/**
 * Created by Maksim1 on 03.02.2018.
 */

public class GameConfig {

    public static final String GAME_NAME = "tictactoe";

    public static final float WIDTH = 480f;
    public static final float HEIGTH = 800f;

    public static final float HUD_WIDTH = 480f;  //world unit
    public static final float HUD_HEIGTH = 800f;  //world unit

    public static final float WORLD_WIDTH = 6.0f; //world unit
    public static final float WORLD_HEIGTH = 10.0f;

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2;
    public static final float WORLD_CENTER_Y = WORLD_HEIGTH / 2;

    public static final float BUTTON_WIDTH = 1.5f;
    public static final float BUTTON_HEIGTH = 1.5f;

    public static final float RESTART_COOLDAWN = 5f;

    public static final int CELLS_COUNT = 9;


    private GameConfig() {
    }
}
