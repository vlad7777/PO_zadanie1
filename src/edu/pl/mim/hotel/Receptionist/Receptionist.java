package edu.pl.mim.hotel.Receptionist;

import edu.pl.mim.hotel.Requirement.Requirements;
import edu.pl.mim.hotel.Room;

/**
 * Created by vlad on 15.04.16.
 */
public abstract class Receptionist {

    protected String name;
    protected String type;

    public Receptionist(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName() + ", " + getType();
    }

    public String getName() {
        return name;
    }

    public abstract Room selectRoom(Room[] rooms, Requirements requirements);

    public String getType() {
        return type;
    }
}
