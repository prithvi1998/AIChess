package AI.chess.peice;

import AI.chess.board.Board;
import AI.chess.board.BoardUtils;
import AI.chess.board.Move;
import AI.chess.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Peice {

    private  int[] legal = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(int c, int pos) {
        super(c, pos);
    }

    public List<Move> LegalMoves(Board board) {

        List<Move> legalMoves = new ArrayList<>();

        for (int i : legal) {
            int temp = this.peicePosition + i;
            if (BoardUtils.isvalid(temp)) {

                if(isFirstColumnExclusion(this.peicePosition,i)||isSecondColumnExclusion(this.peicePosition,i)
                        ||isSeventhColumnExclusion(this.peicePosition,i)||isEighthColumnExclusion(this.peicePosition,i))
                {
                    continue;
                }

                Square sq = board.getSquare(this.peicePosition + i);
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
        return PeiceType.KNIGHT.toString();
    }
    private static boolean isFirstColumnExclusion(final int pos, final int off) {
        return BoardUtils.FIRST_COLUMN[pos] && ((off == -17) ||
                (off== -10) || (off== 6) || (off == 15));
    }

    private static boolean isSecondColumnExclusion(final int pos, final int off) {
        return BoardUtils.SECOND_COLUMN[pos] && ((off == -10) || (off == 6));
    }

    private static boolean isSeventhColumnExclusion(final int pos, final int off) {
        return BoardUtils.SEVENTH_COLUMN[pos] && ((off == -6) || (off == 10));
    }

    private static boolean isEighthColumnExclusion(final int pos, final int off) {
        return BoardUtils.EIGHTH_COLUMN[pos] && ((off == -15) || (off == -6) ||
                (off == 10) || (off == 17));

    }
}