import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);

        System.out.println("Enter matrix in dimension(n x n): ");
        int n= scan.nextInt();;
        char matrix[][]=new char[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=0;
            }
        }

        System.out.println("Enter Adventure Position (row col): ");
        int arow= scan.nextInt(), acol= scan.nextInt();
        matrix[arow][acol]='A';

        System.out.println("Enter Destination position (row col): ");
        int drow= scan.nextInt(), dcol=scan.nextInt();
        matrix[drow][dcol]='D';

        System.out.println("Matrix: ");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

        int shortPath=find(arow, acol, drow, dcol);
        System.out.println(shortPath);
    }

    private static int find(int arow, int acol, int drow, int dcol) {
        return Math.max(Math.abs(arow-drow), Math.abs(dcol-acol));
    }

}