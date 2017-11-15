package AI.chess.board;
import AI.chess.peice.Peice;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;


public abstract class  Square {

    protected final int squareNumber;

    //creating map for empty squares
    private static final Map<Integer,EmptySquare> EMPTY_SQUARES = createAllPossibleEmptySquares();

    private static Map<Integer,EmptySquare> createAllPossibleEmptySquares() {
        //creating a map and for 64 squares create empty entries in it
        final Map<Integer,EmptySquare> emptySquareMap = new HashMap<>();

        for(int i=0;i<64;i++)
        {
            emptySquareMap.put(i,new EmptySquare(i));
        }
        //still we need to make immutable copy
        return  ImmutableMap.copyOf(emptySquareMap);
    }


    public static Square createSquare(final int squareNumber, final Peice peice){
        if(peice!=null){
            return  new OccupiedSquare(squareNumber,peice);
        }
        else {
            return  EMPTY_SQUARES.get(squareNumber);
        }
    }
    Square(int x){
        this.squareNumber = x;
    }
    //Public methods
    public abstract boolean isEmpty();
    public abstract Peice getPeice();
    public int getSquareNumber(){
        return  squareNumber;
    }

}
