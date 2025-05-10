package logic;

import java.util.*;

public class Room {
    private String name;
    private String description;
    private Map<String, Room> nearbyRooms;

    public Room(String name, String description){
        this.name = name;
        this.description = description;
        nearbyRooms = new HashMap<>();
    }

    public String getName() { return name; }

    public void setExits(Room roomWest, Room roomEast)
    { nearbyRooms.put(roomWest.getName(), roomWest);
        nearbyRooms.put(roomEast.getName(), roomEast); }

    public int hashCode() {
        int result = 3;

        int hashName = java.util.Objects.hashCode(this.name);

        result = 37 * result + hashName;

        return result;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Room secondRoom)) {
            return false;
        }

        return (java.util.Objects.equals(this.name, secondRoom.name));
    }

    private String nearbyRoomsDescription(){
        return "Nearby rooms: " + String.join(" ", nearbyRooms.keySet());
    }

    public String extendedDescription(){
        return "You are in " + name + " " + description + ".\n"
                + nearbyRoomsDescription();
    }

    public Room getNearbyRoom(String name){
        return nearbyRooms.get(name);
    }

    public Collection<Room> getNearbyRooms() { return nearbyRooms.values(); }
}
