import javax.sound.sampled.Line;
import javax.swing.*;

public class Main {

    //Start Game

    public static void main(String[] args) {
        JFrame frame = new JFrame("Nines men's morris");
        MainFrame myNineFrame = new MainFrame();
        frame.setContentPane(myNineFrame.getPanelPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(250,100,720,520);
        frame.setVisible(true);
    }
}
