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

        //Remover
        curretLogic.removePiece("d3");

        //Verificamos
        Assertions.assertEquals("0",curretLogic.myTable[4][3]);

    }



    @Test
    public void getOptimalPositionToMoveTest(){
        Logic curretLogic = new Logic();
        int n = 7;

        //1)Primera instancia
        //Sea un instante en el juego y tenemos esta configuración
        //Se observa que el oponente computador con fichas "2"
        //Puede conseguir un tres en raya, si sube el "2" de la posición.
        //(3,5) a la posición (1,5)
        //Entonces cuando es el turno del computador
        //tendrá que mover de (3,5) f4 a la posición (1,5) f6
        int[][] array = {
                {0,0,0,0,0,0,0},
                {0,2,0,2,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,2,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {1,0,0,1,0,0,0}
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
        //Inicio (3,5) y llegada (1,5) en sus notaciones de la tabla de juego
        //Es decir Inicio "f4", llegada "f6".

        Assertions.assertEquals("f4",curretLogic.getOptimalPositionToMove()[0]);
        Assertions.assertEquals("f6",curretLogic.getOptimalPositionToMove()[1]);



        //2)Segunda instancia
        //Sea un instante en el juego y tenemos esta configuración
        //Se observa que el oponente humano con fichas "1"
        //Puede conseguir un tres en raya, si baja el "1" de la posición.
        //(0,3) a la posición (1,3)
        //Pero es el turno de la computadora
        //Asi que ella tendrá que evitar ese tres en raya
        //Moviendo su pieza "2" de (2,3) a (1,3)

        int[][] array2 = {
                {0,0,0,1,0,0,0},
                {0,1,0,0,0,1,0},
                {0,0,0,2,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0}
        };

        //Estableciendo aceros nuevamente
        curretLogic.fillMyTable();

        //Agregando ese instante a la tabla
        for (int i = 0;i<n;i++){
            for (int j = 0;j<n;j++){
                String y =  curretLogic.whatNotationRow(j);
                String x =  curretLogic.whatNotationColumn(i);
                curretLogic.insertPiece(x+y,Integer.toString(array2[j][i]));
            }
        }

        //Entonces si ejecutamos el método getOptimalPositionToMove
        //Lo que nos devolverá será un String[] con dos posiciones:
        //Inicio = (2,3) y llegada = (1,3) en sus notaciones de la tabla de juego
        //Es decir Inicio "d5", llegada "d6".

        Assertions.assertEquals("d5",curretLogic.getOptimalPositionToMove()[0]);
        Assertions.assertEquals("d6",curretLogic.getOptimalPositionToMove()[1]);

        //La tercera instancia mueve a un lugar random.
        //No es relevante a este nivel hacer su test.
        //En los próximos sprints se hará el test complementado con la dificultad.


    }
}