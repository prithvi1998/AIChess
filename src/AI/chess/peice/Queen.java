package AI.chess.peice;

import AI.chess.board.Board;
import AI.chess.board.BoardUtils;
import AI.chess.board.Move;
import AI.chess.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Peice {

    private int[] legal = {-1,-8,1,8,9,7,-7,-9};

    Queen(int pos, int c) {
        super(pos, c);
    }

    public List<Move> LegalMoves(Board board) {

        List<Move> legalMoves = new ArrayList<>();

        for(int i : legal) {
            int temp = this.peicePosition;
            while(BoardUtils.isvalid(temp)){

                if(isFirstColumnExclusion(temp,i)||isEighthColumnExclusion(temp,i)) {
                    break;
                }
                temp = temp+i;
                if(BoardUtils.isvalid(temp)){
                    Square sq = board.getSquare(this.peicePosition + i);
                    if (sq.isEmpty())
                        legalMoves.add(new Move.NormalMove(board,this,temp));
                    else {
                        Peice x = sq.getPeice();
                        int col = x.color;
                        if (this.color != col)
                            legalMoves.add(new Move.AttackMove(board,this,temp,x));
                        break;
                    }

                }
            }
        }
        return legalMoves;
    }

    private static boolean isFirstColumnExclusion(final int pos, final int off) {
        return BoardUtils.FIRST_COLUMN[pos] && ((off == -9) || (off== 7)|| (off == -1));
    }
    private static boolean isEighthColumnExclusion(final int pos, final int off) {
        return BoardUtils.EIGHTH_COLUMN[pos] && ((off == -7) || (off== 9)|| (off == 1));
    }
}
