package edu.pl.mim.hotel.Recepcjonista;

import edu.pl.mim.hotel.Pokoj;
import edu.pl.mim.hotel.Ankieta.Ankieta;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by vlad on 15.04.16.
 */
public class PerfekcyjnyRecepcjonista extends Recepcjonista {

    public PerfekcyjnyRecepcjonista(String imie) {
        super(imie);
        this.typ = "Perfekcjonista";
    }

    private class PerfectComparator implements Comparator<Pokoj> {

        private Ankieta ankieta;

        public PerfectComparator(Ankieta ankieta) {
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
            else if (Integer.compare(a.numer(), b.numer()) != 0)
                return Integer.compare(a.numer(), b.numer()); // we need the lowest number
            else
                return 0;
        }
    }

    public Pokoj wybierzPokoj(Pokoj[] pokoje, Ankieta ankieta) {
        Pokoj r = Collections.min(Arrays.asList(pokoje), new PerfectComparator(ankieta));
        if (r != null && ankieta.czyPasujeDoskonale(r))
            return r;
        return null;
    }
}
