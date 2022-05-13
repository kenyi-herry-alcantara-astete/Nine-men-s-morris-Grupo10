public class Logic {
    // Orden de la matriz
    private int n = 7;
    // Matriz de casillas disponibles
    protected boolean[][] availableBox = new boolean[n][n];
    //Matriz tabla que muestra las jugadas en el tiempo
    protected String [][] myTable = new String[n][n];

    public Logic(){
        fillInBoxes();
        fillMyTable();
    }
    public void fillInBoxes(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                availableBox[i][j] = true;
            }
        }
    }

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

    public int whatIndexColumn(char notationColumn){
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
    public  int whatIndexRow(char notationRow){
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
    public void insertPiece(String positionPiece, String player1o2){

        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));
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
        showMatrixTableInTHeConsole();
        return pieceToRemove;
    }

    public void movePiece(String positionPieceToRemove,String  newPositionPiece){

       /* if(myTable[i][j] =) {
            insertPiece(newPositionPiece, removePiece(positionPieceToRemove));
        }*/
    }

    //Is available a content piece?
    //Is available a content piece?
    public boolean isAvailableContentPiece(String positionPiece){
        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));
        if (myTable[indexRow][indexColumn] == " "){
            showMatrixTableInTHeConsole();
            return true;
        }
        showMatrixTableInTHeConsole();
        return false;
    }

}
