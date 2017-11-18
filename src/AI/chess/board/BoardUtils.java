package AI.chess.board;

import com.google.common.collect.ImmutableList;

import java.util.List;

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
    public static final boolean[] SIXTH_COLUMN = {
            false,false,false,false,false,true,false,false,
            false,false,false,false,false,true,false,false,
            false,false,false,false,false,true,false,false,
            false,false,false,false,false,true,false,false,
            false,false,false,false,false,true,false,false,
            false,false,false,false,false,true,false,false,
            false,false,false,false,false,true,false,false,
            false,false,false,false,false,true,false,false
    };
    public static final boolean[] FIFTH_COLUMN = {
            false,false,false,false,true,false,false,false,
            false,false,false,false,true,false,false,false,
            false,false,false,false,true,false,false,false,
            false,false,false,false,true,false,false,false,
            false,false,false,false,true,false,false,false,
            false,false,false,false,true,false,false,false,
            false,false,false,false,true,false,false,false,
            false,false,false,false,true,false,false,false
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
    public static final boolean[] SECOND_ROW = {
            false,false,false,false,false,false,false,false,
            true,true,true,true,true,true,true,true,
            false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false
    };
    public static final boolean[] SEVENTH_ROW = {
            false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,
            true,true,true,true,true,true,true,true,
            false,false,false,false,false,false,false,false
    };

    //remaining colomn
    public static final List<Boolean> FOURTH_COLUMN = initColumn(3);
    public static final List<Boolean> THIRD_COLUMN = initColumn(2);
    //public final List<Boolean> SECOND_COLUMN = initColumn(1);
    //public final List<Boolean> FIRST_COLUMN = initColumn(0);


    public static final List<Boolean> FIRST_ROW = initRow(0);
   // public final List<Boolean> SECOND_ROW = initRow(8);
    public static final List<Boolean> THIRD_ROW = initRow(16);
    public static final List<Boolean> FOURTH_ROW = initRow(24);
    public static final List<Boolean> FIFTH_ROW = initRow(32);
    public static final List<Boolean> SIXTH_ROW = initRow(40);
    //public final List<Boolean> SEVENTH_ROW = initRow(48);
    public static final List<Boolean> EIGHTH_ROW = initRow(56);


    public static final int START_TILE_INDEX = 0;
    public static final int NUM_TILES_PER_ROW = 8;
    public static final int NUM_TILES = 64;



    //fucntion to create boolean of colomn
    private static List<Boolean> initColumn(int columnNumber) {
        final Boolean[] column = new Boolean[NUM_TILES];
        for(int i = 0; i < column.length; i++) {
            column[i] = false;
        }
        do {
            column[columnNumber] = true;
            columnNumber += NUM_TILES_PER_ROW;
        } while(columnNumber < NUM_TILES);
        return ImmutableList.copyOf(column);
    }

    //remaining rows for function
    private static List<Boolean> initRow(int rowNumber) {
        final Boolean[] row = new Boolean[NUM_TILES];
        for(int i = 0; i < row.length; i++) {
            row[i] = false;
        }
        do {
            row[rowNumber] = true;
            rowNumber++;
        } while(rowNumber % NUM_TILES_PER_ROW != 0);
        return ImmutableList.copyOf(row);
    }

    public static boolean isvalid(int temp) {
        return temp>=0&&temp<64;
    }
}
