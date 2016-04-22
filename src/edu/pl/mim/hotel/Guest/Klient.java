package edu.pl.mim.hotel.Guest;

import edu.pl.mim.hotel.Requirement.Ankieta;
import edu.pl.mim.hotel.Pokoj;

/**
 * Created by vlad on 15.04.16.
 */

public abstract class Klient {

    private String imie;
    protected String typ;

    public Klient(String imie) {
        this.imie = imie;
    }

    public String toString() {
        return imie() + ", " + typ();
    }

    public String imie() {
        return imie;
    }

    public String typ() {
        return typ;
    }

    public abstract boolean rozwaz(Pokoj pokoj, Ankieta ankieta);
}
