import java.util.*;

public class Main {
    static final int WIDTH = 10;
    static final int HEIGHT = 10;
    static final char EMPTY = '.';
    static final char FOOD = '*';
    static final char SNAKE = 'O';

    static LinkedList<int[]> snake = new LinkedList<>();
    static int[] food = new int[2];
    static String direction = "RIGHT";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        snake.add(new int[]{0, 0});
        generateFood();

        while (true) {
            printBoard();

            System.out.print("Enter direction (W/A/S/D): ");
            String input = scanner.nextLine().toUpperCase();
            switch (input) {
                case "W": direction = "UP"; break;
                case "S": direction = "DOWN"; break;
                case "A": direction = "LEFT"; break;
                case "D": direction = "RIGHT"; break;
                default: System.out.println("Invalid input."); continue;
            }

            if (!move()) {
                System.out.println("Game Over!");
                break;
            }
        }
    }

    static void generateFood() {
        Random rand = new Random();
        while (true) {
            int x = rand.nextInt(HEIGHT);
            int y = rand.nextInt(WIDTH);
            boolean onSnake = false;
            for (int[] part : snake) {
                if (part[0] == x && part[1] == y) {
                    onSnake = true;
                    break;
                }
            }
            if (!onSnake) {
                food[0] = x;
                food[1] = y;
                break;
            }
        }
    }

    static boolean move() {
        int[] head = snake.peekFirst();
        int x = head[0], y = head[1];

        switch (direction) {
            case "UP": x--; break;
            case "DOWN": x++; break;
            case "LEFT": y--; break;
            case "RIGHT": y++; break;
        }

        // Check wall collision
        if (x < 0 || x >= HEIGHT || y < 0 || y >= WIDTH) return false;

        // Check self collision
        for (int[] part : snake) {
            if (part[0] == x && part[1] == y) return false;
        }

        // Move snake
        snake.addFirst(new int[]{x, y});
        if (x == food[0] && y == food[1]) {
            generateFood(); // grow
        } else {
            snake.removeLast(); // move forward
        }
        return true;
    }

    static void printBoard() {
        char[][] board = new char[HEIGHT][WIDTH];
        for (char[] row : board) Arrays.fill(row, EMPTY);

        for (int[] part : snake) board[part[0]][part[1]] = SNAKE;
        board[food[0]][food[1]] = FOOD;

        System.out.println("\nSNAKE GAME BOARD:");
        for (char[] row : board) {
            for (char cell : row) System.out.print(cell + " ");
            System.out.println();
        }
    }
}
