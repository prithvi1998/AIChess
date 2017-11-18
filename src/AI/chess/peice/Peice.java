package AI.chess.peice;

import AI.chess.board.Board;
import AI.chess.board.Move;

import java.util.List;

public abstract class Peice {

    protected PeiceType peiceType;
    public int peicePosition;
    public int color;   //-1 for white ,1 for black
    public  boolean flag ;
    private int hash;

    Peice(PeiceType peiceType,int c,int pos,boolean flag){
        peicePosition = pos;
        color = c;
        this.flag = flag;
        this.peiceType = peiceType;
        this.hash = computeHash();
    }

    private int computeHash() {
        int r =peiceType.hashCode();
        r = 31*r+color;
        r = 31*r+peicePosition;
        r = 31*r+ (flag?1:0);
        return r;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!(o instanceof Peice))
            return false;
        Peice otherPeice = (Peice)o;
        return peicePosition == otherPeice.peicePosition && color == otherPeice.color && peiceType == otherPeice.peiceType && flag == otherPeice.flag;
    }

    @Override
    public int hashCode() {
        return this.hash;
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
