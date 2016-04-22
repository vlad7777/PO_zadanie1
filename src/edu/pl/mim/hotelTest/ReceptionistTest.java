package edu.pl.mim.hotelTest;

import edu.pl.mim.hotel.Receptionist.ApproximatingReceptionist;
import edu.pl.mim.hotel.Receptionist.EvilReceptionist;
import edu.pl.mim.hotel.Receptionist.PerfectReceptionist;
import edu.pl.mim.hotel.Receptionist.RandomReceptionist;
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
 * Created by vlad on 19.04.16.
 */

public class ReceptionistTest {

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
        requirements.add(new Requirements(100, 1, Style.Oriental, ColourPalette.Grey, Direction.West, false, checkInM, 6));

        this.requirements = requirements.toArray(new Requirements[requirements.size()]);
    }

    @Test
    public void evilReceptionistTest() throws Exception {
        EvilReceptionist evil = new EvilReceptionist("Uriah Heep");

        assertEquals(8, evil.selectRoom(rooms, requirements[0]).getNumber());
        assertEquals(3, evil.selectRoom(rooms, requirements[1]).getNumber());
        assertEquals(6, evil.selectRoom(rooms, requirements[2]).getNumber());
        assertEquals(3, evil.selectRoom(rooms, requirements[3]).getNumber());
        assertNull(evil.selectRoom(rooms, requirements[4]));

        assertEquals("Uriah Heep, Evil", evil.toString());
    }

    @Test
    public void perfectReceptionistTest() throws Exception {
        PerfectReceptionist perfect = new PerfectReceptionist("Mrs. Clara Copperfield");

        assertEquals(6, perfect.selectRoom(rooms, requirements[0]).getNumber());
        assertNull(perfect.selectRoom(rooms, requirements[1]));
        assertEquals(8, perfect.selectRoom(rooms, requirements[2]).getNumber());
        assertNull(perfect.selectRoom(rooms, requirements[3]));
        assertNull(perfect.selectRoom(rooms, requirements[4]));

        assertEquals("Mrs. Clara Copperfield, Perfectionist", perfect.toString());
    }

    @Test
    public void randomReceptionistTest() throws Exception {
        RandomReceptionist random = new RandomReceptionist("Master Davy");

        assertNotNull(random.selectRoom(rooms, requirements[0]));
        assertNotNull(random.selectRoom(rooms, requirements[1]));
        assertNotNull(random.selectRoom(rooms, requirements[2]));
        assertNotNull(random.selectRoom(rooms, requirements[3]));
        assertNull(random.selectRoom(rooms, requirements[4]));

        assertEquals("Master Davy, Random", random.toString());
    }

    @Test
    public void approximateReceptionistTest() throws Exception {
        ApproximatingReceptionist approximate = new ApproximatingReceptionist("James Steerforth");

        assertEquals(6, approximate.selectRoom(rooms, requirements[0]).getNumber());
        assertEquals(8, approximate.selectRoom(rooms, requirements[1]).getNumber());
        assertEquals(8, approximate.selectRoom(rooms, requirements[2]).getNumber());
        assertEquals(7, approximate.selectRoom(rooms, requirements[3]).getNumber());
        assertNull(approximate.selectRoom(rooms, requirements[4]));

        assertEquals("James Steerforth, Approximator", approximate.toString());
    }
}