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

    Peice(final PeiceType peiceType,final int c,final int pos,final boolean flag){
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

    public int getPeiceValue(){
        return this.peiceType.getPeiceValue();
    }

    public abstract Peice movePeice(Move move);

    public boolean isSet(){
        return this.flag;
    }
    public abstract List<Move> LegalMoves(Board board);

    public enum PeiceType{

        PAWN(100,"P") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KNIGHT(300,"N") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        QUEEN(900,"Q") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KING(10000,"K") {
            @Override
            public boolean isKing() {
                return true;
            }
        },
        BISHOP(300,"B") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        ROOK(500,"R") {
            @Override
            public boolean isKing() {
                return false;
            }
        };

        private String peiceName;private int peiceValue;

        PeiceType(int peiceValue,String peiceName) {
            this.peiceName = peiceName;
            this.peiceValue = peiceValue;
        }

        @Override
        public String toString() {
            return this.peiceName;
        }

        public int getPeiceValue() {
            return peiceValue;
        }
        public abstract boolean isKing();


    }
}
