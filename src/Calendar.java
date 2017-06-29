import Code.CalendarMethods;

public class Calendar {
    public static void main(String[] ArgsNumber) {
        CalendarMethods oneMoreCalendarMethods = new CalendarMethods();
        int NumberOfMonth = Integer.parseInt(ArgsNumber[0]);
        oneMoreCalendarMethods.DisplayCalendar(NumberOfMonth);
    }
}