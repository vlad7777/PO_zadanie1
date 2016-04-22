package edu.pl.mim.hotel;

import edu.pl.mim.hotel.Guest.Guest;
import edu.pl.mim.hotel.Requirement.Requirements;

/**
 * Created by vlad on 15.04.16.
 */
public class Order {

    private int tries = 0;
    private final Guest guest;
    private final Requirements requirements;

    public Order(Guest guest, Requirements requirements) {
        this.guest = guest;
        this.requirements = requirements;
    }

    public void complete(Room room) throws Room.RoomAlreadyBookedException {
        room.book(requirements.getCheckInDate(), requirements.getCheckOutDate());
    }

    @Override
    public String toString() {
        String res = "viewed " + tries + (tries == 1 ? " time; " : " times; ");
        res += requirements.toString();
        return res;
    }

    public Requirements getRequirements() {
        return requirements;
    }


    public int getTries() {
        return tries;
    }

    public void incTries() {
        tries++;
    }

    public Guest getGuest() {
        return guest;
    }
}
