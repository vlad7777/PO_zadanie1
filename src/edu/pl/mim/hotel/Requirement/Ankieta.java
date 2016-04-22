package edu.pl.mim.hotel.Requirement;

import edu.pl.mim.hotel.Pokoj;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by vlad on 15.04.16.
 */

public class Ankieta {

    public static final int LICZBA_WYMAGAN = 6;

    private final int maksCena;
    private final int liczbaGosci;
    private final Styl styl;
    private final Kolorystyka kolorystyka;
    private final Kierunek kierunek;
    private final boolean czyPotrzebnyInternet;
    private final Date dataPrzyjazdu;
    private final Date dataWyjazdu;

    public Ankieta(int maksCena, int liczbaGosci, Styl styl, Kolorystyka kolorystyka, Kierunek kierunek, boolean czyPotrzebnyInternet, Date dataPrzyjazdu, int dlugoscPobytu) {
        this.maksCena = maksCena;
        this.liczbaGosci = liczbaGosci;
        this.styl = styl;
        this.kolorystyka = kolorystyka;
        this.kierunek = kierunek;
        this.czyPotrzebnyInternet = czyPotrzebnyInternet;
        this.dataPrzyjazdu = dataPrzyjazdu;
        this.dataWyjazdu = dodajDni(dataPrzyjazdu, dlugoscPobytu);
    }

    public int iloscSpelnionychWymagan(Pokoj pokoj) {

        //critical requirement: we can't put someone in a pokoj that is already booked
        if (!pokoj.sprawdzDostepnosc(dataPrzyjazdu, dataWyjazdu))
            return -1;

        int ret = 0;

        if (pokoj.maksymalnaLiczbaGosci() >= liczbaGosci)
            ret++;

        if (maksCena >= pokoj.cena())
            ret++;

        if (styl == pokoj.styl())
            ret++;

        if (kolorystyka == pokoj.kolorystyka())
            ret++;

        if (kierunek == pokoj.kierunek())
            ret++;

        if (!czyPotrzebnyInternet || (czyPotrzebnyInternet == pokoj.czyDostepnyInternet()))
            ret++;

        return ret;
    }

    public static Date dodajDni(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    @Override
    public String toString() {
        String res = "";
        if (liczbaGosci == 1)
            res += "1 łóżko, ";
        else
            res += liczbaGosci + " łóżka są potrzebne, ";
        res += "maksymalna cena " + maksCena + " PLN, ";
        res += styl.toString() + " styl, ";
        res += kolorystyka.toString() + " kolorystyka, ";
        res += "okna skierowane na " + kierunek.toString() + ", ";
        res += "Internet " + (czyPotrzebnyInternet ? "" : "nie ") + "potrzebny";
        return res;
    }

    public boolean czyPasujeDoskonale(Pokoj pokoj) {
        return iloscSpelnionychWymagan(pokoj) == LICZBA_WYMAGAN;
    }

    public Date dataWyjazdu() {
        return dataWyjazdu;
    }

    public Date dataPrzyjazdu() {
        return dataPrzyjazdu;
    }

    public Kierunek kierunek() {
        return kierunek;
    }

    public int maksCena() {
        return maksCena;
    }

}
