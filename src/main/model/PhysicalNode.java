// PhysicalNode.java class
package main.model;

public class PhysicalNode {
    // Instance Variables
    private String name;
    private String color;
    private int id;

    // Constructor
    public PhysicalNode(String name, String color, int id) {
        this.name = name;
        this.color = color;
        this.id = id;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
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
