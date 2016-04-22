package edu.pl.mim.hotel.Guest;

import edu.pl.mim.hotel.Requirement.Requirements;
import edu.pl.mim.hotel.Room;

/**
 * Created by vlad on 20.04.16.
 */
public class IndifferentGuest extends Guest {

    public IndifferentGuest(String name) {
        super(name);
        this.type = "Indifferent";
    }

    @Override
    public boolean consider(Room room, Requirements requirements) {
        return requirements.getNumberOfFulfilledRequirements(room) > -1;
    }
}
