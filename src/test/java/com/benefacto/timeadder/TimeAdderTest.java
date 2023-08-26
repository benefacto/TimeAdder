package com.benefacto.timeadder;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeAdderTest {

    @Test
    public void testAddMinutes() {
        // Test cases
        runTest("9:13 AM", 200);
        runTest("9:13 AM", 47);
        runTest("3:45 PM", 75);
        runTest("9:13 AM", -13);
        runTest("3:45 PM", -45);
        runTest("11:59 AM", 1);
        runTest("11:59 PM", 1);
        runTest("12:00 AM", -1);
        runTest("12:00 PM", -1);
        runTest("9:13 AM", 720);
        runTest("9:13 AM", 1440);
        runTest("9:13 AM", -720);
    }

    public void runTest(String inputTime, int minutes) {
        String expected = calculateWithLocalTime(inputTime, minutes);
        String result = TimeAdder.addMinutes(inputTime, minutes);
        assertEquals(expected, result, "Failed for inputTime: " + inputTime + " and minutes: " + minutes);
    }

    public String calculateWithLocalTime(String inputTime, int minutes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        LocalTime time = LocalTime.parse(inputTime, formatter);
        LocalTime adjustedTime = time.plusMinutes(minutes);
        return formatter.format(adjustedTime).toUpperCase();
    }
}
