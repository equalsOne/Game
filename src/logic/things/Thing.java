package logic.things;

public class Thing {
    private String name;
    private int space;
    private boolean isCarriable;

    public Thing(String name, int space, boolean isCarriable){
        this.name = name;
        this.space = space;
        this.isCarriable = isCarriable;
    }

    public Thing(String name, boolean isCarriable){
        this.name = name;
        this.isCarriable = isCarriable;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public int getSpace() { return space; }

    public boolean isCarriable() { return isCarriable; }
}
