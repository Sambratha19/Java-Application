import java.util.*;
public class Main {
    public static void main(String[] args) {
        String paragraph=new String("This Greek myth is the original story of love lost… and found again, and has inspired countless fairy tales from Cinderella to (basically) ever romance you’ve seen or read. The story goes like this: There once was a beautiful princess named Psyche who made Aphrodite (the goddess of love) jealous. Aphrodite sent her son Cupid to shoot the young girl with one of his arrows, so she would fall in love with a hideous beast. Wouldn’t you know it, but Cupid pricked himself and fell in love. He swept Psyche away to his paradise-like abode, with one stipulation: she must never look upon him. Psyche’s sisters were jealous, and they convinced her to take a peak. She did, and fell madly in love with the sleeping Cupid—but awakened him with trembling hands that spilled oil from her lamp. Cupid had to keep his word: he fled, leaving Psyche behind.");
        List<String> list=new LinkedList<>();
        Scanner scanner=new Scanner(System.in);
        Function function=new Function();
        while(true){
            System.out.println("1. Print test\n2. Delete word\n3. Delete line\n4. search word\n5. Replace word\n6. No. of words in text\n7. Exit");
            int input=scanner.nextInt();

            switch (input){
                case 1:
                    list=function.printText(paragraph);
                    int i=1;
                    for(String s:list){
                        System.out.println((i++)+ " "+s);
                    }
                    break;
                case 2:
                    String deleteWord="countless";
                    if(paragraph.contains(deleteWord)) {
                        paragraph = function.deleteWord(deleteWord, paragraph);
                    }else{
                        System.out.println("Word not found...");
                    }
                    break;
                case 3:
                    int lineNo=scanner.nextInt();
                    if (lineNo-1< list.size()) {
                        list.remove(lineNo-1);
                    }

                    String string="";
                    for(String s:list){
                        string+=s+" ";
                    }
                    paragraph=string.trim();
                    break;
                case 4:
                    String search="the";

                    if(paragraph.contains(search)){

                        for(int j=0;j< list.size();j++){
                            System.out.println(list.get(j));
                            if(list.get(j).contains(search)) {
                                String str[] = list.get(j).split(" ");
                                int col = 0;
                                for (int k = 0; k < str.length; k++) {
                                    if (str[k].equals(search)) {
                                        System.out.println("Line: " + (j + 1) + ", Column: " + k);
                                    }
                                }

                            }else{
                                System.out.println("Nope...");
                            }
                        }
                    }else{
                        System.out.println("Not found");
                    }
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
            }

        }

    }
}