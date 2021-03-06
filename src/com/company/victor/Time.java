package com.company.victor;

public class Time {
    int theHour= 0;   //0-23
    int theMinute= 0; //0-59
    int theSecond= 0; //0-59

    String timeSet(int hour, int minute, int second){

        if(hour < 0 || hour > 23 || minute < 0 || minute > 59 || second <0 || second > 59) {
            return "Time error : format not accepted";
        } else {
            theHour = hour;
            theMinute = minute;
            theSecond = second;

            return showTime();
        }
    }

    String showTime(){
        String hourToString = String.valueOf(theHour);
        String minuteToString = String.valueOf(theMinute);
        String secondToString = String.valueOf(theSecond);

        return hourToString + " : " + minuteToString + " : " + secondToString;

    }
}
