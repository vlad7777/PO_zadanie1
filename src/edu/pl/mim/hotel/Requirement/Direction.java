package edu.pl.mim.hotel.Requirement;

/**
 * Created by vlad on 15.04.16.
 */

public enum Direction {
    North("North"), South("South"), East("East"), West("West");

    private final String value;

    Direction(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
