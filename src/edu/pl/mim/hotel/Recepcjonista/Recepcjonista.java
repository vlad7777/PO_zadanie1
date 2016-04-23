package edu.pl.mim.hotel.Recepcjonista;

import edu.pl.mim.hotel.Ankieta.Ankieta;
import edu.pl.mim.hotel.Klient.Klient;
import edu.pl.mim.hotel.Pokoj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vlad on 15.04.16.
 */
public abstract class Recepcjonista {

    public class BrakWolnychPokojow extends Exception {};

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

    public static List<Pokoj> wybierzWolnePokoje(Pokoj[] pokoje, Date dataPrzyjazdu, Date dataWyjazdu) {
        List<Pokoj> ret = new ArrayList<>();
        for (Pokoj pokoj : pokoje) {
            if (pokoj.sprawdzDostepnosc(dataPrzyjazdu, dataWyjazdu))
                ret.add(pokoj);
        }
        return ret;
    }

    public boolean zapytajKlienta(Pokoj pokoj, Ankieta ankieta, Klient klient) {
        return klient.rozwaz(pokoj, ankieta);
    }

    public abstract Pokoj wybierzPokoj(Pokoj[] pokoje, Ankieta ankieta) throws BrakWolnychPokojow;

    public String typ() {
        return typ;
    }
}
