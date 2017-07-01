package Code;

class ListOfDay {
    private int Day;
    private String DayOfWeek;
    private int NumberOfWeek;
    ListOfDay(int Day, String DayOfWeek, int NumberOfWeek){
        this.Day = Day;
        this.DayOfWeek = DayOfWeek;
        this.NumberOfWeek = NumberOfWeek;
    }

    int getDay() {
        return Day;
    }

    String getDayOfWeek() {
        return DayOfWeek;
    }

    int getNumberOfWeek() {
        return NumberOfWeek;
    }
}