package com.company.victor;
//importer les package Time et Date ?
enum State{
    DISPLAYTIME,
    DISPLAYDATE,
    CHANGETIME,
    CHANGEDATE
}

class Clock {
    Time theTime = new Time();
    Date theDate = new Date();

    //add attributes to save the sate
    State currentState = State.DISPLAYTIME;




    String changeMode(){
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


    String ready(){
        switch (this.currentState){
            case DISPLAYTIME:
                return "time is ready to set";
            case DISPLAYDATE:
                return "date is ready to set";
            default:
                return "transition impossible";
        }
    }
    String set(int p1, int p2, int p3){

        switch (this.currentState) {
            case CHANGEDATE:
                // setDate
                return this.theDate.dateSet(p1, p2, p3);
            case CHANGETIME:
                // setTime
                return  this.theTime.timeSet(p1, p2, p3);
            default:
               return  "transition impossible";
        }
    }
}
