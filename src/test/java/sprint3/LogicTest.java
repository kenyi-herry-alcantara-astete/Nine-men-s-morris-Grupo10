package sprint3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LogicTest {


    @Test
    void insertAndRemovePieceTest(){
        Logic curretLogic = new Logic();

        //Al principio myTabla 7x7 está con "0"s
        //Insertemos un "1"(Jugador left) en la posición "d3"
        //Que en la tabla sería la posición (4,3)

        //Insertamos
        curretLogic.insertPiece("d3","1");

        //Verificamos
        Assertions.assertEquals("1",curretLogic.myTable[4][3]);

        //Insertamos
        curretLogic.removePiece("d3");

        //Verificamos
        Assertions.assertEquals("0",curretLogic.myTable[4][3]);

    }

    @Test
    public void getOptimalPositionToMoveTest(){
        Logic curretLogic = new Logic();
        int n = 7;

        //Sea un instante en el juego y tenemos esta configuración
        //Se observa que el oponente humano con fichas "1"
        //Puede conseguir un tres en raya, si baja el "1" de la posición.
        //(0,3) a la posición (1,3)
        //Pero es el turno de la computadora
        //Asi que ella tendrá que evitar ese tres en raya
        //Moviendo su pieza "2" de (2,3) a (1,3)
        int[][] array = {
                {0,0,0,1,0,0,0},
                {0,1,0,0,0,1,0},
                {0,0,0,2,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0}
        };

        //Agregando ese instante a la tabla
        for (int i = 0;i<n;i++){
            for (int j = 0;j<n;j++){
                String y =  curretLogic.whatNotationRow(j);
                String x =  curretLogic.whatNotationColumn(i);
                curretLogic.insertPiece(x+y,Integer.toString(array[j][i]));
            }
        }

        //Entonces si ejecutamos el método getOptimalPositionToMove
        //Lo que nos devolverá será un String[] con dos posiciones:
        //Inicio = (2,3) y llegada = (1,3) en sus notaciones de la tabla de juego
        //Es decir Inicio "d5", llegada "d6".

        Assertions.assertEquals("d5",curretLogic.getOptimalPositionToMove()[0]);
        Assertions.assertEquals("d6",curretLogic.getOptimalPositionToMove()[1]);


    }

    @Test
    public void movePieceTest(){
        Logic curretLogic = new Logic();

        //una vez efectuado el moverPieza el metodo movePiece liberara la pieza que se
        //mover a un estado de libre para que pueda se usada despues
        curretLogic.movePiece("a1");
        Assertions.assertEquals(true ,curretLogic.getIsAvailableContentPiece("a1") );
    }

    @Test
    public void validateMoveTest(){
        Logic curretLogic = new Logic();

        //Test para corroborar si una posición es adyacente a otra

        // a4 es adyacente de a1 entonces la función tiene que devolverte true
        Assertions.assertEquals(true,curretLogic.validateMove("a4","a1"));
        // a7 no es adyacente de a1 entonces la función tiene que devolverte false
        Assertions.assertEquals(false,curretLogic.validateMove("g7","a1"));

    }
}