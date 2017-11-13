package AI.chess.board;

public final class EmptySquare extends Square{
    EmptySquare(final int x){
        super(x);
    }
    public boolean isEmpty(){
        return true;
    }
    public Piece getPiece(){
        return null;
    }
}
//empty git :)
