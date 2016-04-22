package edu.pl.mim.hotelTest;

import edu.pl.mim.hotel.Requirement.ColourPalette;
import edu.pl.mim.hotel.Requirement.Direction;
import edu.pl.mim.hotel.Requirement.Requirements;
import edu.pl.mim.hotel.Requirement.Style;
import edu.pl.mim.hotel.Room;

import java.util.Date;
import static org.junit.Assert.*;

/**
 * Created by vlad on 18.04.16.
 */
public class RequirementsTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void getNumberOfFulfilledRequirements() throws Exception {
        Date today = new Date();
        Date march1 = new Date(2016, 3, 1, 14, 00);
        Date march2 = new Date(2016, 3, 2, 14, 00);
        Date march3 = new Date(2016, 3, 3, 14, 00);
        Date march4 = new Date(2016, 3, 4, 14, 00);

        Room r1 = new Room(1, 200, 3, Style.Navy, ColourPalette.Celadon, Direction.East, true);
        r1.book(march1, march2);

        Requirements req1 = new Requirements(100, 2, Style.Modern, ColourPalette.Celadon, Direction.North, true, march1, 1);
        assertEquals(-1, req1.getNumberOfFulfilledRequirements(r1));

        Requirements req2 = new Requirements(100, 2, Style.Modern, ColourPalette.Celadon, Direction.North, true, march3, 1);
        assertEquals(3, req2.getNumberOfFulfilledRequirements(r1));
    }

    @org.junit.Test
    public void isPerfectFit() throws Exception {

    }

    @org.junit.Test
    public void getCheckOutDate() throws Exception {

    }

    @org.junit.Test
    public void getCheckInDate() throws Exception {

    }

}