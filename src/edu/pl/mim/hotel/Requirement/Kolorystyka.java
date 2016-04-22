package edu.pl.mim.hotel.Requirement;

/**
 * Created by vlad on 15.04.16.
 */

public enum Kolorystyka {
    Szary("Szara"), Stalowy("Stalowa"), Purpurowy("Purpurowa"), Morski("Morska"), Seledynowy("Seledynowa"), Jasnozielony("Jasnozielona");

    private final String wartosc;

    Kolorystyka(String wartosc) {
        this.wartosc = wartosc;
    }

    @Override
    public String toString() {
        return wartosc;
    }
}
