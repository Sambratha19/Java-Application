import java.util.*;
public class Main {

    public static void main(String[] args) {
        String str="(2x+y)*(3x-5y)";
        String[] str1=str.split("\\*");
        String output="";
        int one=0, two=0;

        while(one<str1[0].length() || two<str1[1].length()){
            if(isdigit(str1[0].charAt(one)) && isdigit(str1[1].charAt(two))){
                int a=(int)str1[0].charAt(one)-'0';
                int b=(int)str1[1].charAt(two)-'0';
                output+=""+(a*b);
                one++;two++;

            }else if(str1[0].charAt(one)==(str1[1].charAt(two))){
                output+=""+str1[0].charAt(one)+"^2";
                one++;two++;
            }
//            break;
        }
        System.out.println(output);



    }

    private static boolean isdigit(char c) {
        if(((int)c-'0')>=1 && ((int)c-'0'<=9)){
            return true;
        }
        return false;
    }

    public static boolean isOperator(char x) {
        String a=""+x;
        return "+-*/".contains(a);
    }


}