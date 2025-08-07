public class User {
    static int i=1;
    int id;
    char start;
    char end;
    int amount;
    String time;
    int distance;
    String finalTime;
    User(char start, char end, String time){
        this.start=start;
        this.end=end;
        this.id=i++;
        this.time=time;
        distance();
        time();
        taxiSelect();
    }

    public void taxiSelect(){
        Taxi taxi=new Taxi();
        taxi.taxiAllocation(end, time, finalTime);
    }


    public void distance(){
        int point=Math.abs(start-end);
        this.distance=point*15;
        int initial_5KM=100;

        amount=initial_5KM+((distance-5)*10);
    }


    public void time(){
        String needTime="";
        int totalMinutes=totalMinutes();
        System.out.println("min: "+totalMinutes);

//        int am_pm=am_pm(time);
//        String sub=time.substring(0, am_pm);

        String times[]=time.split(":");
        times[0]=times[0].trim();
        times[1]=times[1].trim();
        int hour=Integer.parseInt(times[0]);
        int minutes = Integer.parseInt(times[1]);

        finalTime=finalTime(totalMinutes, hour, minutes);
        System.out.println(finalTime);


    }

//    public int am_pm(String time){
//
//        if(time.contains("am")){
//            pm=false;
//            return time.lastIndexOf("am");
//
//        } else if (time.contains("pm")) {
//            pm=true;
//            return time.lastIndexOf("pm");
//
//        }
//        return 0;
//    }

    public String finalTime(int totalMinutes, int hours, int minutes){

        while (totalMinutes>0){
            minutes++;
            totalMinutes--;
            if(minutes==60){
                minutes=0;
                hours++;
            }
            if(hours==24){
                hours=0;
            }

        }

        return String.format("%02d:%02d", hours, minutes);
    }

    public int totalMinutes(){
        return  (distance/15)*60;
    }

}
