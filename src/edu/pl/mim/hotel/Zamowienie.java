package edu.pl.mim.hotel;

import edu.pl.mim.hotel.Klient.Klient;
import edu.pl.mim.hotel.Ankieta.Ankieta;

/**
 * Created by vlad on 15.04.16.
 */
public class Zamowienie {

    private int ileRazZobaczone = 0;
    private final Klient klient;
    private final Ankieta ankieta;

    public Zamowienie(Klient klient, Ankieta ankieta) {
        this.klient = klient;
        this.ankieta = ankieta;
    }

    public void zakoncz(Pokoj pokoj) throws Pokoj.PokojJuzZajety {
        pokoj.rezerwuj(ankieta.dataPrzyjazdu(), ankieta.dataWyjazdu());
    }

    @Override
    public String toString() {
        String res = "zobaczone " + ileRazZobaczone + (ileRazZobaczone == 1 ? " raz; " : " razy; ");
        res += ankieta.toString();
        return res;
    }

    public Ankieta ankieta() {
        return ankieta;
    }


    public int ileRazZobaczone() {
        return ileRazZobaczone;
    }

    public void zobacz() {
        ileRazZobaczone++;
    }

    public Klient klient() {
        return klient;
    }
}
