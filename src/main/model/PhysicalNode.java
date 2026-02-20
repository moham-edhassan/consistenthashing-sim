// PhysicalNode.java class
package main.model;

import java.awt.Color;

public class PhysicalNode {
    // Instance Variables
    private String name;
    private Color color;
    private int id;

    // Constructor
    public PhysicalNode(String name, Color color, int id) {
        this.name = name;
        this.color = color;
        this.id = id;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setId(int id) {
        this.id = id;
    }

    // toString method
    @Override
    public String toString() {
        return "Physical Node: Name = " + name + "color = " + color + "ID = " + id + ".";
    }

}
