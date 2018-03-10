package kmaksim.tictactoe.entity;

/**
 * Created by Maksim1 on 02.03.2018.
 */

public class Background {
    private float x;
    private float y;
    private float width;
    private float heigth;

    public Background() {
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void setSize(float width, float heigth){
        this.width = width;
        this.heigth = heigth;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeigth() {
        return heigth;
    }
}
