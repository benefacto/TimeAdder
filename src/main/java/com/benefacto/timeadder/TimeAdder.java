package com.benefacto.timeadder;

public class TimeAdder {
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

  public static String addMinutes(String timeStamp, int minutes) {
    Time time = new Time(timeStamp);
    time.addMinutes(minutes);
    return time.toString();
  }
}
