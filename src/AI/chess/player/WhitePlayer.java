package AI.chess.player;

import AI.chess.board.Board;
import AI.chess.board.Move;
import AI.chess.board.Square;
import AI.chess.peice.Peice;
import AI.chess.peice.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentLegals) {
        List<Move> kingCastles = new ArrayList<>();
        if(this.playerKing.flag && !this.isInCheck()){
            if(this.board.getSquare(61).isEmpty() && this.board.getSquare(61).isEmpty()){
                Square rookSquare = this.board.getSquare(63);
                if(!rookSquare.isEmpty() && rookSquare.getPeice().flag){
                    if(Player.calculateAttackOnSquare(61,opponentLegals).isEmpty()&&
                       Player.calculateAttackOnSquare(62,opponentLegals).isEmpty())
                    kingCastles.add(new Move.KCastleMove(this.board,this.playerKing,62,(Rook)rookSquare.getPeice(),rookSquare.getSquareNumber(),61));
                }
            }
            if(this.board.getSquare(59).isEmpty() && this.board.getSquare(58).isEmpty() && this.board.getSquare(57).isEmpty()){
                Square rookSquare = this.board.getSquare(56);
                if(!rookSquare.isEmpty() && rookSquare.getPeice().flag){
                    if(Player.calculateAttackOnSquare(59,opponentLegals).isEmpty()&&
                            Player.calculateAttackOnSquare(58,opponentLegals).isEmpty()&&
                            Player.calculateAttackOnSquare(57,opponentLegals).isEmpty())
                    kingCastles.add(new Move.QCastleMove(this.board,this.playerKing,58,(Rook)rookSquare.getPeice(),rookSquare.getSquareNumber(),59));
                }
            }
        }
        return kingCastles;
    }
}
