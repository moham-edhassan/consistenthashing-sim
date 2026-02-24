/*
KeyEntry.java class

This class represents the key entry data structure that is used to store the keys and their assigned nodes.

@author: Mohamed Hassan
@date: 2026-02-24
*/
package main.model;

public class KeyEntry {
    // Instance Variables
    private String name;
    private Long hashValue;
    private PhysicalNode assignedPhysicalNode;

    // Constructor
    public KeyEntry(String name, Long hashValue, PhysicalNode assignedPhysicalNode) {
        this.name = name;
        this.hashValue = hashValue;
        this.assignedPhysicalNode = assignedPhysicalNode;
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public Long getHashValue() {
        return hashValue;
    }

    public PhysicalNode getAssignedPhysicalNode() {
        return assignedPhysicalNode;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setHashValue(Long hashValue) {
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
