package AI.chess.board;
import AI.chess.peice.Peice;

public abstract class  Square {

    //Private variables
    private int squareNumber;

    //Public methods
    Square(int x){
        this.squareNumber = x;
    }
    public abstract boolean isEmpty();
    public abstract Peice getPeice();

}
