import Code.CalendarMethods;

public class Calendar {
    public static void main(String[] ArgsNumber) {
        CalendarMethods newCalendar = new CalendarMethods();

        newCalendar.DisplayCalendar(newCalendar.GetNumberOfMonth(ArgsNumber));
    }
}