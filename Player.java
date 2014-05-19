package maze;

import java.awt.Color;

abstract public class Player {

    private String name;
    private Color colour;

    public Player () {
        this.name = "Unknown";
        this.colour = new Color (127, 127, 127);
    }

    public Player (String name, Color colour){
        this.name = name;
        this.colour = colour;
    }

    public int getMove(int[][] surroundings){
        return -1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        //make the names less than 16 characters long
        if (name.length() > 16) {
            name = name.substring(0, 16);
        }
        this.name = name;
    }

    public Color getColour() {
        return this.colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }
}
