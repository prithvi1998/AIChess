

import AI.chess.board.Board;
import AI.chess.gui.Table;
public class Jchess {

    public static void main(String[] args){
        Table table = new Table();
        Board board = Board.initial();
        System.out.println(board);
    }

}
