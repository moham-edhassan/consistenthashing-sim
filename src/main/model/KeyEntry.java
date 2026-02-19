//KeyEntry.java class
package main.model;

public class KeyEntry {

    private String name;
    private int hashValue;
    private PhysicalNode assignedPhysicalNode;

    // Constructor
    public KeyEntry(String name, int hashValue, PhysicalNode assignedPhysicalNode) {
        this.name = name;
        this.hashValue = hashValue;
        this.assignedPhysicalNode = assignedPhysicalNode;
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public int getHashValue() {
        return hashValue;
    }

    public PhysicalNode getAssignedPhysicalNode() {
        return assignedPhysicalNode;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setHashValue(int hashValue) {
        this.hashValue = hashValue;
    }

    public void setAssignedPhysicalNode(PhysicalNode assignedPhysicalNode) {
        this.assignedPhysicalNode = assignedPhysicalNode;
    }

    // toString method
    @Override
    public String toString() {
        return "Key Entry: Name = " + name + ", Hash Value = " + hashValue + " Assigned Pysical Node = "
                + assignedPhysicalNode + ".";
    }

}
