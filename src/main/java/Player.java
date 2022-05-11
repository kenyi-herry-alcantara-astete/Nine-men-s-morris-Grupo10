public class Player extends User {

    public String type; //Left || Right
    public int numberPieces = 9;
    public String  turn;  //uno || dos
    public Player(String name, String type, String turn ){
        super(name);
        this.type = type;
        this.turn = turn;
    }

}
