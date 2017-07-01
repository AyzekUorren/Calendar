package Code;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class CalendarHandler implements InterfaceForCalendar {

    private void printTableCalendar(List<ListOfDay> CalendarList){
        String[] arrayofDaysShort = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat" , "Sun"};
        int currentnumberWeek = 0;

        for (int indexOfDay = 0; indexOfDay < 7; indexOfDay++){
            if (Objects.equals(arrayofDaysShort[indexOfDay], "Sat") || Objects.equals(arrayofDaysShort[indexOfDay], "Sun")){
                System.out.print(getANSIStringCodeColour("Red"));
                System.out.format("%9s", arrayofDaysShort[indexOfDay]);
                System.out.print(getANSIStringCodeColour("Reset"));
            } else {
                System.out.format("%9s", arrayofDaysShort[indexOfDay]);
            }
        }

        for (ListOfDay currentDay : CalendarList) {
            if (currentnumberWeek < currentDay.getNumberOfWeek()) {
                currentnumberWeek = currentDay.getNumberOfWeek();
                System.out.print("\n\n");
            }
            switch (checkStateSelectedDay(currentDay)) {
                case "Weekend": {
                    System.out.format("%7s", "");
                    System.out.print(getANSIStringCodeColour("Red"));
                    System.out.format("%02d", currentDay.getDayNumber());
                    System.out.print(getANSIStringCodeColour("Reset"));
                    break;
                }
                case "Today":{
                    System.out.format("%7s", "");
                    System.out.print(getANSIStringCodeColour("Green"));
                    System.out.format("%02d", currentDay.getDayNumber());
                    System.out.print(getANSIStringCodeColour("Reset"));
                    break;
                }
                case "SimpleDay":{
                    System.out.format("%7s", "");
                    System.out.format("%02d", currentDay.getDayNumber());
                    break;
                }
                case "EmptyDay":{
                    System.out.format("%9s", "");
                    break;
                }
            }
        }
    }

    private String checkStateSelectedDay(ListOfDay selectedDay){
        String stateSelectedDay;
        int todayNumber = LocalDate.now().getDayOfMonth();
        if ((Objects.equals(selectedDay.getDayShortName(), "Sat") ||
                Objects.equals(selectedDay.getDayShortName(), "Sun")) &&
                selectedDay.getDayNumber() != todayNumber &&
                selectedDay.getDayNumber() != 0)
        {
            stateSelectedDay = "Weekend";
        } else if (selectedDay.getDayNumber() == todayNumber) {
            stateSelectedDay = "Today";
        } else if(selectedDay.getDayNumber() == 0){
            stateSelectedDay = "EmptyDay";
        } else {
            stateSelectedDay = "SimpleDay";
        }
        return stateSelectedDay;
    }

    private List<ListOfDay> createWeekOfDays(int firstDayOfWeek, int lengthOfMonth){
        String[] ArrayofDaysShortNames = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat" , "Sun"};
        int dayNumberOfMonth = 1;
        int weekNumber = 1;
        List<ListOfDay> currentWeek = new ArrayList<ListOfDay>() {};
        if (firstDayOfWeek > 0){
            for(int indexEmptyDay = 0; indexEmptyDay < firstDayOfWeek; indexEmptyDay++){
                currentWeek.add(new ListOfDay(0, ArrayofDaysShortNames[indexEmptyDay], weekNumber));
            }
        }
        while (dayNumberOfMonth < lengthOfMonth){
            for (int indexOfDay = firstDayOfWeek; indexOfDay < 7; indexOfDay++) {
                if(dayNumberOfMonth <= lengthOfMonth) {
                    currentWeek.add(new ListOfDay(dayNumberOfMonth, ArrayofDaysShortNames[indexOfDay], weekNumber));
                    dayNumberOfMonth++;
                } else {
                    currentWeek.add(new ListOfDay(0, ArrayofDaysShortNames[indexOfDay], weekNumber));
                }
            }
            weekNumber++;
            firstDayOfWeek = 0;
        }
        return currentWeek;
    }

    private static String getANSIStringCodeColour(String chooseсolour) {
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

    private int getCurrentMonth(){
        LocalDate today = LocalDate.now();
        return today.getMonthValue();
    }

    public int getNumberOfMonth(String[] argumentsConsole){
        int numberOfMonth;
        if (argumentsConsole.length != 0) {
            numberOfMonth = Integer.parseInt(argumentsConsole[0]);
        } else  {
            numberOfMonth = this.getCurrentMonth();
        }
        return numberOfMonth;
    }

    private LocalDate getFirstSelectedDayOfMonth(int selectedMonth, int numberDayOfWeek) throws IllegalArgumentException{
        if (numberDayOfWeek < 1 || numberDayOfWeek > 7) {
            throw new IllegalArgumentException();
        }
        DayOfWeek[] arrayDaysOfWeek = {null, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
        LocalDate today = LocalDate.now();
        int selectedDay = today.getDayOfMonth();

        if (GetLengthOfMonth(selectedMonth) < GetLengthOfMonth(today.getMonthValue())){
            selectedDay = 1;
        }

        LocalDate todayInSelectedMonth = LocalDate.of(today.getYear(), selectedMonth, selectedDay);
        return todayInSelectedMonth.with(TemporalAdjusters.firstInMonth(arrayDaysOfWeek[numberDayOfWeek]));
    }

    private int GetLengthOfMonth(int selectedMonth) {
        LocalDate today = LocalDate.now();

        return Month.of(selectedMonth).length(today.isLeapYear());
    }

    private int getFirsMondayOfMonth(int selectedMonth){
        int firstDayAfterFirstMonday = getFirstSelectedDayOfMonth(selectedMonth, 1).getDayOfMonth() - 1;
        int numberFirstDayofWeekInMonth = 0;
        if (firstDayAfterFirstMonday != 0) {
            numberFirstDayofWeekInMonth = 7 - firstDayAfterFirstMonday;
        }
        return numberFirstDayofWeekInMonth;
    }

    @Override
    public void DisplayCalendar(int selectedMonth) throws IllegalArgumentException{
        if (selectedMonth > 12 || selectedMonth < 1) {
            throw new IllegalArgumentException();
        }
        Month month = Month.of(selectedMonth);
        List<ListOfDay> CalendarList = createWeekOfDays(getFirsMondayOfMonth(selectedMonth), GetLengthOfMonth(selectedMonth));

        System.out.println(getANSIStringCodeColour("Yellow") + "Month: " + month.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("uk_UA")) + getANSIStringCodeColour("Reset"));
        System.out.println();
        printTableCalendar(CalendarList);
    }
}