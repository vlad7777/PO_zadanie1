package edu.pl.mim.hotel.Guest;

import edu.pl.mim.hotel.Requirement.Ankieta;
import edu.pl.mim.hotel.Pokoj;

/**
 * Created by vlad on 20.04.16.
 */
public class AproksymacyjnyKlient extends Klient {

    public AproksymacyjnyKlient(String imie) {
        super(imie);
        this.typ = "Aproksymacyjny";
    }

    @Override
    public boolean rozwaz(Pokoj pokoj, Ankieta ankieta) {
        return ankieta.iloscSpelnionychWymagan(pokoj) > ankieta.LICZBA_WYMAGAN / 2;
    }
}
