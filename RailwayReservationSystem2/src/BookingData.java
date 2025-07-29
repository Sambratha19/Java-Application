public class BookingData {
    public static int seatNo;
    public static int price;
    public static String type;
    public BookingData(int seatNo, int price, String type) {
        this.seatNo = seatNo;
        this.price = price;
        this.type=type;
    }

    public static String getType() {
        return type;
    }

    public static int getSeatNo() {
        return seatNo;
    }

    public static int getPrice() {
        return price;
    }
}
