import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame {

    public Player player1;
    public Player player2;

    //public Logic currentLogicGame = new Logic();

    ImageIcon IconWithPiece1 = new ImageIcon("src/main/resources/Image/IconWithPiece1.png");
    ImageIcon IconWithPiece2 = new ImageIcon("src/main/resources/Image/IconWithPiece2.png");
    ImageIcon IconContentEmpty = new ImageIcon("src/main/resources/Image/IconContentPiece.png");

    // Orden de la matriz
    private int n = 7;
    // Matriz de casillas disponibles
    private boolean[][] availableBox = new boolean[n][n];
    //Matriz tabla que muestra las jugadas en el tiempo
    private String [][] myTable = new String[n][n];

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
        String positionPiece = contentPiece.getText();
        int indexRow = whatIndexRow(positionPiece.charAt(2));
        int indexColumn = whatIndexColumn(positionPiece.charAt(1));

        if(availableBox[indexRow][indexColumn]){
            if (player1.turn == "uno"){
                contentPiece.setIcon(IconWithPiece1);
                player1.numberPieces--;
                insertPiece(indexRow, indexColumn,"1");
            }else {
                contentPiece.setIcon(IconWithPiece2);
                player2.numberPieces--;
                insertPiece(indexRow, indexColumn,"2");
            }
            changeTurn();
        }
    }

    // Inicializa casillas disponibles
    public void fillInBoxes(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                availableBox[i][j] = true;
            }
        }
    }

    // Inicializa tablero
    public void fillMyTable(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                myTable[i][j] = "0";
            }
        }
    }


    public void showMatrixTableInTHeConsole (){
        System.out.println("--------------------------------");

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(myTable[i][j] + " ");
            }
            System.out.println();
        }

    }
    private int whatIndexColumn(char notationColumn){
        int indexColum = -1;
        switch (notationColumn){
            case 'a':
                indexColum = 0;
                break;
            case 'b':
                indexColum = 1;
                break;
            case 'c':
                indexColum = 2;
                break;
            case 'd':
                indexColum = 3;
                break;
            case 'e':
                indexColum = 4;
                break;
            case 'f':
                indexColum = 5;
                break;
            case 'g':
                indexColum = 6;
                break;
        }
        return indexColum;
    }
    private  int whatIndexRow(char notationRow){
        int indexRow = -1;
        switch (notationRow){
            case '7':
                indexRow = 0;
                break;
            case '6':
                indexRow = 1;
                break;
            case '5':
                indexRow = 2;
                break;
            case '4':
                indexRow = 3;
                break;
            case '3':
                indexRow = 4;
                break;
            case '2':
                indexRow = 5;
                break;
            case '1':
                indexRow = 6;
                break;
        }
        return indexRow;
    }
    public void insertPiece(int indexRow, int indexColumn, String player1o2){
        if(availableBox[indexRow][indexColumn]){
            myTable[indexRow][indexColumn] = player1o2;
            showMatrixTableInTHeConsole();
            availableBox[indexRow][indexColumn] = false;
        }
    }

    public String removePiece(String positionPiece){
        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));
        String pieceToRemove = myTable[indexRow][indexColumn];
        myTable[indexRow][indexColumn] = " ";
        return pieceToRemove;
    }

    public void movePiece(String positionPieceToRemove,String  newPositionPiece){
        //RemovePiece
        //SetNewPiece
        //insertPiece(newPositionPiece,removePiece(positionPieceToRemove));
    }

    //Is available a content piece?
    public boolean isAvailableContentPiece(String positionPiece){
        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));
        if (myTable[indexRow][indexColumn] != " "){
            return true;
        }
        return false;
    }

    public MainFrame() {

        a7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertPieceToUI(a7);
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

        // Inicializa casillas disponibles
        fillInBoxes();
        // Inicializa tablero
        fillMyTable();
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
