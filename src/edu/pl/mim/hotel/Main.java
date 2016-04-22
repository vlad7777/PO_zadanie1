package edu.pl.mim.hotel;

import edu.pl.mim.hotel.Guest.*;
import edu.pl.mim.hotel.Receptionist.*;
import edu.pl.mim.hotel.Requirement.ColourPalette;
import edu.pl.mim.hotel.Requirement.Direction;
import edu.pl.mim.hotel.Requirement.Requirements;
import edu.pl.mim.hotel.Requirement.Style;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Date checkIn = new Date(2016, 03, 01);
        Date checkOut = new Date(2016, 03, 05);

        Date checkInM = new Date(2016, 03, 9);
        Date checkOutM = new Date(2016, 03, 15);

        List<Room> roomsList = new ArrayList<>();

        roomsList.add(new Room(1, 30, 1, Style.Modern, ColourPalette.Celadon, Direction.North, true));
        roomsList.add(new Room(2 ,60, 1, Style.Modern, ColourPalette.Celadon, Direction.North, true));
        roomsList.add(new Room(3 ,900, 1, Style.Modern, ColourPalette.Celadon, Direction.North, true));
        roomsList.add(new Room(4 ,30, 3, Style.Navy, ColourPalette.Grey, Direction.South, false));
        roomsList.add(new Room(5 ,310, 2, Style.Secessionist, ColourPalette.Purple, Direction.East, true));
        roomsList.add(new Room(6 ,210, 5, Style.Rustic, ColourPalette.Steel, Direction.West, false));
        roomsList.add(new Room(7, 1, 1300, Style.Rustic, ColourPalette.Steel, Direction.West, true));
        roomsList.add(new Room(8, 100500, 1, Style.Modern, ColourPalette.LightGreen, Direction.East, true));
        roomsList.add(new Room(9, 500, 9, Style.Modern, ColourPalette.Navy, Direction.East, false));
        roomsList.add(new Room(10, 50, 2, Style.Modern, ColourPalette.Navy, Direction.East, true));

        for (Room r : roomsList)
            try {
                r.book(checkInM, checkOutM);
            } catch (Exception e) {
                System.out.println("Room booking error");
            }

        List<Requirements> requirements = new ArrayList<>();
        requirements.add(new Requirements(210, 5, Style.Rustic, ColourPalette.Steel, Direction.West, false, checkIn, 4));
        requirements.add(new Requirements(10, 3, Style.Modern, ColourPalette.LightGreen, Direction.East, false, checkIn, 4));
        requirements.add(new Requirements(1000400, 1, Style.Modern, ColourPalette.LightGreen, Direction.East, true, checkIn, 4));
        requirements.add(new Requirements(100, 5, Style.Rustic, ColourPalette.Steel, Direction.East, true, checkIn, 4));
        requirements.add(new Requirements(100, 1, Style.Modern, ColourPalette.Celadon, Direction.North, false, checkInM, 6));

        List<Guest> guests = new ArrayList<>();
        guests.add(new ApproximatingGuest("Wilkins Micawber"));
        guests.add(new IndifferentGuest("Jane Murdstone"));
        guests.add(new PerfectGuest("Little Em'ly"));
        guests.add(new ViewLoverGuest("Mr. Sharp"));
        guests.add(new LowBudgetGuest("Mr. Barkis"));

        List<Receptionist> receptionists = new ArrayList<>();
        receptionists.add(new ApproximatingReceptionist("Ham Peggoty"));
        receptionists.add(new RandomReceptionist("Clara Paggoty"));
        receptionists.add(new EvilReceptionist("Uriah Heep"));
        receptionists.add(new PerfectReceptionist("David Copperfield"));

        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < guests.size(); i++) {
            orders.add(Hotel.placeOrder(guests.get(i), requirements.get(i)));
        }

        Room[] roomsArray = roomsList.toArray(new Room[roomsList.size()]);
        Receptionist[] receptionistsArray = receptionists.toArray(new Receptionist[receptionists.size()]);
        Order[] ordersArray = orders.toArray(new Order[orders.size()]);

        Hotel greenGables = new Hotel(roomsArray, receptionistsArray);
        try {
            greenGables.processOrders(ordersArray, roomsArray, receptionistsArray);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
