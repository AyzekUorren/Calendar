package Code;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CalendarHandler implements CalendarInterface{
    private LocalDate selectedMonthDate;
    private List<LocalDate> ListOfDays = null;

    public CalendarHandler(String[] ArgsNumber){
        setSelectedMonthDate(getNumberOfMonth(ArgsNumber));
    }

    private LocalDate getSelectedMonthDate() {
        return selectedMonthDate;
    }

    private void setSelectedMonthDate(int selectedMonthNumber) {
        LocalDate todayDate = LocalDate.now();
        LocalDate selectedDate = LocalDate.of(todayDate.getYear(), selectedMonthNumber, 1);
        int lengthTodayMonth = todayDate.getMonth().length(todayDate.isLeapYear());
        int lengthSelectedMonth = selectedDate.getMonth().length(todayDate.isLeapYear());

        if(lengthTodayMonth < lengthSelectedMonth){
            selectedDate = LocalDate.of(todayDate.getYear(), selectedMonthNumber, todayDate.getDayOfMonth());
        }

        this.selectedMonthDate = selectedDate;
    }

    private int getNumberOfMonth(String[] argumentsConsole){
        int numberOfMonth;
        LocalDate today = LocalDate.now();
        if (argumentsConsole.length != 0) {
            numberOfMonth = Integer.parseInt(argumentsConsole[0]);
        } else  {
            numberOfMonth = today.getMonthValue();
        }
        return numberOfMonth;
    }

    private void printFullNameOfMonth() {
        System.out.println(getANSIStringCodeColour("Yellow") + "Month: " +
                getSelectedMonthDate().getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("uk_UA")) +
                getANSIStringCodeColour("Reset"));
    }

    private List<LocalDate> createListOfDays(){
        LocalDate currentDayOfMonth = LocalDate.of(getSelectedMonthDate().getYear(), getSelectedMonthDate().getMonth(), 1);
        List<LocalDate> DaysOfMonth = new ArrayList<LocalDate>() {};
        DaysOfMonth.add(currentDayOfMonth);

        int numberDayOfCurrentMonth = currentDayOfMonth.getDayOfMonth();
        int lengthSelectedMonth = currentDayOfMonth.getMonth().length(currentDayOfMonth.isLeapYear());


        while (numberDayOfCurrentMonth + 1 < lengthSelectedMonth){
            currentDayOfMonth = LocalDate.of(currentDayOfMonth.getYear(), currentDayOfMonth.getMonth(), currentDayOfMonth.getDayOfMonth() + 1);
            numberDayOfCurrentMonth = currentDayOfMonth.getDayOfMonth();
            DaysOfMonth.add(currentDayOfMonth);
        }
        return DaysOfMonth;
    }

    private void PrintCalendar(){
        System.out.format("%9s%9s%9s%9s%9s", "Mon","Tue","Wed","Thu","Fri");
        System.out.print(getANSIStringCodeColour("Red"));
        System.out.format("%9s%9s","Sat","Sun");
        System.out.println("\n" + getANSIStringCodeColour("Reset"));

        int EmptyDays = getNumberDaysBeforeFirstDayMonth(getListOfDays().get(0));
        if(EmptyDays > 0) {
            for (int IndexDay = 1; IndexDay <= EmptyDays; IndexDay++){
                System.out.format("%9s", "");
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("       dd");
        for (LocalDate currentDate : getListOfDays()) {

            switch (checkStateSelectedDay(currentDate)) {
                case "Weekend": {
                    System.out.print(getANSIStringCodeColour("Red"));
                    break;
                }
                case "Today":{
                    System.out.print(getANSIStringCodeColour("Green"));
                    break;
                }
                case "SimpleDay":{
                    System.out.print(getANSIStringCodeColour("Reset"));
                    break;
                }
            }

            System.out.print(formatter.format(currentDate));
            if (currentDate.getDayOfWeek().getValue() == 7){
                System.out.print("\n\n");
            }
        }
    }

    private int getNumberDaysBeforeFirstDayMonth(LocalDate firstDayOnMonth){
        int numberDaysBeforeFirstDay = firstDayOnMonth.getDayOfWeek().getValue();
        if (numberDaysBeforeFirstDay > 1) {
            numberDaysBeforeFirstDay = numberDaysBeforeFirstDay - 1;
        } else numberDaysBeforeFirstDay = 0;
        return numberDaysBeforeFirstDay;
    }

    private String checkStateSelectedDay(LocalDate selectedDay){
        String stateSelectedDay;
        int todayNumber = LocalDate.now().getDayOfMonth();

        if ((selectedDay.getDayOfWeek().getValue() >= 6) &&
                selectedDay.getDayOfMonth() != todayNumber)
        {
            stateSelectedDay = "Weekend";
        } else if (selectedDay.getDayOfMonth() == todayNumber) {
            stateSelectedDay = "Today";
        } else {
            stateSelectedDay = "SimpleDay";
        }
        return stateSelectedDay;
    }


    private static String getANSIStringCodeColour(String chooseсolour) {
        final char EscCode = 27;
        String ANSI_codeColour = null;
        switch (chooseсolour) {
            case "Red":
                ANSI_codeColour = EscCode + "[31m";
                break;
            case "Green":
                ANSI_codeColour = EscCode + "[32m";
                break;
            case "Yellow":
                ANSI_codeColour = EscCode + "[33m";
                break;
            case "Reset":
                ANSI_codeColour = EscCode + "[0m";
                break;
        }
        return ANSI_codeColour;
    }

    private List<LocalDate> getListOfDays() {
        return ListOfDays;
    }

    private void setListOfDays(List<LocalDate> listOfDays) {
        ListOfDays = listOfDays;
    }

    public void DisplayCalendar() {
        printFullNameOfMonth();
        setListOfDays(createListOfDays());

        PrintCalendar();
    }
}