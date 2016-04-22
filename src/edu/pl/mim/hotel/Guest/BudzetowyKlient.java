package edu.pl.mim.hotel.Guest;

import edu.pl.mim.hotel.Pokoj;
import edu.pl.mim.hotel.Requirement.Ankieta;

/**
 * Created by vlad on 20.04.16.
 */
public class BudzetowyKlient extends Klient {

    public BudzetowyKlient(String name) {
        super(name);
        this.typ = "Budżetowy";
    }

    @Override
    public boolean rozwaz(Pokoj pokoj, Ankieta ankieta) {
        return ankieta.iloscSpelnionychWymagan(pokoj) > -1 && pokoj.cena() <= ankieta.maksCena();
    }
}
