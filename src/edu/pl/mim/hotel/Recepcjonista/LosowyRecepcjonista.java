package edu.pl.mim.hotel.Recepcjonista;

import edu.pl.mim.hotel.Ankieta.Ankieta;
import edu.pl.mim.hotel.Pokoj;

import java.util.List;
import java.util.Random;

/**
 * Created by vlad on 15.04.16.
 */
public class LosowyRecepcjonista extends Recepcjonista {

    public LosowyRecepcjonista(String imie) {
        super(imie);
        this.typ = "Losowy";
    }

    public Pokoj wybierzPokoj(Pokoj[] pokoje, Ankieta ankieta) throws BrakWolnychPokojow {
        List<Pokoj> wolnePokoje = wybierzWolnePokoje(pokoje, ankieta.dataPrzyjazdu(), ankieta.dataWyjazdu());
        if (wolnePokoje.size() == 0)
            throw new BrakWolnychPokojow();

        int ind = new Random().nextInt(wolnePokoje.size());

        return wolnePokoje.get(ind);
    }
}
