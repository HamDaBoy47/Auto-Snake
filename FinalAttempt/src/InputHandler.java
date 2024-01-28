import java.util.ArrayList;

public class InputHandler {
    public static void graphicInit(int[][] board) {
        for (int c = 0; c < 50; ++c ) {
            for (int r = 0; r < 50; ++r) {
                if (r == 0 || r == 49 || c == 0 || c == 49) {
                    board[c][r] = -2;
                }
            }
        }
    }

    public static String getCoords(String snakeLine) {
        String snake = "";
        String[] bufferSnake = snakeLine.split(" ");
        String[] snakeArray = new String[bufferSnake.length - 3];

        for (int c = 3; c < bufferSnake.length; ++c) {
            snakeArray[c - 3] = bufferSnake[c];
        }

        snake += snakeArray[0];

        for (int b = 1; b < snakeArray.length; ++b) {
            snake += " " + snakeArray[b];
        }
        return snake;
    }

    public static String getLength(String snakeLine) {
        String snake = "";
        String[] bufferSnake = snakeLine.split(" ");
        String[] snakeArray = new String[bufferSnake.length - 3];

        for (int c = 3; c < bufferSnake.length; ++c) {
            snakeArray[c - 3] = bufferSnake[c];
        }

        snake += snakeArray[0];

        for (int b = 1; b < snakeArray.length; ++b) {
            snake += " " + snakeArray[b];
        }
        return snake;
    }

    public static Pair appleSetter(String line, int[][] board) {
        String[] appleBuffer = line.split(" ");

        int appleX = Integer.parseInt(appleBuffer[0]);
        int appleY = Integer.parseInt(appleBuffer[1]);

        Pair apple = new Pair(appleX, appleY);
        board[appleY][appleX] = -3;

        return apple;
    }

    public static Pair headSetter(String snake, int[][] board) {
        String[] headBuffer = snake.split(" ");
        String[] coords = headBuffer[0].split(",");

        int headX = Integer.parseInt(coords[0]);
        int headY = Integer.parseInt(coords[1]);

        Pair head = new Pair(headX, headY);
        board[headY][headX] = -4;

        return head;
    }

    public static Pair tailSetter(String snake, int[][] board) {
        String[] tailBuffer = snake.split(" ");
        int lastPos = tailBuffer.length - 1;
        String[] coords = tailBuffer[lastPos].split(",");

        int tailX = Integer.parseInt(coords[0]);
        int tailY = Integer.parseInt(coords[1]);

        Pair tail = new Pair(tailX, tailY);
        board[tailY][tailX] = -6;

        return tail;
    }

    public static void headTracker(String snake, ArrayList<Pair> enemyHeads) {
        String[] headBuffer = snake.split(" ");
        String[] coords = headBuffer[headBuffer.length-1].split(",");

        int headX = Integer.parseInt(coords[0]);
        int headY = Integer.parseInt(coords[1]);

        enemyHeads.add(new Pair(headX, headY));
    }




}
