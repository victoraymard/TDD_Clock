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
        assertEquals("Show Date",testClock.changeMode());
    }
    @Test
    public void Given_nothing_When_ChangeModeIsCalledAndCurrentStateIsS2_Then_currentStateIsS1() {
        testClock.currentState = State.DISPLAYDATE;
        assertEquals("Show Time",testClock.changeMode());

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
        assertEquals("time set",testClock.set(a,b,c));
    }
    @Test
    public void Given_nothing_When_SetIsCalledAndCurrentStateIsS4_Then_currentStateIsS2() {
        testClock.currentState = State.CHANGEDATE;
        int a=2000;
        int b=1;
        int c=1;
        assertEquals("date set",testClock.set(a,b,c));
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

}
