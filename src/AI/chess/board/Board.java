package AI.chess.board;

import AI.chess.peice.*;
import AI.chess.player.BlackPlayer;
import AI.chess.player.Player;
import AI.chess.player.WhitePlayer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.util.*;

public class Board {

    private List<Square> gameBoard;
    private Collection<Peice> BlackPeices;
    private Collection<Peice> WhitePeices;

    private WhitePlayer whitePlayer;
    private BlackPlayer blackPlayer;
    private Player currentPlayer;

    private Board(Builder builder){
            this.gameBoard = createGameBoard(builder);
            this.BlackPeices = calculateActivePeices(this.gameBoard , 1);
            this.WhitePeices = calculateActivePeices(this.gameBoard , -1);
            Collection<Move> whiteStandardMoves = calculateLegalMoves(this.WhitePeices);
            Collection<Move> blackStandardMoves = calculateLegalMoves(this.BlackPeices);
            this.whitePlayer = new WhitePlayer(this,whiteStandardMoves,blackStandardMoves);
            this.blackPlayer = new BlackPlayer(this,whiteStandardMoves,blackStandardMoves);
            this.currentPlayer = builder.choosePlayer(this.whitePlayer,this.blackPlayer);
    }

    public Player currentPlayer() {
        return currentPlayer;
    }

    private static Collection<Peice> calculateActivePeices(List<Square> gameBoard, int i) {
        List<Peice> ActivePeices = new ArrayList<>();
        for(Square s : gameBoard){
            if(!s.isEmpty()){
                if(s.getPeice().color == i){
                    ActivePeices.add(s.getPeice());
                }
            }
        }
        return ActivePeices;
    }

    private Collection<Move> calculateLegalMoves( Collection<Peice> peices) {
         List<Move> legalMoves = new ArrayList<>(35);
        for ( Peice peice : peices) {
            legalMoves.addAll(peice.LegalMoves(this));
        }
        return legalMoves;
    }

    public Square getSquare(int x){
        return gameBoard.get(x);
    }

    public Collection<Peice> getBlackPeices(){
        return this.BlackPeices;
    }
    public Collection<Peice> getWhitePeices(){
        return this.WhitePeices;
    }

    public Player whitePlayer(){
        return  this.whitePlayer;
    }

    public Player blackPlayer(){
        return  this.blackPlayer;
    }

    private static List<Square> createGameBoard(Builder b){
        Square[] sqaures = new Square[64];
        for(int i=0;i<64;i++){
            sqaures[i] = Square.createSquare(i,b.config.get(i));
        }
            return ImmutableList.copyOf(sqaures);
    }

    public static Board initial(){
        final Builder builder = new Builder();
        // Black Layout
        builder.setpeice(new Rook(1, 0));
        builder.setpeice(new Knight(1, 1));
        builder.setpeice(new Bishop(1, 2));
        builder.setpeice(new Queen(1, 3));
        builder.setpeice(new King(1, 4));
        builder.setpeice(new Bishop(1, 5));
        builder.setpeice(new Knight(1, 6));
        builder.setpeice(new Rook(1, 7));
        builder.setpeice(new pawn(1, 8));
        builder.setpeice(new pawn(1, 9));
        builder.setpeice(new pawn(1, 10));
        builder.setpeice(new pawn(1, 11));
        builder.setpeice(new pawn(1, 12));
        builder.setpeice(new pawn(1, 13));
        builder.setpeice(new pawn(1, 14));
        builder.setpeice(new pawn(1, 15));
        // White Layout
        builder.setpeice(new pawn(-1, 48));
        builder.setpeice(new pawn(-1, 49));
        builder.setpeice(new pawn(-1, 50));
        builder.setpeice(new pawn(-1, 51));
        builder.setpeice(new pawn(-1, 52));
        builder.setpeice(new pawn(-1, 53));
        builder.setpeice(new pawn(-1, 54));
        builder.setpeice(new pawn(-1, 55));
        builder.setpeice(new Rook(-1, 56));
        builder.setpeice(new Knight(-1, 57));
        builder.setpeice(new Bishop(-1, 58));
        builder.setpeice(new Queen(-1, 59));
        builder.setpeice(new King(-1, 60));
        builder.setpeice(new Bishop(-1, 61));
        builder.setpeice(new Knight(-1, 62));
        builder.setpeice(new Rook(-1, 63));
        //white to move
        builder.setMoveMaker(-1);
        //build the board
        return builder.build();
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 64; i++) {
            final String str = prettyPrint(this.gameBoard.get(i));
            sb.append(String.format("%3s", str));
            if ((i + 1) % 8 == 0) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private static String prettyPrint(final Square sq) {
        if(!sq.isEmpty()) {
            return (sq.getPeice().color == 1) ?
                    sq.toString().toLowerCase() : sq.toString();
        }
        return sq.toString();
    }

    public Iterable<Move> getAllLegalMoves() {
        return Iterables.concat(this.whitePlayer.legalMoves ,this.blackPlayer.legalMoves);
    }

    public static class Builder{

        Map<Integer,Peice> config;
        int nextChance;
        pawn enpassantPawn;

        public Builder(){
            this.config = new HashMap<>();

        }

        public Builder setpeice(Peice peice){
            this.config.put(peice.peicePosition,peice);
            return this;
        }

        public Builder setMoveMaker(final int next)
        {
            this.nextChance = next;
            return this;
        }

        public Board build(){
            return new Board(this);
        }

        public Player choosePlayer(WhitePlayer w,BlackPlayer b) {
            if(this.nextChance == 1){
                return b;
            }
            else
                return w;
        }

        public void setEnpassantPawn(pawn enpassantPawn) {
            this.enpassantPawn = enpassantPawn;
        }
    }
}
