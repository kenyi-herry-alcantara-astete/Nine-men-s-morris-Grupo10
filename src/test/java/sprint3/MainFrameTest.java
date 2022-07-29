package sprint3;

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

    @Test
    void scoreThreeInARowTest() {

        MainFrame mf = new MainFrame();

        String player = "1";
        String [][] cases = {{"a7", "d7", "g7"},
                {"b6", "d6", "f6"},
                {"c5", "d5", "e5"},
                {"a4", "b4", "c4"},
                {"e4", "f4", "g4"},
                {"c3", "d3", "e3"},
                {"b2", "d2", "f2"},
                {"a1", "d1", "g1"},
                {"a7", "a4", "a1"},
                {"b6", "b4", "b2"},
                {"c5", "c4", "c3"},
                {"d7", "d6", "d5"},
                {"d3", "d2", "d1"},
                {"e5", "e4", "e3"},
                {"f6", "f4", "f2"},
                {"g7", "g4", "g1"}};

        for(String[] c: cases){
            // Verificar ningún tres en raya inicialmente
            assertFalse(mf.scoreThreeInARow(player));

            // Colocamos piezas
            mf.currentLogicGame.insertPiece(c[0], player);
            mf.currentLogicGame.insertPiece(c[1], player);
            mf.currentLogicGame.insertPiece(c[2], player);

            // Verificamos tres en raya
            assertTrue(mf.scoreThreeInARow(player));

            // Removemos piezas
            mf.currentLogicGame.removePiece(c[0]);
            mf.currentLogicGame.removePiece(c[1]);
            mf.currentLogicGame.removePiece(c[2]);

            // Verificamos tres en raya
            assertFalse(mf.scoreThreeInARow(player));
        }
    }

    @Test
    void restartTest() {

        MainFrame mf = new MainFrame();
        mf.player1.name = "player 1";
        mf.player2.name = "player 2";
        // Posiciones validas
        String[] rc = {"a7", "a4", "a1",
                "b6", "b4", "b2",
                "c5", "c4", "c3",
                "d7", "d6", "d5",
                "d3", "d2", "d1",
                "e5", "e4", "e3",
                "f6", "f4", "f2",
                "g7", "g4", "g1"};

        // reiniciamos juego
        mf.restart();

        // verificamos casillas vacias
        for(String e : rc){
            assertTrue(mf.currentLogicGame.getIsAvailableContentPiece(e));
        }

        // verificamos tablas sin piezas
        for(String e: rc){
            int r = mf.currentLogicGame.whatIndexRow(e.charAt(1));
            int c = mf.currentLogicGame.whatIndexColumn(e.charAt(0));
            assertEquals("0", mf.currentLogicGame.myTable[r][c]);
        }

        // verificamos número de piezas en la parte izquieda y derecha de la interfaz
        assertEquals(9, mf.numberPiecesLeft);
        assertEquals(9, mf.numberPiecesRight);

        // Verificamos numero de piezas de los jugadadores
        assertEquals(9, mf.player1.numberPieces);
        assertEquals(9, mf.player2.numberPieces);

        // verificamos tic tac toe
        assertFalse(mf.existTicTacToe);

        // Memoria de tres en raya vacia
        assertEquals(0, mf.currentLogicGame.MenoryTreEnRaya.size());
    }

    @Test
    void chooseTurnPlayer1Test(){
        MainFrame mf = new MainFrame();
        mf.player1.name = "player 1";
        mf.player2.name = "player 2";
        mf.player1.turn = "uno";
        mf.player2.turn = "dos";
        // escoger turno player 1
        mf.chooseTurn();
        assertEquals("uno", mf.player1.turn);
        assertEquals("dos", mf.player2.turn);
    }

    @Test
    void chooseTurnPlayer2Test(){
        MainFrame mf = new MainFrame();
        mf.player1.name = "player 1";
        mf.player2.name = "player 2";
        mf.player1.turn = "uno";
        mf.player2.turn = "dos";
        // escoger turno player 1
        mf.chooseTurn();
        assertEquals("uno", mf.player2.turn);
        assertEquals("dos", mf.player1.turn);
    }
}