package sprint2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WhoPlaysFirst extends JDialog {
    private JPanel contentPane;
    private JButton btnPlayer1;
    private JButton btnPlayer2;

    private String player1;
    private String player2;

    private String turn;

    public WhoPlaysFirst(JFrame parent, boolean modal, String player1, String player2) {
        super(parent, modal);
        this.player1 = player1;
        this.player2 = player2;
        initButtons();
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setUndecorated(true);
        pack();
        setLocationRelativeTo(parent);

        btnPlayer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turn = player1;
                dispose();
            }
        });
        btnPlayer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turn = player2;
                dispose();
            }
        });
    }

    public String getTurn(){
        return turn;
    }
    private void initButtons(){
        btnPlayer1.setText(player1);
        btnPlayer2.setText(player2);
    }
}