package sprint3;

import java.util.ArrayList;

public class Logic {
    private int n = Constants.getOrdenMatrix();
    //Matriz tabla que muestra las jugadas en el tiempo
    private int[][] myTable;

    Memory memory = Memory.getMyMemoryObject();

    //Casos tres en raya
    int[][][] CasesTresEnRaya = Constants.getCasesTresEnRaya();


    public Logic() {
        myTable = Constants.table;
    }


    public boolean isOneOfUnTresEnRaya(String positionPiece){

        int indexRow = ComplementsMethods.whatIndexRow(positionPiece.charAt(1));
        int indexColumn = ComplementsMethods.whatIndexColumn(positionPiece.charAt(0));

        String position = Integer.toString(indexRow)+Integer.toString(indexColumn);

        for ( String groupPosition: memory.getMenoryTreEnRaya()) {
            String one = groupPosition.substring(0,2);
            String two = groupPosition.substring(2,4);
            String three = groupPosition.substring(4,6);
            if (one.equals(position) || two.equals(position) || three.equals(position)) return true;
        }
        return false;
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



    public void insertPiece(String positionPiece, int player1o2) {

        int indexRow = ComplementsMethods.whatIndexRow(positionPiece.charAt(1));
        int indexColumn = ComplementsMethods.whatIndexColumn(positionPiece.charAt(0));
        if (myTable[indexRow][indexColumn] == 0) {
            myTable[indexRow][indexColumn] = player1o2;
            showMatrixTableInTHeConsole();
            myTable[indexRow][indexColumn] = 0;
        }
    }

    public void removePiece(String positionPiece) {
        int indexRow = ComplementsMethods.whatIndexRow(positionPiece.charAt(1));
        int indexColumn = ComplementsMethods.whatIndexColumn(positionPiece.charAt(0));
        myTable[indexRow][indexColumn] = 0;
        showMatrixTableInTHeConsole();
    }


    public void movePiece(String positionPieceToRemove) {
        int indexRow = ComplementsMethods.whatIndexRow(positionPieceToRemove.charAt(1));
        int indexColumn = ComplementsMethods.whatIndexColumn(positionPieceToRemove.charAt(0));
        myTable[indexRow][indexColumn] = Constants.EMPTY_BOX;
        removePiece(positionPieceToRemove);
    }

    //Metodos utilizados por la computadora a la hora de mover una pieza

    //Devuelve un par de positions de piezas optimos que la computadora debe mover
    //["Inicio","Destiono"]
    public String[] getOptimalPositionToMove(){
        String inicio = "";
        String destino = "";

        int[] posiblePositionToMove = {-1,-1};

        //1)Primera instancia

        //Que la computadora busque formar un tres en raya

        for (int[][] gPosTresR:Constants.getCasesTresEnRaya()) {

            int x,y,m,n,r,p; //(x,y), (m,n), (r,p) Grupo de tres en raya
            x = gPosTresR[0][0];
            y = gPosTresR[0][1];
            m = gPosTresR[1][0];
            n = gPosTresR[1][1];
            r = gPosTresR[2][0];
            p = gPosTresR[2][1];

            //Verificando si puede formar un posible tres en raya.
            //Entonces la computadora tratará de mover al lugar con valor "0"
            //para completar el tres en raya

            //1)Buscamos el lugar que tendrá que mover la computadora para formar el
            //tres en raya

            if (myTable[x][y]==2 && myTable[m][n]==2 && myTable[r][p]==Constants.EMPTY_BOX){
                posiblePositionToMove[0] = r;
                posiblePositionToMove[1] = p;
            }else if (myTable[x][y]==Constants.PLAYER_TWO && myTable[m][n]==Constants.PLAYER_ONE && myTable[r][p]==Constants.PLAYER_TWO){
                posiblePositionToMove[0] = m;
                posiblePositionToMove[1] = n;
            }else if (myTable[x][y]==Constants.PLAYER_ONE && myTable[m][n]==Constants.PLAYER_TWO && myTable[r][p]==Constants.PLAYER_TWO){
                posiblePositionToMove[0] = x;
                posiblePositionToMove[1] = y;

            }
            //2)Buscando si la computadora tiene piezas adyacentes al posiblePositionToMove
            for (int [][] oneGrupoAdy:Constants.getAdjALasPiezas()) { //Recorriendo la matriz de adyacencia
                // para cada contenedor de piezas.
                if(oneGrupoAdy[0][0] == posiblePositionToMove[0] && oneGrupoAdy[0][1] == posiblePositionToMove[1]){
                    //Verificando si sus adyacentes contienen piezas de tipo "2". (Es decir piezas de la computadora).
                    for (int i = 1; i < oneGrupoAdy.length; i++) { //Recorriendo sus adyacentes
                           if (myTable[oneGrupoAdy[i][0]][oneGrupoAdy[i][1]]==Constants.PLAYER_TWO){
                               if(!((x==oneGrupoAdy[i][0] && y==oneGrupoAdy[i][1])||(m==oneGrupoAdy[i][0] && m==oneGrupoAdy[i][1])||(r==oneGrupoAdy[i][0] && p==oneGrupoAdy[i][1]))){
                                   //Asignado inicio
                                   inicio =ComplementsMethods.whatNotationColumn(oneGrupoAdy[i][1])+ ComplementsMethods.whatNotationRow(oneGrupoAdy[i][0]);
                                   //Asignando destino
                                   destino = ComplementsMethods.whatNotationColumn(posiblePositionToMove[1])+ComplementsMethods.whatNotationRow(posiblePositionToMove[0]);
                                   String[] response = {inicio,destino};
                                   return response;
                               }
                           }

                    }
                }
            }
        }



        //Reasignamos al valor original, para hacer una nueva búsqueda.
        posiblePositionToMove[0] = -1; //
        posiblePositionToMove[1] = -1; //


        //2)Instancia
        //Verificamos si hay un posible tres en raya del oponente humano
        for (int[][] gPosTresR:Constants.getCasesTresEnRaya()) {

            int x,y,m,n,r,p; //(x,y), (m,n), (r,p) Grupo de tres en raya
            x = gPosTresR[0][0];
            y = gPosTresR[0][1];
            m = gPosTresR[1][0];
            n = gPosTresR[1][1];
            r = gPosTresR[2][0];
            p = gPosTresR[2][1];

            //Verificando para el oponente humano, posibles tres en raya futuros
            //Entonces la computadora tratará de mover al lugar con valor "0"
            //de dicho posible tres en raya futuro.

            //1)Buscamos el lugar que tendrá que mover la computadora para evitar el
            //tres en raya del oponente humano

            if (myTable[x][y]==Constants.PLAYER_ONE && myTable[m][n]==Constants.PLAYER_ONE && myTable[r][p]==Constants.EMPTY_BOX){
                posiblePositionToMove[0] = r;
                posiblePositionToMove[1] = p;
            }else if (myTable[x][y]==Constants.PLAYER_ONE && myTable[m][n]==Constants.EMPTY_BOX && myTable[r][p]==Constants.PLAYER_ONE){
                posiblePositionToMove[0] = m;
                posiblePositionToMove[1] = n;
            }else if (myTable[x][y]==Constants.EMPTY_BOX && myTable[m][n]==Constants.PLAYER_ONE && myTable[r][p]==Constants.PLAYER_ONE){
                posiblePositionToMove[0] = x;
                posiblePositionToMove[1] = y;

            }
            boolean encontrado = false;
            //2)Buscando si la computadora tiene piezas adyacentes al posiblePositionToMove
            for (int [][] oneGrupoAdy:Constants.getAdjALasPiezas()) { //Recorriendo la matriz de adyacencia
                // para cada contenedor de piezas.
                if(oneGrupoAdy[0][0] == posiblePositionToMove[0] && oneGrupoAdy[0][1] == posiblePositionToMove[1]){
                    //Verificando si sus adyacentes contienen piezas de tipo "2". (Es decir piezas de la computadora).
                    for (int i = 1; i < oneGrupoAdy.length; i++) { //Recorriendo sus adyacentes
                        if (myTable[oneGrupoAdy[i][0]][oneGrupoAdy[i][1]]==Constants.PLAYER_TWO){
                            //Asignado inicio
                            inicio = ComplementsMethods.whatNotationColumn(oneGrupoAdy[i][1])+ ComplementsMethods.whatNotationRow(oneGrupoAdy[i][0]);
                            //Asignando destino
                            destino =ComplementsMethods.whatNotationColumn(posiblePositionToMove[1])+ComplementsMethods.whatNotationRow(posiblePositionToMove[0]);
                            System.out.println(inicio);
                            String[] response = {inicio,destino};
                            return response;
                        }
                    }
                }
            }

        }


        //3) Tercera instancia
        //Si no hay la posibilidad de que la computadora mueva una pieza
        //para evitar un tres en raya del oponente humano
        //Entonces por ahora moverá a un lugar random disponible.
        if(inicio.equals("")&&destino.equals("")){

            for (int [][] oneGrupoAdy:Constants.getAdjALasPiezas()) { //Recorriendo la matriz de adyacencia

                //Busca una pieza de la computadora.
                if(myTable[oneGrupoAdy[0][0]][oneGrupoAdy[0][1]]==Constants.PLAYER_TWO){
                    //Busca un adyacente vacío
                    for (int i = 1; i < oneGrupoAdy.length; i++) { //Recorriendo sus adyacentes
                        if (myTable[oneGrupoAdy[i][0]][oneGrupoAdy[i][1]]==Constants.EMPTY_BOX){
                            //Asignado inicio
                            destino = ComplementsMethods.whatNotationColumn(oneGrupoAdy[i][1])+ ComplementsMethods.whatNotationRow(oneGrupoAdy[i][0]);
                            //Asignando destino
                            inicio=ComplementsMethods.whatNotationColumn(oneGrupoAdy[0][1])+ ComplementsMethods.whatNotationRow(oneGrupoAdy[0][0]);
                            System.out.println("Inicio:"+oneGrupoAdy[i][0]+" "+oneGrupoAdy[0][1]);
                            System.out.println("Destino:"+oneGrupoAdy[0][0]+" "+oneGrupoAdy[0][1]);
                            String[] response = {inicio,destino};
                            System.out.println("Tercera instancia");
                            return response;
                        }
                    }
                }
            }
        }


        String[] response = {inicio,destino};
        return response;
    }

    public String optimalPositionToInsert(){
        int[] posiblePositionToInsert = {-1,-1};
        String destinoInsert = "";
        String response1 = "l";
        for (int[][] gPosTresR1:Constants.getCasesTresEnRaya()) {

            int x, y, m, n, r, p; //(x,y), (m,n), (r,p) Grupo de tres en raya
            x = gPosTresR1[0][0];
            y = gPosTresR1[0][1];
            m = gPosTresR1[1][0];
            n = gPosTresR1[1][1];
            r = gPosTresR1[2][0];
            p = gPosTresR1[2][1];

            if (myTable[x][y]==Constants.PLAYER_ONE && myTable[m][n]==Constants.PLAYER_ONE && myTable[r][p]==Constants.EMPTY_BOX) {
                posiblePositionToInsert[0] = r;
                posiblePositionToInsert[1] = p;
                destinoInsert =ComplementsMethods.whatNotationColumn(posiblePositionToInsert[1])+ComplementsMethods.whatNotationRow(posiblePositionToInsert[0]);
                response1 = destinoInsert;
                return response1;
            } if (myTable[x][y]==Constants.PLAYER_ONE && myTable[m][n]==Constants.EMPTY_BOX&& myTable[r][p]==Constants.PLAYER_ONE) {
                posiblePositionToInsert[0] = m;
                posiblePositionToInsert[1] = n;
                destinoInsert =ComplementsMethods.whatNotationColumn(posiblePositionToInsert[1])+ComplementsMethods.whatNotationRow(posiblePositionToInsert[0]);
                response1 = destinoInsert;
                return response1;
            } if (myTable[x][y]==Constants.EMPTY_BOX&& myTable[m][n]==Constants.PLAYER_ONE && myTable[r][p]==Constants.PLAYER_ONE) {
                posiblePositionToInsert[0] = x;
                posiblePositionToInsert[1] = y;
                destinoInsert =ComplementsMethods.whatNotationColumn(posiblePositionToInsert[1])+ComplementsMethods.whatNotationRow(posiblePositionToInsert[0]);
                response1 = destinoInsert;
                return response1;
            }

        }

        return response1;

    }
    public String optimalPositionToRemove() {
        int[] posiblePositionToInsert = {-1, -1};
        String destinoInsert = "";
        String response1 = "l";
        for (int[][] gPosTresR1 : Constants.getCasesTresEnRaya()) {

            int x, y, m, n, r, p; //(x,y), (m,n), (r,p) Grupo de tres en raya
            x = gPosTresR1[0][0];
            y = gPosTresR1[0][1];
            m = gPosTresR1[1][0];
            n = gPosTresR1[1][1];
            r = gPosTresR1[2][0];
            p = gPosTresR1[2][1];

            if (myTable[x][y]==Constants.PLAYER_ONE && myTable[m][n]==Constants.PLAYER_ONE && myTable[r][p]==Constants.EMPTY_BOX) {
                posiblePositionToInsert[0] = x;
                posiblePositionToInsert[1] = y;
                destinoInsert =ComplementsMethods.whatNotationColumn(posiblePositionToInsert[1]) + ComplementsMethods.whatNotationRow(posiblePositionToInsert[0]);
                response1 = destinoInsert;
                return response1;
            }
            if (myTable[x][y]==Constants.PLAYER_ONE && myTable[m][n]==Constants.EMPTY_BOX&& myTable[r][p]==Constants.PLAYER_ONE) {
                posiblePositionToInsert[0] = r;
                posiblePositionToInsert[1] = p;
                destinoInsert =ComplementsMethods.whatNotationColumn(posiblePositionToInsert[1]) + ComplementsMethods.whatNotationRow(posiblePositionToInsert[0]);
                response1 = destinoInsert;
                return response1;
            }
            if (myTable[x][y]==Constants.EMPTY_BOX&& myTable[m][n]==Constants.PLAYER_ONE && myTable[r][p]==Constants.PLAYER_ONE) {
                posiblePositionToInsert[0] = m;
                posiblePositionToInsert[1] = n;
                destinoInsert =ComplementsMethods.whatNotationColumn(posiblePositionToInsert[1]) + ComplementsMethods.whatNotationRow(posiblePositionToInsert[0]);
                response1 = destinoInsert;
                return response1;
            }

        }

        return response1;
    }



        public String optimalPositionToInsert2(){
        int[] posiblePositionToInsert2 = {-1,-1};
        String destinoInsert2 = "";
        String response2 = "l";
        for (int[][] gPosTresR1:Constants.getCasesTresEnRaya()) {

            int x, y, m, n, r, p; //(x,y), (m,n), (r,p) Grupo de tres en raya
            x = gPosTresR1[0][0];
            y = gPosTresR1[0][1];
            m = gPosTresR1[1][0];
            n = gPosTresR1[1][1];
            r = gPosTresR1[2][0];
            p = gPosTresR1[2][1];

            if (myTable[x][y]==Constants.PLAYER_TWO && myTable[m][n]==Constants.PLAYER_TWO && myTable[r][p]==Constants.EMPTY_BOX) {
                posiblePositionToInsert2[0] = r;
                posiblePositionToInsert2[1] = p;
                destinoInsert2 =ComplementsMethods.whatNotationColumn(posiblePositionToInsert2[1])+ComplementsMethods.whatNotationRow(posiblePositionToInsert2[0]);
                response2 = destinoInsert2;
                return response2;
            } if (myTable[x][y]==Constants.PLAYER_TWO && myTable[m][n]==Constants.EMPTY_BOX&& myTable[r][p]==Constants.PLAYER_TWO) {
                posiblePositionToInsert2[0] = m;
                posiblePositionToInsert2[1] = n;
                destinoInsert2 =ComplementsMethods.whatNotationColumn(posiblePositionToInsert2[1])+ComplementsMethods.whatNotationRow(posiblePositionToInsert2[0]);
                response2 = destinoInsert2;
                return response2;
            } if (myTable[x][y]==Constants.EMPTY_BOX&& myTable[m][n]==Constants.PLAYER_TWO && myTable[r][p]==Constants.PLAYER_TWO) {
                posiblePositionToInsert2[0] = x;
                posiblePositionToInsert2[1] = y;
                destinoInsert2 =ComplementsMethods.whatNotationColumn(posiblePositionToInsert2[1])+ComplementsMethods.whatNotationRow(posiblePositionToInsert2[0]);
                response2 = destinoInsert2;
                return response2;
            }

        }

        return response2;

    }

    //====================================================================

    // obtener verificación si la casilla está vací

    // establecer si la casilla esta vacia o llena


    public boolean validateMove(String positionPiece, String lastButton) {

        int indexRow =ComplementsMethods.whatIndexRow(lastButton.charAt(1));
        int indexColumn =ComplementsMethods.whatIndexColumn(lastButton.charAt(0));
        int indexRow1 =ComplementsMethods.whatIndexRow(positionPiece.charAt(1));
        int indexColumn1 =ComplementsMethods.whatIndexColumn(positionPiece.charAt(0));

        //column
        if (indexColumn == indexColumn1) {
            //porAbajo
            if (indexRow - indexRow1< 0) {
                if (Math.abs(indexRow1 - indexRow) == 1) {
                    return true;
                }
                if (Math.abs(indexRow1 - indexRow) == 2) {

                    if (!(myTable[indexRow + 1][indexColumn]==Constants.EMPTY_BOX) && myTable[indexRow+1][indexColumn] == Constants.EMPTY_BOX) {
                        return true;
                    }
                    return false;
                }
                if (Math.abs(indexRow1 - indexRow) == 3) {

                    if (!(myTable[indexRow + 1][indexColumn]==Constants.EMPTY_BOX) && !(myTable[indexRow + 2][indexColumn]==Constants.EMPTY_BOX) &&
                            myTable[indexRow + 1][indexColumn] ==Constants.EMPTY_BOX && myTable[indexRow + 2][indexColumn] == Constants.EMPTY_BOX) {
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

                    if (!(myTable[indexRow - 1][indexColumn]==Constants.EMPTY_BOX) && myTable[indexRow+1][indexColumn] == Constants.EMPTY_BOX) {
                        return true;
                    }
                    return false;
                }
                if (Math.abs(indexRow1 - indexRow) == 3) {

                    if (!(myTable[indexRow - 1][indexColumn]==Constants.EMPTY_BOX) && !(myTable[indexRow-2][indexColumn]==Constants.EMPTY_BOX) &&
                            myTable[indexRow - 1][indexColumn] == Constants.EMPTY_BOX && myTable[indexRow - 2][indexColumn] == Constants.PLAYER_ONE) {
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

                    if (!(myTable[indexRow][indexColumn+1]==Constants.EMPTY_BOX) && myTable[indexRow][indexColumn+1] == Constants.EMPTY_BOX) {
                        return true;
                    }
                    return false;
                }
                if (Math.abs(indexColumn1 - indexColumn) == 3) {

                    if ((!(myTable[indexRow][indexColumn+1] == Constants.EMPTY_BOX) && !(myTable[indexRow][indexColumn+2] == Constants.EMPTY_BOX) &&
                            (myTable[indexRow ][indexColumn+1] == Constants.EMPTY_BOX) && (myTable[indexRow][indexColumn+2] == Constants.EMPTY_BOX))){
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

                    if (!(myTable[indexRow ][indexColumn-1]==Constants.EMPTY_BOX) && myTable[indexRow][indexColumn-1] == Constants.EMPTY_BOX) {
                        return true;
                    }
                    return false;
                }
                if (Math.abs(indexColumn1 - indexColumn) == 3) {

                    if ((!(myTable[indexRow ][indexColumn - 1]==Constants.EMPTY_BOX) && !(myTable[indexRow ][indexColumn - 2]==Constants.EMPTY_BOX)) &&
                            (myTable[indexRow ][indexColumn - 1] ==Constants.EMPTY_BOX && myTable[indexRow ][indexColumn - 2] == Constants.EMPTY_BOX)) {
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

}
