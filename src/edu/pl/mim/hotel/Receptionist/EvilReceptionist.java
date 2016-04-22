package edu.pl.mim.hotel.Receptionist;

import edu.pl.mim.hotel.Requirement.Requirements;
import edu.pl.mim.hotel.Room;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by vlad on 15.04.16.
 */
public class EvilReceptionist extends Receptionist {

    private class EvilComparator implements Comparator<Room> {

        private Requirements requirements;

        public EvilComparator(Requirements requirements) {
            this.requirements = requirements;
        }

        @Override
        public int compare(Room a, Room b) {
            int reqA = requirements.getNumberOfFulfilledRequirements(a);
            if (reqA == -1)
                return 1;

            int reqB = requirements.getNumberOfFulfilledRequirements(b);
            if (reqB == -1)
                return -1;

            if (Integer.compare(reqA, reqB) != 0)
                return Integer.compare(reqA, reqB); // we need a room that fits the worst
            else if (Integer.compare(a.getPrice(), b.getPrice()) != 0)
                return -1 * Integer.compare(a.getPrice(), b.getPrice()); // we need an expensive room
            else if (Integer.compare(a.getNumber(), b.getNumber()) != 0)
                return Integer.compare(a.getNumber(), b.getNumber()); // we need the lowest number
            else
                return 0;

        }
    }

    public EvilReceptionist(String name) {
        super(name);
        this.type = "Evil";
    }

    @Override
    public Room selectRoom(Room[] rooms, Requirements requirements) {
        Room r = Collections.min(Arrays.asList(rooms), new EvilComparator(requirements));
        if (r != null && requirements.getNumberOfFulfilledRequirements(r) > -1)
            return r;
        return null;
    }

}
