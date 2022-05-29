import java.util.ArrayList;

public class Logic {


    // Orden de la matriz
    private int n = 7;
    // Matriz de casillas disponibles
    protected boolean[][] availableBox = new boolean[n][n];
    //Matriz tabla que muestra las jugadas en el tiempo
    protected String[][] myTable = new String[n][n];

    //CasesTresEnRaya
    int[][][] CasesTresEnRaya = {
            //Filas
            {
                    {0,0},
                    {0,3},
                    {0,6}
            },
            {
                    {1,1},
                    {1,3},
                    {1,5}
            },
            {
                    {2,2},
                    {2,3},
                    {2,4}
            },
            {
                    {3,0},
                    {3,1},
                    {3,2}
            },
            {
                    {3,4},
                    {3,5},
                    {3,6}
            },
            {
                    {4,2},
                    {4,3},
                    {4,4}
            },
            {
                    {5,1},
                    {5,3},
                    {5,5}
            },
            {
                    {6,0},
                    {6,3},
                    {6,6}
            },
            //Columnas
            {
                    {0,0},
                    {3,0},
                    {6,0}
            },
            {
                    {1,1},
                    {3,1},
                    {5,1}
            },
            {
                    {2,2},
                    {3,2},
                    {4,2}
            },
            {
                    {0,3},
                    {1,3},
                    {2,3}
            },
            {
                    {4,3},
                    {5,3},
                    {6,3}
            },
            {
                    {2,4},
                    {3,4},
                    {4,4}
            },
            {
                    {1,5},
                    {3,5},
                    {5,5}
            },
            {
                    {0,6},
                    {3,6},
                    {6,6}
            }
    };

    //Matriz recuerdo
    ArrayList<String> MenoryTreEnRaya = new ArrayList<String>();

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

    public void removeFoTheMemory(String caseTresEnRaya){
        MenoryTreEnRaya.remove(caseTresEnRaya);
    }

    public Logic() {
        fillInBoxes();
        fillMyTable();
    }

    public void fillInBoxes()                                {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                availableBox[i][j] = true;
            }
        }
        availableBox[1][0] = false;
        availableBox[0][1] = false;
        availableBox[0][2] = false;
        availableBox[3][3] = false;
        availableBox[2][0] = false;
        availableBox[2][1] = false;
        availableBox[1][2] = false;
        availableBox[4][0] = false;
        availableBox[4][1] = false;
        availableBox[5][2] = false;
        availableBox[5][0] = false;
        availableBox[6][1] = false;
        availableBox[6][2] = false;

        availableBox[0][4] = false;
        availableBox[0][5] = false;
        availableBox[1][6] = false;
        availableBox[1][4] = false;
        availableBox[2][5] = false;
        availableBox[2][6] = false;
        availableBox[5][4] = false;
        availableBox[4][5] = false;
        availableBox[4][6] = false;
        availableBox[6][4] = false;
        availableBox[6][5] = false;
        availableBox[5][6] = false;
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
        int indexColum = -1;
        switch (notationColumn) {
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

    public int whatIndexRow(char notationRow) {
        int indexRow = -1;
        switch (notationRow) {
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
            showMatrixTableInTHeConsole();
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
