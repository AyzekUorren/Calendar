package Code;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class CalendarHandler implements InterfaceForCalendar {
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

    @Override
    public void DisplayCalendar(int SelectedMonth) throws IllegalArgumentException{
        if (SelectedMonth > 12 || SelectedMonth < 1) {
            throw new IllegalArgumentException();
        }
        LocalDate today = LocalDate.now();
        LocalDate NewDay = LocalDate.of(today.getYear(), SelectedMonth, today.getDayOfMonth());
        System.out.println(NewDay.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
        Month month = Month.of(SelectedMonth);
        month.length(today.isLeapYear());
        System.out.println(GetANSI_StringCodeColour("Red") + "Month: " + month.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("uk_UA")) + GetANSI_StringCodeColour("Reset"));
    }
}