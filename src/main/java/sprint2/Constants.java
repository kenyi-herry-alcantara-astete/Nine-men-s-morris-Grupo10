package sprint2;

public class Constants {
    private static int[][][] CasesTresEnRaya = {
            //Filas
            {{0,0},{0,3},{0,6}},
            {{1,1},{1,3},{1,5}},
            {{2,2},{2,3},{2,4}},
            {{3,0},{3,1},{3,2}},
            {{3,4},{3,5},{3,6}},
            {{4,2},{4,3},{4,4}},
            {{5,1},{5,3},{5,5}},
            {{6,0},{6,3},{6,6}},
            //Columnas
            {{0,0},{3,0},{6,0}},
            {{1,1},{3,1},{5,1}},
            {{2,2},{3,2},{4,2}},
            {{0,3},{1,3},{2,3}},
            {{4,3},{5,3},{6,3}},
            {{2,4},{3,4},{4,4}},
            {{1,5},{3,5},{5,5}},
            {{0,6},{3,6},{6,6}}
    };

    //Adyacentes:
    //La primera position de cada grupo es la pieza en cuestión,
    //El resto sus adyacentes. (Es una Lista de adyacencia).
    private static int[][][] AdjALasPiezas = {
            {{0,0},{3,0},{0,3}},
            {{0,3},{0,0},{0,6},{1,3}},
            {{0,6},{0,3},{3,6}},
            {{1,1},{3,1},{1,3}},
            {{1,3},{0,3},{1,1},{2,3},{1,5}},
            {{1,5},{1,3},{3,5}},
            {{2,2},{3,2},{2,3}},
            {{2,3},{2,2},{1,3},{2,4}},
            {{2,4},{2,3},{3,4}},
            {{3,0},{0,0},{3,1},{6,0}},
            {{3,1},{3,0},{1,1},{3,2},{5,1}},
            {{3,2},{3,1},{2,2},{4,2}},
            {{3,4},{2,4},{3,5},{4,4}},
            {{3,5},{3,4},{1,5},{3,6},{5,5}},
            {{3,6},{3,5},{0,6},{6,6}},
            {{4,2},{3,2},{4,3}},
            {{4,3},{4,2},{5,3},{4,4}},
            {{4,4},{4,3},{3,4}},
            {{5,1},{3,1},{5,3}},
            {{5,3},{5,1},{4,3},{5,5},{6,3}},
            {{5,5},{5,3},{3,5}},
            {{6,0},{3,0},{6,3}},
            {{6,3},{6,0},{5,3},{6,6}},
            {{6,6},{6,3},{3,6}}
    };

    private static int [][] positionInvalid = {{1,0},
            {0,1}, {0,2}, {3,3}, {2,0}, {2,1}, {1,2}, {4,0},
            {4,1}, {5,2}, {5,0}, {6,1}, {6,2}, {0,4}, {0,5}, {1,6},
            {1,4}, {2,5}, {2,6}, {5,4}, {4,5}, {4,6}, {6,4}, {6,5}, {5,6}
    };

    private static String []   lettersAndNumbersEquivalent = {"a","b","c","d","e","f","g"};

    public static String getLettersAndNumbersEquivalent(int index) {
        return lettersAndNumbersEquivalent[index];
    }

    public static int[][][] getCasesTresEnRaya() {
        return CasesTresEnRaya;
    }

    public static int[][] getPositionInvalid() {
        return positionInvalid;
    }

    public static int[][][] getAdjALasPiezas() {
        return AdjALasPiezas;
    }
}
