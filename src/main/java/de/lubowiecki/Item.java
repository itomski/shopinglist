package de.lubowiecki;

import java.io.Serializable;

public class Item implements Serializable {

    private String name;
    private int count;
    private String description;

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

    @Override
    public String toString() {
        String desc = (!description.isEmpty()) ? "("+description+")" : "";
        return name + ", " + count + " " + desc;
    }
}
