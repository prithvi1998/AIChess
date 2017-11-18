package AI.chess.board;

import AI.chess.peice.Peice;
import AI.chess.peice.Rook;
import AI.chess.peice.pawn;

public abstract class Move {

    Board board;
    Peice mpeice;
    private int destination;

    public static Move NULL_MOVE = new NullMove();

    Move(Board x,Peice y,int z){
        this.board =x;
        this.mpeice =y;
        this.destination =z;
    }

    Move(Board x,int z){
        this.board =x;
        this.mpeice =null;
        this.destination =z;
    }

    @Override
    public int hashCode() {
        int r =1;
        r = 31*r + this.destination;
        r = 31*r + this.mpeice.hashCode();

        return r;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!(o instanceof Move))
            return false;
        Move otherMove = (Move)o;
        return this.destination == otherMove.destination && getMpeice().equals(otherMove.getMpeice());
    }

    public int getDestination() {
        return this.destination;
    }

    public Peice getMpeice() {
        return mpeice;
    }

    public boolean isAttack(){
        return false;
    }

    public boolean isCastlingMove(){
        return false;
    }

    public Peice getAttackedPeice(){
        return null;
    }

    public Board execute() {

        Board.Builder builder = new Board.Builder();
        for(Peice p : this.board.currentPlayer().getActivePeices()){
            if(!this.mpeice.equals(p)){
                builder.setpeice(p);
            }
        }
        for(Peice p : this.board.currentPlayer().getOpponent().getActivePeices()){
            builder.setpeice(p);
        }
        builder.setpeice(this.mpeice.movePeice(this));
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getColor());
        return builder.build();
    }

    public static class NormalMove extends Move{

        public NormalMove(Board x, Peice y, int z) {
            super(x, y, z);
        }

    }

    public static class AttackMove extends Move{
        Peice attackedpeice;
        public AttackMove(Board x, Peice y, int z, Peice att ) {
            super(x, y, z);
            this.attackedpeice = att;
        }

        @Override
        public Board execute() {
            return null;
        }

        public boolean isAttack(){
            return true;
        }

        public Peice getAttackedPeice() {
            return this.attackedpeice;
        }

        @Override
        public int hashCode() {
            return this.attackedpeice.hashCode()+super.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if(this == o)
                return true;
            if(!(o instanceof AttackMove))
                return false;
            AttackMove otherMove = (AttackMove)o;
            return super.equals(otherMove) && getAttackedPeice().equals(otherMove.getAttackedPeice());
        }
    }

    public static class PawnMove extends Move{

        public PawnMove(Board x, Peice y, int z) {
            super(x, y, z);
        }

    }

    public static class PawnAttackMove extends AttackMove{

        public PawnAttackMove(Board x, Peice y, int z ,Peice att) {
            super(x, y, z ,att);
        }

    }

    public static class PawnEnpassantAttackMove extends PawnAttackMove{

        public PawnEnpassantAttackMove(Board x, Peice y, int z ,Peice att) {
            super(x, y, z ,att);
        }

    }

    public static class PawnJump extends Move{

        public PawnJump(Board x, Peice y, int z) {
            super(x, y, z);
        }

        @Override
        public Board execute() {
            Board.Builder builder = new Board.Builder();
            for(Peice peice : this.board.currentPlayer().getActivePeices()){
                if(!this.mpeice.equals(peice)){
                    builder.setpeice(peice);
                }
            }
            for(Peice peice : this.board.currentPlayer().getOpponent().getActivePeices()){
                builder.setpeice(peice);
            }
            pawn movedPawn = (pawn)this.mpeice.movePeice(this);
            builder.setpeice(movedPawn);
            builder.setEnpassantPawn(movedPawn);
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getColor());
            return builder.build();
        }
    }

     static abstract class CastleMove extends Move{

        protected Rook castleRook;
        protected int castleRookStart;
        protected int castleRookDestination;

        public CastleMove(Board x, Peice y, int z , Rook u , int v,int w) {
            super(x, y, z);
            this.castleRook = u;
            this.castleRookStart = v;
            this.castleRookDestination =w;
        }

         @Override
         public boolean isCastlingMove() {
             return true;
         }

         @Override
         public Board execute() {
             Board.Builder builder = new Board.Builder();
             for(Peice p : this.board.currentPlayer().getActivePeices()){
                 if(!this.mpeice.equals(p) && !this.castleRook.equals(p)){
                     builder.setpeice(p);
                 }
             }
             for(Peice p : this.board.currentPlayer().getOpponent().getActivePeices()){
                 builder.setpeice(p);
             }
             builder.setpeice(this.mpeice.movePeice(this));
             builder.setpeice(new Rook(this.castleRook.color,this.castleRookDestination));
             builder.setMoveMaker(this.board.currentPlayer().getOpponent().getColor());
             return builder.build();
         }
     }

    public static class KCastleMove extends CastleMove{

        public KCastleMove(Board x, Peice y, int z, Rook u , int v,int w) {
            super(x, y, z,u,v,w);
        }

        @Override
        public String toString() {
            return "O-O";
        }
    }

    public static class QCastleMove extends CastleMove{

        public QCastleMove(Board x, Peice y, int z, Rook u , int v,int w) {
            super(x, y, z,u,v,w);
        }

        @Override
        public String toString() {
            return "O-O-O";
        }

    }

    public static class NullMove extends Move{

        NullMove() {
            super(null,-1);
        }

        @Override
        public Board execute() {
            throw new RuntimeException("Null move Execution");
        }
        @Override
        public int getCurrentCoordinate(){
            return -1;
        }
    }

    public static class MoveFactory{
        private MoveFactory(){
            throw new RuntimeException("Not instantiable");
        }
        public static Move createMove(Board b,int c,int d){
            for(Move move: b.getAllLegalMoves()){
                if(move.currentCoordinate() ==c && move.destination == d)
                    return move;
            }
            return NULL_MOVE;
        }
    }

    private int currentCoordinate() {
        return this.getMpeice().peicePosition;
    }

}
