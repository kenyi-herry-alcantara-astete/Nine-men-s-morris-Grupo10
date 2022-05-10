import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame {

    public Player player1;
    public Player player2;


    ImageIcon IconWithPiece1 = new ImageIcon("src/main/resources/Image/IconWithPiece1.png");
    ImageIcon IconWithPiece2 = new ImageIcon("src/main/resources/Image/IconWithPiece2.png");
    ImageIcon IconContentEmpty = new ImageIcon("src/main/resources/Image/IconContentPiece.png");
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
    private JButton pieceLeft1;
    private JButton pieceLeft2;
    private JButton pieceLeft3;
    private JButton pieceLeft4;
    private JButton pieceLeft5;
    private JButton pieceLeft6;
    private JButton pieceLeft7;
    private JButton pieceLeft8;
    private JButton pieceLeft9;
    private JButton pieceRight1;
    private JButton pieceRight2;
    private JButton pieceRight3;
    private JButton pieceRight4;
    private JButton pieceRight5;
    private JButton pieceRight6;
    private JButton pieceRight7;
    private JButton pieceRight8;
    private JButton pieceRight9;
    private JLabel namePlayerLeft;
    private JLabel namePlayerRight;



    //Methodos que enviaran las entradas de los jugarores
    /**/

    //Current Player
    public Player currentTurn(){
        if (player1.turn == "uno"){
            return player1;
        }else return player2;
    }

    //Show the turn
    public void showTurnInUI(){
        if (player1.turn == "uno"){
            if (namePlayerLeft.getText() == player1.name){
                namePlayerLeft.setBackground(new Color(94,0,215));
                namePlayerRight.setBackground(new Color(32,36,74));
            }else {
                namePlayerRight.setBackground(new Color(94,0,215));
                namePlayerLeft.setBackground(new Color(32,36,74));
            }
        }else{
            if (namePlayerRight.getText() == player2.name){
                namePlayerRight.setBackground(new Color(94,0,215));
                namePlayerLeft.setBackground(new Color(32,36,74));
            }else {
                namePlayerLeft.setBackground(new Color(94,0,215));
                namePlayerRight.setBackground(new Color(32,36,74));
            }
        }
    }


    //Change turn
    public void changeTurn(){
        String aux = player1.turn;
        player1.turn = player2.turn;
        player2.turn = aux;
        showTurnInUI();

    }

    // insetPieceToUI
    public void insertPieceToUI(JButton contentPiece){
        if (player1.turn == "uno"){
            contentPiece.setIcon(IconWithPiece1);
            player1.numberPieces--;
        }else {
            contentPiece.setIcon(IconWithPiece2);
            player2.numberPieces--;
        }
        changeTurn();
    }

    public MainFrame() {
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertPieceToUI(b7);
            }
        });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            insertPieceToUI(b6);
            }
        });
        d7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(d7);
            }
        });
        g7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(g7);
            }
        });
        d6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(d6);
            }
        });
        f6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(f6);
            }

    //


        });
        c5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(c5);
            }
        });
        d5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(d5);
            }
        });
        e5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(e5);
            }
        });
        a4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(a4);
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(b4);
            }
        });
        c4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(c4);
            }
        });
        e4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(e4);
            }
        });
        f4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(f4);
            }
        });
        g4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(g4);
            }
        });
        c3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(c3);
            }
        });
        d3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(d3);
            }
        });
        e3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(e3);
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(b2);
            }
        });
        d2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(d2);
            }
        });
        f2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(f2);
            }
        });
        a1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(a1);
            }
        });
        d1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(d1);
            }
        });
        g1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
insertPieceToUI(g1);
            }
        });

    //Players
        this.player1 = new Player("Kenyi","left", "uno");
        this.player2 = new Player("Herry","right", "dos");

        namePlayerLeft.setText(player1.name);
        namePlayerRight.setText(player2.name);
        showTurnInUI();
    }




    public JPanel getPanelPrincipal(){
        return  PanelPrincipal;
    }

}
