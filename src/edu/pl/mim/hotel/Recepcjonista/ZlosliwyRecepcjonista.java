package edu.pl.mim.hotel.Recepcjonista;

import edu.pl.mim.hotel.Ankieta.Ankieta;
import edu.pl.mim.hotel.Pokoj;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by vlad on 15.04.16.
 */
public class ZlosliwyRecepcjonista extends Recepcjonista {

    public ZlosliwyRecepcjonista(String imie) {
        super(imie);
        this.typ = "Złośliwy";
    }

    private class EvilComparator implements Comparator<Pokoj> {

        private Ankieta ankieta;

        public EvilComparator(Ankieta ankieta) {
            this.ankieta = ankieta;
        }

        @Override
        public int compare(Pokoj a, Pokoj b) {
            int reqA = ankieta.iloscSpelnionychWymagan(a);
            if (reqA == -1)
                return 1;

            int reqB = ankieta.iloscSpelnionychWymagan(b);
            if (reqB == -1)
                return -1;

            if (Integer.compare(reqA, reqB) != 0)
                return Integer.compare(reqA, reqB); // we need a room that fits the worst
            else if (Integer.compare(a.cena(), b.cena()) != 0)
                return -1 * Integer.compare(a.cena(), b.cena()); // we need an expensive room
            else if (Integer.compare(a.numer(), b.numer()) != 0)
                return Integer.compare(a.numer(), b.numer()); // we need the lowest number
            else
                return 0;
        }
    }

    @Override
    public Pokoj wybierzPokoj(Pokoj[] pokoje, Ankieta ankieta) throws BrakWolnychPokojow {
        List<Pokoj> wolnePokoje = wybierzWolnePokoje(pokoje, ankieta.dataPrzyjazdu(), ankieta.dataWyjazdu());
        if (wolnePokoje.size() == 0)
            throw new BrakWolnychPokojow();

        Pokoj r = Collections.min(wolnePokoje, new EvilComparator(ankieta));
        return r;
    }

}
