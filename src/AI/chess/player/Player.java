package AI.chess.player;

import AI.chess.board.Board;
import AI.chess.board.Move;
import AI.chess.peice.King;
import AI.chess.peice.Peice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {

    protected Board board;
    protected King playerKing;
    protected Collection<Move> legalMoves;
    private boolean isInCheck;

    Player(Board board,Collection<Move> legalMoves,Collection<Move> opponentMoves){
        this.board = board;
        this.playerKing = findKing();
        this.legalMoves = legalMoves;
        this.isInCheck = !Player.calculateAttackOnSquare(this.playerKing.peicePosition,opponentMoves).isEmpty();
    }

    private static Collection<Move> calculateAttackOnSquare(int peicePosition, Collection<Move> opponentMoves) {
        List<Move> AttackMoves = new ArrayList<>();
        for(Move m : opponentMoves){
            if(peicePosition == m.getDestination()){
                AttackMoves.add(m);
            }
        }
        return AttackMoves;
    }

    private King findKing() {
        for(Peice peice:getActivePeices()){
            if(peice.getPeiceType().isKing()){
                return(King)peice;
            }
        }
        throw new RuntimeException("NO KING???");
    }
    public abstract Collection<Peice> getActivePeices();
    public abstract int getColor();
    public abstract Player getOpponent();

    public boolean isMoveLegal(Move move){
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck(){
        return this.isInCheck;
    }
    public boolean isInCheckmate(){
        return this.isInCheck && !haveEscape();
    }

    public King getKing() {
        return playerKing;
    }

    protected boolean haveEscape() {
        for(Move m :  legalMoves){
            MoveTransition transition = makeMove(m);
            if(transition.getMoveStatus().isDone()){
                return true;
            }
        }
        return false;
    }

    public boolean isInStalemate(){
        return !this.isInCheck && !haveEscape();
    }
    public boolean isCastled(){
        return false;
    }
    public MoveTransition makeMove(Move move){
        if(!isMoveLegal(move))
            return new MoveTransition(this.board,move,MoveStatus.ILLEGAL_MOVE);
        Board b = move.execute();
        Collection<Move> kingAttacks = Player.calculateAttackOnSquare(b.currentPlayer().getOpponent().getKing().peicePosition,b.currentPlayer().legalMoves);
        if(!kingAttacks.isEmpty()){
            return new MoveTransition(this.board,move,MoveStatus.LEAVES_PLAYER_IN_CHECK);
        }
        return new MoveTransition(b,move,MoveStatus.DONE);
    }
}
