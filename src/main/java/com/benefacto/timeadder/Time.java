package com.benefacto.timeadder;

/**
 * Represents a 12-hour format time with capabilities to add minutes and convert
 * to a string representation.
 */
public class Time {
    private final int hoursPerHalfDay = 12;
    private final int hoursPerDay = 24;
    private final int minutesPerHour = 60;
    private final String timeStringRegex = "(1[0-2]|0?[1-9]):[0-5][0-9] (AM|PM)";
    private int hours;
    private int minutes;

    /**
     * Constructs a Time object from a time string in [H]H:MM {AM|PM} format.
     *
     * @param timeString The input time string.
     *                   The regex "(1[0-2]|0?[1-9]):[0-5][0-9] (AM|PM)" ensures valid input:
     *                   - "(1[0-2]|0?[1-9])" matches the hour, which can be from 01 to 12 (or 1 to 12 without a leading zero).
     *                   - "[0-5][0-9]" matches the minutes, which can be from 00 to 59.
     *                   - "(AM|PM)" matches either "AM" or "PM".
     */
    public Time(String timeString) {
        if (timeString == null || !timeString.matches(timeStringRegex)) {
            throw new IllegalArgumentException("Invalid time format");
        }

        // Split the string into hours, minutes, and AM/PM parts
        String[] parts = timeString.split(":");
        hours = Integer.parseInt(parts[0]);

        // Extract minutes and AM/PM
        String[] minAmPm = parts[1].split(" ");
        minutes = Integer.parseInt(minAmPm[0]);
        if (minAmPm[1].equals("PM") && hours < hoursPerHalfDay) {
            hours += hoursPerHalfDay;
        } else if (minAmPm[1].equals("AM") && hours == hoursPerHalfDay) {
            hours = 0;
        }
    }

    /**
     * Adds the specified number of minutes to the current time.
     * 
     * @param minutesToAdd The number of minutes to add (can be negative to subtract minutes).
     */
    public void addMinutes(int minutesToAdd) {
        long totalMinutesLong = (long) hours * minutesPerHour + minutes + minutesToAdd;
        totalMinutesLong %= (long) hoursPerDay * minutesPerHour;
        if (totalMinutesLong < 0) {
            totalMinutesLong += (long) hoursPerDay * minutesPerHour;
        }
        hours = (int) (totalMinutesLong / minutesPerHour);
        minutes = (int) (totalMinutesLong % minutesPerHour);
    }    

    /**
     * Returns a string representation of the time in [H]H:MM {AM|PM} format.
     *
     * @return A string representation of the time.
     */
    @Override
    public String toString() {
        String amPm = hours < hoursPerHalfDay ? "AM" : "PM";
        int amPmHours = hours > hoursPerHalfDay ? hours - hoursPerHalfDay : hours;
        amPmHours = amPmHours == 0 ? hoursPerHalfDay : amPmHours;
        return String.format("%d:%02d %s", amPmHours, minutes, amPm);
    }
}
