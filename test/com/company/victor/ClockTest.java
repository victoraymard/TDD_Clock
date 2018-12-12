package com.company.victor;

import com.company.victor.Clock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClockTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    Clock testClock = new Clock();


    // CoverageTests
    // change mode
    @Test
    public void Given_nothing_When_ChangeModeIsCalledAndCurrentStateIsS1_Then_currentStateIsS2() {
        testClock.currentState = State.DISPLAYTIME;
        assertEquals("1 / 1 / 2000",testClock.changeMode());
    }
    @Test
    public void Given_nothing_When_ChangeModeIsCalledAndCurrentStateIsS2_Then_currentStateIsS1() {
        testClock.currentState = State.DISPLAYDATE;
        assertEquals("0 : 0 : 0",testClock.changeMode());

    }
    @Test
    public void Given_nothing_When_ChangeModeIsCalledAndCurrentStateIsS3_Then_displayErrorMessage() {
        testClock.currentState = State.CHANGETIME;
        assertEquals("transition impossible",testClock.changeMode());

    }
    @Test
    public void Given_nothing_When_ChangeModeIsCalledAndCurrentStateIsS4_Then_displayErrorMessage() {
        testClock.currentState = State.CHANGEDATE;
        assertEquals("transition impossible",testClock.changeMode());
    }


    // ready

    @Test
    public void Given_nothing_When_ReadyIsCalledAndCurrentStateIsS1_Then_currentStateIsS3() {
        testClock.currentState = State.DISPLAYTIME;
        assertEquals("time is ready to set",testClock.ready());
    }
    @Test
    public void Given_nothing_When_ReadyIsCalledAndCurrentStateIsS2_Then_currentStateIsS4() {
        testClock.currentState = State.DISPLAYDATE;
        assertEquals("date is ready to set",testClock.ready());
    }
    @Test
    public void Given_nothing_When_ReadyIsCalledAndCurrentStateIsS3_Then_displayErrorMessage() {
        testClock.currentState = State.CHANGETIME;
        assertEquals("transition impossible",testClock.ready());
    }
    @Test
    public void Given_nothing_When_ReadyIsCalledAndCurrentStateIsS4_Then_displayErrorMessage() {
        testClock.currentState = State.CHANGEDATE;
        assertEquals("transition impossible",testClock.ready());
    }

    // set
    @Test
    public void Given_nothing_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGETIME;
        int a=0;
        int b=0;
        int c=0;
        assertEquals("0 : 0 : 0",testClock.set(a,b,c));
    }
    @Test
    public void Given_nothing_When_SetIsCalledAndCurrentStateIsS4_Then_currentStateIsS2() {
        testClock.currentState = State.CHANGEDATE;
        int a=2000;
        int b=1;
        int c=1;
        assertEquals("1 / 1 / 2000",testClock.set(a,b,c));
    }
    @Test
    public void Given_nothing_When_SetIsCalledAndCurrentStateIsS1_Then_displayErrorMessage() {
        testClock.currentState = State.DISPLAYTIME;
        assertEquals("transition impossible",testClock.set(0,0,0));
    }
    @Test
    public void Given_nothing_When_SetIsCalledAndCurrentStateIsS2_Then_displayErrorMessage() {
        testClock.currentState = State.DISPLAYDATE;
        assertEquals("transition impossible",testClock.set(0,0,0));
    }

    // tests aux bords (pour les sets de time et date)
    // time
    @Test
    public void Given_negativeHoure_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGETIME;
        int a = -1;
        int b = 0;
        int c = 0;
        assertEquals("Time error : format not accepted",testClock.set(a,b,c));
    }
    @Test
    public void Given_anHoureOver23_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGETIME;
        int a = 24;
        int b = 0;
        int c = 0;
        assertEquals("Time error : format not accepted",testClock.set(a,b,c));
    }
    @Test
    public void Given_negativeMinutes_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGETIME;
        int a = 0;
        int b = -1;
        int c = 0;
        assertEquals("Time error : format not accepted",testClock.set(a,b,c));
    }
    @Test
    public void Given_aMinuteOver59_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGETIME;
        int a=0;
        int b=60;
        int c=0;
        assertEquals("Time error : format not accepted",testClock.set(a,b,c));
    }
    @Test
    public void Given_negativeSecondes_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGETIME;
        int a=0;
        int b=0;
        int c=-1;
        assertEquals("Time error : format not accepted",testClock.set(a,b,c));
    }
    @Test
    public void Given_aSecondOver59_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGETIME;
        int a=0;
        int b=0;
        int c=60;
        assertEquals("Time error : format not accepted",testClock.set(a,b,c));
    }

    @Test
    public void Given_wrongHourAndMinute_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGETIME;
        int a = -1;
        int b = 88;
        int c = 0;
        assertEquals("Time error : format not accepted",testClock.set(a,b,c));
    }

    @Test
    public void Given_correctTime_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGETIME;
        int a = 23;
        int b = 45;
        int c = 3;
        assertEquals("23 : 45 : 3",testClock.set(a,b,c));
    }

    // Date
    @Test
    public void Given_negativeYear_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGEDATE;
        int a = -1;
        int b = 0;
        int c = 0;
        assertEquals("Date error : format not accepted",testClock.set(a,b,c));
    }
    @Test
    public void Given_anHoureUnder2000_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGEDATE;
        int a = 1998;
        int b = 0;
        int c = 0;
        assertEquals("Date error : format not accepted",testClock.set(a,b,c));
    }
    @Test
    public void Given_negativeMonth_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGEDATE;
        int a = 0;
        int b = -1;
        int c = 0;
        assertEquals("Date error : format not accepted",testClock.set(a,b,c));
    }
    @Test
    public void Given_aMinuteOver12_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGEDATE;
        int a = 0;
        int b = 13;
        int c = 0;
        assertEquals("Date error : format not accepted",testClock.set(a,b,c));
    }
    @Test
    public void Given_negativeDay_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGEDATE;
        int a = 0;
        int b = 0;
        int c = -1;
        assertEquals("Date error : format not accepted",testClock.set(a,b,c));
    }
    @Test
    public void Given_aSecondOver31_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGEDATE;
        int a = 0;
        int b = 0;
        int c = 32;
        assertEquals("Date error : format not accepted",testClock.set(a,b,c));
    }

    @Test
    public void Given_wrongYearAndMinuteMonth_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGEDATE;
        int a = -1;
        int b = 14;
        int c = 0;
        assertEquals("Date error : format not accepted",testClock.set(a,b,c));
    }

    @Test
    public void Given_correctDate_When_SetIsCalledAndCurrentStateIsS3_Then_currentStateIsS1() {
        testClock.currentState = State.CHANGEDATE;
        int a = 2018;
        int b = 7;
        int c = 7;
        assertEquals("7 / 7 / 2018",testClock.set(a,b,c));
    }

}
