public class Logic {

    //Matriz tabla que muestra las jugadas en el tiempo

    private String [][] myTable ={{" ","0","0"," ","0","0"," "},
                                  {"0"," ","0"," ","0"," ","0"},
                                  {"0","0"," "," "," ","0","0"},
                                  {" "," "," ","0"," "," "," "},
                                  {"0","0"," "," "," ","0","0"},
                                  {"0"," ","0"," ","0"," ","0"},
                                  {" ","0","0"," ","0","0"," "}};

    public void showMatrixTableInTHeConsole (){
        System.out.print("\n--------------------------------: '_':No permitido  1: jugaros left  2: jugador right\n");

        for (int i = 0; i < 7; i++) {
            String row = "";
            for (int j = 0; j < 7; j++) {
                row = row + " " + myTable[i][j];
            }
            row=row+"\n";
            System.out.print(row);
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
    public void insertPiece(String positionPiece, String player1o2){
        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));
        myTable[indexRow][indexColumn] = player1o2;
        showMatrixTableInTHeConsole();
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
        //RemovePiece
        //SetNewPiece
        insertPiece(newPositionPiece,removePiece(positionPieceToRemove));
    }

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
