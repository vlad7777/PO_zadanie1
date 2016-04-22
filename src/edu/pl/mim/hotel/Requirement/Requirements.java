package edu.pl.mim.hotel.Requirement;

import edu.pl.mim.hotel.Room;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by vlad on 15.04.16.
 */

public class Requirements {

    public static final int TOTAL_REQUIREMENTS = 6;

    private final int maxPrice;
    private final int guests;
    private final Style style;
    private final ColourPalette colourPalette;
    private final Direction direction;
    private final boolean isInternetRequired;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Requirements(int maxPrice, int guests, Style style, ColourPalette colourPalette, Direction direction, boolean isInternetRequired, Date checkInDate, int lengthOfStay) {
        this.maxPrice = maxPrice;
        this.guests = guests;
        this.style = style;
        this.colourPalette = colourPalette;
        this.direction = direction;
        this.isInternetRequired = isInternetRequired;
        this.checkInDate = checkInDate;
        this.checkOutDate = addDays(checkInDate, lengthOfStay);
    }

    public int getNumberOfFulfilledRequirements(Room room) {

        //critical requirement: we can't put someone in a room that is already booked
        if (!room.checkAvailability(checkInDate, checkOutDate))
            return -1;

        int ret = 0;

        if (room.getMaxGuests() >= guests)
            ret++;

        if (maxPrice >= room.getPrice())
            ret++;

        if (style == room.getStyle())
            ret++;

        if (colourPalette == room.getColourPalette())
            ret++;

        if (direction == room.getDirection())
            ret++;

        if (!isInternetRequired || (isInternetRequired == room.isInternetAvailable()))
            ret++;

        return ret;
    }

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    @Override
    public String toString() {
        String res = "";
        if (guests == 1)
            res += "1 bed, ";
        else
            res += guests + " beds required, ";
        res += "highest price " + maxPrice + " PLN, ";
        res += style.toString() + " style, ";
        res += colourPalette.toString() + " colour palette, ";
        res += "windows face " + direction.toString() + ", ";
        res += "Internet " + (isInternetRequired? "" : "not ") + "required";
        return res;
    }

    public boolean isPerfectFit(Room room) {
        return getNumberOfFulfilledRequirements(room) == TOTAL_REQUIREMENTS;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

}
