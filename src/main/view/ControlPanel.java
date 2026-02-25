/*
ControlPanel.java class

This class represents the control panel that is used to control the simulation.

@author: Mohamed Hassan
@date: 2026-02-25
*/
package main.view;

//importing the required libraries
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;

public class ControlPanel extends JPanel {
    //instance variables
    private JButton addNodeButton;
    private JButton removeNodeButton;
    private JTextField nodeNameField;
    private JTextField keyNameField;
    private JButton addKeyButton;
    private JButton addRandomKeyButton;
    private JButton clearButton;
    private JComboBox<String> nodesBox;
    private JLabel sliderLabel;
    private JSlider virtualNodesSlider;

    //constructor
    public ControlPanel(){
        //setting the border and layout of the panel
        setBorder(new TitledBorder("Control Panel"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //initialized the components
        nodeNameField = new JTextField(20);
        keyNameField = new JTextField(20);
        addNodeButton = new JButton("Add Node");
        removeNodeButton = new JButton("Remove Node");
        addKeyButton = new JButton("Add Key");
        addRandomKeyButton = new JButton("Add Random Key");
        clearButton = new JButton("Clear");
        nodesBox = new JComboBox<>();
        virtualNodesSlider = new JSlider(1, 300, 100);
        sliderLabel = new JLabel("100");
    
        //adding the components to the panel
        add(new JLabel("Node Name: "));
        add(nodeNameField);
        add(addNodeButton);
        add(nodesBox);
        add(removeNodeButton);

        add(new JLabel("Virtual Nodes: "));
        add(virtualNodesSlider);
        add(sliderLabel);

        add(new JLabel("Key name:"));
        add(keyNameField);
        add(addKeyButton);
        add(addRandomKeyButton);
        add(clearButton);
    }

    //getters
    public JTextField getNodeNameField() {
        return nodeNameField;
    }
    public JTextField getKeyNameField() {
        return keyNameField;
    }
    public JButton getAddNodeButton() {
        return addNodeButton;
    }
    public JButton getRemoveNodeButton() {
        return removeNodeButton;
    }
    public JButton getAddKeyButton() {
        return addKeyButton;
    }
    public JButton getAddRandomKeyButton() {
        return addRandomKeyButton;
    }
    public JButton getClearButton() {
        return clearButton;
    }
    public JComboBox<String> getNodesBox() {
        return nodesBox;
    }
    public JSlider getVirtualNodesSlider() {
        return virtualNodesSlider;
    }   
    
}
