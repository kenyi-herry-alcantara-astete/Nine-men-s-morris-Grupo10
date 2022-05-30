package sprint2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

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

        //Verificamos la posicion (2,3) el cual es "d5" continen "0"
        Assertions.assertEquals("2",myFrame.currentLogicGame.myTable[1][3]);

        //Verificamos la posición (1,3) el cual es "d6" contiene "2"

        Assertions.assertEquals("2",myFrame.currentLogicGame.myTable[1][3]);

    }

    @Test
    void movePieceToUITest(){

        MainFrame myFrame = new MainFrame();

        //Condiciones Previas
        myFrame.numberPiecesLeft = 0;
        myFrame.numberPiecesRight = 0;
        myFrame.player2.turn = "dos";
        myFrame.player1.turn = "uno";
        myFrame.numberMove = 0;
        myFrame.player1.numberPieces=9;
        ImageIcon IconMove = new ImageIcon("src/main/resources/Image/AvailableContent.png");
        myFrame.a1.setIcon(myFrame.IconWithPiece1) ;

       //Verifiquemos cuando se llama al metodo movePieceToUI este te debe cambiar
        // el numberMove de 0 a 1 para que se pueda efectuar el movimiento y cambiar
        // el color de la ficha seleccionada a blanco
        myFrame.movePieceToUI(myFrame.a1);
        Assertions.assertEquals(1,myFrame.numberMove);
        Assertions.assertEquals(myFrame.IconMove,myFrame.a1.getIcon());
        //Ahora corroboramos con el mismo metodo movePiece que la ficha se pueda mover
        //a un lugar adyasente
        myFrame.movePieceToUI(myFrame.a4);
        Assertions.assertEquals(1,myFrame.numberPiecesLeft);
        Assertions.assertEquals(0,myFrame.numberMove);


    }
    @Test
    void volarPieza(){

        MainFrame myFrame = new MainFrame();

        //Condiciones Previas
        myFrame.numberPiecesLeft = 1;
        myFrame.numberPiecesRight = 0;
        myFrame.player2.turn = "dos";
        myFrame.player1.turn = "uno";
        myFrame.numberMove = 0;
        myFrame.player1.numberPieces=2; // puede ser un número menor a 3 tambien
        myFrame.insertPieceToUI(myFrame.a1); ;
        myFrame.changeTurn();


        //Verifiquemos cuando se llama al metodo volarPieza este te debe cambiar
        // el numberMove de 0 a 1 para que se pueda efectuar el volarPieza y cambiar
        // el color de la ficha seleccionada a blanco
        myFrame.volarPieza(myFrame.a1);
        Assertions.assertEquals(1,myFrame.numberMove);
        Assertions.assertEquals(myFrame.IconMove,myFrame.a1.getIcon());
        //Ahora corroboramos con el mismo metodo volarPieza que la ficha se pueda mover
        //a cualquier parte disponible del tablero
        myFrame.volarPieza(myFrame.g7);
        Assertions.assertEquals(1,myFrame.numberPiecesLeft);
        Assertions.assertEquals(0,myFrame.numberMove);


    }

}