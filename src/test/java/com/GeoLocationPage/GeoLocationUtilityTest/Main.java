package com.GeoLocationPage.GeoLocationUtilityTest;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide locations as arguments.");
            return;
        }

        for (String location : args) {
            String coordinates = GeoLocationUtil.getCoordinates(location);
            System.out.println("Coordinates for " + location + ": " + coordinates);
        }
    }
}
