package kmaksim.tictactoe.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Logger;

import kmaksim.tictactoe.screens.game.GameController;
import kmaksim.tictactoe.screens.game.GameRenderer;

/**
 * Created by Maksim1 on 10.03.2018.
 */

public class AiEasy extends AiBase {

    private static final Logger log = new Logger(AiEasy.class.getName(), Logger.DEBUG);

    // == atributse ==

    private Board board;
    private CellBase [] cells;
    private CellBase cell;
    private GameController controller;

    int move = 0;


    // == constructors ==

    public AiEasy( CellBase [] cells, CellBase cell) {
        this.board = board;
        this.cells = cells;
        this.cell = cell;
    }



    // == public methods ==


    public void setCells(CellBase [] cells){
        this.cells = cells;
    }

    @Override
    public int easyMove() {

        move = MathUtils.random(0, 8);

        if(cells[move].getTag().contains("Cell_null")){
            log.debug("Return point " + cells[move].getPoint());
            log.debug("random number " + move);
            return move;
        }else {
            easyMove();
        }

        return move;
    }
//
//    public int easyMove(){
//
//
//        return 0;
//    }

    public CellBase getCell() {
        return cell;
    }

    // == private methods ==

    private int makeEasyMove(){
        int rand = MathUtils.random(0, 8);
        return rand;
    }

}
