package com.benefacto.timeadder;

public class TimeAdder {
  public static void main(String[] args) {
    System.out.println(addMinutes("12:00 PM", -1));
  }

  public static String addMinutes(String time, int minutes) {
    Time timeManager = new Time(time);
    timeManager.addMinutes(minutes);
    return timeManager.toString();
  }
}