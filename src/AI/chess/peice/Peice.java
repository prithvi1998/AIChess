package AI.chess.peice;

import AI.chess.board.Board;
import AI.chess.board.Move;

import java.util.List;

public abstract class Peice {

    protected PeiceType peiceType;
    public int peicePosition;
    public int color;   //-1 for white ,1 for black
    protected boolean flag ;

    Peice(PeiceType peiceType,int c,int pos){
        peicePosition = pos;
        color = c;
        flag = false;
        this.peiceType = peiceType;
    }

    public PeiceType getPeiceType() {
        return this.peiceType;
    }
    public abstract Peice movePeice(Move move);

    public boolean isSet(){
        return this.flag;
    }
    public abstract List<Move> LegalMoves(Board board);

    public enum PeiceType{

        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }
        };

        private String peiceName;
        PeiceType(String peiceName){
            this.peiceName = peiceName;
        }

        @Override
        public String toString() {
            return this.peiceName;
        }

        public abstract boolean isKing();
    }
}
