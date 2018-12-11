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
}
