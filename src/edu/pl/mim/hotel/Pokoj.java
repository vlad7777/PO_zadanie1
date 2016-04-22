package edu.pl.mim.hotel;

import edu.pl.mim.hotel.Requirement.Kolorystyka;
import edu.pl.mim.hotel.Requirement.Kierunek;
import edu.pl.mim.hotel.Requirement.Ankieta;
import edu.pl.mim.hotel.Requirement.Styl;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vlad on 15.04.16.
 */
public class Pokoj {

    private final int numer;
    private final int cena;
    private final int maksymalnaLiczbaGosci;
    private final Styl styl;
    private final Kolorystyka kolorystyka;
    private final Kierunek kierunek;
    private final boolean czyDostepnyInternet;
    private List<Pair<Date, Date>> zarezerwowane = new ArrayList<>();

    public class RoomAlreadyBookedException extends Exception {};


    public Pokoj(int numer, int cena, int maksymalnaLiczbaGosci, Styl styl, Kolorystyka kolorystyka, Kierunek kierunek, boolean czyDostepnyInternet) {
        this.numer = numer;
        this.cena = cena;
        this.maksymalnaLiczbaGosci = maksymalnaLiczbaGosci;
        this.styl = styl;
        this.kolorystyka = kolorystyka;
        this.kierunek = kierunek;
        this.czyDostepnyInternet = czyDostepnyInternet;
    }

    @Override
    public String toString() {
        String res = "";
        res += "liczba miejsc " + maksymalnaLiczbaGosci;
        res += cena + " PLN, ";
        res += styl.toString() + " styl, ";
        res += kolorystyka.toString() + " kolorystyka, ";
        res += "okna skierowane na " + kierunek.toString() + ", ";
        res += "Internet " + (czyDostepnyInternet ? "" : "nie ") + "dostępny";
        return res;
    }

    public boolean sprawdzDostepnosc(Date dataPrzyjazdu, Date dataWyjazdu) {
        for (Pair<Date, Date> p : zarezerwowane) {
            if (p.getKey().compareTo(dataPrzyjazdu) <= 0 && p.getValue().compareTo(dataWyjazdu) > 0)
                return false;
            if (dataPrzyjazdu.compareTo(p.getKey()) <= 0 && dataWyjazdu.compareTo(p.getKey()) > 0)
                return false;
        }
        return true;
    }

    public void rezerwuj(Date dataPrzyjazdu, int dlugoscPobytu) throws RoomAlreadyBookedException {
        rezerwuj(dataPrzyjazdu, Ankieta.dodajDni(dataPrzyjazdu, dlugoscPobytu));
    }

    public void rezerwuj(Date dataPrzyjazdu, Date dataWyjazdu) throws RoomAlreadyBookedException {
        if (!sprawdzDostepnosc(dataPrzyjazdu, dataWyjazdu))
            throw new RoomAlreadyBookedException();
        zarezerwowane.add(new Pair<Date, Date>(dataPrzyjazdu, dataWyjazdu));
    }

    public boolean czyDostepnyInternet() {
        return czyDostepnyInternet;
    }

    public Kierunek kierunek() {
        return kierunek;
    }

    public Kolorystyka kolorystyka() {
        return kolorystyka;
    }

    public Styl styl() {
        return styl;
    }

    public int maksymalnaLiczbaGosci() {
        return maksymalnaLiczbaGosci;
    }

    public int cena() {
        return cena;
    }

    public int numer() {
        return numer;
    }

}
