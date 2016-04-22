package edu.pl.mim.hotelTest;

import edu.pl.mim.hotel.Requirement.Kolorystyka;
import edu.pl.mim.hotel.Requirement.Kierunek;
import edu.pl.mim.hotel.Requirement.Ankieta;
import edu.pl.mim.hotel.Requirement.Styl;
import edu.pl.mim.hotel.Pokoj;

import java.util.Date;
import static org.junit.Assert.*;

/**
 * Created by vlad on 18.04.16.
 */
public class AnkietaTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void getNumberOfFulfilledRequirements() throws Exception {
        Date march1 = new Date(2016, 3, 1, 14, 00);
        Date march2 = new Date(2016, 3, 2, 14, 00);
        Date march3 = new Date(2016, 3, 3, 14, 00);

        Pokoj r1 = new Pokoj(1, 200, 3, Styl.Morksi, Kolorystyka.Seledynowy, Kierunek.Wchod, true);
        r1.rezerwuj(march1, march2);

        Ankieta req1 = new Ankieta(100, 2, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true, march1, 1);
        assertEquals(-1, req1.iloscSpelnionychWymagan(r1));

        Ankieta req2 = new Ankieta(100, 2, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true, march3, 1);
        assertEquals(3, req2.iloscSpelnionychWymagan(r1));
    }

    @org.junit.Test
    public void doskonalyTest() throws Exception {
        Date march1 = new Date(2016, 3, 1, 14, 00);

        Pokoj r1 = new Pokoj(1, 100, 2, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true);

        Ankieta req1 = new Ankieta(100, 2, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true, march1, 1);
        assertTrue(req1.czyPasujeDoskonale(r1));

        Ankieta req2 = new Ankieta(100, 2, Styl.Orientalny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true, march1, 1);
        assertFalse(req2.czyPasujeDoskonale(r1));

    }

}