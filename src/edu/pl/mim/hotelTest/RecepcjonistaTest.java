package edu.pl.mim.hotelTest;

import edu.pl.mim.hotel.Pokoj;
import edu.pl.mim.hotel.Receptionist.*;
import edu.pl.mim.hotel.Receptionist.ZlosliwyRecepcjonista;
import edu.pl.mim.hotel.Receptionist.PerfekcyjnyRecepcjonista;
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
 * Created by vlad on 19.04.16.
 */

public class RecepcjonistaTest {

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
        ankiety.add(new Ankieta(100, 1, Styl.Orientalny, Kolorystyka.Szary, Kierunek.Zachod, false, dataPrzyjazduM, 6));

        this.ankiety = ankiety.toArray(new Ankieta[ankiety.size()]);
    }

    @Test
    public void zlosliwyReceptionistaTest() throws Exception {
        ZlosliwyRecepcjonista recepcjonista = new ZlosliwyRecepcjonista("Uriah Heep");

        assertEquals(8, recepcjonista.wybierzPokoj(pokoje, ankiety[0]).numer());
        assertEquals(3, recepcjonista.wybierzPokoj(pokoje, ankiety[1]).numer());
        assertEquals(6, recepcjonista.wybierzPokoj(pokoje, ankiety[2]).numer());
        assertEquals(3, recepcjonista.wybierzPokoj(pokoje, ankiety[3]).numer());
        assertNull(recepcjonista.wybierzPokoj(pokoje, ankiety[4]));

        assertEquals("Uriah Heep, Złośliwy", recepcjonista.toString());
    }

    @Test
    public void perfekcyjnyRecepcjonistaTest() throws Exception {
        PerfekcyjnyRecepcjonista recepcjonista = new PerfekcyjnyRecepcjonista("Mrs. Clara Copperfield");

        assertEquals(6, recepcjonista.wybierzPokoj(pokoje, ankiety[0]).numer());
        assertNull(recepcjonista.wybierzPokoj(pokoje, ankiety[1]));
        assertEquals(8, recepcjonista.wybierzPokoj(pokoje, ankiety[2]).numer());
        assertNull(recepcjonista.wybierzPokoj(pokoje, ankiety[3]));
        assertNull(recepcjonista.wybierzPokoj(pokoje, ankiety[4]));

        assertEquals("Mrs. Clara Copperfield, Perfekcjonista", recepcjonista.toString());
    }

    @Test
    public void losowyRecepcjonistaTest() throws Exception {
        LosowyRecepcjonista recepcjonista = new LosowyRecepcjonista("Master Davy");

        assertNotNull(recepcjonista.wybierzPokoj(pokoje, ankiety[0]));
        assertNotNull(recepcjonista.wybierzPokoj(pokoje, ankiety[1]));
        assertNotNull(recepcjonista.wybierzPokoj(pokoje, ankiety[2]));
        assertNotNull(recepcjonista.wybierzPokoj(pokoje, ankiety[3]));
        assertNull(recepcjonista.wybierzPokoj(pokoje, ankiety[4]));

        assertEquals("Master Davy, Losowy", recepcjonista.toString());
    }

    @Test
    public void aproksymacyjnyRecepcjonistaTest() throws Exception {
        AproksymacyjnyRecepcjonista recepcjonista = new AproksymacyjnyRecepcjonista("James Steerforth");

        assertEquals(6, recepcjonista.wybierzPokoj(pokoje, ankiety[0]).numer());
        assertEquals(8, recepcjonista.wybierzPokoj(pokoje, ankiety[1]).numer());
        assertEquals(8, recepcjonista.wybierzPokoj(pokoje, ankiety[2]).numer());
        assertEquals(7, recepcjonista.wybierzPokoj(pokoje, ankiety[3]).numer());
        assertNull(recepcjonista.wybierzPokoj(pokoje, ankiety[4]));

        assertEquals("James Steerforth, Aproksymacyjny", recepcjonista.toString());
    }
}