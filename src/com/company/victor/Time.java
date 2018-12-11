package com.company.victor;

public class Time {
    int theHour=0;   //0-23
    int theMinute=0; //0-59
    int theSecond=0; //0-59

    public String timeSet(int hour, int minute, int second){
        return "time set";
    }
    public String showTime(){
        return "Show Time";
    }
}
