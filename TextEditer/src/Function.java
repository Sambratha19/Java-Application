import java.util.*;

public class Function {


    public static List<String> printText(String s){
        List<String> l=new LinkedList<>();
        int j=0;
        while(j<s.length()){
            int i=Math.min(j+40, s.length());
            String sub=s.substring(j, i);

            int last=sub.lastIndexOf(" ");
            if(last==-1){
                l.add(sub);
                j=i;

            }else{
                l.add(sub.substring(0, last));
                j+=last+1;
            }
        }

        return l;
    }

    public static String deleteWord(String deleteWord, String paragraph){
        String string="";
        String arr[]=paragraph.split(" ");
        for(String s:arr){
            if(s.equals(deleteWord)){
                continue;
            }
            string+=s+" ";
        }
        return string.trim();

    }


}
