package com.benefacto.timeadder;

public class Time {
    private final int hoursPerHalfDay = 12;
    private final int hoursPerDay = 24;
    private final int minutesPerHour = 60; 
    private int hours;
    private int minutes;

    // Structure: "[H]H:MM {AM|PM}"
    public Time(String timeString) {
        // The regular expression (1[0-2]|0?[1-9]):[0-5][0-9] (AM|PM) is used to
        // validate the timeString format.
        // (1[0-2]|0?[1-9]) matches the hour, which can be from 01 to 12 (or 1 to 12
        // without a leading zero).
        // [0-5][0-9] matches the minutes, which can be from 00 to 59.
        // (AM|PM) matches either "AM" or "PM".
        if (timeString == null || !timeString.matches("(1[0-2]|0?[1-9]):[0-5][0-9] (AM|PM)")) {
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

    public void addMinutes(int minutesToAdd) {
        int totalMinutes = hours * minutesPerHour + minutes + minutesToAdd;
        
        // Handle days overflow/underflow
        totalMinutes %= (hoursPerDay * minutesPerHour);
        
        if (totalMinutes < 0) {
            totalMinutes += (hoursPerDay * minutesPerHour);
        }
        
        int newHours = totalMinutes / minutesPerHour;
        
        if (newHours >= hoursPerDay) {
            newHours -= hoursPerDay;
        }
        
        hours = newHours;
        minutes = totalMinutes % minutesPerHour;
    }

    @Override
    public String toString() {
        String amPm = hours < hoursPerHalfDay ? "AM" : "PM";
        int amPmHours = hours > hoursPerHalfDay ? hours - hoursPerHalfDay : hours;
        amPmHours = amPmHours == 0 ? hoursPerHalfDay : amPmHours;
        return String.format("%d:%02d %s", amPmHours, minutes, amPm);
    }

}
