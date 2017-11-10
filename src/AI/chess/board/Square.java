package AI.chess.board;
import AI.chess.peice.Peice;

import java.util.HashMap;
import java.util.Map;


public abstract class  Square {

    //Private variables
    protected final int squareNumber;

    //creating map for empty squares
    private static final Map<Integer,EmptySquare> EMPTY_SQUARES = createAllPossibleEmptySquares();

    private static Map<Integer,EmptySquare> createAllPossibleEmptySquares() {
        //creating a map and for 64 sqauares create empty entries in it
        final Map<Integer,EmptySquare> emptySquareMap = new HashMap<>();

        for(int i=0;i<64;i++)
        {
            emptySquareMap.put(i,new EmptySquare(i));
        }
        //still we need to make immutable copy
        //
        //download Guvava library from google and add into project
        //then just import it
        //
        return  ImmutableMap.copyOf(emptySquareMap);
    }


    //factory method that can only create square
    public static Square createSquare(final int squareNumber, final Peice peice){
        if(peice!=null){
            return  new OccupiedSquare(squareNumber,peice);
        }
        else {
            return  EMPTY_SQUARES.get(squareNumber);
        }
    }
    //making construcor private so that only square can be created using factory method
    Square(int x){
        this.squareNumber = x;
    }
    //Public methods
    public abstract boolean isEmpty();
    public abstract Peice getPeice();

}
