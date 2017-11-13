package AI.chess.board;

import AI.chess.peice.Peice;

public class Move {

    private Board board;
    private Peice mpeice;
    private int destination;

    Move(Board x,Peice y,int z){
        this.board =x;
        this.mpeice =y;
        this.destination =z;
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
    }
}
