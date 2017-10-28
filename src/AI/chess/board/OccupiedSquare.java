package AI.chess.board;
import AI.chess.peice.Peice;

public class OccupiedSquare extends Square{
    //Private variables
    private Peice here;

    //Public methods
    OccupiedSquare(int x,Peice y){
        super(x);
        this.here = y;
    }
    public boolean isEmpty(){
        return false;
    }
    public Peice getPeice(){
        return here;
    }
}
