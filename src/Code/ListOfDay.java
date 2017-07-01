package Code;

class ListOfDay {
    private int dayNumber;
    private String dayShortName;
    private int numberOfWeek;
    ListOfDay(int dayNumber, String dayShortName, int numberOfWeek){
        this.dayNumber = dayNumber;
        this.dayShortName = dayShortName;
        this.numberOfWeek = numberOfWeek;
    }

    int getDayNumber() {
        return dayNumber;
    }

    String getDayShortName() {
        return dayShortName;
    }

    int getNumberOfWeek() {
        return numberOfWeek;
    }
}