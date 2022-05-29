import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame {
    //creamos 2 objetos del tipo Player
    public Player player1 = new Player("left","uno");
    public Player player2 = new Player("right","dos");
    //creamos 1 objeto del tipo Logic
    public Logic currentLogicGame = new Logic();

    ImageIcon IconWithPiece1 = new ImageIcon("src/main/resources/Image/IconWithPiece1.png");
    ImageIcon IconWithPiece2 = new ImageIcon("src/main/resources/Image/IconWithPiece2.png");
    ImageIcon IconContentEmpty = new ImageIcon("src/main/resources/Image/IconContentPiece.png");
    ImageIcon IconMove = new ImageIcon("src/main/resources/Image/AvailableContent.png");

    private JPanel PanelPrincipal;
    private JPanel CenterPanel;
    private JButton a7, d7,g7,b6,d6,f6,c5,d5,e5,a4,b4,c4,e4,f4,g4,c3,d3,e3,b2,d2,f2,a1,d1,g1;
    private JButton pieceLeft1,pieceLeft2, pieceLeft3, pieceLeft4,pieceLeft5,pieceLeft6,pieceLeft7,pieceLeft8,pieceLeft9;
    private JButton pieceRight1,pieceRight2,pieceRight3,pieceRight4,pieceRight5,pieceRight6,pieceRight7,pieceRight8,pieceRight9;


    private JButton[] pieceLeft = {pieceLeft1, pieceLeft2, pieceLeft3, pieceLeft4, pieceLeft5, pieceLeft6, pieceLeft7, pieceLeft8, pieceLeft9};
    private JButton[] pieceRight = {pieceRight1, pieceRight2, pieceRight3, pieceRight4, pieceRight5, pieceRight6, pieceRight7, pieceRight8, pieceRight9};

    JLabel namePlayerLeft; //Nombre del jugador izquierdo

    JLabel namePlayerRight;//Nombre del jugador derecho
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

    private JButton lastButton = a7;
    public int numberMove = 0;
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

        int pushKeybord = 0;


    //
    public void setNameInTheUI(){
        namePlayerLeft.setText(player1.name);
        namePlayerRight.setText(player2.name);
    }
    //mover pieza
    public void movePieceToUI(JButton myMoveContentPiece){
    //--------- AQUI AGREGUE UNA CONDICIONAL --> CHAUCA ANGEL---------------------------------------------------------------------------------
    // Aqui agregue la condicional para que filtre el caso en que algun jugador tenga 3 piezas.
    // Si en un momento se cumple la condicion, se invoca a la funcion " mover_piezas_de_forma_libre() " que permitira mover
    // cualquier pieza a cualquier posicion libre.

            if(player1.numberPieces==3 || player2.numberPieces==3){
                mover_piezas_de_forma_libre(myMoveContentPiece);
            }
    //---------------------------------------------------------------------------------------------------------

            if (player1.turn == "uno") {

                if (numberPiecesLeft == 0 && numberPiecesRight == 0 && myMoveContentPiece.getIcon() == IconWithPiece1 && numberMove == 0) {

                    lastButton = myMoveContentPiece;
                    myMoveContentPiece.setIcon(IconMove);
                    numberMove++;
                    System.out.println(numberMove);
                }
            }
            if (player2.turn == "uno") {

                if (numberPiecesLeft == 0 && numberPiecesRight == 0 && myMoveContentPiece.getIcon() == IconWithPiece2 && numberMove == 0) {

                    lastButton = myMoveContentPiece;
                    myMoveContentPiece.setIcon(IconMove);
                    numberMove++;
                    pushKeybord++;
                    System.out.println(numberMove);
                }
            }



        if(numberMove == 1 && currentLogicGame.getIsAvailableContentPiece(myMoveContentPiece.getText())){
            if(currentLogicGame.validateMove(myMoveContentPiece.getText(), lastButton.getText())) {
                if (currentLogicGame.getIsAvailableContentPiece(myMoveContentPiece.getText())) {
                    if (player1.turn == "uno") {
                        System.out.println(numberMove);
                        numberPiecesLeft++;
                        currentLogicGame.movePiece(lastButton.getText());
                        lastButton.setIcon(IconContentEmpty);
                    } else {
                        System.out.println(numberMove);
                        numberPiecesRight++;
                        currentLogicGame.movePiece(lastButton.getText());
                        lastButton.setIcon(IconContentEmpty);
                    }
                    numberMove--;
                }
            }
        }

    }
//-------------------MI PARTE ----- ANGEL CHAUCA-----------------------------

    public void mover_piezas_de_forma_libre(JButton myMoveContentPiece){
        if (player1.turn == "uno") {

            if (numberPiecesLeft == 0 && numberPiecesRight == 0 && myMoveContentPiece.getIcon() == IconWithPiece1 && numberMove == 0) {

                lastButton = myMoveContentPiece;
                myMoveContentPiece.setIcon(IconMove);
                numberMove++;
                System.out.println(numberMove);
            }
        }
        if (player2.turn == "uno") {

            if (numberPiecesLeft == 0 && numberPiecesRight == 0 && myMoveContentPiece.getIcon() == IconWithPiece2 && numberMove == 0) {

                lastButton = myMoveContentPiece;
                myMoveContentPiece.setIcon(IconMove);
                numberMove++;
                pushKeybord++;
                System.out.println(numberMove);
            }
        }



        if(numberMove == 1 && currentLogicGame.getIsAvailableContentPiece(myMoveContentPiece.getText())){
            if(true) {
                if (currentLogicGame.getIsAvailableContentPiece(myMoveContentPiece.getText())) {
                    if (player1.turn == "uno") {
                        System.out.println(numberMove);
                        numberPiecesLeft++;
                        currentLogicGame.movePiece(lastButton.getText());
                        lastButton.setIcon(IconContentEmpty);
                    } else {
                        System.out.println(numberMove);
                        numberPiecesRight++;
                        currentLogicGame.movePiece(lastButton.getText());
                        lastButton.setIcon(IconContentEmpty);
                    }
                    numberMove--;
                }
            }
        }
    }

//-------------------------------------------------------------------------------


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

        movePieceToUI(currentButtonAction);

        if(!existTicTacToe){
           if ((numberPiecesLeft != 0 || numberPiecesRight != 0) && (currentLogicGame.getIsAvailableContentPiece(currentButtonAction.getText()))) {
               showIUResult.setText("");
               //In the Beginning
               insertPieceToUI(currentButtonAction);

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
            currentLogicGame.setAvailableContentPiece(currentButtonAction.getText(),true);
        }
    }


    // Verifica tres en raya

    public boolean scoreThreeInARow(String num) {
        boolean ganador = false;

            for (int [][] oneGroupCase: currentLogicGame.CasesTresEnRaya) {
                int x = oneGroupCase[0][0];
                int y = oneGroupCase[0][1];
                int r = oneGroupCase[1][0];
                int s = oneGroupCase[1][1];
                int m = oneGroupCase[2][0];
                int n = oneGroupCase[2][1];

                if (currentLogicGame.myTable[x][y].equals(num) && currentLogicGame.myTable[r][s].equals(num) && currentLogicGame.myTable[m][n].equals(num)) {
                    String caseTresEnRaya = ""+x+y+r+s+m+n;
                    System.out.println(caseTresEnRaya);
                    if(currentLogicGame.isInTheMemory(caseTresEnRaya)){
                        break;
                    }else{
                        currentLogicGame.addToMemory(caseTresEnRaya);
                        ganador = true;
                    }
                }

        }

        return ganador;
    }



    public MainFrame() {
        //Caracteristicas de la ventana que se va abrir
        setSize(300,300);
        setContentPane(PanelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250,100,720,520);
        setBounds(250,100,820,620);
        setVisible(true);//mostrar la venta FirstPanel

        a7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPlayerAtTheTime(a7);

            }
        });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             actionPlayerAtTheTime(b6);
            }
        });
        d7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(d7);
            }
        });
        g7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPlayerAtTheTime(g7);
            }
        });
        d6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPlayerAtTheTime(d6);
            }
        });
        f6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(f6);
            }

        });
        c5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(c5);
            }
        });
        d5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(d5);
            }
        });
        e5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(e5);
            }
        });
        a4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(a4);
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(b4);
            }
        });
        c4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(c4);
            }
        });
        e4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(e4);
            }
        });
        f4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(f4);
            }
        });
        g4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(g4);
            }
        });
        c3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(c3);
            }
        });
        d3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(d3);
            }
        });
        e3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(e3);
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(b2);
            }
        });
        d2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(d2);
            }
        });
        f2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(f2);
            }
        });
        a1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(a1);
            }
        });
        d1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(d1);
            }
        });
        g1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionPlayerAtTheTime(g1);
            }
        });

        //Player

        //this.player1 = new Player("Kenyi", "left", "uno");
        //this.player2 = new Player("Herry", "right", "dos");

        showTurnInUI();
    }



    public JPanel getPanelPrincipal(){
        return  PanelPrincipal;
    }

}

