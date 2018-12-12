package com.company.victor;

public class Date {
    int theYear   = 2000; //2000-2100
    int theMonth  = 1;    //1-12
    int theDay    = 1;    //1-31

    public String dateSet(int year, int month, int day) {

        if(year < 2000 || month < 1 || month > 12 || day < 1 || day > 12) {
            return "Date error : format not accepted";
        } else {
            theYear = year;
            theMonth = month;
            theDay = day;

            return showDate();
        }

    }

    public String showDate(){

        String yearToString = String.valueOf(theYear);
        String monthToString = String.valueOf(theMonth);
        String dayToString = String.valueOf(theDay);

        return theDay + " / " + theMonth + " / " + theYear;
    }
}
