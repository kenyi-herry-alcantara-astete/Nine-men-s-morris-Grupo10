import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    private JPanel PanelPrincipal;
    private JPanel CenterPanel;
    private JButton b7;
    private JButton d7;
    private JButton g7;
    private JButton b6;
    private JButton d6;
    private JButton f6;
    private JButton c5;
    private JButton d5;
    private JButton e5;
    private JButton a4;
    private JButton b4;
    private JButton c4;
    private JButton e4;
    private JButton f4;
    private JButton g4;
    private JButton c3;
    private JButton d3;
    private JButton e3;
    private JButton b2;
    private JButton d2;
    private JButton f2;
    private JButton a1;
    private JButton d1;
    private JButton g1;


    //Methodos que enviaran las entradas de los jugarores
    /**/

    public MainFrame() {
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getPanelPrincipal(){
        return  PanelPrincipal;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
