package edu.pl.mim.hotel.Guest;

import edu.pl.mim.hotel.Requirement.Requirements;
import edu.pl.mim.hotel.Room;

/**
 * Created by vlad on 20.04.16.
 */
public class ViewLoverGuest extends Guest {

    public ViewLoverGuest(String name) {
        super(name);
        this.type = "View Lover";
    }

    @Override
    public boolean consider(Room room, Requirements requirements) {
        return requirements.getNumberOfFulfilledRequirements(room) > -1 && room.getDirection() == requirements.getDirection();
    }
}
