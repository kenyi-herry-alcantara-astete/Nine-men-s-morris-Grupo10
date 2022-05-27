import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainFrame extends JFrame {



    public Player player1 = new Player();
    public Player player2 = new Player();

    public boolean isPlayerAComputer = false; //Estado si se esta jugando con la computadora
    public String modeDifficulty = ""; //Nivel de la computadora : basic | estandar | expert

    public Logic currentLogicGame = new Logic();

    ImageIcon IconWithPiece1 = new ImageIcon("src/main/resources/Image/IconWithPiece1.png");
    ImageIcon IconWithPiece2 = new ImageIcon("src/main/resources/Image/IconWithPiece2.png");
    ImageIcon IconContentEmpty = new ImageIcon("src/main/resources/Image/IconContentPiece.png");
    ImageIcon IconMove = new ImageIcon("src/main/resources/Image/AvailableContent.png");

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

    private JButton [] allBtn = {a7, d7, g7, b6, d6, f6, c5, d5, e5, a4, b4, c4, e4, f4, g4, c3, d3, e3, b2, d2, f2, a1, d1, g1};
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
    JLabel namePlayerLeft;
    JLabel namePlayerRight;
    private JLabel showIUResult;


    //Show the turn
    public void showTurnInUI() {
        if (player1.turn.equals("uno")) {
            if (namePlayerLeft.getText().equals(player1.name)) {
                namePlayerLeft.setBackground(new Color(94, 0, 215));
                namePlayerRight.setBackground(new Color(32, 36, 74));
            } else {
                namePlayerRight.setBackground(new Color(94, 0, 215));
                namePlayerLeft.setBackground(new Color(32, 36, 74));
            }
        }

        if (player2.turn.equals("uno")){
            if (namePlayerRight.getText().equals(player2.name)) {
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

    private JButton lastButton;
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
            if(player1.numberPieces <= 2){
                System.out.println("Gana el jugador 2");
                showIUResult.setText("Gana el jugador 2");
            }
            if(player2.numberPieces <= 2){
                System.out.println("Gana el jugador 1");
                showIUResult.setText("Gana el jugador 1");
            }

            //Verificando si la pieza eliminada pertenecia a un tres en raya
            if (currentLogicGame.isOneOfUnTresEnRaya(myContentPieceToRemove.getText())){
                //Removing of the memory
                currentLogicGame.removeOfTheMemory(myContentPieceToRemove.getText());
            }

        if(player1.numberPieces <= 9 ){
            WindowWinner windowWinner = new WindowWinner(this, true, player2.name);
            windowWinner.setVisible(true);
            restart();
        }
        if(player2.numberPieces <= 2){
            WindowWinner windowWinner = new WindowWinner(this, true, player1.name);
            windowWinner.pack();
            windowWinner.setVisible(true);
            restart();
        }

        changeTurn();
    }

    //Moviendo pieza por la computadora
    public void movePieceByTheComputer(){
        //Preguntando si la computadora es el oponente y esta de turno
       if (isPlayerAComputer && player2.turn == "uno" && numberPiecesLeft == 0 && numberPiecesRight == 0){
           //Se llama dos veces ya que la computadora realizará inmediatamente
           //luego de elegir que pieza movera
           // la accion de mover
           //

           //En comparación al humano que lo hace en dos clicks

           String[] parThePositions = currentLogicGame.getOptimalPositionToMove();

           //Buscando la referencia a los botones de la GUI para la First position
           //Luego aplica el primer paso para mover.
           for (JButton myOneBtn:allBtn) {
               if (myOneBtn.getText().equals(parThePositions[0])){
                   movePieceToUI(myOneBtn); //Asignar la pieza que se moverá
               }
           }

           //Buscando la referencia a los botones de la GUI para la End position
           //Luego aplica el segundo paso para mover.
           for (JButton myOneBtn:allBtn) {
               if (myOneBtn.getText().equals((parThePositions[1]))){
                   movePieceToUI(myOneBtn); //Inserta en el destino la pieza
               }
           }
       }
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

        //Siempre entra a este método, pero en el método se pregunta si
        //la computadora puede mover o no. De lo contrario deja sin efecto.
        if(isPlayerAComputer){
            actionComputer();
        }
    }


    public void actionComputer(){
            movePieceByTheComputer();
    }

    // Verifica tres en raya

    public boolean scoreThreeInARow(String num) {
        boolean threeInArrow = false;

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

                if(!currentLogicGame.isInTheMemory(caseTresEnRaya)){
                    currentLogicGame.addToMemory(caseTresEnRaya);
                    threeInArrow = true;
                    break;
                }
            }

        }

        return threeInArrow;
    }

    private void initPieces() {
        initPiecesLeft();
        initPiecesRight();
    }

    // Iniciar piezas sin jugar en la parte izquierda
    private void initPiecesLeft() {
        pieceLeft = new JButton[9];
        pieceLeft[0] = pieceLeft1;
        pieceLeft[1] = pieceLeft2;
        pieceLeft[2] = pieceLeft3;
        pieceLeft[3] = pieceLeft4;
        pieceLeft[4] = pieceLeft5;
        pieceLeft[5] = pieceLeft6;
        pieceLeft[6] = pieceLeft7;
        pieceLeft[7] = pieceLeft8;
        pieceLeft[8] = pieceLeft9;
        // Llenar los iconos
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                pieceLeft[i].setIcon(IconWithPiece1);
            }
        }
    }

    // Iniciar piezas sin jugar en la parte derecha
    private void initPiecesRight() {
        pieceRight = new JButton[9];
        pieceRight[0] = pieceRight1;
        pieceRight[1] = pieceRight2;
        pieceRight[2] = pieceRight3;
        pieceRight[3] = pieceRight4;
        pieceRight[4] = pieceRight5;
        pieceRight[5] = pieceRight6;
        pieceRight[6] = pieceRight7;
        pieceRight[7] = pieceRight8;
        pieceRight[8] = pieceRight9;
        // Llenar los iconos
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                pieceRight[i].setIcon(IconWithPiece2);
            }
        }
    }


    public void restart() {
        currentLogicGame.fillInBoxes();
        currentLogicGame.fillMyTable();
        numberPiecesLeft = 9;
        numberPiecesRight = 9;
        existTicTacToe = false;
        showIUResult.setText("");
        initPieces();
        // a
        a7.setIcon(IconContentEmpty);
        a4.setIcon(IconContentEmpty);
        a1.setIcon(IconContentEmpty);
        // b
        b6.setIcon(IconContentEmpty);
        b4.setIcon(IconContentEmpty);
        b2.setIcon(IconContentEmpty);
        // c
        c5.setIcon(IconContentEmpty);
        c4.setIcon(IconContentEmpty);
        c3.setIcon(IconContentEmpty);
        // d
        d7.setIcon(IconContentEmpty);
        d6.setIcon(IconContentEmpty);
        d5.setIcon(IconContentEmpty);
        d3.setIcon(IconContentEmpty);
        d2.setIcon(IconContentEmpty);
        d1.setIcon(IconContentEmpty);
        // e
        e5.setIcon(IconContentEmpty);
        e4.setIcon(IconContentEmpty);
        e3.setIcon(IconContentEmpty);
        // f
        f6.setIcon(IconContentEmpty);
        f4.setIcon(IconContentEmpty);
        f2.setIcon(IconContentEmpty);
        // g
        g7.setIcon(IconContentEmpty);
        g4.setIcon(IconContentEmpty);
        g1.setIcon(IconContentEmpty);

        // Limpiando tres en raya
        currentLogicGame.MenoryTreEnRaya.clear();
        // Escoger Turno
        chooseTurn();
    }

    // Escoger turno
    private void  chooseTurn(){
        WhoPlaysFirst whoPlaysFirst = new WhoPlaysFirst(this, true, player1.name, player2.name);
        whoPlaysFirst.setVisible(true);

        String turn = whoPlaysFirst.getTurn();
        if(player1.name.equals(turn)){
            player1.turn = "uno";
            player2.turn = "dos";
        }else{
            player2.turn = "uno";
            player1.turn = "dos";
        }
        showTurnInUI();
    }

    private void addMenuBar(){
        // Barra de Menú
        JMenuBar menuBar = new JMenuBar();

        // Menús en la Barra de Menú
        JMenu menu = new JMenu("Menu");
        JMenu help = new JMenu("Ayuda");

        // items del menú "menu"
        JMenuItem playAgain = new JMenuItem("Jugar de Nuevo");
        JMenuItem showResults = new JMenuItem("Mostrar Resultados");
        JMenuItem quit = new JMenuItem("Salir");

        // items de menú "ayuda"
        JMenuItem tutorial = new JMenuItem("Tutorial");

        // Iconos del menú "Menu"
        ImageIcon imgPlayAgain = new ImageIcon("src/main/resources/Image/imgPlayAgain.png");
        ImageIcon imgShowResults = new ImageIcon("src/main/resources/Image/imgShowResults.png");
        ImageIcon imgQuit = new ImageIcon("src/main/resources/Image/imgQuit.png");
        playAgain.setIcon(imgPlayAgain);
        showResults.setIcon(imgShowResults);
        quit.setIcon(imgQuit);

        // Iconos del menu "Ayuda"
        ImageIcon imgTutorial = new ImageIcon("src/main/resources/Image/imgTutorial.png");
        tutorial.setIcon(imgTutorial);

        // Evento para reinciar el juego
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        });

        // Evento para salir del juego
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Eventos para ver tutorial
        tutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://www.dma.fi.upm.es/recursos/aplicaciones/matematicas_recreativas/web/nine_mens_morris/reglas.html"));
                } catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Colores
        menu.setForeground(Color.white);
        help.setForeground(Color.white);
        menuBar.setBackground(new Color(43, 43, 43));

        // Agragando items al menú "ayuda"
        help.add(tutorial);

        // Agregando items al menu "menu"
        menu.add(playAgain);
        menu.add(showResults);
        menu.add(quit);

        // Agregando menús(menu y ayuda) a la barra de menú
        menuBar.add(menu);
        menuBar.add(help);

        // Agregando la barra de menú a la ventana
        setJMenuBar(menuBar);
    }


    public MainFrame() {
        //Caracteristicas de la ventana que se va abrir
        setSize(300,300);
        setContentPane(PanelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250,100,720,520);
        setBounds(250,100,820,620);
        addMenuBar();

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

    }

    public JPanel getPanelPrincipal(){
        return  PanelPrincipal;
    }

}

