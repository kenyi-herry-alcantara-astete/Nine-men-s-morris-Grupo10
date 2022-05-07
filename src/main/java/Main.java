import javax.swing.*;
import java.awt.*;

public class Main {

    //Methodos
    /*
    1.Elegir el turno del jugador
    2.Configurar un juego
    */


    public static void main(String[] args) {
        JFrame frame = new JFrame("Nines men's morris");
        MainFrame myNineFrame = new MainFrame();
        frame.setContentPane(myNineFrame.getPanelPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(250,100,720,480);
        frame.setVisible(true);
    }
}
