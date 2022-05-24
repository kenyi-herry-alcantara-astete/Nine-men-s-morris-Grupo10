import java.util.ArrayList;

public class Logic {


    // Orden de la matriz
    private int n = 7;
    // Matriz de casillas disponibles
    protected boolean[][] availableBox = new boolean[n][n];
    //Matriz tabla que muestra las jugadas en el tiempo
    protected String[][] myTable = new String[n][n];

    //CasesTresEnRaya
    int[][][] CasesTresEnRaya = Constants.getCasesTresEnRaya();

    //Matriz recuerdo
    protected ArrayList<String> MenoryTreEnRaya = new ArrayList<String>();

    public boolean isInTheMemory(String caseTresEnRaya){
        boolean res = false;

        for (String oneCase: MenoryTreEnRaya){
            if(oneCase.equals(caseTresEnRaya)){
                res = true;
            }
        }
        return  res;

    }

    public void addToMemory(String caseTresEnRaya){
        MenoryTreEnRaya.add(caseTresEnRaya);
    }

    public void removeOfTheMemory(String positionPiece){


        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));

        String posititon = ""+indexRow+""+indexColumn;
        ArrayList <String> Temporary = new ArrayList<String>();

        for ( String groupPosition:MenoryTreEnRaya) {
           if (MenoryTreEnRaya.size() != 0){
               String one = groupPosition.substring(0,2);
               String two = groupPosition.substring(2,4);
               String three = groupPosition.substring(4,6);
               if (one.equals(posititon) || two.equals(posititon) || three.equals(posititon)) {
                   Temporary.add(one+two+three);
               }
           }
        }
        for (String group:Temporary) {
            MenoryTreEnRaya.remove(group);
        }
        //Elimnando Temporary
        Temporary.clear();

    }



    public boolean isOneOfUnTresEnRaya(String positionPiece){

        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));

        String posititon = ""+indexRow+""+indexColumn;

        for ( String groupPosition:MenoryTreEnRaya) {
            String one = groupPosition.substring(0,2);
            String two = groupPosition.substring(2,4);
            String three = groupPosition.substring(4,6);
            if (one.equals(posititon) || two.equals(posititon) || three.equals(posititon)) return true;
        }
        return false;
    }


    public Logic() {
        fillInBoxes();
        fillMyTable();
    }

    public void fillInBoxes() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                availableBox[i][j] = true;
            }
        }
        for (int [] onePositionInvalid : Constants.getPositionInvalid()) {
            availableBox[onePositionInvalid[0]][onePositionInvalid[1]] = false;
        }
    }

    public void fillMyTable() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                myTable[i][j] = "0";
            }
        }
    }

    public void showMatrixTableInTHeConsole() {
        System.out.println("--------------------------------");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(myTable[i][j] + " ");
            }
            System.out.println();
        }

    }

    public int whatIndexColumn(char notationColumn) {
        return Character.getNumericValue(notationColumn) - 10;
    }

    public int whatIndexRow(char notationRow) {
     return 7-Character.getNumericValue(notationRow);
    }


    public void insertPiece(String positionPiece, String player1o2) {

        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));
        if (availableBox[indexRow][indexColumn]) {
            myTable[indexRow][indexColumn] = player1o2;
            showMatrixTableInTHeConsole();
            availableBox[indexRow][indexColumn] = false;
        }
    }

    public String removePiece(String positionPiece) {
        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));
        String pieceToRemove = myTable[indexRow][indexColumn];
        myTable[indexRow][indexColumn] = "0";
        showMatrixTableInTHeConsole();
        setAvailableContentPiece(positionPiece, true);
        return pieceToRemove;
    }

    public void movePiece(String positionPieceToRemove) {
        //RemovePiece
        //SetNewPiece
        setAvailableContentPiece(positionPieceToRemove, true);
        removePiece(positionPieceToRemove);
        //insertPiece(newPositionPiece,removePiece(positionPieceToRemove));
    }

    // obtener verificación si la casilla está vacía
    public boolean getIsAvailableContentPiece(String positionPiece) {
        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));
        if (availableBox[indexRow][indexColumn]) {
            return true;
        }
        return false;
    }

    // establecer si la casilla esta vacia o llena
    public void setAvailableContentPiece(String positionPiece, boolean available) {
        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));
        availableBox[indexRow][indexColumn] = available;
    }

    public boolean validateMove(String positionPiece, String lastButton) {

        int indexRow = whatIndexRow(lastButton.charAt(1));
        int indexColumn = whatIndexColumn(lastButton.charAt(0));
        int indexRow1 = whatIndexRow(positionPiece.charAt(1));
        int indexColumn1 = whatIndexColumn(positionPiece.charAt(0));

        //column
        if (indexColumn == indexColumn1) {
            //porAbajo
            if (indexRow - indexRow1< 0) {
                if (Math.abs(indexRow1 - indexRow) == 1) {
                    return true;
                }
                if (Math.abs(indexRow1 - indexRow) == 2) {

                    if (!availableBox[indexRow + 1][indexColumn] && myTable[indexRow+1][indexColumn] == "0") {
                        return true;
                    }
                    return false;
                }
                if (Math.abs(indexRow1 - indexRow) == 3) {

                    if (!availableBox[indexRow + 1][indexColumn] && !availableBox[indexRow + 2][indexColumn] &&
                            myTable[indexRow + 1][indexColumn] == "0" && myTable[indexRow + 2][indexColumn] == "0") {
                        return true;
                    }
                    return false;
                }
            }
            //porArriba
            if (indexRow - indexRow1 > 0) {
                if (Math.abs(indexRow1 - indexRow) == 1) {
                    return true;
                }
                if (Math.abs(indexRow1 - indexRow) == 2) {

                    if (!availableBox[indexRow - 1][indexColumn] && myTable[indexRow+1][indexColumn] == "0") {
                        return true;
                    }
                    return false;
                }
                if (Math.abs(indexRow1 - indexRow) == 3) {

                    if (!availableBox[indexRow - 1][indexColumn] && !availableBox[indexRow - 2][indexColumn] &&
                            myTable[indexRow - 1][indexColumn] == "0" && myTable[indexRow - 2][indexColumn] == "0") {
                        return true;
                    }
                    return false;
                }
            }
        }
        //filas
        if (indexRow == indexRow1) {
            //a la derecha
            if (indexColumn - indexColumn1 < 0) {
                if (Math.abs(indexColumn1 - indexColumn) == 1) {
                    return true;
                }
                if (Math.abs(indexColumn1 - indexColumn) == 2) {

                    if (!availableBox[indexRow][indexColumn+1] && myTable[indexRow][indexColumn+1] == "0") {
                        return true;
                    }
                    return false;
                }
                if (Math.abs(indexColumn1 - indexColumn) == 3) {

                    if ((!availableBox[indexRow ][indexColumn+1] && !availableBox[indexRow][indexColumn+2]) &&
                            (myTable[indexRow ][indexColumn+1] == "0" && myTable[indexRow][indexColumn+2] == "0")) {
                        return true;
                    }
                    return false;
                }
            }
            //a la izquierda
            if (indexColumn - indexColumn1 > 0) {
                if (Math.abs(indexColumn1 - indexColumn) == 1) {
                    return true;
                }
                if (Math.abs(indexColumn1 - indexColumn) == 2) {

                    if (!availableBox[indexRow ][indexColumn-1] && myTable[indexRow][indexColumn-1] == "0") {
                        return true;
                    }
                    return false;
                }
                if (Math.abs(indexColumn1 - indexColumn) == 3) {

                    if ((!availableBox[indexRow ][indexColumn - 1] && !availableBox[indexRow ][indexColumn - 2]) &&
                            (myTable[indexRow ][indexColumn - 1] == "0" && myTable[indexRow ][indexColumn - 2] == "0")) {
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

}
