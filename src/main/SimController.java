/*
SimController.java class

This class represents the controller of the simulation that handles the events and updates the views.

@author: Mohamed Hassan
@date: 2026-02-25
*/
package main;

//importing the required libraries
import main.model.HashingRing;
import main.model.PhysicalNode;
import main.model.KeyEntry;
import main.view.RingPanel;
import main.view.ControlPanel;
import java.awt.Color;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class SimController {

    //instance variables
    private HashingRing hashingRing;
    private RingPanel ringPanel;
    private ControlPanel controlPanel;
    private int nodeIdCounter = 0;

    //array of colors for the nodes
    private Color[] colors = {
        Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
        Color.MAGENTA, Color.CYAN, Color.PINK, Color.YELLOW
    };

    //constructor
    public SimController(HashingRing hashingRing, RingPanel ringPanel, ControlPanel controlPanel) {

        this.hashingRing = hashingRing;
        this.ringPanel = ringPanel;
        this.controlPanel = controlPanel;

        //adding the action listeners to the buttons
        controlPanel.getAddNodeButton().addActionListener(e -> handleAddNode());
        controlPanel.getRemoveNodeButton().addActionListener(e -> handleRemoveNode());
        controlPanel.getAddKeyButton().addActionListener(e -> handleAddKey());
        controlPanel.getAddRandomKeyButton().addActionListener(e -> handleAddRandomKeys());
        controlPanel.getClearButton().addActionListener(e -> handleClear());
    }

    //handling the add node event
    private void handleAddNode() {
        //getting the name from the text field
        String name = controlPanel.getNodeNameField().getText();
        //checking if the name is empty
        if (name.isEmpty()){
            return;
        }

        //getting the color for the node
        Color color = colors[nodeIdCounter % colors.length];
        //incrementing the node id counter
        //creating a new physical node with the name, color, and id
        PhysicalNode node = new PhysicalNode(name, color, nodeIdCounter);
        //adding the node to the hashing ring
        nodeIdCounter++;

        //getting the number of virtual nodes from the slider
        int numVirtual = controlPanel.getVirtualNodesSlider().getValue();
    
        //adding the node to the hashing ring and getting the migrated key entries
        try {
            List<KeyEntry> migrated = hashingRing.addNode(node, numVirtual);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }

        //adding the node to the nodes box
        controlPanel.getNodesBox().addItem(name);
        //clearing the node name field
        controlPanel.getNodeNameField().setText("");
        //refreshing the views
        refreshViews();
    }

    //handling the remove node event
    private void handleRemoveNode() {
        //getting the name from the nodes box
        String name = (String) controlPanel.getNodesBox().getSelectedItem();
        if (name == null){
            return;
        }
        //checking if the hashing ring is empty
        if(hashingRing.getNodes().isEmpty()){
            return;
        }

        //getting the target node
        PhysicalNode target = null;
        //looping through the nodes in the hashing ring
        for (PhysicalNode node : hashingRing.getNodes()) {
            //checking if the node name is the same as the name
            if (node.getName().equals(name)) {
                //setting the target node
                target = node;
                //breaking the loop
                break;
            }
        }
        //checking if the target node is null
        if (target == null){
            return;
        }

        //getting the number of virtual nodes from the slider

        int numVirtual = controlPanel.getVirtualNodesSlider().getValue();

        //removing the node from the hashing ring and getting the migrated key entries
        try {
            hashingRing.removeNode(target, numVirtual);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }

        controlPanel.getNodesBox().removeItem(name);

        refreshViews();
    }

    //handling the add key event
    private void handleAddKey() {
        //getting the name from the text field
        String name = controlPanel.getKeyNameField().getText();
        //checking if the name is empty
        if (name.isEmpty()){
            return;
        }
        //checking if the hashing ring is empty
        if(hashingRing.getNodes().isEmpty()){
            return;
        }
        //assigning a key to the hashing ring
        try {
            hashingRing.assignKey(name);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }

        controlPanel.getKeyNameField().setText("");
        refreshViews();
    }

    //handling the add random keys event
    private void handleAddRandomKeys() {
        //checking if the hashing ring is empty
        if(hashingRing.getNodes().isEmpty()){
            return;
        }
        //looping through 100 keys
        try {
            for (int i = 0; i < 100; i++) {
                //assigning a random key to the hashing ring
                hashingRing.assignKey("key-" + i);
            }
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
        //refreshing the views
        refreshViews();
    }

    //handling the clear event
    private void handleClear() {
        //clearing the key entries
        hashingRing.getKeyEntry().clear();
        //refreshing the views
        refreshViews();
    }

    //refreshing the views
    private void refreshViews() {
        //updating the nodes
        ringPanel.updateNodes(hashingRing.getNodes());
        //updating the virtual nodes
        ringPanel.updateVirtualNodes(hashingRing.getVirtualNodes());
        //updating the keys
        ringPanel.updateKeys(hashingRing.getKeyEntry());
    }
}