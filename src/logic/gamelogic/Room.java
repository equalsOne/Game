package logic.gamelogic;

import logic.characters.Character;
import logic.characters.Spider;
import logic.things.Thing;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Room represents a location in the game world.
 * Each room has a name, description, connections to nearby rooms,
 * things that can be found there, and characters present.
 *
 * @author Ihor Skosar
 * @version LS 2024/2025, June 2025
 */
public class Room {
    private String name;
    private String description;
    private Map<String, Room> nearbyRooms;
    private Collection<Thing> thingsInRoom;
    private List<logic.characters.Character> charactersInRoom;

    /**
     * Constructs a new Room with the specified name and description.
     * Initializes the lists and maps to manage exits, things, and characters.
     *
     * @param name the name of the room
     * @param description the description of the room
     */
    public Room(String name, String description){
        this.name = name;
        this.description = description;
        this.nearbyRooms = new HashMap<>();
        this.thingsInRoom = new ArrayList<>();
        this.charactersInRoom = new ArrayList<>();
    }

    /**
     * Removes a character from the room.
     *
     * @param character the character to remove
     */
    public void removeCharacter(Character character) {
        charactersInRoom.remove(character);
    }

    /**
     * Checks if there is an alive Spider character in the room.
     *
     * @return true if a Spider is present, false otherwise
     */
    public boolean hasAliveSpider() {
        return charactersInRoom.stream().anyMatch(c -> c instanceof Spider);
    }

    /**
     * Removes a thing from the room.
     *
     * @param thing the thing to remove
     */
    public void removeThing(Thing thing) { thingsInRoom.remove(thing); }

    /**
     * Adds a character to the room.
     *
     * @param character the character to add
     */
    public void addCharacter(Character character) {
        charactersInRoom.add(character);
    }

    /**
     * Returns a list of characters currently in the room.
     *
     * @return list of characters in the room
     */
    public List<Character> getCharactersInRoom() {
        return charactersInRoom;
    }

    /**
     * Adds a thing to the room.
     *
     * @param thing the thing to add
     */
    public void addToThingsInRoom(Thing thing)
    { thingsInRoom.add(thing); }

    /**
     * Returns the collection of things present in the room.
     *
     * @return collection of things in the room
     */
    public Collection<Thing> getThingsInRoom() { return thingsInRoom; }

    /**
     * Returns the name of the room.
     *
     * @return the room name
     */

    public String getName() { return name; }

    /**
     * Sets the rooms connected to this room (exits).
     * Typically sets two exits: west and east.
     *
     * @param roomWest the room to the west
     * @param roomEast the room to the east
     */
    public void setExits(Room roomWest, Room roomEast)
    { nearbyRooms.put(roomWest.getName(), roomWest);
        nearbyRooms.put(roomEast.getName(), roomEast); }


    /**
     * Computes hash code based on the room name.
     *
     * @return hash code of the room
     */
    public int hashCode() {
        int result = 3;

        int hashName = java.util.Objects.hashCode(this.name);

        result = 37 * result + hashName;

        return result;
    }

    /**
     * Checks if this room equals another object.
     * Equality is based on the room's name.
     *
     * @param o the object to compare with
     * @return true if the other object is a Room with the same name
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Room secondRoom)) {
            return false;
        }

        return (java.util.Objects.equals(this.name, secondRoom.name));
    }

    /**
     * Returns a string listing the nearby connected rooms.
     *
     * @return description of nearby rooms
     */
    public String nearbyRoomsDescription(){
        return "Nearby rooms: " + String.join(", ", nearbyRooms.keySet());
    }

    /**
     * Returns detailed information about the room,
     * including name, description, nearby rooms,
     * things present, and characters present.
     *
     * @return detailed room information
     */
    public String informationDescription(){
        StringBuilder builder = new StringBuilder();

        builder.append("Information on this room: ").append("\n \n").append(name).append("\n")
                .append(description).append("\n \n")
                .append(nearbyRoomsDescription());

        if (!thingsInRoom.isEmpty()) {
            builder.append("\n \nThings here: ")
                    .append(thingsInRoom.stream()
                            .map(Thing::getName)
                            .collect(Collectors.joining(", ")));
        }

        if(!charactersInRoom.isEmpty()){
            builder.append("\n \nCharacters here: ")
                    .append(charactersInRoom.stream()
                            .map(Character::getName)
                            .collect(Collectors.joining(", ")));
        }

        return builder.toString();
    }

    /**
     * Returns an extended description of the room,
     * similar to informationDescription but more narrative.
     *
     * @return extended room description
     */
    public String extendedDescription(){
        StringBuilder builder = new StringBuilder();

        builder.append("You are in ").append(name).append("\n \n")
                .append(description).append("\n \n")
                .append(nearbyRoomsDescription());

        if (!thingsInRoom.isEmpty()) {
            builder.append("\n \nThings here: ")
                    .append(thingsInRoom.stream()
                            .map(Thing::getName)
                            .collect(Collectors.joining(", ")));
        }

        if(!charactersInRoom.isEmpty()){
            builder.append("\n \nCharacters here: ")
                    .append(charactersInRoom.stream()
                            .map(Character::getName)
                            .collect(Collectors.joining(", ")));
        }

        return builder.toString();
    }

    /**
     * Returns a nearby room by its name (case-insensitive).
     *
     * @param name the name of the nearby room
     * @return the Room if found, or null if not found
     */
    public Room getNearbyRoom(String name) {
        for (Map.Entry<String, Room> nearby : nearbyRooms.entrySet()) {
            if (nearby.getKey().equalsIgnoreCase(name))
            { return nearby.getValue(); }
        }

        return null;
    }
}
