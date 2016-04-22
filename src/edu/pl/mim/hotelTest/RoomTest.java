package edu.pl.mim.hotelTest;

import edu.pl.mim.hotel.Requirement.ColourPalette;
import edu.pl.mim.hotel.Requirement.Direction;
import edu.pl.mim.hotel.Requirement.Style;
import edu.pl.mim.hotel.Room;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by vlad on 19.04.16.
 */
public class RoomTest {
    @Test
    public void book() throws Exception {
        Date today = new Date();
        Date march1 = new Date(2016, 3, 1, 14, 00);
        Date march2 = new Date(2016, 3, 2, 14, 00);
        Date march3 = new Date(2016, 3, 3, 14, 00);
        Date march4 = new Date(2016, 3, 4, 14, 00);

        Room r1 = new Room(1, 200, 3, Style.Navy, ColourPalette.Celadon, Direction.East, true);
        r1.book(march2, march4);

        assertFalse(r1.checkAvailability(march1, march3));
        assertFalse(r1.checkAvailability(march2, march4));
        assertTrue(r1.checkAvailability(march1, march2));
    }

}