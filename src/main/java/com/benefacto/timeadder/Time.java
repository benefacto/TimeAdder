package com.benefacto.timeadder;

public class Time {
    private int hours;
    private int minutes;
    private boolean isAM; // true if AM, false if PM

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
        isAM = minAmPm[1].equals("AM");
    }

    public void addMinutes(int minutes) {
        // Calculate total current minutes
        int totalMinutes = this.hours * 60 + this.minutes + minutes;

        // Handle days overflow/underflow
        totalMinutes %= (24 * 60); // 24 hours * 60 minutes

        // Handle negative minutes (going backwards in time)
        if (totalMinutes < 0) {
            totalMinutes += (24 * 60);
        }

        // Calculate new hours and minutes
        this.hours = totalMinutes / 60;
        this.minutes = totalMinutes % 60;

        // Adjust 12-hour format and AM/PM
        if (this.hours == 0) {
            this.hours = 12;
            this.isAM = true;
        } else if (this.hours == 12) {
            this.isAM = false;
        } else if (this.hours > 12) {
            this.hours -= 12;
            this.isAM = !this.isAM;
        }
    }

    @Override
    public String toString() {
        String amPm = isAM ? "AM" : "PM";
        return String.format("%d:%02d %s", hours, minutes, amPm);
    }

}
