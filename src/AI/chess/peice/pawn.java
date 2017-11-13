package AI.chess.peice;

import AI.chess.board.Board;
import AI.chess.board.BoardUtils;
import AI.chess.board.Move;

import java.util.ArrayList;
import java.util.List;

public class pawn extends Peice {

    private int[] legal = {8,16};

    pawn(int pos, int c) {
        super(pos, c);
    }

    public List<Move> LegalMoves(Board board) {

        List<Move> legalMoves = new ArrayList<>();

        for(int i : legal){
            int temp = this.peicePosition + (this.color*i);
            if(!BoardUtils.isvalid(temp))
                continue;
            if(i ==8 && board.getSqare(temp).isEmpty()) {
                legalMoves.add(new Move.NormalMove(board,this,temp));
            }
            else if(i==16 && this.isSet() && (BoardUtils.SECOND_ROW[this.peicePosition] && (this.color == 1)
                    ||BoardUtils.SEVENTH_ROW[this.peicePosition] && (this.color == -1)))
            {
                    int behind = this.peicePosition + (this.color * 8);
                    if(board.getSquare(behind).isEmpty() && board.getSquare(temp).isEmpty()){

                    }
            }
        }
        return legalMoves;
    }
}
