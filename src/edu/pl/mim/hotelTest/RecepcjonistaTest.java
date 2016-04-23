package edu.pl.mim.hotelTest;

import edu.pl.mim.hotel.Ankieta.Ankieta;
import edu.pl.mim.hotel.Ankieta.Kierunek;
import edu.pl.mim.hotel.Ankieta.Kolorystyka;
import edu.pl.mim.hotel.Ankieta.Styl;
import edu.pl.mim.hotel.Pokoj;
import edu.pl.mim.hotel.Recepcjonista.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vlad on 19.04.16.
 */

public class RecepcjonistaTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Pokoj[] pokoje;
    private Ankieta[] ankiety;

    @Before
    public void setUp() throws Exception {

        Date dataPrzyjazdu = new GregorianCalendar(2016, 3, 1).getTime();
        Date dataPrzyjazduM = new GregorianCalendar(2016, 3, 9).getTime();

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
            r.rezerwuj(dataPrzyjazduM, 6);

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
    public void wybierzWolnePokojeTest() throws Exception {
        Date dataPrzyjazdu = new GregorianCalendar(2016, 3, 1).getTime();
        Date dataWyjazdu = Ankieta.dodajDni(dataPrzyjazdu, 4);
        Date dataPrzyjazduM = new GregorianCalendar(2016, 3, 9).getTime();
        Date dataWyjazduM = Ankieta.dodajDni(dataPrzyjazduM, 6);

        List<Pokoj> pokoje = Recepcjonista.wybierzWolnePokoje(this.pokoje, dataPrzyjazdu, dataWyjazdu);
        assertEquals(this.pokoje.length, pokoje.size());

        pokoje = Recepcjonista.wybierzWolnePokoje(this.pokoje, dataPrzyjazduM, dataWyjazduM);
        assertEquals(0, pokoje.size());
    }

    @Test
    public void zlosliwyReceptionistaTest() throws Exception {
        ZlosliwyRecepcjonista recepcjonista = new ZlosliwyRecepcjonista("Uriah Heep");

        assertEquals("Uriah Heep, Złośliwy", recepcjonista.toString());

        assertEquals(8, recepcjonista.wybierzPokoj(pokoje, ankiety[0]).numer());
        assertEquals(3, recepcjonista.wybierzPokoj(pokoje, ankiety[1]).numer());
        assertEquals(6, recepcjonista.wybierzPokoj(pokoje, ankiety[2]).numer());
        assertEquals(3, recepcjonista.wybierzPokoj(pokoje, ankiety[3]).numer());

        exception.expect(Recepcjonista.BrakWolnychPokojow.class);
        assertNull(recepcjonista.wybierzPokoj(pokoje, ankiety[4]));
    }

    @Test
    public void perfekcyjnyRecepcjonistaTest() throws Exception {
        PerfekcyjnyRecepcjonista recepcjonista = new PerfekcyjnyRecepcjonista("Mrs. Clara Copperfield");

        assertEquals("Mrs. Clara Copperfield, Perfekcjonista", recepcjonista.toString());

        assertEquals(6, recepcjonista.wybierzPokoj(pokoje, ankiety[0]).numer());
        assertNull(recepcjonista.wybierzPokoj(pokoje, ankiety[1]));
        assertEquals(8, recepcjonista.wybierzPokoj(pokoje, ankiety[2]).numer());
        assertNull(recepcjonista.wybierzPokoj(pokoje, ankiety[3]));

        exception.expect(Recepcjonista.BrakWolnychPokojow.class);
        assertNull(recepcjonista.wybierzPokoj(pokoje, ankiety[4]));
    }

    @Test
    public void losowyRecepcjonistaTest() throws Exception {
        LosowyRecepcjonista recepcjonista = new LosowyRecepcjonista("Master Davy");

        assertEquals("Master Davy, Losowy", recepcjonista.toString());

        assertNotNull(recepcjonista.wybierzPokoj(pokoje, ankiety[0]));
        assertNotNull(recepcjonista.wybierzPokoj(pokoje, ankiety[1]));
        assertNotNull(recepcjonista.wybierzPokoj(pokoje, ankiety[2]));
        assertNotNull(recepcjonista.wybierzPokoj(pokoje, ankiety[3]));

        exception.expect(Recepcjonista.BrakWolnychPokojow.class);
        assertNull(recepcjonista.wybierzPokoj(pokoje, ankiety[4]));
    }

    @Test
    public void aproksymacyjnyRecepcjonistaTest() throws Exception {
        AproksymacyjnyRecepcjonista recepcjonista = new AproksymacyjnyRecepcjonista("James Steerforth");

        assertEquals("James Steerforth, Aproksymacyjny", recepcjonista.toString());

        assertEquals(6, recepcjonista.wybierzPokoj(pokoje, ankiety[0]).numer());
        assertEquals(8, recepcjonista.wybierzPokoj(pokoje, ankiety[1]).numer());
        assertEquals(8, recepcjonista.wybierzPokoj(pokoje, ankiety[2]).numer());
        assertEquals(7, recepcjonista.wybierzPokoj(pokoje, ankiety[3]).numer());

        exception.expect(Recepcjonista.BrakWolnychPokojow.class);
        assertNull(recepcjonista.wybierzPokoj(pokoje, ankiety[4]));
    }
}