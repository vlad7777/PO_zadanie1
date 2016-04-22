package edu.pl.mim.hotel.Requirement;

/**
 * Created by vlad on 15.04.16.
 */

public enum Style {
    Oriental("Oriental"), Navy("Navy"), Modern("Modern"), Rustic("Rustic"), Secessionist("Secessionist");

    private final String value;

    Style(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
