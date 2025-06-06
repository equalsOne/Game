package logic.gamelogic;

import logic.characters.Character;
import logic.characters.Spider;
import logic.things.Thing;

import java.util.*;
import java.util.stream.Collectors;

public class Room {
    private String name;
    private String description;
    private Map<String, Room> nearbyRooms;
    private Collection<Thing> thingsInRoom;
    private List<logic.characters.Character> charactersInRoom;

    public Room(String name, String description){
        this.name = name;
        this.description = description;
        this.nearbyRooms = new HashMap<>();
        this.thingsInRoom = new ArrayList<>();
        this.charactersInRoom = new ArrayList<>();
    }

    public void removeCharacter(Character character) {
        charactersInRoom.remove(character);
    }

    public boolean hasAliveSpider() {
        return charactersInRoom.stream().anyMatch(c -> c instanceof Spider);
    }

    public void removeThing(Thing thing) { thingsInRoom.remove(thing); }

    public void addCharacter(logic.characters.Character character) {
        charactersInRoom.add(character);
    }

    public List<Character> getCharactersInRoom() {
        return charactersInRoom;
    }

    public void addToThingsInRoom(Thing thing)
    { thingsInRoom.add(thing); }

    public Collection<Thing> getThingsInRoom() { return thingsInRoom; }

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

    public String nearbyRoomsDescription(){
        return "Nearby rooms: " + String.join(", ", nearbyRooms.keySet());
    }

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

    public Room getNearbyRoom(String name) {
        for (Map.Entry<String, Room> nearby : nearbyRooms.entrySet()) {
            if (nearby.getKey().equalsIgnoreCase(name))
            { return nearby.getValue(); }
        }

        return null;
    }
}
