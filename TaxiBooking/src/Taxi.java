import java.lang.constant.DynamicConstantDesc;
import java.util.LinkedList;
import java.util.List;

public class Taxi {
    static String taxi[]=new String[4];
    static char position[]=new char[taxi.length];
    static String startTimes[]=new String[taxi.length];
    static String endtimes[]=new String[taxi.length];

    static {
        for(int i=0;i<position.length;i++){
            taxi[i]="T"+(i+1);
            position[i]='a';
            startTimes[i]="00:00";
            endtimes[i]="00:00";
        }
    }


    public void taxiAllocation(char destination, String time, String finalTime){
        List<Integer> taxiShortlisted=new LinkedList<>();

        for(int i=0;i< taxi.length;i++){
            if(!there(startTimes[i], endtimes[i], time)){
                taxiShortlisted.add(i);
            }
        }
        int min=Integer.MAX_VALUE;
        String finalTaxi="";
        for(int i=0;i< taxiShortlisted.size();i++){
            int distance=Math.abs(position[taxiShortlisted.get(i)]-destination);
            if(min>distance){
                min=distance;
                finalTaxi=taxi[taxiShortlisted.get(i)];
            }
        }

        for(int i=0;i<taxi.length;i++){
            if(finalTaxi.equals(taxi[i])){
                startTimes[i]=time;
                endtimes[i]=finalTime;
                position[i]=destination;
            }
        }
        System.out.println(finalTaxi);

    }



    public boolean there(String start, String end, String time){
        String startTime[]=start.split(":");
        String endTime[]=end.split(":");
        String finalTime[]=time.split(":");

        int h=Integer.parseInt(startTime[0]), m=Integer.parseInt(startTime[1]);
        while(h!=(Integer.parseInt(endTime[0])) || m!=Integer.parseInt(endTime[1])){

            if(h==Integer.parseInt(finalTime[0]) && m==Integer.parseInt(finalTime[1])){

                return true;
            }

            m++;
            if(m==60){
                h++;
                m=0;
            }
            if(h==24){
                h=0;
            }
        }
        return false;
    }


}
