package edu.pl.mim.hotelTest;

import edu.pl.mim.hotel.Ankieta.Kierunek;
import edu.pl.mim.hotel.Ankieta.Kolorystyka;
import edu.pl.mim.hotel.Ankieta.Styl;
import edu.pl.mim.hotel.Pokoj;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by vlad on 19.04.16.
 */
public class PokojTest {
    @Rule
    ExpectedException exception = ExpectedException.none();

    @Test
    public void rezerwacjaTest() throws Exception {

        Date marzec1 = new GregorianCalendar(2016, 3, 1, 14, 00).getTime();
        Date marzec2 = new GregorianCalendar(2016, 3, 2, 14, 00).getTime();
        Date marzec3 = new GregorianCalendar(2016, 3, 3, 14, 00).getTime();
        Date marzec4 = new GregorianCalendar(2016, 3, 4, 14, 00).getTime();

        Pokoj r1 = new Pokoj(1, 200, 3, Styl.Morksi, Kolorystyka.Seledynowy, Kierunek.Wchod, true);
        r1.rezerwuj(marzec2, marzec4);

        assertFalse(r1.sprawdzDostepnosc(marzec1, marzec3));
        assertFalse(r1.sprawdzDostepnosc(marzec2, marzec4));
        assertTrue(r1.sprawdzDostepnosc(marzec1, marzec2));
    }

    @Test
    void pokojJuzZajetyTest() throws Exception {
        Date dzis = new Date();

        Pokoj r = new Pokoj(1, 1, 1, Styl.Orientalny, Kolorystyka.Jasnozielony, Kierunek.Polnoc, true);
        r.rezerwuj(dzis, 1);
        exception.expect(Pokoj.PokojJuzZajety.class);
        r.rezerwuj(dzis, 1);
    }

}