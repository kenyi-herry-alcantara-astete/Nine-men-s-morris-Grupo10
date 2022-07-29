package sprint3;

interface Buscar{
    void myMetodo();
}

class Buscar1 implements Buscar{

    String[] myMetodo(int x,,int i,int[][] myTable, int[][] oneGrupoAdy, String inicio,String destino,int[] posiblePositionToMove){
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



public class OptimalPosition {
    public  OptimalPosition(){

    }
    public void Ejecute(){
        for (int [][] oneGrupoAdy:Constants.getAdjALasPiezas()) { //Recorriendo la matriz de adyacencia
            // para cada contenedor de piezas.
            if(oneGrupoAdy[0][0] == posiblePositionToMove[0] && oneGrupoAdy[0][1] == posiblePositionToMove[1]){
                //Verificando si sus adyacentes contienen piezas de tipo "2". (Es decir piezas de la computadora).
                for (int i = 1; i < oneGrupoAdy.length; i++) { //Recorriendo sus adyacentes


                }
            }
        }
    }
}
