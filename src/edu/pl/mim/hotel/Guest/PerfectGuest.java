package edu.pl.mim.hotel.Guest;

import edu.pl.mim.hotel.Requirement.Requirements;
import edu.pl.mim.hotel.Room;

/**
 * Created by vlad on 20.04.16.
 */
public class PerfectGuest extends Guest {

    public PerfectGuest(String name) {
        super(name);
        this.type = "Perfectionist";
    }

    @Override
    public boolean consider(Room room, Requirements requirements) {
        return requirements.isPerfectFit(room);
    }

}
