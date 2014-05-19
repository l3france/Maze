package maze;

import java.awt.Color;
import java.util.ArrayList;

public class Franceschini_Player extends Player {

    public Franceschini_Player () {
        super ("Franceschini", new Color (0, 0, 255));
    }

    public Franceschini_Player (String name, Color colour) {
        super (name, colour);
    }

    @Override
    public int getMove(int[][] surroundings) {
        ArrayList<Integer> num = new ArrayList<Integer>();
        if (surroundings[2][1] == 0) {
            num.add(1);
        }
        if (surroundings[3][2] == 0) {
            num.add(2);
        }
        if (surroundings[2][3] == 0) {
            num.add(3);
        }
        if (surroundings[1][2] == 0) {
            num.add(4);
        }

        //do if next to treasure
        if (surroundings[2][1] == 2) {
            return 1;
        }
        if (surroundings[3][2] == 2) {
            return 2;
        }
        if (surroundings[2][3] == 2) {
            return 3;
        }
        if (surroundings[1][2] == 2) {
            return 4;
        }

        return num.get ((int) (Math.random() * num.size()));
    }

}
