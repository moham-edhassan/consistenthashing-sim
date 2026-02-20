//VirtualNode.java class
package main.model;

public class VirtualNode {
    // Instance Variables
    private int replicaIndex;
    private Long position;
    private PhysicalNode parentNode;

    // Constructor
    public VirtualNode(int replicaIndex, Long position, PhysicalNode parentNode) {
        this.replicaIndex = replicaIndex;
        this.position = position;
        this.parentNode = parentNode;
    }

    // Getters
    public int getReplicaIndex() {
        return replicaIndex;
    }

    public Long getPosition() {
        return position;
    }

    public PhysicalNode getParentNode() {
        return parentNode;
    }

    // Setters
    public void setReplicaIndex(int replicaIndex) {
        this.replicaIndex = replicaIndex;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public void setParentNode(PhysicalNode parentNode) {
        this.parentNode = parentNode;
    }

    // toString method
    @Override
    public String toString() {
        return "Virtual Node: Replica Index = " + replicaIndex + ", position = " + position + ", parent node = "
                + parentNode + ".";
    }
}
