package com.benefacto.timeadder;

/**
 * TimeAdder class provides functionality to add minutes to a given time.
 * This class also contains the main method to demonstrate the usage from the command line.
 */
public class TimeAdder {

  /**
   * The main method to run the TimeAdder.
   * Expects arguments in the format: [H]H MM {AM|PM} minutes
   * For example: 9:00 AM 15 would add 15 minutes to 9:00 AM
   *
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    if (args.length < 3) {
      System.out.println("Please provide the time string ([H]H:MM {AM|PM}) and minutes integer as arguments.");
      return;
    }
    String timeString = String.format("%s %s", args[0], args[1]);
    int minutes;
    try {
      minutes = Integer.parseInt(args[2]);
    } catch (NumberFormatException e) {
      System.out.println("Invalid number of minutes provided.");
      return;
    }
    System.out.println(addMinutes(timeString, minutes));
  }

  /**
   * Adds the specified minutes to the given time string and returns the resulting time.
   *
   * @param timeStamp The original time string in [H]H:MM {AM|PM} format.
   * @param minutes The number of minutes to add (can be negative to subtract minutes).
   * @return The new time string after adding the minutes.
   */
  public static String addMinutes(String timeStamp, int minutes) {
    Time time = new Time(timeStamp);
    time.addMinutes(minutes);
    return time.toString();
  }
}
