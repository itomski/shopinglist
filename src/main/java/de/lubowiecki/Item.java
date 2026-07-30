package de.lubowiecki;

import java.io.Serializable;

public class Item implements Serializable {

    private String name;
    private int count;
    private String description;
    private boolean done;

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, int count, String description) {
        this.name = name;
        this.count = count;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void toggleDone() {
        done = !done; // Zustand wird invertiert
    }

    @Override
    public String toString() {
        String desc = (!description.isEmpty()) ? " ("+description+")" : "";
        return name + ", " + count + desc + " - " + ((done) ? "erledigt" : "offen");
    }
}
