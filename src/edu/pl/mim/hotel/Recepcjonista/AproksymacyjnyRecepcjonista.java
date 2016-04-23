package edu.pl.mim.hotel.Recepcjonista;

import edu.pl.mim.hotel.Ankieta.Ankieta;
import edu.pl.mim.hotel.Pokoj;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by vlad on 15.04.16.
 */
public class AproksymacyjnyRecepcjonista extends Recepcjonista {

    public AproksymacyjnyRecepcjonista(String imie) {
        super(imie);
        this.typ = "Aproksymacyjny";
    }

    private class ApproximateComparator implements Comparator<Pokoj> {

        private Ankieta ankieta;

        public ApproximateComparator(Ankieta ankieta) {
            this.ankieta = ankieta;
        }

        public int compare(Pokoj a, Pokoj b) {
            int reqA = ankieta.iloscSpelnionychWymagan(a);
            if (reqA == -1)
                return 1;

            int reqB = ankieta.iloscSpelnionychWymagan(b);
            if (reqB == -1)
                return -1;

            if (Integer.compare(reqA, reqB) != 0)
                return -1 * Integer.compare(reqA, reqB); // we need a room that fits the best
            else if (Integer.compare(a.cena(), b.cena()) != 0)
                return -1 * Integer.compare(a.cena(), b.cena()); // we need an expensive room
            else if (Integer.compare(a.numer(), b.numer()) != 0)
                return Integer.compare(a.numer(), b.numer()); // we need the lowest number
            else
                return 0;
        }
    }

    public Pokoj wybierzPokoj(Pokoj[] pokoje, Ankieta ankieta) throws BrakWolnychPokojow {
        List<Pokoj> wolnePokoje = wybierzWolnePokoje(pokoje, ankieta.dataPrzyjazdu(), ankieta.dataWyjazdu());
        if (wolnePokoje.size() == 0)
            throw new BrakWolnychPokojow();

        Pokoj r = Collections.min(Arrays.asList(pokoje), new ApproximateComparator(ankieta));
        if (ankieta.iloscSpelnionychWymagan(r) >= Ankieta.LICZBA_WYMAGAN / 2)
            return r;
        else
            return null;
    }

}
