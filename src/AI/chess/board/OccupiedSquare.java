package AI.chess.board;
import AI.chess.peice.Peice;

public final class OccupiedSquare extends Square{
    //Private variables
    private final Peice here;

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
    public String toString() {
        return (getPeice().color == 1) ?
                getPeice().toString().toLowerCase():getPeice().toString();
    }
}
