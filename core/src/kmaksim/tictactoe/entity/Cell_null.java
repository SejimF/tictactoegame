package kmaksim.tictactoe.entity;

/**
 * Created by Maksim1 on 02.03.2018.
 */

public class Cell_null extends CellBase {

    private static final String TAG = Cell_null.class.getName();
    private int point = 0;

    public Cell_null() {

}

    public int getPoint() {
        return point;
    }

    public String getTag(){
        return TAG;
    }

}
