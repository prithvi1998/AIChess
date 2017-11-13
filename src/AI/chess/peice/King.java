package AI.chess.peice;

import AI.chess.board.Board;
import AI.chess.board.BoardUtils;
import AI.chess.board.Move;
import AI.chess.board.Square;

import java.util.ArrayList;
import java.util.List;

public class King extends Peice{

    private int[] legal = {1,7,8,9,-1,-7,-8,-9};

    public King(int c, int pos) {
        super(c, pos);
    }

    public List<Move> LegalMoves(Board board) {

        List<Move> legalMoves = new ArrayList<>();

        for(int i : legal){
            int temp = this.peicePosition + i;

            if(isFirstColumnExclusion(this.peicePosition,i) ||isEighthColumnExclusion(this.peicePosition,i))
            {
                continue;
            }

            if(BoardUtils.isvalid(temp)){
                Square sq = board.getSquare(temp);
                if (sq.isEmpty())
                    legalMoves.add(new Move.NormalMove(board,this,temp));
                else {
                    Peice x = sq.getPeice();
                    int col = x.color;
                    if (this.color != col)
                        legalMoves.add(new Move.AttackMove(board,this,temp,x));
                }
            }
        }
        return legalMoves;
    }

    @Override
    public String toString() {
        return PeiceType.KING.toString();
    }

    private static boolean isFirstColumnExclusion(final int pos, final int off) {
        return BoardUtils.FIRST_COLUMN[pos] && ((off == -9) ||
                (off== -1) || (off== 7));
    }

    private static boolean isEighthColumnExclusion(final int pos, final int off) {
        return BoardUtils.EIGHTH_COLUMN[pos] && ((off == -7) || (off == 1)|| (off == 9));
    }
}
