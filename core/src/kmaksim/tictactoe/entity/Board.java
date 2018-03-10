package kmaksim.tictactoe.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;

import kmaksim.tictactoe.config.GameConfig;

/**
 * Created by Maksim1 on 03.03.2018.
 */

public class Board {



    private static final Logger log = new Logger(Board.class.getName(), Logger.DEBUG);

//    private Array<CellBase> cells = new Array<CellBase>(9);

    private CellBase[] cells;


    public Board() {

        init();
    }

    // == init ==
    private void init(){



        cells = new CellBase[9];


            for(int i = 0; i <= 8; i++) {

                cells[i] = new Cell_null();
            }

            setCellsPositions();

    }

    public void changeCell(){
//        log.debug("changeCell");
        float newX = cells[1].getX();
        float newY = cells[1].getY();
        cells[1] = new Cell_X();
        cells[1].setPosition(newX, newY);

        newX = cells[2].getX();
        newY = cells[2].getY();

        cells[2] = new Cell_O();
        cells[2].setPosition(newX, newY);
    }



    public void setCellsPositions(){
        cells[0].setPosition(0.2f,2);
        cells[1].setPosition(2.2f,2);
        cells[2].setPosition(4.2f,2);

        cells[3].setPosition(0.2f,5);
        cells[4].setPosition(2.2f,5);
        cells[5].setPosition(4.2f,5);

        cells[6].setPosition(0.2f,8);
        cells[7].setPosition(2.2f,8);
        cells[8].setPosition(4.2f,8);

    }


    public void searchHit(SpriteBatch batch, TextureRegion textureRegion){

        }

        public CellBase[] getCells(){
            return cells;
        }


}



