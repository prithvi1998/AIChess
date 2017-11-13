package AI.chess.board;

public final class OccupiedSquare extends Square{
    //Private variables
    private final Piece here;

    //Public methods
    OccupiedSquare(int x,Piece y){
        super(x);
        this.here = y;
    }
    public boolean isEmpty(){
        return false;
    }
    public Piece getPiece(){
        return here;
    }
}
