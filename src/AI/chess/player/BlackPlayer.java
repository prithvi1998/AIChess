package AI.chess.player;

import AI.chess.board.Board;
import AI.chess.board.Move;
import AI.chess.board.Square;
import AI.chess.peice.Peice;
import AI.chess.peice.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentLegals) {
        List<Move> kingCastles = new ArrayList<>();
        if(this.playerKing.flag && !this.isInCheck()){
            if(this.board.getSquare(5).isEmpty() && this.board.getSquare(6).isEmpty()){
                Square rookSquare = this.board.getSquare(7);
                if(!rookSquare.isEmpty() && rookSquare.getPeice().flag){
                    if(Player.calculateAttackOnSquare(5,opponentLegals).isEmpty()&&
                       Player.calculateAttackOnSquare(6,opponentLegals).isEmpty())
                        kingCastles.add(new Move.KCastleMove(this.board,this.playerKing,6,(Rook)rookSquare.getPeice(),rookSquare.getSquareNumber(),5));
                }
            }
            if(this.board.getSquare(1).isEmpty() && this.board.getSquare(2).isEmpty() && this.board.getSquare(3).isEmpty()){
                Square rookSquare = this.board.getSquare(0);
                if(!rookSquare.isEmpty() && rookSquare.getPeice().flag){
                    if(Player.calculateAttackOnSquare(1,opponentLegals).isEmpty()&&
                       Player.calculateAttackOnSquare(2,opponentLegals).isEmpty()&&
                       Player.calculateAttackOnSquare(3,opponentLegals).isEmpty())
                    kingCastles.add(new Move.QCastleMove(this.board,this.playerKing,2,(Rook)rookSquare.getPeice(),rookSquare.getSquareNumber(),3));
                }
            }
        }
        return kingCastles;
    }
}
