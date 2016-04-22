package edu.pl.mim.hotel.Requirement;

/**
 * Created by vlad on 15.04.16.
 */

public enum Styl {
    Orientalny("Orientalny"), Morksi("Morski"), Nowoczesny("Nowoczesny"), Rustykalny("Rustykalny"), Secesyjny("Secesyjny");

    private final String value;

    Styl(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
