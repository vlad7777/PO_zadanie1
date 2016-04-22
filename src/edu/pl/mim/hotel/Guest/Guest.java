package edu.pl.mim.hotel.Guest;

import edu.pl.mim.hotel.Requirement.Requirements;
import edu.pl.mim.hotel.Room;

/**
 * Created by vlad on 15.04.16.
 */

public abstract class Guest {

    private String name;
    protected String type;

    public Guest(String name) {
        this.name = name;
    }

    public String toString() {
        return getName() + ", " + getType();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public abstract boolean consider(Room room, Requirements requirements);
}
