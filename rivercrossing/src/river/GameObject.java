package river;

import java.awt.*;

public class GameObject {

    private final String label;
    private Location location;
    private final Color color;
    private final boolean isDriver;

    public GameObject(String label, Location location, Color color, Boolean isDriver) {
        this.label = label;
        this.location = location;
        this.color = color;
        this.isDriver = isDriver;
    }

    public String getLabel() {
        return label;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location loc) {
        this.location = loc;
    }

    public Color getColor() { return this.color; }
    public boolean isDriver() { return isDriver; }

}
