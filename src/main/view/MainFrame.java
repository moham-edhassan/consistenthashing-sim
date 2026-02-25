/*
MainFrame.java class

This class represents the main frame of the simulation.

@author: Mohamed Hassan
@date: 2026-02-25
*/
package main.view;

//importing the required libraries
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {
    //instance variables
    private ControlPanel controlPanel;
    private RingPanel ringPanel;

    //constructor
    public MainFrame(){
        //setting the title, size, and default close operation of the frame
        setTitle("Consistent Hashing Simulation");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ControlPanel controlPanel = new ControlPanel();
        RingPanel ringPanel = new RingPanel();
        //creating the main split pane
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT , controlPanel, ringPanel);
        //setting the divider location to 250
        mainSplitPane.setDividerLocation(250);
        //adding the main split pane to the frame
        add(mainSplitPane,BorderLayout.CENTER);
        setVisible(true);
    }

    //getters
    public ControlPanel getControlPanel() {
        return controlPanel;
    }
    public RingPanel getRingPanel() {
        return ringPanel;
    }

    
}
