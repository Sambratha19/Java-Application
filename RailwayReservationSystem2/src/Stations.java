public class Stations {
    public static final String station[]={
            "Chennai",
            "Katpadi",
            "Salem",
            "Erode",
            "Coimbatore"
    };
    Stations(){

    }

    public static String getStation(int index) {
        if (index+1 >= 0 && index <= station.length) {
            return station[index-1];
        }
        return "Invalid Station Index";
    }

    public static int getStationValue(String value) {
        for(int i=0;i<station.length;i++){
            if(value.equals(station[i])){
                return i;
            }
        }
        return -1;
    }
}
