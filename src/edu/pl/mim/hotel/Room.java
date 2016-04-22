package edu.pl.mim.hotel;

import edu.pl.mim.hotel.Requirement.ColourPalette;
import edu.pl.mim.hotel.Requirement.Direction;
import edu.pl.mim.hotel.Requirement.Requirements;
import edu.pl.mim.hotel.Requirement.Style;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vlad on 15.04.16.
 */
public class Room {

    private final int number;
    private final int price;
    private final int maxGuests;
    private final Style style;
    private final ColourPalette colourPalette;
    private final Direction direction;
    private final boolean internetAvailable;
    private List<Pair<Date, Date>> booked = new ArrayList<>();

    public class RoomAlreadyBookedException extends Exception {};


    public Room(int number, int price, int maxGuests, Style style, ColourPalette colourPalette, Direction direction, boolean internetAvailable) {
        this.number = number;
        this.price = price;
        this.maxGuests = maxGuests;
        this.style = style;
        this.colourPalette = colourPalette;
        this.direction = direction;
        this.internetAvailable = internetAvailable;
    }

    @Override
    public String toString() {
        String res = "";
        if (maxGuests == 1)
            res += "1 bed, ";
        else
            res += maxGuests + " beds, ";
        res += price + " PLN, ";
        res += style.toString() + " style, ";
        res += colourPalette.toString() + " colour palette, ";
        res += "windows face " + direction.toString() + ", ";
        res += "Internet " + (internetAvailable ? "" : "not ") + "available";
        return res;
    }

    public boolean checkAvailability(Date checkIn, Date checkOut) {
        for (Pair<Date, Date> p : booked) {
            if (p.getKey().compareTo(checkIn) <= 0 && p.getValue().compareTo(checkOut) > 0)
                return false;
            if (checkIn.compareTo(p.getKey()) <= 0 && checkOut.compareTo(p.getKey()) > 0)
                return false;
        }
        return true;
    }

    public void book(Date checkIn, int lengthOfStay) throws RoomAlreadyBookedException {
        book(checkIn, Requirements.addDays(checkIn, lengthOfStay));
    }

    public void book(Date checkIn, Date checkOut) throws RoomAlreadyBookedException {
        if (!checkAvailability(checkIn, checkOut))
            throw new RoomAlreadyBookedException();
        booked.add(new Pair<Date, Date>(checkIn, checkOut));
    }

    public boolean isInternetAvailable() {
        return internetAvailable;
    }

    public Direction getDirection() {
        return direction;
    }

    public ColourPalette getColourPalette() {
        return colourPalette;
    }

    public Style getStyle() {
        return style;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

}
