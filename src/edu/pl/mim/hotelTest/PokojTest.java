package edu.pl.mim.hotelTest;

import edu.pl.mim.hotel.Requirement.Kolorystyka;
import edu.pl.mim.hotel.Requirement.Kierunek;
import edu.pl.mim.hotel.Requirement.Styl;
import edu.pl.mim.hotel.Pokoj;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by vlad on 19.04.16.
 */
public class PokojTest {
    @Test
    public void rezerwacjaTest() throws Exception {
        Date marzec1 = new Date(2016, 3, 1, 14, 00);
        Date marzec2 = new Date(2016, 3, 2, 14, 00);
        Date marzec3 = new Date(2016, 3, 3, 14, 00);
        Date marzec4 = new Date(2016, 3, 4, 14, 00);

        Pokoj r1 = new Pokoj(1, 200, 3, Styl.Morksi, Kolorystyka.Seledynowy, Kierunek.Wchod, true);
        r1.rezerwuj(marzec2, marzec4);

        assertFalse(r1.sprawdzDostepnosc(marzec1, marzec3));
        assertFalse(r1.sprawdzDostepnosc(marzec2, marzec4));
        assertTrue(r1.sprawdzDostepnosc(marzec1, marzec2));
    }

}