package AI.chess.board;

public class BoardUtils {
    public static final boolean[] SEVENTH_COLUMN = {
            false,false,false,false,false,false,true,false,
            false,false,false,false,false,false,true,false,
            false,false,false,false,false,false,true,false,
            false,false,false,false,false,false,true,false,
            false,false,false,false,false,false,true,false,
            false,false,false,false,false,false,true,false,
            false,false,false,false,false,false,true,false,
            false,false,false,false,false,false,true,false
    };
    public static final boolean[] EIGHTH_COLUMN = {
            false,false,false,false,false,false,false,true,
            false,false,false,false,false,false,false,true,
            false,false,false,false,false,false,false,true,
            false,false,false,false,false,false,false,true,
            false,false,false,false,false,false,false,true,
            false,false,false,false,false,false,false,true,
            false,false,false,false,false,false,false,true,
            false,false,false,false,false,false,false,true
    };
    public static final boolean[] SECOND_COLUMN = {
            false,true,false,false,false,false,false,false,
            false,true,false,false,false,false,false,false,
            false,true,false,false,false,false,false,false,
            false,true,false,false,false,false,false,false,
            false,true,false,false,false,false,false,false,
            false,true,false,false,false,false,false,false,
            false,true,false,false,false,false,false,false,
            false,true,false,false,false,false,false,false
    };
    public static final boolean[] FIRST_COLUMN = {
            true,false,false,false,false,false,false,false,
            true,false,false,false,false,false,false,false,
            true,false,false,false,false,false,false,false,
            true,false,false,false,false,false,false,false,
            true,false,false,false,false,false,false,false,
            true,false,false,false,false,false,false,false,
            true,false,false,false,false,false,false,false,
            true,false,false,false,false,false,false,false
    };
    public static final boolean[] SECOND_ROW = null;
    public static final boolean[] SEVENTH_ROW = null;


    public static boolean isvalid(int temp) {
        return temp>=0&&temp<64;
    }
}
