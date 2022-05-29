package sprint2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainFrameTest {
    @Test
    void movePieceByTheComputerTest(){

        MainFrame myFrame = new MainFrame();

        //Dando las condiciones previas
        myFrame.numberPiecesLeft = 0;
        myFrame.numberPiecesRight = 0;
        myFrame.isPlayerAComputer = true;
        myFrame.player2.turn = "uno";
        myFrame.player1.turn = "dos";

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
                String y =  myFrame.currentLogicGame.whatNotationRow(j);
                String x =  myFrame.currentLogicGame.whatNotationColumn(i);
                myFrame.currentLogicGame.insertPiece(x+y,Integer.toString(array[j][i]));
            }
        }

        //Ejecutemos y verifiquemos las casillas

        myFrame.movePieceByTheComputer();

        //Verificamos la posición (2,3) el cual es "d5" contienen "0"
        Assertions.assertEquals("0",myFrame.currentLogicGame.myTable[2][3]);

        //Verificamos la posición (1,3) el cual es "d6" contiene "2"

        Assertions.assertEquals("2",myFrame.currentLogicGame.myTable[1][3]);

    }
}