package edu.pl.mim.hotel.Ankieta;

/**
 * Created by vlad on 15.04.16.
 */

public enum Kierunek {

    Polnoc("Północ"), Poludnie("Południe"), Wchod("Wschód"), Zachod("Zachód");

    private final String wartosc;

    Kierunek(String wartosc) {
        this.wartosc = wartosc;
    }

    @Override
    public String toString() {
        return wartosc;
    }
}
