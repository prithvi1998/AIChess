package AI.chess.player;

import AI.chess.board.Board;
import AI.chess.board.Move;
import AI.chess.peice.Peice;

import java.util.Collection;

public class BlackPlayer extends Player{
    public BlackPlayer(Board board, Collection<Move> whiteStandardMoves, Collection<Move> blackStandardMoves) {

        super(board,blackStandardMoves,whiteStandardMoves);
    }

    @Override
    public Collection<Peice> getActivePeices() {
        return this.board.getBlackPeices();
    }

    @Override
    public int getColor() {
        return 1;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }
}
