package edu.pl.mim.hotel.Klient;

import edu.pl.mim.hotel.Ankieta.Ankieta;
import edu.pl.mim.hotel.Pokoj;

/**
 * Created by vlad on 20.04.16.
 */
public class WidokowyKlient extends Klient {

    public WidokowyKlient(String imie) {
        super(imie);
        this.typ = "Widokowy";
    }

    @Override
    public boolean rozwaz(Pokoj pokoj, Ankieta ankieta) {
        return ankieta.iloscSpelnionychWymagan(pokoj) > -1 && pokoj.kierunek() == ankieta.kierunek();
    }
}
