public class Logic {

    //Definir una matriz de 7x7 [1,2,..,7][a,b,c...,g]
    //Este nos dira la disponibilidad de los campos en el tiempo

    private String [][] myTable ={{" ","0","0"," ","0","0"," "},
                                  {"0"," ","0"," ","0"," ","0"},
                                  {"0","0"," "," "," ","0","0"},
                                  {" "," "," ","0"," "," "," "},
                                  {"0","0"," "," "," ","0","0"},
                                  {"0"," ","0"," ","0"," ","0"},
                                  {" ","0","0"," ","0","0"," "}};

    private int whatIndexColumn(char notationColumn){
        return  notationColumn - 60;
    }
    private  int whatIndexRow(char notationRow){
        int indexRow = notationRow;
        return (indexRow)%7;
    }
    public void insertPiece(String positionPiece){
        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));
        myTable[indexRow][indexColumn] = positionPiece + positionPiece.charAt(2);
    }

    public void removePiece(String positionPiece){
        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));
        myTable[indexRow][indexColumn] = " ";
    }

    public void movePiece(String positionPieceToRemove,String  newPositionPiece){
        //RemovePiece
        removePiece(positionPieceToRemove);
        //SetNewPiece
        insertPiece(newPositionPiece);
    }

    //Is available a content piece?
    public boolean isAvailableContentPiece(String positionPiece){
        int indexRow = whatIndexRow(positionPiece.charAt(1));
        int indexColumn = whatIndexColumn(positionPiece.charAt(0));
        if (myTable[indexRow][indexColumn] != " "){
            return true;
        }
        return false;
    }


//Definir una matriz de adyacencia del grafo
//Este nos dira los conectores por donde podemos movernos

