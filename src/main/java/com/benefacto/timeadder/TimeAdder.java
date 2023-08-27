package com.benefacto.timeadder;

/**
 * TimeAdder class provides functionality to add minutes to a given time.
 * This class also contains the main method to demonstrate the usage from the
 * command line.
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
    if (areArgumentsInvalid(args)) {
      System.out.println("Please provide the time string ([H]H:MM {AM|PM}) and minutes integer as arguments.");
      return;
    }

    int minutes = getMinutesFromArguments(args);
    if (minutes == Integer.MIN_VALUE) {
      System.out.println("Invalid number of minutes provided.");
      return;
    }

    String timeString = String.format("%s %s", args[0], args[1]);
    System.out.println(addMinutes(timeString, minutes));
  }

  /**
   * Validates the command line arguments.
   *
   * @param args Command line arguments.
   * @return true if the provided arguments are valid, false otherwise.
   */
  private static boolean areArgumentsInvalid(String[] args) {
    if (args.length < 3) {
      return false;
    }
    String timeString = String.format("%s %s", args[0], args[1]);
    return !timeString.matches(Time.TIME_STRING_REGEX);
  }

  /**
   * Extracts and returns the number of minutes from the command line arguments.
   *
   * @param args Command line arguments.
   * @return The number of minutes if valid, Integer.MIN_VALUE if invalid.
   */
  private static int getMinutesFromArguments(String[] args) {
    try {
      return Integer.parseInt(args[2]);
    } catch (NumberFormatException e) {
      return Integer.MIN_VALUE;
    }
  }

  /**
   * Adds the specified minutes to the given time string and returns the resulting
   * time.
   *
   * @param timeStamp The original time string in [H]H:MM {AM|PM} format.
   * @param minutes   The number of minutes to add (can be negative to subtract
   *                  minutes).
   * @return The new time string after adding the minutes.
   */
  public static String addMinutes(String timeStamp, int minutes) {
    Time time = new Time(timeStamp);
    time.addMinutes(minutes);
    return time.toString();
  }
}
