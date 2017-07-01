package Code;

public class ListOfDay {
    private int Day;
    private String DayOfWeek;
    private int NumberOfWeek;
    ListOfDay(int Day, String DayOfWeek, int NumberOfWeek){
        this.Day = Day;
        this.DayOfWeek = DayOfWeek;
        this.NumberOfWeek = NumberOfWeek;
    }

    public int getDay() {
        return Day;
    }

    public String getDayOfWeek() {
        return DayOfWeek;
    }

    public int getNumberOfWeek() {
        return NumberOfWeek;
    }
}