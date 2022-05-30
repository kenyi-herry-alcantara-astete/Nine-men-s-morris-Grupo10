package sprint2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void isAvailableContentPieceTest() {
        Logic currentLogic = new Logic();

        /*1)Verificando si la casilla a7 esta disponible
         * El metodo debería retornará true (Si esta vacia)*/
        //test
        Assertions.assertEquals(true, currentLogic.getIsAvailableContentPiece("a7"));
        /*2) Insertamos una pieza al a7
         * El metodo debería retornar fales (No esta vacía)*/
        //Insertando el una pieza del segundo jugador
        currentLogic.insertPiece("a7", "2");
        //test
        Assertions.assertEquals(false, currentLogic.getIsAvailableContentPiece("a7"));

        /*3) Quitando una pieza
         * El metodo debería retornará true (Si esta vacia)*/
        //Quitando la pieza insertada
        currentLogic.removePiece("a7");
        //test
        Assertions.assertEquals(true, currentLogic.getIsAvailableContentPiece("a7"));

    }


    @Test
    void getIsAvailabletPieceTest() {
        // Posiciones no ocupadas
        String[] rc = {"a7", "a4", "a1",
                "b6", "b4", "b2",
                "c5", "c4", "c3",
                "d7", "d6", "d5",
                "d3", "d2", "d1",
                "e5", "e4", "e3",
                "f6", "f4", "f2",
                "g7", "g4", "g1"};
        String player = "1";

        // Verificamos posiciones vacias
        Logic currentLogic = new Logic();
        for (String e : rc) {
            assertTrue(currentLogic.getIsAvailableContentPiece(e));
        }
        // Colocamos piezas
        for (String e : rc) {
            currentLogic.insertPiece(e, player);
        }
        // Verificamos posiciones ocupadas (no vacias)
        for (String e : rc) {
            assertFalse(currentLogic.getIsAvailableContentPiece(e));
        }
    }
}