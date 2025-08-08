import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static  char board[][]=new char[3][3];
    static int pos=1;
    static {
        for(int i=0;i<board.length;i++){
            for(int j=0;j< board.length;j++){
                board[i][j]=String.valueOf(pos++).charAt(0);
            }
        }
    }
    static int k=0;
    static Queue<Integer> x=new LinkedList<>();
    static Queue<Integer> o=new LinkedList<>();
    public static void main(String[] args) {
        System.out.println("Tic-Tac-Toe");
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter position from(1-9) for X: ");
        pos=scanner.nextInt();
        x.offer(pos);
        char value='X';
        print(value);
        while(!win_or_loss(pos)){
            System.out.println("Enter position from(1-9) for O: ");
            pos=scanner.nextInt();
            value='O';
            o.offer(pos);
            print(value);
            if(win_or_loss(pos)) System.out.println("WIN");
            System.out.print("Enter position from(1-9) for X: ");
            pos=scanner.nextInt();
            value='X';
            x.offer(pos);
            print(value);
        }
        if(win_or_loss(pos)) System.out.println("WIN");
    }


    public static boolean win_or_loss(int pos){
        if(x.size()==0 ||o.size()==0) return false;
        if(x.size()==3) x.remove();
        if (o.size()==3) o.remove();
        if(board[0][0]=='X'){
            int i=0, j=0;
            if(board[i+1][j+1]=='X') {
                while ((i < 3 && j < 3) && board[i][j] == 'X') {
                    i++;
                    j++;
                }
                if(i<3 && j<3) return false;
                else return true;
            }
            else if(board[i][j+1]=='X'){
                while ((i < 3 && j < 3) && board[i][j] == 'X') {
                    j++;
                }
                if(j<3) return false;
                else return true;
            }
            else if(board[i+1][j]=='X'){
                while ((i < 3 && j < 3) && board[i][j] == 'X') {
                    i++;
                }
                if(i<3) return false;
                else return true;
            }

        }
        if(board[0][0]=='O'){
            int i=0, j=0;
            if(board[i+1][j+1]=='O') {
                while ((i < 3 && j < 3) && board[i][j] == 'O') {
                    i++;
                    j++;
                }
                if(i<3 && j<3) return false;
                else return true;
            }
            else if(board[i][j+1]=='O'){
                while ((i < 3 && j < 3) && board[i][j] == 'O') {
                    j++;
                }
                if(j<3) return false;
                else return true;
            }
            else if(board[i+1][j]=='O'){
                while ((i < 3 && j < 3) && board[i][j] == 'O') {
                    i++;
                }
                if(i<3) return false;
                else return true;
            }
        }

        return false;
    }

    public static void print(char value){
        switch (pos){
            case 1:
                board[0][0]=value;
                break;
            case  2:
                board[0][1]=value;
                break;
            case 3:
                board[0][2]=value;
                break;
            case  4:
                board[1][0]=value;
                break;
            case 5:
                board[1][1]=value;
                break;
            case 6:
                board[1][2]=value;
                break;
            case 7:
                board[2][0]=value;
                break;
            case 8:
                board[2][1]=value;
                break;
            case 9:
                board[2][2]=value;
                break;

            default:
                System.out.println("Invalid");
                break;
        }

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
}