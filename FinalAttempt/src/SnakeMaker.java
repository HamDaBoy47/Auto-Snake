public class SnakeMaker {
    public static void drawLine(int[][] board, String p1, String p2, int snakeNo) {

        String[] new_p1 = p1.split(",");
        String[] new_p2 = p2.split(",");

        int p1_x = Integer.parseInt(new_p1[0]);
        int p1_y = Integer.parseInt(new_p1[1]);
        int p2_x = Integer.parseInt(new_p2[0]);
        int p2_y = Integer.parseInt(new_p2[1]);

        if (p1_x == p2_x) {
            if (p1_y < p2_y) {
                for (int y = p1_y; y <= p2_y; ++y) {
                    board[y][p1_x] = snakeNo;
                    zombieBuffer(board,snakeNo,p1_x,y);
                }

            } else {
                for (int y = p2_y; y <= p1_y; ++y) {
                    board[y][p1_x] = snakeNo;
                    zombieBuffer(board,snakeNo,p1_x,y);
                }
            }
        } else {
            if (p1_x < p2_x) {
                for (int x = p1_x; x <= p2_x; ++x) {
                    board[p1_y][x] = snakeNo;
                    zombieBuffer(board,snakeNo,x,p1_y);
                }

            } else {
                for (int x = p2_x; x <= p1_x; ++x) {
                    board[p1_y][x] = snakeNo;
                    zombieBuffer(board,snakeNo,x,p1_y);
                }
            }
        }
    }

    public static void zombieBuffer(int[][] board, int snakeNo, int r, int c) {
        int rows[] = {1, 0, 0, -1};
        int cols[] = {0, -1, 1, 0};

        if (snakeNo == -1) {
            for (int k = 0; k < 4; k++) {
                int C = c + cols[k];
                int R = r + rows[k];

                if (R < (50 - 1) && C < (50 - 1) && R > 0 && C > 0) {
                    board[C][R] = -1;
                }
            }
        }
    }

    public static void drawSnake(String snake, int snakeNo, int[][] board) {
        String[] coords = snake.split(" ");

        for (int i = 0; i < coords.length-1; ++i) {
            drawLine(board, coords[i], coords[i+1], snakeNo);
        }
    }
}
