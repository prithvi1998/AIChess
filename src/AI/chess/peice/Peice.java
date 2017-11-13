package AI.chess.peice;

import AI.chess.board.Board;
import AI.chess.board.Move;

import java.util.List;

public abstract class Peice {

    protected int peicePosition;
    protected int color;   //-1 for white ,1 for black
    protected boolean flag ;

    Peice(int pos,int c){
        peicePosition = pos;
        color = c;
        flag = false;
    }

    public boolean isSet(){
        return this.flag;
    }
    public abstract List<Move> LegalMoves(Board board);
}
