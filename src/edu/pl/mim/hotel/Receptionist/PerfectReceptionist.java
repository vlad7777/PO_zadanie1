package edu.pl.mim.hotel.Receptionist;

import edu.pl.mim.hotel.Requirement.Requirements;
import edu.pl.mim.hotel.Room;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by vlad on 15.04.16.
 */
public class PerfectReceptionist extends Receptionist {

    private class PerfectComparator implements Comparator<Room> {

        private Requirements requirements;

        public PerfectComparator(Requirements requirements) {
            this.requirements = requirements;
        }

        public int compare(Room a, Room b) {
            int reqA = requirements.getNumberOfFulfilledRequirements(a);
            if (reqA == -1)
                return 1;

            int reqB = requirements.getNumberOfFulfilledRequirements(b);
            if (reqB == -1)
                return -1;

            if (Integer.compare(reqA, reqB) != 0)
                return -1 * Integer.compare(reqA, reqB); // we need a room that fits the best
            else if (Integer.compare(a.getNumber(), b.getNumber()) != 0)
                return Integer.compare(a.getNumber(), b.getNumber()); // we need the lowest number
            else
                return 0;
        }
    }
    public PerfectReceptionist(String name) {
        super(name);
        this.type = "Perfectionist";
    }

    public Room selectRoom(Room[] rooms, Requirements requirements) {
        Room r = Collections.min(Arrays.asList(rooms), new PerfectComparator(requirements));
        if (r != null && requirements.isPerfectFit(r))
            return r;
        return null;
    }
}
