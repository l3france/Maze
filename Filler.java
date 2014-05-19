package maze;

import java.io.*;


public class Filler {
    static int[][] bigMaze;
    //This method is the driver of the entire class. It is called with a String parameter that is simply a filepath. That filepath must lead to a valid maze document in a text file.
    //It will return an int[][] that is filled with number that represent the distance from that tile to the treasure.
    public static int[][] floodFill(String filePath) throws FileNotFoundException, IOException {
        bigMaze = hashToInt(readMaze(filePath));
        int x = 0;
        int y = 0;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (bigMaze[j][i] == 0) {
                    x = j;
                    y = i;
                }
            }
        }
        int[] paths = getPaths(x, y);
        

        for (int i = 0; i < paths.length; i++) {
            if (paths[i] == 1) {
                follow(x + 1, y, x, y);
            }
            if (paths[i] == 2) {
                follow(x, y + 1, x, y);
            }
            if (paths[i] == 3) {
                follow(x - 1, y, x, y);
            }
            if (paths[i] == 4) {
                follow(x, y - 1, x, y);
            }
        }
        return bigMaze;


    }
    public static int[][] refill(int[][] field) throws FileNotFoundException, IOException {
        bigMaze = field;
        for(int i = 0; i < 50;i++) {
            for(int j = 0; j < 50;j++) {
                if(bigMaze[j][i] != 0 && bigMaze[j][i] != -1) {
                    bigMaze[j][i] = -2;
                }
            }
        }
        int x = 0;
        int y = 0;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (bigMaze[j][i] == 0) {
                    x = j;
                    y = i;
                }
            }
        }
        int[] paths = getPaths(x, y);


        for (int i = 0; i < paths.length; i++) {
            if (paths[i] == 1) {
                follow(x + 1, y, x, y);
            }
            if (paths[i] == 2) {
                follow(x, y + 1, x, y);
            }
            if (paths[i] == 3) {
                follow(x - 1, y, x, y);
            }
            if (paths[i] == 4) {
                follow(x, y - 1, x, y);
            }
        }
        return bigMaze;


    }
    // this is a method that is used recursively. This method is called with parameters of the treasures position int x,y form. It then counts up and sets the x,y coords to that number.
    //It calls itself with the x,y parameters of all bordering, unfilled tiles. This will branch off until there are no more squares left. 
    public static void follow(int x, int y, int xold, int yold) {

        bigMaze[x][y] = bigMaze[xold][yold] + 1;
        if (getPaths(x, y).length == 0) {
        } else {
            int[] paths = getPaths(x, y);
            for (int i = 0; i < paths.length; i++) {
                if (paths[i] == 1) {
                    follow(x + 1, y, x, y);
                }
                if (paths[i] == 2) {
                    follow(x, y + 1, x, y);
                }
                if (paths[i] == 3) {
                    follow(x - 1, y, x, y);
                }
                if (paths[i] == 4) {
                    follow(x, y - 1, x, y);
                }

            }
        }
    }
    // returns possible paths that one could take at a point in the maze.
    public static int[] getPaths(int x, int y) {
        int[] paths = new int[4];
        int j = 0;
        if (bigMaze[x + 1][y] == -2) {
            paths[j] = 1;
            j++;
        }

        if (bigMaze[x][y + 1] == -2) {
            paths[j] = 2;
            j++;
        }

        if (bigMaze[x - 1][y] == -2) {
            paths[j] = 3;
            j++;
        }

        if (bigMaze[x][y - 1] == -2) {
            paths[j] = 4;

        }

        int[] paths2 = new int[j + 1];
        for (int i = 0; i < paths2.length; i++) {
            paths2[i] = paths[i];
        }
        return paths2;
    }
    //This returns the value of the square that was previously filled by the recursive algorithm so that the bordering tile can be made 1 higher.
    public static int getBack(int x, int y) {
        if (bigMaze[x + 1][y] != -2 && bigMaze[x + 1][y] != -1) {
            return bigMaze[x + 1][y];
        }

        if (bigMaze[x][y + 1] != -2 && bigMaze[x + 1][y] != -1) {
            return bigMaze[x][y + 1];
        }

        if (bigMaze[x - 1][y] != -2 && bigMaze[x + 1][y] != -1) {
            return bigMaze[x - 1][y];
        }

        if (bigMaze[x][y - 1] != -2 && bigMaze[x + 1][y] != -1) {
            return bigMaze[x][y - 1];
        }
        return 0;
    }
    //converts the String array of hash tags and spaces into int[][] format
    public static int[][] hashToInt(String[][] maze) {
        int[][] newMaze = new int[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (maze[j][i].equals("#")) {
                    newMaze[j][i] = -1;
                }
                if (maze[j][i].equals(" ") || maze[j][i].equals("@")) {
                    newMaze[j][i] = -2;
                }
                if (maze[j][i].equals("$")) {
                    newMaze[j][i] = 0;
                }
            }
        }
        return newMaze;
    }
    //This reads the characters of a maze out of a text file. 
    public static String[][] readMaze(String fileName) throws FileNotFoundException, IOException {
        String[][] maze = new String[50][50];

        BufferedReader bufferedReader = new BufferedReader(new FileReader (fileName));
        String line = "";
        int j = 0;
        while ((line = bufferedReader.readLine()) != null) {
            for (int i = 0; i < 50; i++) {
                maze[i][j] = line.substring(i, i + 1);
            }
            j++;

        }

        bufferedReader.close();
        return maze;
    }
}
