package Code;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class CalendarHandler implements InterfaceForCalendar {

    /*private List<ListOfDays> CalendarList = new ArrayList<ListOfDays>();*/

    private void PrintTableCalendar(int SelectedMonth){
        String[] ArrayofDaysShort = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int LastDayOfMonth = GetLengthOfMonth(SelectedMonth);
        for (String ChoseDay: ArrayofDaysShort) {
            System.out.format("%9s", ChoseDay);
        }
        int CounterofDayNumber = 1;
        System.out.print("\n\n");
        if (GetFirstSelectedDayOfMonth(SelectedMonth, 1).getDayOfMonth() != 1)
        for (int i = 1; i <= 7 - (GetFirstSelectedDayOfMonth(SelectedMonth, 1).getDayOfMonth() - 1); i++){
            System.out.format("%9s", "");
            CounterofDayNumber++;
        }
        int currentDayNumber = 1;
        while (currentDayNumber <= LastDayOfMonth){
            if (CounterofDayNumber == 8){
                System.out.print("\n\n");
                CounterofDayNumber = 1;
            }
            System.out.format("%7s", "");
            System.out.format("%02d", currentDayNumber);
            currentDayNumber++;
            CounterofDayNumber++;
        }
    }

    /*private ListOfDays CreateWeekOfDays(int FirstDay){
        int CurrentDay = FirstDay;
        String Days[] = new String[7];
            for (int IndexOfDay = 0; IndexOfDay < 7; IndexOfDay++) {
                if (IndexOfDay < FirstDay) {
                    Days[IndexOfDay] = "1";
                } else {
                    Days[IndexOfDay] = String.valueOf(CurrentDay);
                    CurrentDay++;
                }
            }
            return new ListOfDays(Days[0], Days[1], Days[2], Days[3], Days[4], Days[5], Days[6], CurrentDay);
    }
*/
    /*private int GetNumberDayOfWeek(LocalDate SelectedDate){
        return SelectedDate.getDayOfWeek().getValue();
    }*/

    private static String GetANSI_StringCodeColour(String chooseсolour) {
        final char EscCode = 27;
        String ANSI_codeColour = null;
        switch (chooseсolour) {
            case "Black":
                ANSI_codeColour = EscCode + "[30m";
                break;
            case "Red":
                ANSI_codeColour = EscCode + "[31m";
                break;
            case "Green":
                ANSI_codeColour = EscCode + "[32m";
                break;
            case "Yellow":
                ANSI_codeColour = EscCode + "[33m";
                break;
            case "Blue":
                ANSI_codeColour = EscCode + "[34m";
                break;
            case "Purple":
                ANSI_codeColour = EscCode + "[35m";
                break;
            case "Cyan":
                ANSI_codeColour = EscCode + "[36m";
                break;
            case "White":
                ANSI_codeColour = EscCode + "[37m";
                break;
            case "Reset":
                ANSI_codeColour = EscCode + "[0m";
                break;
        }
        
        return ANSI_codeColour;
    }

    private int GetCurrentMonth(){
        LocalDate today = LocalDate.now();
        return today.getMonthValue();
    }

    public int GetNumberOfMonth(String[] ArgumentsConsole){
        int NumberOfMonth;
        if (ArgumentsConsole.length != 0) {
            NumberOfMonth = Integer.parseInt(ArgumentsConsole[0]);
        } else  {
            NumberOfMonth = this.GetCurrentMonth();
        }
        return NumberOfMonth;
    }

    private LocalDate GetFirstSelectedDayOfMonth(int SelectedMonth, int NumberDayOfWeek) throws IllegalArgumentException{
        if (NumberDayOfWeek < 1 || NumberDayOfWeek > 7) {
            throw new IllegalArgumentException();
        }
        DayOfWeek[] ArrayDaysOfWeek = {null, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
        LocalDate today = LocalDate.now();
        int SelectedDay = today.getDayOfMonth();

        if (GetLengthOfMonth(SelectedMonth) < GetLengthOfMonth(today.getMonthValue())){
            SelectedDay = 1;
        }

        LocalDate todayInSelectedMonth = LocalDate.of(today.getYear(), SelectedMonth, SelectedDay);
        return todayInSelectedMonth.with(TemporalAdjusters.firstInMonth(ArrayDaysOfWeek[NumberDayOfWeek]));
    }

    private int GetLengthOfMonth(int SelectedMonth) {
        LocalDate today = LocalDate.now();

        return Month.of(SelectedMonth).length(today.isLeapYear());
    }

    @Override
    public void DisplayCalendar(int SelectedMonth) throws IllegalArgumentException{
        if (SelectedMonth > 12 || SelectedMonth < 1) {
            throw new IllegalArgumentException();
        }
        Month month = Month.of(SelectedMonth);
        System.out.println("First Monday in selected Month: " + GetFirstSelectedDayOfMonth(SelectedMonth, 1));
        System.out.println(GetANSI_StringCodeColour("Red") + "Month: " + month.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("uk_UA")) + GetANSI_StringCodeColour("Reset"));
        PrintTableCalendar(SelectedMonth);
    }
}