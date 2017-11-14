package AI.chess.player;

import AI.chess.board.Board;
import AI.chess.board.Move;
import AI.chess.peice.Peice;

import java.util.Collection;

public class WhitePlayer extends Player{
    public WhitePlayer(Board board, Collection<Move> whiteStandardMoves, Collection<Move> blackStandardMoves) {

        super(board,whiteStandardMoves,blackStandardMoves);
    }

    @Override
    public Collection<Peice> getActivePeices() {
        return this.board.getWhitePeices();
    }

    @Override
    public int getColor() {
        return -1;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }
}
