package AI.chess.peice;

import AI.chess.board.Board;
import AI.chess.board.BoardUtils;
import AI.chess.board.Move;

import java.util.ArrayList;
import java.util.List;

public class pawn extends Peice {

    private int[] legal = {8, 16, 7, 9};

    public pawn(int c, int pos) {
        super(PeiceType.PAWN,c, pos);
    }

    @Override
    public pawn movePeice(Move move) {
        return new pawn(move.getMpeice().color,move.getDestination());
    }

    @Override
    public String toString() {
        return PeiceType.PAWN.toString();
    }

    public List<Move> LegalMoves(Board board) {

        List<Move> legalMoves = new ArrayList<>();

        for (int i : legal) {

            int temp = this.peicePosition + (this.color * i);
            if (!BoardUtils.isvalid(temp))
                continue;
            if (i == 8 && board.getSquare(temp).isEmpty()) {
                legalMoves.add(new Move.NormalMove(board, this, temp));
            } else if (i == 16 && this.isSet() && (BoardUtils.SECOND_ROW[this.peicePosition] && (this.color == 1)
                    || BoardUtils.SEVENTH_ROW[this.peicePosition] && (this.color == -1))) {
                int behind = this.peicePosition + (this.color * 8);
                if (board.getSquare(behind).isEmpty() && board.getSquare(temp).isEmpty()) {
                    legalMoves.add(new Move.NormalMove(board, this, temp));
                }
            } else if (i == 7 &&
                    !((BoardUtils.EIGHTH_COLUMN[this.peicePosition] && (this.color == -1)) ||
                            (BoardUtils.FIRST_COLUMN[this.peicePosition] && (this.color == 1)))) {
                if (!board.getSquare(temp).isEmpty()) {
                    Peice p = board.getSquare(temp).getPeice();
                    if (this.color != p.color) {
                        legalMoves.add(new Move.NormalMove(board, this, temp));
                    }
                }

            } else if (i == 9 &&
                    !((BoardUtils.FIRST_COLUMN[this.peicePosition] && (this.color == -1)) ||
                            (BoardUtils.EIGHTH_COLUMN[this.peicePosition] && (this.color == 1)))) {
                if (!board.getSquare(temp).isEmpty()) {
                    Peice p = board.getSquare(temp).getPeice();
                    if (this.color != p.color) {
                        legalMoves.add(new Move.NormalMove(board, this, temp));
                    }
                }


            }
        }
            return legalMoves;
    }
}
