package edu.pl.mim.hotelTest;

import edu.pl.mim.hotel.Ankieta.Kolorystyka;
import edu.pl.mim.hotel.Ankieta.Kierunek;
import edu.pl.mim.hotel.Ankieta.Ankieta;
import edu.pl.mim.hotel.Ankieta.Styl;
import edu.pl.mim.hotel.Pokoj;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by vlad on 18.04.16.
 */
public class AnkietaTest {

    @Test
    public void getNumberOfFulfilledRequirements() throws Exception {
        Date marzec1 = new GregorianCalendar(2016, 3, 1).getTime();
        Date marzec2 = new GregorianCalendar(2016, 3, 2).getTime();
        Date marzec3 = new GregorianCalendar(2016, 3, 3).getTime();

        Pokoj r1 = new Pokoj(1, 200, 3, Styl.Morksi, Kolorystyka.Seledynowy, Kierunek.Wchod, true);
        r1.rezerwuj(marzec1, marzec2);

        Ankieta req1 = new Ankieta(100, 2, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true, marzec1, 1);
        assertEquals(-1, req1.iloscSpelnionychWymagan(r1));

        Ankieta req2 = new Ankieta(100, 2, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true, marzec3, 1);
        assertEquals(3, req2.iloscSpelnionychWymagan(r1));

    }

    @Test
    public void toStringTest() throws Exception {
        Date marzec1 = new GregorianCalendar(2016, 3, 1).getTime();
        Ankieta req1 = new Ankieta(100, 2, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true, marzec1, 1);
        assertEquals("od 2016-04-01 do 2016-04-02, liczba miejsc: 2, maksymalna cena 100 PLN, Nowoczesny styl, Seledynowa kolorystyka, okna skierowane na Północ, Internet potrzebny", req1.toString());
    }


    @Test
    public void doskonalyTest() throws Exception {
        Date marzec1 = new GregorianCalendar(2016, 3, 1).getTime();

        Pokoj r1 = new Pokoj(1, 100, 2, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true);

        Ankieta req1 = new Ankieta(100, 2, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true, marzec1, 1);
        assertTrue(req1.czyPasujeDoskonale(r1));

        Ankieta req2 = new Ankieta(100, 2, Styl.Orientalny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true, marzec1, 1);
        assertFalse(req2.czyPasujeDoskonale(r1));

    }

}