package kmaksim.tictactoe.entity;

/**
 * Created by Maksim1 on 03.03.2018.
 */

public class Player {

    private CellBase button;
    private String name;


    public Player(CellBase button, String name) {
        this.button = button;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public CellBase getButton() {
        return button;
    }
}
