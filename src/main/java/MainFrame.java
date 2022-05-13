import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame {

    public Player player1;
    public Player player2;

    public Logic currentLogicGame = new Logic();

    ImageIcon IconWithPiece1 = new ImageIcon("src/main/resources/Image/IconWithPiece1.png");
    ImageIcon IconWithPiece2 = new ImageIcon("src/main/resources/Image/IconWithPiece2.png");
    ImageIcon IconContentEmpty = new ImageIcon("src/main/resources/Image/IconContentPiece.png");

    private JPanel PanelPrincipal;
    private JPanel CenterPanel;
    private JButton a7;
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


    private JButton[] pieceLeft = {pieceLeft1, pieceLeft2, pieceLeft3, pieceLeft4, pieceLeft5, pieceLeft6, pieceLeft7, pieceLeft8, pieceLeft9};
    private JButton[] pieceRight = {pieceRight1, pieceRight2, pieceRight3, pieceRight4, pieceRight5, pieceRight6, pieceRight7, pieceRight8, pieceRight9};
    private JLabel namePlayerLeft;
    private JLabel namePlayerRight;
    private JLabel showIUResult;


    //Methodos que enviaran las entradas de los jugarores
    /**/

    //Current Player
    public Player currentTurn() {
        if (player1.turn == "uno") {
            return player1;
        } else return player2;
    }

    //Show the turn
    public void showTurnInUI() {
        if (player1.turn == "uno") {
            if (namePlayerLeft.getText() == player1.name) {
                namePlayerLeft.setBackground(new Color(94, 0, 215));
                namePlayerRight.setBackground(new Color(32, 36, 74));
            } else {
                namePlayerRight.setBackground(new Color(94, 0, 215));
                namePlayerLeft.setBackground(new Color(32, 36, 74));
            }
        } else {
            if (namePlayerRight.getText() == player2.name) {
                namePlayerRight.setBackground(new Color(94, 0, 215));
                namePlayerLeft.setBackground(new Color(32, 36, 74));
            } else {
                namePlayerLeft.setBackground(new Color(94, 0, 215));
                namePlayerRight.setBackground(new Color(32, 36, 74));
            }
        }
    }


    //Change turn
    public void changeTurn() {
        String aux = player1.turn;
        player1.turn = player2.turn;
        player2.turn = aux;
        showTurnInUI();

    }

    public int numberPiecesLeft = 9;
    public int numberPiecesRight = 9;
    // insetPieceToUI
    public void insertPieceToUI(JButton contentPiece){
            if (currentLogicGame.getIsAvailableContentPiece(contentPiece.getText())) {
                if (numberPiecesLeft != 0 || numberPiecesRight != 0) {
                    if (player1.turn == "uno") {
                        contentPiece.setIcon(IconWithPiece1);
                        pieceLeft[9 - numberPiecesLeft].setIcon(IconContentEmpty);
                        numberPiecesLeft--;
                        currentLogicGame.insertPiece(contentPiece.getText(), "1");

                    } else {
                        contentPiece.setIcon(IconWithPiece2);
                        pieceRight[9 - numberPiecesRight].setIcon(IconContentEmpty);
                        numberPiecesRight--;
                        currentLogicGame.insertPiece(contentPiece.getText(), "2");
                    }
                    changeTurn();
                } else {
                    System.out.println("Todas las piezas insertadas");
                }
                currentLogicGame.setAvailableContentPiece(contentPiece.getText(), false);
            }
        }

    //Remove Opponent's pieces
    public void removeOpponentsPiecesOfUI(JButton myContentPieceToRemove){

            if(myContentPieceToRemove.getIcon() == IconWithPiece1){
                myContentPieceToRemove.setIcon(IconContentEmpty);
                currentLogicGame.removePiece(myContentPieceToRemove.getText());
                player1.numberPieces --;
            }

        if(myContentPieceToRemove.getIcon() == IconWithPiece2){
                myContentPieceToRemove.setIcon(IconContentEmpty);
                currentLogicGame.removePiece(myContentPieceToRemove.getText());
                player2.numberPieces --;
            }
        if(player1.numberPieces <= 2 && player2.numberPieces <=2 ){
            System.out.println("Empate");
            showIUResult.setText("Empate!");
        }
        changeTurn();
    }

    //Action Player at the time
    boolean existTicTacToe = false;

    public void actionPlayerAtTheTime(JButton currentButtonAction) {

       if(!existTicTacToe){
           if ((numberPiecesLeft != 0 || numberPiecesRight != 0) && (currentLogicGame.getIsAvailableContentPiece(currentButtonAction.getText()))) {
               showIUResult.setText("");
               //In the Beginning
               insertPieceToUI(currentButtonAction);
               //Verificando el tres en raya



               if (player1.turn == "dos"){
                   existTicTacToe =  scoreThreeInARow("1");
                   if(existTicTacToe){
                       // Mostrando alerta de tres en raya
                       showIUResult.setText("Tres en raya para el jugador 1");
                       //Regresando el tunos, para que jueue nuevamente
                       changeTurn();
                   }
               }
              else {
                   if(player2.turn == "dos"){
                       existTicTacToe= scoreThreeInARow("2");
                       if (existTicTacToe){
                           // Mostrando alerta de tres en raya
                           showIUResult.setText("Tres en raya para el jugador 2");
                           //Regresando el tunos, para que jueue nuevamente
                           changeTurn();
                       }
                   }
               }

           }
       }else{
            removeOpponentsPiecesOfUI(currentButtonAction);
            existTicTacToe = false;
            showIUResult.setText("");
        }
    }


    // Verifica tres en raya

    public boolean scoreThreeInARow(String num) {
        boolean ganador = false;
        // Filas
        if (currentLogicGame.myTable[0][0].equals(num) && currentLogicGame.myTable[0][3].equals(num) && currentLogicGame.myTable[0][6].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[1][1].equals(num) && currentLogicGame.myTable[1][3].equals(num) && currentLogicGame.myTable[1][5].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[2][2].equals(num) && currentLogicGame.myTable[2][3].equals(num) && currentLogicGame.myTable[2][4].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[3][0].equals(num) && currentLogicGame.myTable[3][1].equals(num) && currentLogicGame.myTable[3][2].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[3][4].equals(num) && currentLogicGame.myTable[3][5].equals(num) && currentLogicGame.myTable[3][6].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[4][2].equals(num) && currentLogicGame.myTable[4][3].equals(num) && currentLogicGame.myTable[4][4].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[5][1].equals(num) && currentLogicGame.myTable[5][3].equals(num) && currentLogicGame.myTable[5][5].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[6][0].equals(num) && currentLogicGame.myTable[6][3].equals(num) && currentLogicGame.myTable[6][6].equals(num)) {
            ganador = true;
        } // Columnas
        else if (currentLogicGame.myTable[0][0].equals(num) && currentLogicGame.myTable[3][0].equals(num) && currentLogicGame.myTable[6][0].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[1][1].equals(num) && currentLogicGame.myTable[3][1].equals(num) && currentLogicGame.myTable[5][1].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[2][2].equals(num) && currentLogicGame.myTable[3][2].equals(num) && currentLogicGame.myTable[4][2].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[0][3].equals(num) && currentLogicGame.myTable[1][3].equals(num) && currentLogicGame.myTable[2][3].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[4][3].equals(num) && currentLogicGame.myTable[5][3].equals(num) && currentLogicGame.myTable[6][3].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[2][4].equals(num) && currentLogicGame.myTable[3][4].equals(num) && currentLogicGame.myTable[4][4].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[1][5].equals(num) && currentLogicGame.myTable[3][5].equals(num) && currentLogicGame.myTable[5][5].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[0][6].equals(num) && currentLogicGame.myTable[3][6].equals(num) && currentLogicGame.myTable[6][6].equals(num)) {
            ganador = true;
        }// Diagonal
        else if (currentLogicGame.myTable[0][0].equals(num) && currentLogicGame.myTable[1][1].equals(num) && currentLogicGame.myTable[2][2].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[2][4].equals(num) && currentLogicGame.myTable[1][5].equals(num) && currentLogicGame.myTable[0][6].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[4][2].equals(num) && currentLogicGame.myTable[5][1].equals(num) && currentLogicGame.myTable[6][0].equals(num)) {
            ganador = true;
        } else if (currentLogicGame.myTable[4][4].equals(num) && currentLogicGame.myTable[5][5].equals(num) && currentLogicGame.myTable[6][6].equals(num)) {
            ganador = true;
        }
        return ganador;
    }

    public MainFrame() {

        a7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPlayerAtTheTime(a7);
                insertPieceToUI(a7);

            }
        });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             actionPlayerAtTheTime(b6);
            insertPieceToUI(b6);
            }
        });
        d7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(d7);
                insertPieceToUI(d7);
            }
        });
        g7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(g7);
                insertPieceToUI(g7);
            }
        });
        d6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(d6);
                insertPieceToUI(d6);
            }
        });
        f6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(f6);
                insertPieceToUI(f6);
            }

        });
        c5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(c5);
                insertPieceToUI(c5);
            }
        });
        d5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(d5);
                insertPieceToUI(d5);
            }
        });
        e5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(e5);
                insertPieceToUI(e5);
            }
        });
        a4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(a4);
                insertPieceToUI(a4);
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(b4);
                insertPieceToUI(b4);
            }
        });
        c4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(c4);
                insertPieceToUI(c4);
            }
        });
        e4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(e4);
                insertPieceToUI(e4);
            }
        });
        f4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(f4);
                insertPieceToUI(f4);
            }
        });
        g4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(g4);
                insertPieceToUI(g4);
            }
        });
        c3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            actionPlayerAtTheTime(c3);

                insertPieceToUI(c3);
            }
        });
        d3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            actionPlayerAtTheTime(d3);

                insertPieceToUI(d3);
            }
        });
        e3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            actionPlayerAtTheTime(e3);

                insertPieceToUI(e3);
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            actionPlayerAtTheTime(b2);

                insertPieceToUI(b2);
            }
        });
        d2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            actionPlayerAtTheTime(d2);

                insertPieceToUI(d2);
            }
        });
        f2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            actionPlayerAtTheTime(f2);

                insertPieceToUI(f2);
            }
        });
        a1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            actionPlayerAtTheTime(a1);

                insertPieceToUI(a1);
            }
        });
        d1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            actionPlayerAtTheTime(d1);
                insertPieceToUI(d1);
            }
        });
        g1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(g1);
                insertPieceToUI(g1);
            }
        });

        //Players
        this.player1 = new Player("Kenyi", "left", "uno");
        this.player2 = new Player("Herry", "right", "dos");

        namePlayerLeft.setText(player1.name);
        namePlayerRight.setText(player2.name);



        showTurnInUI();
    }


    public JPanel getPanelPrincipal(){
        return  PanelPrincipal;
    }

}

