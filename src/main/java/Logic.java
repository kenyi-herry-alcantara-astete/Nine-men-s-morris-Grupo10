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

    //Recibe las notacions de la tabla GUI y retorna la position para la tabla logica.
    public int whatIndexColumn(char notationColumn) {
        return Character.getNumericValue(notationColumn) - 10;
    }

    public int whatIndexRow(char notationRow) {
     return 7-Character.getNumericValue(notationRow);
    }

    //Recibe la postion de la tabla logica y retorna las notaciones de la tabla de la GUI.
    public String whatNotationColumn(int indexColumn) {
        return Constants.getLettersAndNumbersEquivalent(indexColumn);
    }

    public String whatNotationRow(int indexRow) {
        return ""+(7-indexRow);
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

    public void removePiece(String positionPiece) {
        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));
        myTable[indexRow][indexColumn] = "0";
        showMatrixTableInTHeConsole();
        setAvailableContentPiece(positionPiece, true);
    }


    public void movePiece(String positionPieceToRemove) {
        //RemovePiece
        //SetNewPiece
        setAvailableContentPiece(positionPieceToRemove, true);
        removePiece(positionPieceToRemove);
        //insertPiece(newPositionPiece,removePiece(positionPieceToRemove));
    }

    //Metodos utilizados por la computadora a la hora de mover una pieza

    //Devuelve un par de positions de piezas optimos que la computadora debe mover
    //["Inicio","Destiono"]
    public String[] getOptimalPositionToMove(){
        String inicio = "";
        String destino = "";

        int[] posiblePositionToMove = {-1,-1};

        //1)Primera instancia
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

            if (myTable[x][y].equals("1") && myTable[m][n].equals("1") && myTable[r][p].equals("0")){
                posiblePositionToMove[0] = r;
                posiblePositionToMove[1] = p;
            }else if (myTable[x][y].equals("1") && myTable[m][n].equals("0") && myTable[r][p].equals("1")){
                posiblePositionToMove[0] = m;
                posiblePositionToMove[1] = n;
            }else if (myTable[x][y].equals("0") && myTable[m][n].equals("1") && myTable[r][p].equals("1")){
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
                        if (myTable[oneGrupoAdy[i][0]][oneGrupoAdy[i][1]].equals("2")){
                            //Asignado inicio
                            inicio = whatNotationColumn(oneGrupoAdy[i][1])+ whatNotationRow(oneGrupoAdy[i][0]);
                            //Asignando destino
                            destino =whatNotationColumn(posiblePositionToMove[1])+whatNotationRow(posiblePositionToMove[0]);
                            System.out.println("Inicio:"+oneGrupoAdy[i][0]+" "+oneGrupoAdy[0][1]);
                            System.out.println("Destino:"+posiblePositionToMove[0]+" "+posiblePositionToMove[1]);
                            System.out.println(inicio);
                            encontrado = true;
                            String[] response = {inicio,destino};
                            return response;
                        }
                    }
                    break;
                }
            }
            if (encontrado){
                break;
            }
        }

        //Reasignamos al valor original, para hacer una nueva búsqueda.
        posiblePositionToMove[0] = -1; //
        posiblePositionToMove[1] = -1; //
        //2)Instancia
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

            if (myTable[x][y].equals("2") && myTable[m][n].equals("2") && myTable[r][p].equals("0")){
                posiblePositionToMove[0] = r;
                posiblePositionToMove[1] = p;
            }else if (myTable[x][y].equals("2") && myTable[m][n].equals("0") && myTable[r][p].equals("2")){
                posiblePositionToMove[0] = m;
                posiblePositionToMove[1] = n;
            }else if (myTable[x][y].equals("0") && myTable[m][n].equals("2") && myTable[r][p].equals("2")){
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
                        if (myTable[oneGrupoAdy[i][0]][oneGrupoAdy[i][1]].equals("2")){
                            //Asignado inicio
                            inicio = whatNotationColumn(oneGrupoAdy[i][1])+ whatNotationRow(oneGrupoAdy[i][0]);
                            //Asignando destino
                            destino =whatNotationColumn(posiblePositionToMove[1])+whatNotationRow(posiblePositionToMove[0]);
                            System.out.println("Inicio:"+oneGrupoAdy[i][0]+" "+oneGrupoAdy[0][1]);
                            System.out.println("Destino:"+posiblePositionToMove[0]+" "+posiblePositionToMove[1]);
                            encontrado = true;
                            String[] response = {inicio,destino};
                            return response;
                        }
                    }
                    break;
                }
            }
            if (encontrado){
                break;
            }
        }

        //3) Tercera instancia
        //Si no hay la posibilidad de que la computadora mueva una pieza
        //para evitar un tres en raya del oponente humano
        //Entonces por ahora moverá a un lugar random disponible.
        if(inicio.equals("")&&destino.equals("")){

            for (int [][] oneGrupoAdy:Constants.getAdjALasPiezas()) { //Recorriendo la matriz de adyacencia

                // Verifica si hay un lugar disponible.
                if(myTable[oneGrupoAdy[0][0]][oneGrupoAdy[0][1]].equals("0")){
                    //Verificando si sus adyacentes contienen piezas de tipo "2". (Es decir piezas de la computadora).
                    for (int i = 1; i < oneGrupoAdy.length; i++) { //Recorriendo sus adyacentes
                        if (myTable[oneGrupoAdy[i][0]][oneGrupoAdy[i][1]].equals("2")){
                            //Asignado inicio
                            inicio = whatNotationColumn(oneGrupoAdy[i][1])+ whatNotationRow(oneGrupoAdy[i][0]);
                            //Asignando destino
                            destino =whatNotationColumn(oneGrupoAdy[0][1])+whatNotationRow(oneGrupoAdy[0][0]);
                            System.out.println("Inicio:"+oneGrupoAdy[i][0]+" "+oneGrupoAdy[0][1]);
                            System.out.println("Destino:"+oneGrupoAdy[0][0]+" "+oneGrupoAdy[0][1]);
                            String[] response = {inicio,destino};
                            return response;
                        }
                    }
                    break;
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

            if (myTable[x][y].equals("1") && myTable[m][n].equals("1") && myTable[r][p].equals("0")) {
                posiblePositionToInsert[0] = r;
                posiblePositionToInsert[1] = p;
                destinoInsert =whatNotationColumn(posiblePositionToInsert[1])+whatNotationRow(posiblePositionToInsert[0]);
                response1 = destinoInsert;
                return response1;
            } if (myTable[x][y].equals("1") && myTable[m][n].equals("0") && myTable[r][p].equals("1")) {
                posiblePositionToInsert[0] = m;
                posiblePositionToInsert[1] = n;
                destinoInsert =whatNotationColumn(posiblePositionToInsert[1])+whatNotationRow(posiblePositionToInsert[0]);
                response1 = destinoInsert;
                return response1;
            } if (myTable[x][y].equals("0") && myTable[m][n].equals("1") && myTable[r][p].equals("1")) {
                posiblePositionToInsert[0] = x;
                posiblePositionToInsert[1] = y;
                destinoInsert =whatNotationColumn(posiblePositionToInsert[1])+whatNotationRow(posiblePositionToInsert[0]);
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

            if (myTable[x][y].equals("1") && myTable[m][n].equals("1") && myTable[r][p].equals("0")) {
                posiblePositionToInsert[0] = x;
                posiblePositionToInsert[1] = y;
                destinoInsert = whatNotationColumn(posiblePositionToInsert[1]) + whatNotationRow(posiblePositionToInsert[0]);
                response1 = destinoInsert;
                return response1;
            }
            if (myTable[x][y].equals("1") && myTable[m][n].equals("0") && myTable[r][p].equals("1")) {
                posiblePositionToInsert[0] = r;
                posiblePositionToInsert[1] = p;
                destinoInsert = whatNotationColumn(posiblePositionToInsert[1]) + whatNotationRow(posiblePositionToInsert[0]);
                response1 = destinoInsert;
                return response1;
            }
            if (myTable[x][y].equals("0") && myTable[m][n].equals("1") && myTable[r][p].equals("1")) {
                posiblePositionToInsert[0] = m;
                posiblePositionToInsert[1] = n;
                destinoInsert = whatNotationColumn(posiblePositionToInsert[1]) + whatNotationRow(posiblePositionToInsert[0]);
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

            if (myTable[x][y].equals("2") && myTable[m][n].equals("2") && myTable[r][p].equals("0")) {
                posiblePositionToInsert2[0] = r;
                posiblePositionToInsert2[1] = p;
                destinoInsert2 =whatNotationColumn(posiblePositionToInsert2[1])+whatNotationRow(posiblePositionToInsert2[0]);
                response2 = destinoInsert2;
                return response2;
            } if (myTable[x][y].equals("2") && myTable[m][n].equals("0") && myTable[r][p].equals("2")) {
                posiblePositionToInsert2[0] = m;
                posiblePositionToInsert2[1] = n;
                destinoInsert2 =whatNotationColumn(posiblePositionToInsert2[1])+whatNotationRow(posiblePositionToInsert2[0]);
                response2 = destinoInsert2;
                return response2;
            } if (myTable[x][y].equals("0") && myTable[m][n].equals("2") && myTable[r][p].equals("2")) {
                posiblePositionToInsert2[0] = x;
                posiblePositionToInsert2[1] = y;
                destinoInsert2 =whatNotationColumn(posiblePositionToInsert2[1])+whatNotationRow(posiblePositionToInsert2[0]);
                response2 = destinoInsert2;
                return response2;
            }

        }

        return response2;

    }

    //====================================================================

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
