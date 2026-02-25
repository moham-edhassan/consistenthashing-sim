/*
RingPanel.java class

This class represents the ring panel that is used to display the ring with its nodes and keys on it.

@author: Mohamed Hassan
@date: 2026-02-25
*/
package main.view;

import javax.swing.JPanel;
import java.util.List;
import main.model.PhysicalNode;
import main.model.VirtualNode;
import main.model.KeyEntry;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;

public class RingPanel extends JPanel {
    //instance variables
    private List<PhysicalNode> nodes;
    private List<VirtualNode> virtualNodes;
    private List<KeyEntry> keys;
    private long maxHashValue = Long.MAX_VALUE;

    //constructor
    public RingPanel(){
        nodes = new ArrayList<>();
        virtualNodes = new ArrayList<>();
        keys = new ArrayList<>();
        setBackground(Color.WHITE);
    }

    //hashing the hash value to an angle and converting it to x and y coordinates
    public double hashToAngle(long hashValue){
        return (hashValue /(double) maxHashValue)  * 2 * Math.PI;
    }
    //converting the angle to x coordinate
    public int angleToX(double angle, int radius, int centerX){
        return centerX + (int)(radius * Math.cos(angle));
    }
    //converting the angle to y coordinate
    public int angleToY(double angle, int radius, int centerY){
        return centerY + (int)(radius * Math.sin(angle));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        //calling the super method to paint the component
        super.paintComponent(g);
        //casting the graphics object to a graphics2d object
        Graphics2D g2 = (Graphics2D) g;
    
        //setting the rendering hints for the graphics object
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //getting the center of the panel
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 40;
    
        //drawing the ring 
        g2.setColor(Color.LIGHT_GRAY);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
    
        //drawing the physical node and its virtual nodes
        for (PhysicalNode node : nodes) {
            for (VirtualNode vnode : virtualNodes) {
                if (vnode.getParentNode() == node) {
                    double angle = hashToAngle(vnode.getPosition());
                    int x = angleToX(angle, radius, centerX);
                    int y = angleToY(angle, radius, centerY);
                    g2.setColor(node.getColor());
                    g2.fillOval(x - 8, y - 8, 16, 16);
                    g2.setColor(Color.BLACK);
                    g2.drawString(node.getName(), x + 12, y + 4);
                    break;    // only use the first vnode, not all 150
                }
            }
        }
    
        //drawing the keys using their owner node's color 
        for (KeyEntry key : keys) {
            double angle = hashToAngle(key.getHashValue());
            int x = angleToX(angle, radius, centerX);
            int y = angleToY(angle, radius, centerY);
            g2.setColor(key.getAssignedPhysicalNode().getColor());
            g2.fillOval(x - 3, y - 3, 6, 6);
        }
    }

    //updating the physical nodes
    public void updateNodes(List<PhysicalNode> nodes){
        this.nodes = nodes;
        repaint();
    }

    //updating the virtual nodes
    public void updateVirtualNodes(List<VirtualNode> virtualNodes){
        this.virtualNodes = virtualNodes;
        repaint();
    }

    //updating the keys
    public void updateKeys(List<KeyEntry> keys){
        this.keys = keys;
        repaint();
    }
}
