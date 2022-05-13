import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    @Test
    public void  isAvailableContentPieceTest(){
        Logic currentLogic = new Logic();

        /*1)Verificando si la casilla a7 esta disponible
        * El metodo debería retornará true (Si esta vacia)*/
        //test
        Assertions.assertEquals(true,currentLogic.isAvailableContentPiece("a7"));

        /*2) Insertamos una pieza al a7
        * El metodo debería retornar fales (No esta vacía)*/
        //Insertando el una pieza del segundo jugador
        currentLogic.insertPiece("a7","2");
        //test
        Assertions.assertEquals(false,currentLogic.isAvailableContentPiece("a7"));

        /*3) Quitando una pieza
        * El metodo debería retornará true (Si esta vacia)*/
        //Quitando la pieza insertada
        currentLogic.removePiece("a7");
        //test
        Assertions.assertEquals(true,currentLogic.isAvailableContentPiece("a7"));

    }

}