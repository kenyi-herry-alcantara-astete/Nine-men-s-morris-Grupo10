package sprint3;

public class ComplementsMethods {

    //Recibe las notacions de la tabla GUI y retorna la position para la tabla logica.
    public static int whatIndexColumn(char notationColumn) {
        return Character.getNumericValue(notationColumn) - 10;
    }

    public static int whatIndexRow(char notationRow) {
        return 7-Character.getNumericValue(notationRow);
    }

    //Recibe la postion de la tabla logica y retorna las notaciones de la tabla de la GUI.
    public static String whatNotationColumn(int indexColumn) {
        return Constants.getLettersAndNumbersEquivalent(indexColumn);
    }

    public static String whatNotationRow(int indexRow) {
        return ""+(7-indexRow);
    }

}
