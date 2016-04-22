package edu.pl.mim.hotel;

import edu.pl.mim.hotel.Guest.Guest;
import edu.pl.mim.hotel.Receptionist.Receptionist;
import edu.pl.mim.hotel.Requirement.Requirements;

import java.util.*;

/**
 * Created by vlad on 15.04.16.
 */

public class Hotel {

    public static class UnknownReceptionistException extends Exception {
        public UnknownReceptionistException(String s){ super(s); }
    };

    public static class UnknownRoomException extends Exception {
        public UnknownRoomException(String s) { super(s); }
    };

    private List<Room> rooms = new ArrayList<>();
    private List<Receptionist> receptionists = new ArrayList<>();

    public Hotel(Room[] rooms, Receptionist[] receptionists) {
        this.rooms.addAll(Arrays.asList(rooms));
        this.receptionists.addAll(Arrays.asList(receptionists));
    }

    public void processOrders(Order[] orders, Room[] rooms, Receptionist[] receptionists) throws UnknownReceptionistException, UnknownRoomException, Room.RoomAlreadyBookedException {
        for (Room room : rooms) {
            if (!this.rooms.contains(room)) {
                throw new UnknownRoomException("The hotel doesn't have a room with number " + room.getNumber());
            }
        }

        for (Receptionist receptionist : receptionists) {
            if (!this.receptionists.contains(receptionist)) {
                throw new UnknownReceptionistException("The hotel doesn't employ a receptionist named " + receptionist.getName());
            }
        }

        Queue<Order> queue = new ArrayDeque<>();
        queue.addAll(Arrays.asList(orders));

        int currentReceptionist = 0;

        while (!queue.isEmpty()) {
            Order currentOrder = queue.poll();

            if (currentOrder.getTries() == 3)
                continue;
            currentOrder.incTries();

            System.out.println("----------------------------");
            System.out.println("Receptionist: " + receptionists[currentReceptionist].toString());
            System.out.println("Order: " + currentOrder.toString());

            Room selectedRoom = receptionists[currentReceptionist].selectRoom(rooms, currentOrder.getRequirements());

            System.out.println("Offered room: " + (selectedRoom == null ? "none" : selectedRoom.toString()));
            System.out.println("Client: " + currentOrder.getGuest().toString());

            if (selectedRoom != null && currentOrder.getGuest().consider(selectedRoom, currentOrder.getRequirements())) {
                currentOrder.complete(selectedRoom);
                System.out.println("Client approved the room");
            } else {
                queue.add(currentOrder);
                System.out.println("Client declined the room");
            }
            System.out.println("-----------------------------\n\n");

            currentReceptionist++;
            currentReceptionist %= receptionists.length;
        }
    }

    public static Order placeOrder(Guest guest, Requirements requirements) {
        return new Order(guest, requirements);
    }

}
