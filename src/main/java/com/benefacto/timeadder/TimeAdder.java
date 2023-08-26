package com.benefacto.timeadder;

public class TimeAdder {
  public static void main(String[] args) {
    System.out.println(addMinutes("9:13 AM", 200));
  }

  public static String addMinutes(String time, int minutes) {
    Time timeManager = new Time(time);
    timeManager.addMinutes(minutes);
    return timeManager.toString();
  }
}