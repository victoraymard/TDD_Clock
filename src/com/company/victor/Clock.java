package com.company.victor;
//importer les package Time et Date ?
enum State{
    DISPLAYTIME,
    DISPLAYDATE,
    CHANGETIME,
    CHANGEDATE
}

public class Clock {
    Time theTime = new Time();
    Date theDate = new Date();
    //add attributes to save the sate
    public State lastState = null;
    public State currentState = State.DISPLAYTIME;


// lol fdp

    public String changeMode(){
        //Time nico;
        switch (this.currentState)
        {
            case DISPLAYTIME:
                return this.theDate.showDate();
            case DISPLAYDATE:
                return this.theTime.showTime();
            default:
                return "transition impossible";
        }
    }
    public String ready(){
        return "Ready";
    }
    public String set(int p1, int p2, int p3){
        return "";
    }

}
