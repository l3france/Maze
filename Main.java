package maze;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) throws IOException {
        //creates the window, adjusts variour attributes
        JFrame frame = new JFrame ("Maze Runner");
        frame.setSize (850, 558);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);
        
        Maze maze = new Maze ("maze_0.txt");
        maze.setDelay(5);
        
        //uncomment the following line for some cool visual effects
        maze.enableFloodFill();
        
        
        frame.add(maze);

        maze.playGame();
    }

}
