package kmaksim.tictactoe.entity;

import kmaksim.tictactoe.config.GameConfig;
import kmaksim.tictactoe.screens.game.GameController;

/**
 * Created by Maksim1 on 10.03.2018.
 */

public abstract class AiBase {

    // == atributs ==
    protected int cellsCount = GameConfig.CELLS_COUNT;

    protected CellBase [] cells;

    protected CellBase aiPlayer;
    protected CellBase humanPlayer;

    // == constructors ==

    public AiBase(){

    }

    // == public methods ==

    abstract int easyMove();

    // == private methods ==
}
