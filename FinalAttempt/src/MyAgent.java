import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import za.ac.wits.snake.DevelopmentAgent;

    public class MyAgent extends DevelopmentAgent {
        int[][] graphicBoard;
        ArrayList<Pair> enemyHeads;
        Pair head;
        Pair tail;
        Pair apple;
        int count = 0;
        int snakeLength = 0;

        public static void main(String args[]) {
            MyAgent agent = new MyAgent();
            MyAgent.start(agent, args);
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                String initString = br.readLine();
                String[] temp = initString.split(" ");
                int nSnakes = Integer.parseInt(temp[0]);


                while (true) {
                    graphicBoard = new int[50][50];
                    enemyHeads = new ArrayList<>();
//                    InputHandler.graphicInit(graphicBoard);

                    String line = br.readLine();
                    if (line.contains("Game Over")) {
                        break;
                    }

                    apple = InputHandler.appleSetter(line,graphicBoard);

                    //do stuff with apples

                    for (int z = 0; z < 6; z++) {
                        String zombieLine=br.readLine();
                        SnakeMaker.drawSnake(zombieLine,-1,graphicBoard);
                    }

                    int mySnakeNum = Integer.parseInt(br.readLine());
                    for (int i = 0; i < nSnakes; i++) {
                        String snake;
                        String snakeLine = br.readLine();
                        if (snakeLine.contains("alive")) {
                            if (i == mySnakeNum) {
                                snake = InputHandler.getCoords(snakeLine);
                                SnakeMaker.drawSnake(snake,i+1,graphicBoard);
                                head = InputHandler.headSetter(snake,graphicBoard);
                                tail = InputHandler.tailSetter(snake,graphicBoard);
                            } else {
                                snake = InputHandler.getCoords(snakeLine);
                                SnakeMaker.drawSnake(snake,i+1,graphicBoard);
                                InputHandler.headTracker(snake, enemyHeads);
                            }
                        }
                    }

                    //finished reading, calculate move:
                    BFS search = new BFS();
                    search.VisitedMaze();
                    search.ParentMaze();
                    search.start = apple;
                    search.goal = head;

                    BFS caught = new BFS();
                    caught.VisitedMaze();
                    caught.ParentMaze();
                    caught.start = tail;
                    caught.goal = head;

                    int dist = search.Solve(graphicBoard);
                    int desperate = caught.Solve(graphicBoard);
                    int move = 0;

                    if (dist != -1 && appleNotValid(enemyHeads, apple, graphicBoard)) {
                        move = search.PathGen(graphicBoard);
                    } else if (dist == 1 && dist > 60) {
                        int rows[] = {1, 0, 0, -1};
                        int cols[] = {0, -1, 1, 0};

                        for (int k = 0; k < 4; k++) {
                            int C = head.y + cols[k];
                            int R = head.x + rows[k];

                            if (R < (50 - 1) && C < (50 - 1) && R > 0 && C > 0 && graphicBoard[C][R] == 0) {
                                if (R < head.x) {
                                    move = 2;
                                } else if (R > head.x) {
                                    move = 3;
                                } else if (C < head.y) {
                                    move = 0;
                                } else if (C > head.y) {
                                    move = 1;
                                }
                            }
                        }
                    } else {
                        if (desperate != -1) {
                            move = caught.PathGen(graphicBoard);

                        } else {
                            move = new Random().nextInt(4);
                        }
                    }

                    System.out.println(move);

//                    Logger.printBoard(graphicBoard);
//                    Logger.printBoard(search.visited);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Boolean appleNotValid(ArrayList<Pair> eHeads, Pair apple, int[][] board) {
            for (int i = 0; i < eHeads.size(); ++i) {
                if (Math.abs(eHeads.get(i).x - apple.x) == 1 && Math.abs(eHeads.get(i).y - apple.y) == 1) {
                    return false;
                }
            }
            return true;
        }
    }