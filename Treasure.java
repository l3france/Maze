package maze;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Nathan
 */
public class Treasure {

    int[][] mazer = new int[50][50];
    int lastMove = 0;
    int x = 0;
    int y = 0;

    public Treasure(int[][] maze1) {
        mazer = maze1;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {

                if (mazer[j][i] == 0) {
                    x = j;
                    y = i;

                }



            }
        }

    }

    public int getMove(ArrayList<Point> pos) {

        if (almostCaught(pos)) {
            int move = getDirect(pos);
            if (move != -1) {
                System.out.println("agagagagagag " + move);
                lastMove = move;

                if (move == 1) {
                    y--;
                }
                if (move == 2) {
                    x++;
                }
                if (move == 3) {
                    y++;
                }
                if (move == 4) {
                    x--;
                }
                return move;
            }
        }

        int[][] surrounding = new int[3][3];

        for (int i = y - 1; i < y + 2; i++) {
            for (int j = x - 1; j < x + 2; j++) {
                surrounding[j - (x - 1)][i - (y - 1)] = mazer[j][i];
            }
        }

        if (!isDeadEnd(surrounding)) {
            boolean isGood = true;
            int move = 0;
            while (isGood) {
                move = (int) (Math.random() * 4) + 1;
                if (move != getOpp(lastMove) && !isWall(move)) {
                    isGood = false;
                }
            }
            lastMove = move;

            if (move == 1) {
                y--;
            }
            if (move == 2) {
                x++;
            }
            if (move == 3) {
                y++;
            }
            if (move == 4) {
                x--;
            }

            return move;
        } else {
            int move = getEscape(surrounding);
            lastMove = move;

            if (move == 1) {
                y--;
            }
            if (move == 2) {
                x++;
            }
            if (move == 3) {
                y++;
            }
            if (move == 4) {
                x--;
            }
            return move;
        }




    }

    public boolean almostCaught(ArrayList<Point> pos) {
        for (int i = 0; i < pos.size(); i++) {
            if (pos.get(i).x - x == 0 && (pos.get(i).y - y <= 2 || pos.get(i).y - y >= -2)) {
                return true;
            }
            if (pos.get(i).y - y == 0 && (pos.get(i).x - x <= 2 || pos.get(i).x - x >= -2)) {
                return true;
            }

        }
        return false;
    }

    public int getDirect(ArrayList<Point> pos) {
        for (int i = 0; i < pos.size(); i++) {
            if (pos.get(i).x == x && pos.get(i).y == y - 1) {
                if (!isWall(3)) {
                    return 3;
                }
            }
            if (pos.get(i).x == x && pos.get(i).y == y + 1) {
                if (!isWall(1)) {
                    return 1;
                }
            }
            if (pos.get(i).x == x - 1 && pos.get(i).y == y) {
                if (!isWall(2)) {
                    return 2;
                }
            }
            if (pos.get(i).x == x + 1 && pos.get(i).y == y) {
                if (!isWall(4)) {
                    return 4;
                }
            }
            if (pos.get(i).x == x && pos.get(i).y == y - 2) {
                if (!isWall(3)) {
                    return 3;
                }
            }
            if (pos.get(i).x == x && pos.get(i).y == y + 2) {
                if (!isWall(1)) {
                    return 1;
                }
            }
            if (pos.get(i).x == x - 2 && pos.get(i).y == y) {
                if (!isWall(2)) {
                    return 2;
                }
            }
            if (pos.get(i).x == x + 2 && pos.get(i).y == y) {
                if (!isWall(4)) {
                    return 4;
                }
            }
        }
        return -1;
    }

    public int getEscape(int[][] a) {
        if (a[1][0] != -1) {
            return 1;
        } else if (a[0][1] != -1) {
            return 4;
        } else if (a[1][2] != -1) {
            return 3;
        } else if (a[2][1] != -1) {
            return 2;
        }
        return 1;

    }

    public boolean isDeadEnd(int[][] a) {
        int count = 0;
        if (a[1][0] == -1) {
            count++;
        }
        if (a[2][1] == -1) {
            count++;
        }
        if (a[0][1] == -1) {
            count++;
        }
        if (a[1][2] == -1) {
            count++;
        }
        if (count == 3) {
            return true;
        }
        return false;
    }

    public boolean isWall(int a) {
        if (a == 1) {
            if (mazer[x][y - 1] == -1) {
                return true;
            }
        }
        if (a == 2) {
            if (mazer[x + 1][y] == -1) {
                return true;
            }
        }
        if (a == 3) {
            if (mazer[x][y + 1] == -1) {
                return true;
            }
        }
        if (a == 4) {
            if (mazer[x - 1][y] == -1) {
                return true;
            }
        }
        return false;
    }

    public int getOpp(int a) {
        if (a == 1) {
            return 3;
        } else if (a == 2) {
            return 4;
        } else if (a == 3) {
            return 1;
        } else {
            return 2;
        }
    }
}
