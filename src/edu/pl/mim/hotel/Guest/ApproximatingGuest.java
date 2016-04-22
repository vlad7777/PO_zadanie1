package edu.pl.mim.hotel.Guest;

import edu.pl.mim.hotel.Requirement.Requirements;
import edu.pl.mim.hotel.Room;

/**
 * Created by vlad on 20.04.16.
 */
public class ApproximatingGuest extends Guest {

    public ApproximatingGuest(String name) {
        super(name);
        this.type = "Approximator";
    }

    @Override
    public boolean consider(Room room, Requirements requirements) {
        return requirements.getNumberOfFulfilledRequirements(room) > requirements.TOTAL_REQUIREMENTS / 2;
    }
}
