package edu.pl.mim.hotel;

import edu.pl.mim.hotel.Klient.*;
import edu.pl.mim.hotel.Recepcjonista.*;
import edu.pl.mim.hotel.Ankieta.Ankieta;
import edu.pl.mim.hotel.Ankieta.Kierunek;
import edu.pl.mim.hotel.Ankieta.Kolorystyka;
import edu.pl.mim.hotel.Ankieta.Styl;

import java.util.*;

/**
 * Created by vlad on 15.04.16.
 */

public class Hotel {

    public static class NieznanyRecepcjonista extends Exception {
        public NieznanyRecepcjonista(String s){ super(s); }
    };

    public static class NieznanyPokoj extends Exception {
        public NieznanyPokoj(String s) { super(s); }
    };

    public static class BrakRecepcjonistow extends Exception { };

    public static class BrakPokojow extends Exception { };

    private List<Pokoj> pokoje = new ArrayList<>();
    private List<Recepcjonista> recepcjonisci = new ArrayList<>();

    public Hotel(Pokoj[] pokoje, Recepcjonista[] recepcjonisci) {
        if (pokoje == null || recepcjonisci == null)
            throw new NullPointerException();
        this.pokoje.addAll(Arrays.asList(pokoje));
        this.recepcjonisci.addAll(Arrays.asList(recepcjonisci));
    }

    public void akceptuj(Zamowienie[] zamowienia, Pokoj[] pokoje, Recepcjonista[] recepcjonisci) throws Exception {

        if (zamowienia == null)
            return;

        if ((recepcjonisci == null || recepcjonisci.length == 0) && zamowienia.length > 0)
            throw new BrakRecepcjonistow();

        if ((pokoje == null || pokoje.length == 0) && zamowienia.length > 0)
            throw new BrakPokojow();

        for (Pokoj pokoj : pokoje) {
            if (!this.pokoje.contains(pokoj)) {
                throw new NieznanyPokoj("W tym hotelu nie ma pokoju numer " + pokoj.numer());
            }
        }

        for (Recepcjonista recepcjonista : recepcjonisci) {
            if (!this.recepcjonisci.contains(recepcjonista)) {
                throw new NieznanyRecepcjonista("W tym hotelu nie pracuje recepcjonisty o imienui " + recepcjonista.imie());
            }
        }
        Queue<Zamowienie> queue = new ArrayDeque<>();
        queue.addAll(Arrays.asList(zamowienia));

        int aktualnyRecepcjonista = 0;

        while (!queue.isEmpty()) {
            Zamowienie aktualneZamowienie = queue.poll();

            if (aktualneZamowienie.ileRazZobaczone() == 3)
                continue;
            aktualneZamowienie.zobacz();

            System.out.println("----------------------------");
            System.out.println("Recepcjonista: " + recepcjonisci[aktualnyRecepcjonista].toString());
            System.out.println("Zamówienie: " + aktualneZamowienie.toString());

            try {
                Pokoj wybranyPokoj = recepcjonisci[aktualnyRecepcjonista].wybierzPokoj(pokoje, aktualneZamowienie.ankieta());
                System.out.println("Proponowany pokój: " + (wybranyPokoj == null ? "nie znalieżiono odpowiedniego pokoju" : wybranyPokoj.toString()));
                System.out.println("Klient: " + aktualneZamowienie.klient().toString());

                if (wybranyPokoj != null && recepcjonisci[aktualnyRecepcjonista].zapytajKlienta(wybranyPokoj, aktualneZamowienie.ankieta(), aktualneZamowienie.klient())) {
                    aktualneZamowienie.zakoncz(wybranyPokoj);
                    System.out.println("tak");
                } else {
                    queue.add(aktualneZamowienie);
                    System.out.println("nie");
                }
            } catch (Recepcjonista.BrakWolnychPokojow e) {
                System.out.println("Proponowany pokój: brak wolnych pokojów, usuwamy zamówienie");
                System.out.println("Klient: " + aktualneZamowienie.klient().toString());
                System.out.println("nie");
            }

            System.out.println("-----------------------------\n\n");

            aktualnyRecepcjonista++;
            aktualnyRecepcjonista %= recepcjonisci.length;
        }
    }

    public static Zamowienie przyjmijZamowienie(Klient klient, Ankieta ankieta) {
        return new Zamowienie(klient, ankieta);
    }

    public static void main(String[] args) {

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

        Pokoj[] pokojeArray = pokoje.toArray(new Pokoj[pokoje.size()]);
        Recepcjonista[] recepcjonisciArray = recepcjonisci.toArray(new Recepcjonista[recepcjonisci.size()]);
        Zamowienie[] zamowieniaArray = zamowienia.toArray(new Zamowienie[zamowienia.size()]);

        Hotel greenGables = new Hotel(pokojeArray, recepcjonisciArray);
        try {
            greenGables.akceptuj(zamowieniaArray, pokojeArray, recepcjonisciArray);
        } catch (BrakPokojow e) {
            System.out.println("Błąd: Brak pokojów");
        } catch (BrakRecepcjonistow e) {
            System.out.println("Błąd: Brak recepcjonistów");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
