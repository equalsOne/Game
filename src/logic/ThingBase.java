package logic;

import java.util.Set;

public class ThingBase {
    private String name;
    private int space;
    private boolean isCarriable;

    public ThingBase(String name, int space, boolean isCarriable){
        this.name = name;
        this.space = space;
        this.isCarriable = isCarriable;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setSpace(int space) { this.space = space; }
    public int getSpace() { return space; }

    public boolean isCarriable() { return isCarriable; }
}
