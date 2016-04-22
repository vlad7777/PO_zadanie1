package edu.pl.mim.hotelTest;

import edu.pl.mim.hotel.Guest.*;
import edu.pl.mim.hotel.Pokoj;
import edu.pl.mim.hotel.Requirement.Kolorystyka;
import edu.pl.mim.hotel.Requirement.Kierunek;
import edu.pl.mim.hotel.Requirement.Ankieta;
import edu.pl.mim.hotel.Requirement.Styl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vlad on 20.04.16.
 */
public class KlientTest {

    private Pokoj[] pokoje;
    private Ankieta[] ankiety;

    @Before
    public void setUp() throws Exception {
        Date dataPrzyjazdu = new Date(2016, 03, 01);
        Date dataWyjazdu = new Date(2016, 03, 05);

        Date dataPrzyjazduM = new Date(2016, 03, 9);
        Date dataWyjazduM = new Date(2016, 03, 15);

        List<Pokoj> listaPokojow = new ArrayList<>();

        listaPokojow.add(new Pokoj(1, 30, 1, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true));
        listaPokojow.add(new Pokoj(2 ,60, 1, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true));
        listaPokojow.add(new Pokoj(3 ,900, 1, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true));
        listaPokojow.add(new Pokoj(4 ,30, 3, Styl.Morksi, Kolorystyka.Szary, Kierunek.Poludnie, false));
        listaPokojow.add(new Pokoj(5 ,310, 2, Styl.Secesyjny, Kolorystyka.Purpurowy, Kierunek.Wchod, true));
        listaPokojow.add(new Pokoj(6 ,210, 5, Styl.Rustykalny, Kolorystyka.Stalowy, Kierunek.Zachod, false));
        listaPokojow.add(new Pokoj(7, 1, 1300, Styl.Rustykalny, Kolorystyka.Stalowy, Kierunek.Zachod, true));
        listaPokojow.add(new Pokoj(8, 100500, 1, Styl.Nowoczesny, Kolorystyka.Jasnozielony, Kierunek.Wchod, true));
        listaPokojow.add(new Pokoj(9, 500, 9, Styl.Nowoczesny, Kolorystyka.Morski, Kierunek.Wchod, false));
        listaPokojow.add(new Pokoj(10, 50, 2, Styl.Nowoczesny, Kolorystyka.Morski, Kierunek.Wchod, true));
        for (Pokoj r : listaPokojow)
            r.rezerwuj(dataPrzyjazduM, dataWyjazduM);

        this.pokoje = listaPokojow.toArray(new Pokoj[listaPokojow.size()]);

        List<Ankieta> ankiety = new ArrayList<>();
        ankiety.add(new Ankieta(210, 5, Styl.Rustykalny, Kolorystyka.Stalowy, Kierunek.Zachod, false, dataPrzyjazdu, 4));
        ankiety.add(new Ankieta(10, 3, Styl.Nowoczesny, Kolorystyka.Jasnozielony, Kierunek.Wchod, false, dataPrzyjazdu, 4));
        ankiety.add(new Ankieta(1000400, 1, Styl.Nowoczesny, Kolorystyka.Jasnozielony, Kierunek.Wchod, true, dataPrzyjazdu, 4));
        ankiety.add(new Ankieta(100, 5, Styl.Rustykalny, Kolorystyka.Stalowy, Kierunek.Wchod, true, dataPrzyjazdu, 4));
        ankiety.add(new Ankieta(100, 1, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, false, dataPrzyjazduM, 6));

        this.ankiety = ankiety.toArray(new Ankieta[ankiety.size()]);
    }

    @Test
    public void perfekcyjnyKlientTest() throws Exception {
        PerfecyjnyKlient gosc = new PerfecyjnyKlient("Agnes Wickfield");
        assertTrue(gosc.rozwaz(pokoje[5], ankiety[0]));
        assertTrue(gosc.rozwaz(pokoje[7], ankiety[2]));
        assertFalse(gosc.rozwaz(pokoje[9], ankiety[4]));
        assertFalse(gosc.rozwaz(pokoje[0], ankiety[2]));

        assertEquals("Agnes Wickfield, Perfekcjonista", gosc.toString());
    }

    @Test
    public void ugodowyKlientTest() throws Exception {
        UgodowyKlient gosc = new UgodowyKlient("Tommy Traddles");
        assertTrue(gosc.rozwaz(pokoje[1], ankiety[2]));
        assertTrue(gosc.rozwaz(pokoje[2], ankiety[0]));
        assertFalse(gosc.rozwaz(pokoje[2], ankiety[4]));

        assertEquals("Tommy Traddles, Ugodowy", gosc.toString());
    }

    @Test
    public void widokowyKlientTest() throws Exception {
        WidokowyKlient gosc = new WidokowyKlient("David Copperfield");
        assertTrue(gosc.rozwaz(pokoje[8], ankiety[3]));
        assertFalse(gosc.rozwaz(pokoje[0], ankiety[1]));
        assertFalse(gosc.rozwaz(pokoje[0], ankiety[4]));

        assertEquals("David Copperfield, Widokowy", gosc.toString());
    }

    @Test
    public void budzetowyKlientTest() throws Exception {
        BudzetowyKlient gosc = new BudzetowyKlient("Mr. Barkis");
        assertTrue(gosc.rozwaz(pokoje[6], ankiety[2]));
        assertFalse(gosc.rozwaz(pokoje[3], ankiety[1]));
        assertFalse(gosc.rozwaz(pokoje[0], ankiety[4]));

        assertEquals("Mr. Barkis, Budżetowy", gosc.toString());
    }

    @Test
    public void aproksymacyjnyKlientTest() throws Exception {
        AproksymacyjnyKlient gosc = new AproksymacyjnyKlient("Betsy Trotwood");
        assertTrue(gosc.rozwaz(pokoje[7], ankiety[2]));
        assertFalse(gosc.rozwaz(pokoje[8], ankiety[0]));
        assertFalse(gosc.rozwaz(pokoje[0], ankiety[4]));

        assertEquals("Betsy Trotwood, Aproksymacyjny", gosc.toString());
    }

}
