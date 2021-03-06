package edu.pl.mim.hotel.Klient;

import edu.pl.mim.hotel.Ankieta.Ankieta;
import edu.pl.mim.hotel.Pokoj;

/**
 * Created by vlad on 20.04.16.
 */
public class PerfecyjnyKlient extends Klient {

    public PerfecyjnyKlient(String imie) {
        super(imie);
        this.typ = "Perfekcjonista";
    }

    @Override
    public boolean rozwaz(Pokoj pokoj, Ankieta ankieta) {
        return ankieta.czyPasujeDoskonale(pokoj);
    }

}
