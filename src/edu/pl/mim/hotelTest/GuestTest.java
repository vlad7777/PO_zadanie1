package edu.pl.mim.hotelTest;

import edu.pl.mim.hotel.Guest.*;
import edu.pl.mim.hotel.Requirement.ColourPalette;
import edu.pl.mim.hotel.Requirement.Direction;
import edu.pl.mim.hotel.Requirement.Requirements;
import edu.pl.mim.hotel.Requirement.Style;
import edu.pl.mim.hotel.Room;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vlad on 20.04.16.
 */
public class GuestTest {

    private Room[] rooms;
    private Requirements[] requirements;

    @Before
    public void setUp() throws Exception {
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
            r.book(checkInM, checkOutM);

        this.rooms = roomsList.toArray(new Room[roomsList.size()]);

        List<Requirements> requirements = new ArrayList<>();
        requirements.add(new Requirements(210, 5, Style.Rustic, ColourPalette.Steel, Direction.West, false, checkIn, 4));
        requirements.add(new Requirements(10, 3, Style.Modern, ColourPalette.LightGreen, Direction.East, false, checkIn, 4));
        requirements.add(new Requirements(1000400, 1, Style.Modern, ColourPalette.LightGreen, Direction.East, true, checkIn, 4));
        requirements.add(new Requirements(100, 5, Style.Rustic, ColourPalette.Steel, Direction.East, true, checkIn, 4));
        requirements.add(new Requirements(100, 1, Style.Modern, ColourPalette.Celadon, Direction.North, false, checkInM, 6));

        this.requirements = requirements.toArray(new Requirements[requirements.size()]);
    }

    @Test
    public void perfectGuestTest() throws Exception {
        PerfectGuest guest = new PerfectGuest("Agnes Wickfield");
        assertTrue(guest.consider(rooms[5], requirements[0]));
        assertTrue(guest.consider(rooms[7], requirements[2]));
        assertFalse(guest.consider(rooms[9], requirements[4]));
        assertFalse(guest.consider(rooms[0], requirements[2]));

        assertEquals("Agnes Wickfield, Perfectionist", guest.toString());
    }

    @Test
    public void indifferentGuestTest() throws Exception {
        IndifferentGuest guest = new IndifferentGuest("Tommy Traddles");
        assertTrue(guest.consider(rooms[1], requirements[2]));
        assertTrue(guest.consider(rooms[2], requirements[0]));
        assertFalse(guest.consider(rooms[2], requirements[4]));

        assertEquals("Tommy Traddles, Indifferent", guest.toString());
    }

    @Test
    public void viewLoverGuestTest() throws Exception {
        ViewLoverGuest guest = new ViewLoverGuest("David Copperfield");
        assertTrue(guest.consider(rooms[8], requirements[3]));
        assertFalse(guest.consider(rooms[0], requirements[1]));
        assertFalse(guest.consider(rooms[0], requirements[4]));

        assertEquals("David Copperfield, View Lover", guest.toString());
    }

    @Test
    public void lowBudgetGuest() throws Exception {
        LowBudgetGuest guest = new LowBudgetGuest("Mr. Barkis");
        assertTrue(guest.consider(rooms[6], requirements[2]));
        assertFalse(guest.consider(rooms[3], requirements[1]));
        assertFalse(guest.consider(rooms[0], requirements[4]));

        assertEquals("Mr. Barkis, Low Budget", guest.toString());
    }

    @Test
    public void approximatingGuestTest() throws Exception {
        ApproximatingGuest guest = new ApproximatingGuest("Betsy Trotwood");
        assertTrue(guest.consider(rooms[7], requirements[2]));
        assertFalse(guest.consider(rooms[8], requirements[0]));
        assertFalse(guest.consider(rooms[0], requirements[4]));

        assertEquals("Betsy Trotwood, Approximator", guest.toString());
    }

}
