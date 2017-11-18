package AI.chess.peice;

import AI.chess.board.Board;
import AI.chess.board.BoardUtils;
import AI.chess.board.Move;
import AI.chess.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Peice {

    private int[] legal = {-1,-8,1,8};

    public Rook(final int c,final int pos) {
        super(PeiceType.ROOK,c, pos,true);
    }
    public Rook(final int c,final int pos,final boolean flag){
        super(PeiceType.ROOK,c, pos,flag);
    }

    @Override
    public Rook movePeice(Move move) {
        return new Rook(move.getMpeice().color,move.getDestination());
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

    @Override
    public String toString() {
        return PeiceType.ROOK.toString();
    }

    private static boolean isFirstColumnExclusion(final int pos, final int off) {
        return BoardUtils.FIRST_COLUMN[pos] && ((off == -1));
    }
    private static boolean isEighthColumnExclusion(final int pos, final int off) {
        return BoardUtils.EIGHTH_COLUMN[pos] && ((off == 1));
    }
}
