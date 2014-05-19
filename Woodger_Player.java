package maze;

import java.awt.Color;

public class Woodger_Player extends Player {

    public Woodger_Player () {
        super ("Woodger", new Color (0, 255, 0));
    }

    @Override
    public int getMove(int[][] area) {
        int[] nums = {1,2,2,2,3,3,3,4};
        //gets stuck far too easily
        //return nums[(int)(Math.random() * 8)];
        return (int) (Math.random() * 4) + 1;
    }
 }