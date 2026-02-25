package main;

//importing the required libraries
import main.model.HashingRing;
import main.view.MainFrame;

public class Main {

    //main method
    public static void main(String[] args){
        HashingRing hashingRing = new HashingRing();
        MainFrame mainFrame = new MainFrame();
        SimController simController = new SimController(hashingRing, mainFrame.getRingPanel(), mainFrame.getControlPanel());
    }
    
}
