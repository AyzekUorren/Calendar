import Code.CalendarHandler;

public class Calendar {
    public static void main(String[] ArgsNumber) {
        CalendarHandler newCalendarHandler = new CalendarHandler();

        newCalendarHandler.DisplayCalendar(newCalendarHandler.getNumberOfMonth(ArgsNumber));
    }
}