int[][]   adjacencyMatrix = {{1,1,0,0,0,0,0,0,0,1, 0,0 ,0 ,0 ,0 ,0 , 0 , 0 ,0  ,0  ,0  ,0  ,0  ,0  },
        {1,1,1,0,1,0,0, 0, 0, 0,0  ,  0, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,0  , 0 },
        {0,1,1,0 , 0, 0, 0, 0, 0, 0, 0 , 0 ,  0,  0, 1, 0 , 0 , 0 ,  0,  0,  0,  0,  0, 0 },
        { 0, 0, 0,1,1,0 ,0 , 0,0 , 0, 1, 0 ,  0,  0,  0, 0 , 0 , 0 ,  0,  0,  0,  0,  0,  0},
        {0,1,0 ,1,1,1,0 ,1, 0, 0, 0 , 0 ,  0,  0,  0, 0 , 0 , 0 ,  0,  0,  0,  0,  0,  0},
        {0, 0, 0, 0,1,1, 0, 0, 0, 0, 0 , 0 , 0 , 1,  0, 0 , 0 , 0 ,  0,  0,  0,  0,  0,  0},
        { 0, 0, 0, 0,0 , 0,1,1, 0, 0, 0 , 1, 0 ,  0,  0, 0 , 0 , 0 ,  0,  0,  0,  0,  0,  0},
        { 0,0 ,0 ,0 ,1,0 ,1,1,1, 0,  0, 0 ,  0,  0,  0, 0 , 0 , 0 ,  0,  0,  0,  0,  0,  0},
        { 0,0 ,0 ,0 ,0 ,0 ,0 ,1,1, 0,  0, 0 , 1,  0,  0, 0 , 0 , 0 ,  0,  0,  0,  0,  0,  0},
        {1, 0, 0, 0, 0, 0, 0,0 , 0,1, 1,  0,  0,  0,  0, 0 , 0 , 0 ,0  , 0 , 0 , 1,  0, 0 },
        {0 ,0 , 0,1,0 , 0,0 , 0,0 ,1, 1, 1,  0,  0,0  , 0 , 0 ,  0, 1, 0 ,  0, 0 ,  0, 0 },
        {0 ,0 , 0, 0, 0, 0,1,0 ,0 , 0, 1, 1,  0,  0, 0 , 1, 0 , 0 , 0 , 0 ,0  , 0 ,  0, 0 },
        {0 ,0 , 0, 0, 0,0 , 0, 0,1, 0, 0 ,  0, 1, 1,  0, 0 ,  0, 1, 0 ,  0,  0, 0 ,  0, 0 },
        {0 ,0 , 0, 0, 0,1, 0, 0, 0, 0,  0,  0, 1, 1, 1,  0, 0 , 0 , 0 , 0 , 1,  0,  0, 0 },
        {0 ,0 ,1, 0, 0, 0,0 ,0 , 0, 0,  0,0  ,  0, 1, 1,  0, 0 , 0 ,  0, 0 , 0 ,0  , 0 , 1},
        {0 ,0 , 0,0 ,0 ,0 , 0, 0,0 , 0, 0 , 1, 0 , 0 , 0 , 1, 1,  0, 0 , 0 ,  0, 0 , 0 ,  0},
        {0 ,0 , 0,0 ,0 ,0 , 0, 0,0 , 0, 0 ,  0, 0 ,  0, 0 , 1, 1, 1,  0, 1, 0 ,  0,  0, 0 },
        {0 ,0 , 0,0 ,0 ,0 , 0, 0,0 , 0, 0 ,  0, 1,  0,  0,  0, 1, 1, 0 ,  0, 0 ,  0,  0, 0 },
        {0 ,0 , 0,0 ,0 ,0 , 0, 0,0 , 0, 1,  0, 0 ,  0,  0,  0,  0, 0 , 1, 1, 0 ,  0,  0, 0 },
        {0 ,0 , 0,0 ,0 ,0 , 0, 0,0 , 0, 0 ,  0, 0 , 0 , 0 , 0 , 1, 0 , 1, 1, 1, 0 , 1,0  },
        {0 ,0 , 0,0 ,0 ,0 , 0, 0,0 , 0, 0 ,  0, 0 , 1, 0 , 0 ,  0, 0 , 0 , 1, 1,  0, 0 ,0  },
        {0 ,0 , 0,0 ,0 ,0 , 0, 0,0 ,1, 0 ,  0, 0 ,  0,  0,  0,  0, 0 , 0 ,  0, 0 , 1, 1,0  },
        {0 ,0 , 0,0 ,0 ,0 , 0, 0,0 , 0, 0 ,  0, 0 , 0 , 0 , 0 , 0 ,0  ,0  , 1,  0, 1, 1, 1},
        {0 ,0 , 0,0 ,0 ,0 , 0, 0,0 , 0, 0 ,  0, 0 , 0 , 1, 0 ,  0, 0 , 0 , 0 ,  0, 0 , 1, 1}};


    //Finding the node
    public int findingTheNode(String positionPiece) {
        int node = -1;
        switch (positionPiece) {
            case "a7":
                node = 0;
                break;
            case "d7":
                node = 1;
                break;
            case "g7":
                node = 2;
                break;
            case "b6":
                node = 3;
                break;
            case "d6":
                node = 4;
                break;
            case "f6":
                node = 5;
                break;
            case "c5":
                node = 6;
                break;
            case "d5":
                node = 7;
                break;
            case "e5":
                node = 8;
                break;
            case "a4":
                node = 9;
                break;
            case "b4":
                node = 10;
            case "c4":
                node = 11;
                break;
            case "e4":
                node = 12;
                break;
            case "f4":
                node = 13;
                break;
            case "g4":
                node = 14;
                break;
            case "c3":
                node = 15;
                break;
            case "d3":
                node = 16;
                break;
            case "e3":
                node = 17;
                break;
            case "b2":
                node = 18;
                break;
            case "d2":
                node = 19;
                break;
            case "f2":
                node = 20;
                break;
            case "a1":
                node = 21;
                break;
            case "d1":
                node = 22;
                break;
            case "g1":
                node = 23;
                break;

        }
        return node;
    }

    //Finding the piece
    public int findingThePiece(int node) {
        String piece = "";
        switch (node) {
            case 0:
                piece = "a7";
                break;
            case 1:
                piece = "d7";
                break;
            case 2:
                piece = "g7";
                break;
            case 3:
                piece = "b6";
                break;
            case 4:
                piece = "d6";
                break;
            case 5:
                piece = "f6";
                break;
            case 6:
                piece = "c5";
                break;
            case 7:
                piece = "d5";
                break;
            case 8:
                piece = "e5";
                break;
            case 9:
                piece = "a4";
                break;
            case 10:
                piece = "b4";
            case 11:
                piece = "c4";
                break;
            case 12:
                piece = "e4";
                break;
            case 13:
                piece = "f4";
                break;
            case 14:
                piece = "g4";
                break;
            case 15:
                piece = "c3";
                break;
            case 16:
                piece = "d3";
                break;
            case 17:
                piece = "e3";
                break;
            case 18:
                piece = "b2";
                break;
            case 19:
                piece = "d2";
                break;
            case 20:
                piece = "f2";
                break;
            case 21:
                piece = "a1";
                break;
            case 22:
                piece = "d1";
                break;
            case 23:
                piece = "g1";
                break;

        }
        return node;
    }
    //Return the adjacent empty nodes
    public String adjacentNodes(String positionPiece){
        int myNode = findingTheNode(positionPiece.charAt(0)+positionPiece.charAt(1)+"");
        //Finding pieces adjacent
        String pieces = "";
        for (int j = 0; j<24;j++){
                if (adjacencyMatrix[myNode][j] == 1 && isAvailableContentPiece(positionPiece)){
                    pieces  = pieces + findingThePiece(j);
                }
            }
        return pieces;
    }


}
