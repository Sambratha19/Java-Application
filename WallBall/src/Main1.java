import java.util.*;

public class Main1 {
    static int n = 7;
    static char board[][] = new char[n][n];
    static int bricks = 6;
    static int positionBrick[][] = {{2, 2}, {2, 3}, {2, 4}, {3, 2}, {3, 3}, {3, 4}};
    static char brickvalue[]={'1', '1', '2', '1', '1', '1'};
    static boolean boardBollean[][]=new boolean[n][n];
    static int lives = 5;
    static int ball[]= {6, 3};

    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        print();
        while(lives>0){
            System.out.print("Enter: ");
            String moves=scanner.nextLine();

            switch (moves){
                case "st":
                    move(-1, 0);
                    break;
                case "ld":
                    move(-1, -1);
                    if(ball[0]>0 && ball[0]<n && ball[1]-1>0 && ball[1]-1<n){
                        ball[1]--;
                    }else{
                        System.out.println("Game Over");
                        System.exit(0);
                    }

                    break;
                case "rd":
                    move(-1,1);
                    if(ball[0]>0 && ball[0]<n && ball[1]+1>0 && ball[1]+1<n){
                        ball[1]++;
                    }else{
                        System.out.println("Game Over");
                        System.exit(0);
                    }

                    break;
                default:
                    System.out.println("invalid");
            }
            print();
        }
    }

    private static void move(int l, int r){
        int i=ball[0], j=ball[1];
        int b1=-1, b2=-1;
        while((i>=0 && j>=0 && i<n&&j<n) && !(board[i][j]>='1' && board[i][j]<='9') && !(board[i][j]=='W')){
            System.out.println(i+" "+j);
            i+=l;j+=r;
            board[ball[0]][ball[1]]='o';
            print();
        }
        boolean brick=false;
        if(board[i][j]>='1' && board[i][j]<='9') brick=true;
        b1=i;b2=j;
        System.out.println(b1 + " " + b2);
        if(brick) {
            int replace = -1;
            for (int x = 0; x < positionBrick.length; x++) {
                if (positionBrick[x][0] == b1 && positionBrick[x][1] == b2) replace = x;
            }
            System.out.println(replace);
            brickvalue[replace]--;
        }
        print();

    }

    public static void print(){
        int k=0;
        for(int i=0;i<n;i++) {
            Arrays.fill(board[i], ' ');
        }
        while(k<n) {
            board[0][k]='W';
            k++;
        }

        for(int i=1;i<n;i++) {
            board[i][0]='W';
            board[i][n-1]='W';
        }
        k=n-2;
        while(k>0){
            board[n-1][k]='g';
            k--;
        }
        board[ball[0]][ball[1]]='o';
        k=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {

                if (k<positionBrick.length && i == positionBrick[k][0] && j == positionBrick[k][1]) {
                    if(brickvalue[k]=='0') brickvalue[k]=' ';
                    board[i][j] = brickvalue[k];
                    k++;
                }
            }
        }

        for(int i=0;i<n;i++){
           for(int j=0;j<n;j++){
               System.out.print(board[i][j]+" ");
           }
           System.out.println();
        }


    }


}
