package edu.pl.mim.hotelTest;

import edu.pl.mim.hotel.Ankieta.Ankieta;
import edu.pl.mim.hotel.Ankieta.Kierunek;
import edu.pl.mim.hotel.Ankieta.Kolorystyka;
import edu.pl.mim.hotel.Ankieta.Styl;
import edu.pl.mim.hotel.Hotel;
import edu.pl.mim.hotel.Klient.*;
import edu.pl.mim.hotel.Pokoj;
import edu.pl.mim.hotel.Recepcjonista.*;
import edu.pl.mim.hotel.Zamowienie;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by vlad on 23.04.16.
 */
public class HotelTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    Pokoj[] pokoje;
    Zamowienie[] zamowienia;
    Recepcjonista[] recepcjonisci;

    @Before
    public void setUp() {

        Date dataPrzyjazdu = new GregorianCalendar(2016, 3, 1).getTime();
        Date dataPrzyjazduM = new GregorianCalendar(2016, 3, 9).getTime();

        List<Pokoj> pokoje = new ArrayList<>();

        pokoje.add(new Pokoj(1, 30, 1, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true));
        pokoje.add(new Pokoj(2 ,60, 1, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true));
        pokoje.add(new Pokoj(3 ,900, 1, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, true));
        pokoje.add(new Pokoj(4 ,30, 3, Styl.Morksi, Kolorystyka.Szary, Kierunek.Poludnie, false));
        pokoje.add(new Pokoj(5 ,310, 2, Styl.Secesyjny, Kolorystyka.Purpurowy, Kierunek.Wchod, true));
        pokoje.add(new Pokoj(6 ,210, 5, Styl.Rustykalny, Kolorystyka.Stalowy, Kierunek.Zachod, false));
        pokoje.add(new Pokoj(7, 1, 1300, Styl.Rustykalny, Kolorystyka.Stalowy, Kierunek.Zachod, true));
        pokoje.add(new Pokoj(8, 100500, 1, Styl.Nowoczesny, Kolorystyka.Jasnozielony, Kierunek.Wchod, true));
        pokoje.add(new Pokoj(9, 500, 9, Styl.Nowoczesny, Kolorystyka.Morski, Kierunek.Wchod, false));
        pokoje.add(new Pokoj(10, 50, 2, Styl.Nowoczesny, Kolorystyka.Morski, Kierunek.Wchod, true));

        for (Pokoj r : pokoje)
            try {
                r.rezerwuj(dataPrzyjazduM, 6);
            } catch (Exception e) {
                System.out.println("Błąd rezerwacji");
            }

        List<Ankieta> ankiety = new ArrayList<>();
        ankiety.add(new Ankieta(210, 5, Styl.Rustykalny, Kolorystyka.Stalowy, Kierunek.Zachod, false, dataPrzyjazdu, 4));
        ankiety.add(new Ankieta(10, 3, Styl.Nowoczesny, Kolorystyka.Jasnozielony, Kierunek.Wchod, false, dataPrzyjazdu, 4));
        ankiety.add(new Ankieta(1000400, 1, Styl.Nowoczesny, Kolorystyka.Jasnozielony, Kierunek.Wchod, true, dataPrzyjazdu, 4));
        ankiety.add(new Ankieta(100, 5, Styl.Rustykalny, Kolorystyka.Stalowy, Kierunek.Wchod, true, dataPrzyjazdu, 4));
        ankiety.add(new Ankieta(100, 1, Styl.Nowoczesny, Kolorystyka.Seledynowy, Kierunek.Polnoc, false, dataPrzyjazduM, 6));

        List<Klient> klienci = new ArrayList<>();
        klienci.add(new AproksymacyjnyKlient("Wilkins Micawber"));
        klienci.add(new UgodowyKlient("Jane Murdstone"));
        klienci.add(new PerfecyjnyKlient("Little Em'ly"));
        klienci.add(new WidokowyKlient("Mr. Sharp"));
        klienci.add(new BudzetowyKlient("Mr. Barkis"));

        List<Recepcjonista> recepcjonisci = new ArrayList<>();
        recepcjonisci.add(new AproksymacyjnyRecepcjonista("Ham Peggoty"));
        recepcjonisci.add(new LosowyRecepcjonista("Clara Paggoty"));
        recepcjonisci.add(new ZlosliwyRecepcjonista("Uriah Heep"));
        recepcjonisci.add(new PerfekcyjnyRecepcjonista("David Copperfield"));

        List<Zamowienie> zamowienia = new ArrayList<>();
        for (int i = 0; i < klienci.size(); i++) {
            zamowienia.add(Hotel.przyjmijZamowienie(klienci.get(i), ankiety.get(i)));
        }

        this.pokoje = pokoje.toArray(new Pokoj[pokoje.size()]);
        this.recepcjonisci = recepcjonisci.toArray(new Recepcjonista[recepcjonisci.size()]);
        this.zamowienia = zamowienia.toArray(new Zamowienie[zamowienia.size()]);
    }


    @Test
    public void brakPokojowTest() throws Exception {
        Hotel chesneyWold = new Hotel(pokoje, recepcjonisci);
        chesneyWold.akceptuj(null, pokoje, recepcjonisci);
        exception.expect(Hotel.BrakPokojow.class);
        chesneyWold.akceptuj(zamowienia, null, recepcjonisci);
    }

    @Test
    public void brakRecepjonistowTest() throws Exception {
        Hotel chesneyWold = new Hotel(pokoje, recepcjonisci);
        exception.expect(Hotel.BrakRecepcjonistow.class);
        chesneyWold.akceptuj(zamowienia, pokoje, null);
    }

    @Test
    public void nieznanyRecepcjonistaTest() throws Exception {
        Hotel chesneyWold = new Hotel(pokoje, new Recepcjonista[0]);
        exception.expect(Hotel.NieznanyRecepcjonista.class);
        chesneyWold.akceptuj(zamowienia, pokoje, recepcjonisci);
    }

    @Test
    public void nieznanyPokojTest() throws Exception {
        Hotel chesneyWold = new Hotel(new Pokoj[0], recepcjonisci);
        exception.expect(Hotel.NieznanyPokoj.class);
        chesneyWold.akceptuj(zamowienia, pokoje, recepcjonisci);
    }
}
