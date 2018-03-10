package kmaksim.tictactoe.entity;

/**
 * Created by Maksim1 on 02.03.2018.
 */

public class Cell_X extends CellBase {

    private static final String TAG = Cell_X.class.getName();
    private int point = -10;

    public Cell_X() {

    }

    public int getPoint() {
        return point;
    }

    public String getTag(){
        return TAG;
    }
}
