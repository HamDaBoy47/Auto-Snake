import java.util.*;

public class BFS {

    int length = 50;
    int width = 50;

    int min_dist = 1000;

    int[][] visited = new int[length][width];
    Pair[][] parents = new Pair[length][width];

    Queue<Pair> moves = new LinkedList<Pair>();

    Queue<Pair> path = new LinkedList<Pair>();

    int rows[] = {1, 0, 0, -1};
    int cols[] = {0, -1, 1, 0};

    public Pair start;
    public Pair goal;

    void VisitedMaze() {
        for (int col = 0; col < length; ++col) {
            for (int row = 0; row < width; ++row) {
                visited[col][row] = -1;
            }
        }
    }

    void ParentMaze() {
        for (int col = 0; col < length; ++col) {
            for (int row = 0; row < width; ++row) {
                parents[col][row] = new Pair(-2, -2);
            }
        }
    }

    //REMEMBER TO CHANGE -4 TO -3
    Boolean isValid(int[][] board, int row, int col) {
        if (row != -1 && row != length && col != -1 && col != width) {
            if ((board[col][row] == 0 || board[col][row] == -4) && visited[col][row] == -1) {
                return true;
            }
        }
        return false;
    }


    public int Solve(int[][] board) {
        int R = start.x;
        int C = start.y;

        visited[C][R] = 0;
        moves.add(start);

        while (moves.size() != 0) {
            Pair temp;
            temp = moves.peek();
            moves.remove();

            int r = temp.x, c = temp.y;

            if (r == goal.x && c == goal.y) {
                min_dist = visited[c][r];
                break;
            }

            for (int k = 0; k < 4; k++) {
                if (r <= (length - 1) && c <= (width - 1) && r >= 0 && c >= 0) {
                    if (isValid(board, r + rows[k], c + cols[k])) {
                        visited[c + cols[k]][r + rows[k]] = visited[c][r] + 1;
                        parents[c + cols[k]][r + rows[k]] = temp;
                        moves.add(new Pair(r + rows[k], c + cols[k]));
                    }
                }
            }
        }

        if (min_dist != 1000) {
            return min_dist;
        }

        return -1;
    }

    public int PathGen(int[][] board) {
        path.add(goal);

        for (int i = 0; i < min_dist; ++i) {
            Pair temp;
            temp = path.peek();
            path.remove();

            int r = temp.x, c = temp.y;

            path.add(parents[c][r]);

            if (i != 0) board[c][r] = -5;
        }

        int r = goal.x, c = goal.y;
        Pair temp = parents[c][r];

        if (temp.x == r) {
            if (temp.y < c) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (temp.x < r) {
                return 2;
            } else {
                return 3;
            }
        }
    }
}

