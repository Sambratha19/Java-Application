import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Size: ");
        int n=3;
        System.out.println("Atom Positions: ");
        int position[][]={{1, 3}, {2,2}, {3, 1}};
        char matrix[][]=new char[n][n];

        int noOfRays=3;
        String[] rays={"R3", "C1", "C3"};
        int k=0;
        for(int i=0;i<n;i++){
            Arrays.fill(matrix[i], '-');
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(k< position.length && i==position[k][0]-1 &&  j==position[k][1]-1){
                    matrix[i][j]='X';
                    k++;
                }
            }
        }

        print(matrix);

        //rays



    }

    public static void print(char[][] matrix){
        for(int i=0;i<matrix.length;i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
