package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {

    public MainFrame(){
        setTitle("Consistent Hashing Simulation");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ControlPanel controlPanel = new ControlPanel();
        RingPanel ringPanel = new RingPanel();
        JPanel statusPanel = new JPanel();
        JPanel logPanel = new JPanel();
    }

    
}
