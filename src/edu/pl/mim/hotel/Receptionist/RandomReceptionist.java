package edu.pl.mim.hotel.Receptionist;

import edu.pl.mim.hotel.Requirement.Requirements;
import edu.pl.mim.hotel.Room;

import java.util.Random;

/**
 * Created by vlad on 15.04.16.
 */
public class RandomReceptionist extends Receptionist {

    public RandomReceptionist(String name) {
        super(name);
        this.type = "Random";
    }

    public Room selectRoom(Room[] rooms, Requirements requirements) {
       int freeRooms = 0;
        for (Room room : rooms) {
            if (requirements.getNumberOfFulfilledRequirements(room) != -1)
                freeRooms++;
        }

        if (freeRooms == 0)
            return null;

        int ind = new Random().nextInt(freeRooms);

        for (Room room : rooms) {
            if (requirements.getNumberOfFulfilledRequirements(room) != -1) {
                if (ind == 0)
                    return room;
                ind--;
            }
        }
        return null;
    }
}
