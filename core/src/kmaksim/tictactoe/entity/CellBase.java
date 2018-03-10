package kmaksim.tictactoe.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import kmaksim.tictactoe.config.GameConfig;

/**
 * Created by Maksim1 on 02.03.2018.
 */

public class CellBase extends Actor{

    private static final String TAG = CellBase.class.getName();
    private int point;

    public int getPoint() {
        return point;
    }

    private float pos_x;
    private float pos_y;
    private float width = GameConfig.BUTTON_WIDTH;
    private float height = GameConfig.BUTTON_HEIGTH;


    public CellBase() {
    }

    public void setPosition(float x, float y){
        pos_x = x;
        pos_y = y;
    }

    public float getX() {
        return pos_x;
    }


    public float getY() {
        return pos_y;
    }



    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getTag(){
        return TAG;
    }
}


