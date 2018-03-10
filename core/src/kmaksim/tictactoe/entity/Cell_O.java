package kmaksim.tictactoe.entity;

/**
 * Created by Maksim1 on 03.03.2018.
 */

public class Cell_O extends CellBase {

    private static final String TAG = Cell_O.class.getName();
    private int point = 10;

    public Cell_O() {

    }

    public int getPoint() {
        return point;
    }

    public String getTag(){
        return TAG;
    }
}
