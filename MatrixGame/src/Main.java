public class Main {
    static char[][] grid;
    static int rows, cols;

    static class Position {
        int row, col;
        int dRow, dCol;

        Position(int r, int c, int dr, int dc) {
            row = r;
            col = c;
            dRow = dr;
            dCol = dc;
        }
    }

    public static void main(String[] args) {
        // Direct input instead of Scanner
        rows = 4;
        cols = 4;
        grid = new char[rows][cols];

        for (char[] row : grid)
            java.util.Arrays.fill(row, '-');

        // Atoms
        int[][] atoms = {
                {4, 4},
                {2, 2}
        };
        for (int[] atom : atoms)
            grid[atom[0] - 1][atom[1] - 1] = 'X';

        // Rays
        String[] rays = {"R4", "C2"};

        for (String ray : rays)
            processRay(ray);

        // Print grid
        for (char[] row : grid) {
            for (char cell : row)
                System.out.print(cell + " ");
            System.out.println();
        }
    }

    static void processRay(String rayId) {
        Position pos = getStartPosition(rayId);
        if (pos == null) return;

        java.util.Set<String> visited = new java.util.HashSet<>();

        while (true) {
            int r = pos.row;
            int c = pos.col;

            if (r < 0 || r >= rows || c < 0 || c >= cols)
                break;

            String key = r + "," + c + "," + pos.dRow + "," + pos.dCol;
            if (visited.contains(key))
                break;
            visited.add(key);

            if (grid[r][c] == 'X') {
                grid[r][c] = 'H';
                break;
            }

            boolean diagLeft = isAtom(r - 1, c - 1) || isAtom(r + 1, c - 1);
            boolean diagRight = isAtom(r - 1, c + 1) || isAtom(r + 1, c + 1);

            if (diagLeft && diagRight) {
                grid[r][c] = 'R';
                break;
            }

            if (diagLeft || diagRight) {
                if (diagLeft) {
                    int temp = pos.dRow;
                    pos.dRow = pos.dCol;
                    pos.dCol = temp;
                } else {
                    int temp = pos.dRow;
                    pos.dRow = -pos.dCol;
                    pos.dCol = -temp;
                }
                pos.row += pos.dRow;
                pos.col += pos.dCol;
                continue;
            }

            pos.row += pos.dRow;
            pos.col += pos.dCol;
        }
    }

    static boolean isAtom(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 'X';
    }

    static Position getStartPosition(String rayId) {
        if (rayId.charAt(0) == 'R') {
            int r = Integer.parseInt(rayId.substring(1)) - 1;
            return new Position(r, 0, 0, 1); // Left to right
        } else if (rayId.charAt(0) == 'C') {
            int c = Integer.parseInt(rayId.substring(1)) - 1;
            return new Position(rows - 1, c, -1, 0); // Bottom to top
        }
        return null;
    }
}
