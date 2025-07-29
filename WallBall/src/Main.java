import java.util.*;

public class Main {
    static int N;
    static char[][] mat;
    static int life;
    static int bRow=6, bCol=3;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Size: ");
        N = scan.nextInt();
        mat = new char[N][N]; // Initialize the matrix with size N
        System.out.println("Enter No of Brick: ");
        int bricks = 6;
        int positionB[][] = {{2, 2}, {2, 3}, {2, 4}, {3, 2}, {3, 3}, {3, 4}};
        life = 5;

        fullMat(positionB);
        while(life>0) {
            printMat();

            System.out.println("Enter comman (st/lt/rt): ");
            String command = scan.next();

            switch (command) {
                case "st":
                    move(-1, 0);
                    break; //straight
                case "lt":
                    move(-1, -1);
                    break; //left daignal
                case "rt":
                    move(-1, 1);
                    break; //right daignal
                default:
                    System.out.println("Invalid");
                    break;
            }
        }
        System.out.println("Game over!");
        scan.close();
    }

    private static void move(int dr, int dc) {
        int r=bRow, c=bCol;
        mat[r][c]='g';//remove ball;

        while(true){
            r+=dr;c+=dc;
            if(r<0||c<0||r>=N||c>=N) break;

            if(mat[r][c]=='w'){

                if (dr == -1 && dc == -1) {
                    dc = 1;
                    r -= dr;
                    c -= dc;
                    continue;
                } else if (dr == -1 && dc == 1) {
                    dc = -1;
                    r -= dr;
                    c -= dc;
                    continue;
                } else{
                    break;
                }

            }else if(mat[r][c]=='1'){
                mat[r][c]='_';
                break;
            }
        }

        if(r!=bRow && c!=bCol){
            bRow=r;bCol=c;life--;
        }

        mat[bRow][bCol]='o';
        System.out.println("life left: "+life);
    }

    private static void fullMat(int[][] positionB) {
        // Initialize the matrix with '0' for empty spaces
        for (int i = 0; i < N; i++) {
            Arrays.fill(mat[i], ' ');
        }

        // Set boundaries
        for (int i = 0; i < N; i++) {
            mat[i][0] = 'w'; // Left boundary
            mat[N - 1][i] = 'w'; // Bottom boundary
            mat[i][N - 1] = 'w'; // Right boundary
            mat[0][i] = 'w'; // Top boundary
        }

        for(int i=1;i<N-1;i++){
            mat[N-2][i]='g';
        }
        mat[bRow][bCol]='o';


        // Set bricks
        for (int i = 0; i < positionB.length; i++) {
            mat[positionB[i][0]][positionB[i][1]] = '1';
        }
    }

    private static void printMat() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println(); // New line for each row
        }
    }
}
