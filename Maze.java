package maze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JComponent;

public class Maze extends JComponent {

    private int[][] field;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Player> finished = new ArrayList<Player>();
    private ArrayList<Point> pos = new ArrayList<Point>();
    private ArrayList<Integer> moves = new ArrayList<Integer>();
    private int delay = 10;
    private Point tPos = new Point();
    private Treasure gold;
    private int offset = 0;

    public Maze(String filename) throws IOException {
        field = Filler.floodFill(filename);
        gold = new Treasure(Filler.floodFill(filename));
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (field[j][i] == 0) {
                    tPos = new Point(j, i);
                }
            }
        }
        players.add (new Franceschini_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        players.add(new Woodger_Player());
        
        
        
        
        
        

        for (int i = 0; i < players.size(); i = i + 1) {
            pos.add(new Point(1, 1));
        }

        //graphical settings
        this.setBounds(10, 10, 800, 500);
        this.setLocation(10, 10);
    }

    public void playGame() throws IOException {

        for (int i = 0; i < 1000000; i++) {
            for (int k = 0; k < players.size(); k++) {
                doMove(k);
                if (((int) pos.get(k).getX() == tPos.x) && ((int) pos.get(k).getY() == tPos.y)) {
                    System.out.println(players.get(k).getName() + " has finished the maze!");
                    finished.add(players.get(k));
                    players.remove(k);
                    pos.remove(k);
                    moves.add(i + 1);
                    k = k - 1;
                }
            }
            run();
            for (int k = 0; k < players.size(); k++) {
                if (((int) pos.get(k).getX() == tPos.x) && ((int) pos.get(k).getY() == tPos.y)) {
                    System.out.println(players.get(k).getName() + " has finished the maze!");
                    finished.add(players.get(k));
                    players.remove(k);
                    pos.remove(k);
                    moves.add(i + 1);
                }
            }
            field = Filler.refill(field);




            //update the output
            this.repaint();

            //stop if everyone got to the treasure
            if (players.isEmpty()) {
                System.out.println("\nEveryone found the treasure!");
                break;
            }

            //stop after too many turns
            if (i == 999999) {
                System.out.println("\nEveryone else took too long!");
            }

            //take a break
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
        }
    }

    private void run() {
        int move = gold.getMove(pos);

        if (isValidRun(move)) {
            field[tPos.x][tPos.y] = 1;
            if (move == 1) {
                tPos = new Point(tPos.x, tPos.y - 1);
                field[tPos.x][tPos.y] = 0;
            } else if (move == 2) {
                tPos = new Point(tPos.x + 1, tPos.y);
                field[tPos.x][tPos.y] = 0;
            } else if (move == 3) {
                tPos = new Point(tPos.x, tPos.y + 1);
                field[tPos.x][tPos.y] = 0;
            } else if (move == 4) {
                tPos = new Point(tPos.x - 1, tPos.y);
                field[tPos.x][tPos.y] = 0;
            }
        }


    }

    private void doMove(int playNum) {
        int x = (int) pos.get(playNum).getX();
        int y = (int) pos.get(playNum).getY();

        //gets the area around the player
        int[][] area = new int[5][5];
        for (int i = 0; i < 5; i = i + 1) {
            for (int j = 0; j < 5; j = j + 1) {
                try {
                    if (field[x - 2 + j][y - 2 + i] == -1) {
                        area[j][i] = 1;
                    } else if (field[x - 2 + j][y - 2 + i] == 0) {
                        area[j][i] = 2;
                    } else {
                        area[j][i] = 0;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    area[j][i] = 1;
                }
            }
        }
        //check if their move is valid, if so, update their position
        int move = players.get(playNum).getMove(area);
        if (move == -1) {
            System.out.println(players.get(playNum).getName() + " did not override the getMove() method.");
        } else if (this.isValidMove(playNum, move)) {
            if (move == 1) {
                pos.get(playNum).setLocation(x, y - 1);
            }
            if (move == 2) {
                pos.get(playNum).setLocation(x + 1, y);
            }
            if (move == 3) {
                pos.get(playNum).setLocation(x, y + 1);
            }
            if (move == 4) {
                pos.get(playNum).setLocation(x - 1, y);
            }
        }
    }

    //used to find the flood filled distance from the treasure
    public int getDist(int playNum) {
        int x = (int) pos.get(playNum).getX();
        int y = (int) pos.get(playNum).getY();
        return field[x][y];
    }

    //     1
    //     ^
    //     |
    //  4<-@->2
    //     |
    //     v
    //     3
    public boolean isValidMove(int playNum, int a) {
        int x = (int) pos.get(playNum).getX();
        int y = (int) pos.get(playNum).getY();

        if (a == 1) {
            return field[x][y - 1] != -1;
        }
        if (a == 2) {
            return field[x + 1][y] != -1;
        }
        if (a == 3) {
            return field[x][y + 1] != -1;
        }
        if (a == 4) {
            return field[x - 1][y] != -1;
        }
        return false;
    }

    public boolean isValidRun(int a) {
        int x = tPos.x;
        int y = tPos.y;

        if (a == 1) {
            return field[x][y - 1] != -1;
        }
        if (a == 2) {
            return field[x + 1][y] != -1;
        }
        if (a == 3) {
            return field[x][y + 1] != -1;
        }
        if (a == 4) {
            return field[x - 1][y] != -1;
        }
        return false;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public void paintComponent(Graphics g) {
        paintMaze(g);
        paintFloodFill(g);
        paintPlayers(g);
        paintScoreBoard(g);

    }

    private void paintMaze(Graphics g) {
        //paint the maze
        g.setColor(Color.BLACK);

        for (int y = 0; y < field.length; y = y + 1) {
            for (int x = 0; x < field[y].length; x = x + 1) {
                if (field[x][y] == -1) {                                        //draw the walls
                    g.fillRect(x * 10 + offset, y * 10 + offset, 10, 10);
                } else if (field[x][y] == 0) {                                  //draw the treasure
                    g.setColor(Color.MAGENTA);
                    g.fillRect(x * 10 + offset, y * 10 + offset, 10, 10);
                    g.setColor(Color.YELLOW);
                    g.fillRect(x * 10 + offset + 2, y * 10 + offset + 2, 6, 6);
                    g.setColor(Color.BLACK);
                }

                /*
                 * //shows the flood fill effects else { g.setColor(new Color
                 * (0, 0, Math.min (255, field[x][y]))); g.fillRect(x * 10 + 10,
                 * y * 10 + 10, 10, 10); g.setColor (Color.BLACK); }
                 */
            }
        }


    }

    public void paintPlayers(Graphics g) {

        //paint the players in the maze
        for (int i = 0; i < players.size(); i = i + 1) {
            g.setColor(players.get(i).getColour());
            g.fillRect((int) pos.get(i).getX() * 10 + offset, (int) pos.get(i).getY() * 10 + offset, 10, 10);
        }
    }

    public void paintScoreBoard(Graphics g) {

        //paint the people still running the maze
        if (!players.isEmpty()) {
            g.setColor(Color.BLACK);
            g.drawString("Scoreboard", 550, 10);
            for (int i = 0; i < players.size(); i = i + 1) {
                //player colour
                g.setColor(players.get(i).getColour());
                g.fillRect(520, i * 20 + 25, 10, 10);

                //distance from treasure
                //just a temporary placeholder, will be changed to red-green scale
                //g.setColor(new Color(0, 0, Math.min(255, Math.max(0, 255 - this.field[(int) pos.get(i).getX()][(int) pos.get(i).getY()] * 2))));
                int distance = getDist(i);
                g.setColor(new Color(Math.max(0, Math.min(255, distance)), Math.max(0, Math.min(255, 255 - distance + 127)), 0));
                g.fillRect(540, i * 20 + 25, 10, 10);

                //print the player name
                g.setColor(Color.BLACK);
                g.drawString(players.get(i).getName(), 560, i * 20 + 35);
            }
        }

        //paint the people who have completed the maze, in order of completion
        //150 pixels over from the scoreboard
        if (!finished.isEmpty()) {
            for (int i = 0; i < finished.size(); i = i + 1) {
                g.drawString("Results", 680, 10);
                g.setColor(finished.get(i).getColour());
                g.fillRect(670, i * 20 + 25, 10, 10);
                g.setColor(Color.BLACK);
                g.drawString(finished.get(i).getName() + " " + moves.get(i).toString(), 690, i * 20 + 35);

            }
        }
    }
    //shows the floodfilling effect from the treasure

    public void paintFloodFill(Graphics g) {
        for (int y = 0; y < field.length; y = y + 1) {
            for (int x = 0; x < field[y].length; x = x + 1) {
                if (this.field[x][y] > 0) {
                    g.setColor(new Color(Math.max(0, Math.min(255 - (1 * field[x][y]), 255)), 0, 0));
                    g.fillRect(x * 10 + offset, y * 10 + offset, 10, 10);
                }
            }
        }
    }
}
