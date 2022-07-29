package sprint3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowWinner extends JDialog {
    private JPanel contentPane;
    private JButton btnQuit;
    private JButton btnPlayAgain;
    private JLabel labelWinner;

    private String winner;
    public WindowWinner(JFrame parent, boolean modal, String winner) {
        super(parent, modal);
        this.winner = winner;
        labelWinner.setText("Felicitaciones " + winner);
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setUndecorated(true);
        pack();
        setLocationRelativeTo(parent);

        btnPlayAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
