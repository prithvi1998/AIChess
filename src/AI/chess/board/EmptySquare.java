package AI.chess.board;
import AI.chess.peice.Peice;

public class EmptySquare extends Square{
    EmptySquare(int x){
        super(x);
    }
    public boolean isEmpty(){
        return true;
    }
    public Peice getPeice(){
        return null;
    }
}
//empty git :)
