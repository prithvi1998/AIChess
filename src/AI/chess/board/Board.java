package AI.chess.board;

import AI.chess.peice.Peice;

import java.util.Map;

public class Board {

    private Board(Builder builder){

    }

    public Square getSquare(int x){
        return null;
    }

    public static class Builder{

        Map<Integer,Peice> config;
        int nextChance;

        public Builder(){

        }


        public Board build(){
            return new Board(this);
        }
    }
}
