package AI.chess.player;
import AI.chess.board.Board;
import AI.chess.board.Move;

public class MoveTransition {
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveTransition(final Board transtionBoard,
                          final Move move,
                          final MoveStatus moveStatus){
        this.transitionBoard = transtionBoard;
        this.move= move;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus(){
        return this.moveStatus;
    }

    public Board getTransitionBoard(){
        return this.transitionBoard;
    }


}
