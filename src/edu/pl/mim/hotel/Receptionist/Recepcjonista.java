package edu.pl.mim.hotel.Receptionist;

import edu.pl.mim.hotel.Pokoj;
import edu.pl.mim.hotel.Requirement.Ankieta;

/**
 * Created by vlad on 15.04.16.
 */
public abstract class Recepcjonista {

    protected String imie;
    protected String typ;

    public Recepcjonista(String imie) {
        this.imie = imie;
    }

    @Override
    public String toString() {
        return imie() + ", " + typ();
    }

    public String imie() {
        return imie;
    }

    public abstract Pokoj wybierzPokoj(Pokoj[] pokoje, Ankieta ankieta);

    public String typ() {
        return typ;
    }
}
