package edu.pl.mim.hotel.Requirement;

/**
 * Created by vlad on 15.04.16.
 */

public enum ColourPalette {
    Grey("Grey"), Steel("Steel"), Purple("Purple"), Navy("Navy"), Celadon("Celadon"), LightGreen("LightGreen");

    private final String value;

    ColourPalette(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
