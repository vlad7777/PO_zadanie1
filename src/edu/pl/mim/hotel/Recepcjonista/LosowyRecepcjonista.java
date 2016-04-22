package edu.pl.mim.hotel.Recepcjonista;

import edu.pl.mim.hotel.Pokoj;
import edu.pl.mim.hotel.Ankieta.Ankieta;

import java.util.Random;

/**
 * Created by vlad on 15.04.16.
 */
public class LosowyRecepcjonista extends Recepcjonista {

    public LosowyRecepcjonista(String imie) {
        super(imie);
        this.typ = "Losowy";
    }

    public Pokoj wybierzPokoj(Pokoj[] pokoje, Ankieta ankieta) {
       int freeRooms = 0;
        for (Pokoj pokoj : pokoje) {
            if (ankieta.iloscSpelnionychWymagan(pokoj) != -1)
                freeRooms++;
        }

        if (freeRooms == 0)
            return null;

        int ind = new Random().nextInt(freeRooms);

        for (Pokoj pokoj : pokoje) {
            if (ankieta.iloscSpelnionychWymagan(pokoj) != -1) {
                if (ind == 0)
                    return pokoj;
                ind--;
            }
        }
        return null;
    }
}
