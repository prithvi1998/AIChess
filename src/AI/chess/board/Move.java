package AI.chess.board;

import AI.chess.peice.Peice;

public abstract class Move {

    Board board;
    Peice mpeice;
    private int destination;

    Move(Board x,Peice y,int z){
        this.board =x;
        this.mpeice =y;
        this.destination =z;
    }

    public int getDestination() {
        return this.destination;
    }

    public Peice getMpeice() {
        return mpeice;
    }

    public abstract Board execute();

    public static class NormalMove extends Move{

        public NormalMove(Board x, Peice y, int z) {
            super(x, y, z);
        }

        @Override
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
    }
}
