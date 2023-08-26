package com.benefacto.timeadder;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the TimeAdder utility.
 */
public class TimeAdderTest {

    /**
     * Tests the addition of minutes to various times using the TimeAdder utility.
     */
    @Test
    public void testAddMinutes() {
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
        runTest("12:00 AM", 1);
        runTest("11:59 PM", 1);
        runTest("3:45 PM", 0);
        runTest("9:13 AM", 10080);
        runTest("9:13 AM", -10080);
        runTest("11:30 AM", 31);
        runTest("11:30 PM", 31);
        runTest("12:00 AM", -1441);
        runTest("11:55 AM", 10);
        runTest("11:55 PM", 10);
        runTest("9:13 AM", Integer.MAX_VALUE);
        runTest("9:13 AM", Integer.MIN_VALUE);
    }

    /**
     * Tests the TimeAdder utility for various invalid input cases.
     */
    @Test
    public void testInvalidInputCases() {
        assertThrows(IllegalArgumentException.class, () -> TimeAdder.addMinutes("25:00 AM", 10));
        assertThrows(IllegalArgumentException.class, () -> TimeAdder.addMinutes("11:65 AM", 10));
        assertThrows(IllegalArgumentException.class, () -> TimeAdder.addMinutes("11:00 XM", 10));
        assertThrows(IllegalArgumentException.class, () -> TimeAdder.addMinutes("ABCD", 10));
        assertThrows(IllegalArgumentException.class, () -> TimeAdder.addMinutes(null, 10));
    }

    /**
     * Helper method to run a single test scenario for the TimeAdder utility.
     *
     * @param inputTime The input time as a String.
     * @param minutes   The number of minutes to add or subtract.
     */
    public void runTest(String inputTime, int minutes) {
        String expected = calculateWithLocalTime(inputTime, minutes);
        String result = TimeAdder.addMinutes(inputTime, minutes);
        assertEquals(expected, result, "Failed for inputTime: " + inputTime + " and minutes: " + minutes);
    }

    /**
     * Calculates the new time after adding/subtracting minutes using Java's
     * LocalTime.
     *
     * @param inputTime The input time as a String.
     * @param minutes   The number of minutes to add or subtract.
     * @return A String representation of the new time.
     */
    public String calculateWithLocalTime(String inputTime, int minutes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        LocalTime time = LocalTime.parse(inputTime, formatter);
        LocalTime adjustedTime = time.plusMinutes(minutes);
        return formatter.format(adjustedTime).toUpperCase();
    }
}
