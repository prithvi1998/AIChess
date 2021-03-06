package AI.chess.gui;


//TODO in highlight for legal move feature still pawn.java file has to make changes video number 37.
import AI.chess.board.Board;
import AI.chess.board.BoardUtils;
import AI.chess.board.Move;
import AI.chess.board.Square;
import AI.chess.peice.Peice;
import AI.chess.player.MoveTransition;
import com.google.common.collect.Lists;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static javax.swing.SwingUtilities.invokeLater;
import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class Table {

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private  Board chessBoard;

    //variables to take track of selected peice to play a move
    private Square sourceSquare;
    private Square destinationSquare;
    private Peice humanMovedPeice;
    private BoardDirection boardDirection;
    private boolean highlightLegalMoves;

    private static final Dimension OUTER_FRAME_DIMENSION = new Dimension(600,600);
    private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
    private static final Dimension TILE_PANEL_DIMENSION = new Dimension(10,10);
    private static String defaultPeiceImagesPath = "sprites/";
    private Color lightTileColor = Color.decode("#FFFACD");
    private Color darkTileColor = Color.decode("#593E1A");

    //constructor
    public Table(){
        this.gameFrame = new JFrame("Java Chess");
        this.gameFrame.setLayout(new BorderLayout());
        //create a menu panel and attach it to gameframe
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        //set dimensions for outer chess board
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        //create chess board
        this.chessBoard = Board.initial();
        //create a board panel and add it to the center of the game frame layout
        this.boardPanel = new BoardPanel();
        this.boardDirection = BoardDirection.NORMAL;
        this.highlightLegalMoves = false;
        this.gameFrame.add(this.boardPanel,BorderLayout.CENTER);
        //set visible true
        this.gameFrame.setVisible(true);

    }

    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        tableMenuBar.add(createPreferenceMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Load PGN File");
        //adding a listener to PGN option
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open up that pgn file!");
            }
        });

        fileMenu.add(openPGN);

        final JMenu exitMenu = new JMenu("Exit");
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenu);
        return fileMenu;
    }

    //creating preference menu in top tableMenuBar
    private JMenu createPreferenceMenu(){
        final JMenu preferenceMenu = new JMenu("Preference");
        final JMenuItem flipBoardMenuItem = new JMenuItem("Flip Board");
        flipBoardMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardDirection = boardDirection.opposite();
                boardPanel.drawBoard(chessBoard);
            }
        });

        preferenceMenu.add(flipBoardMenuItem);

        //add option of highlighting the legal moves
        preferenceMenu.addSeparator();

        final JCheckBoxMenuItem leagalMoveHighlighterCheckbox = new JCheckBoxMenuItem("Highlight Legal Moves",false);

        leagalMoveHighlighterCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightLegalMoves = leagalMoveHighlighterCheckbox.isSelected();
            }
        });
        preferenceMenu.add(leagalMoveHighlighterCheckbox);
        return preferenceMenu;
    }

    public enum BoardDirection {
        NORMAL {
            @Override
            List<TilePanel>traverse(final List<TilePanel> boardTiles) {
                return boardTiles;
            }

            @Override
            BoardDirection opposite(){
                return FLIPPED;
            }
        },
        FLIPPED {
            @Override
            List<TilePanel> traverse(final List<TilePanel> boardTiles){
                return Lists.reverse(boardTiles);
                //return boardTiles;
            }
            @Override BoardDirection opposite(){
                return NORMAL;
            }

        };

        abstract List<TilePanel> traverse(final List<TilePanel> boardTiles);
        abstract BoardDirection opposite();
    }

    //board  panel class to represent visual component on board
    private class BoardPanel extends JPanel{

        final List<TilePanel> boardTiles;

        BoardPanel(){
            //todo check GridBagLayout or just GridLayout
            super(new GridLayout(8,8));
            this.boardTiles = new ArrayList<>();
            /*
                here first declare BoardUtils class and related member that change it with below loop

             */
            for(int i =0;i<64;i++){
                final TilePanel tilePanel = new TilePanel(this,i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }

        //redraw method after each move
        public void drawBoard(final Board board){
            removeAll();
            for(final TilePanel tilePanel : boardDirection.traverse(boardTiles)){
                tilePanel.drawTile(board);
                add(tilePanel);
            }
            validate();
            repaint();
        }

    }

    //tile panel class to represent visual component on the tile
    private class TilePanel extends JPanel{

        private final int tileId ;

        TilePanel(final BoardPanel boardPanel , final int tileId){
            super(new GridBagLayout());
            this.tileId = tileId;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignTileColor();
            assignTilePeiceIcon(chessBoard);

            //adding mouse listener to tile
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    if(isLeftMouseButton(e)){
                        //if right button of mouse is pressed just unselect the selected Peice

                        //when source tile is not picked up
                        if(sourceSquare==null){
                            sourceSquare = chessBoard.getSquare(tileId);
                            humanMovedPeice = sourceSquare.getPeice();
                            //in case we clicked on empty tile
                            if(humanMovedPeice==null){
                                sourceSquare = null;
                            }
                        }else{
                            //its a second click. sourceSquare is not null
                            destinationSquare = chessBoard.getSquare(tileId);
                            final Move move = Move.MoveFactory.createMove(chessBoard,sourceSquare.getSquareNumber(),destinationSquare.getSquareNumber());
                            final MoveTransition transition = chessBoard.currentPlayer().makeMove(move);
                            if(transition.getMoveStatus().isDone()){
                                chessBoard = transition.getTransitionBoard();
                                //TODO add the move that was made to the move log in case we are implementing that also
                            }
                            //now after making the move just reset all the variable to null
                            sourceSquare = null;
                            destinationSquare = null;
                            humanMovedPeice = null;
                        }
                    }
                    else if(isRightMouseButton(e)){
                        //just cancel if there was some Peice was selected
                        sourceSquare = null;
                        destinationSquare = null;
                        humanMovedPeice = null;
                    }
                    invokeLater(new Runnable() {
                        public void run() {
                            //gameHistoryPanel.redo(chessBoard, moveLog);
                            //takenPiecesPanel.redo(moveLog);
                            //if (gameSetup.isAIPlayer(chessBoard.currentPlayer())) {
                            //Table.get().moveMadeUpdate(PlayerType.HUMAN);
                            //}
                            boardPanel.drawBoard(chessBoard);
                            //debugPanel.redo();
                        }
                    });
                }

                @Override
                public void mousePressed(final MouseEvent e) {

                }

                @Override
                public void mouseReleased(final MouseEvent e) {

                }

                @Override
                public void mouseEntered(final MouseEvent e) {

                }

                @Override
                public void mouseExited(final MouseEvent e) {

                }
            });
            validate();
        }


        public void drawTile(final Board board){
            assignTileColor();
            assignTilePeiceIcon(board);
            highlightLegals(board);
            highlightTileBorder(board);
            validate();
            repaint();
        }

        /*
        JUST TO REMOVE ERROR i created Board class and getTile method
        still to resolve isTileOccupied method
         */

        //mehtod to assign a Peice on the tile
        private void assignTilePeiceIcon(final Board board){
            this.removeAll();
            if(!board.getSquare(this.tileId).isEmpty()){

                try {

                    final BufferedImage image =
                    ImageIO.read(new File(defaultPeiceImagesPath+((board.getSquare(this.tileId).getPeice().color == -1)?"white":"black") + board.getSquare(this.tileId).getPeice().toString()+".png"));
                    add(new JLabel(new ImageIcon(image)));
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        private void highlightLegals(final Board board){
            if(highlightLegalMoves){
                for(Move move : peiceLegalMoves(board)) {
                    if(move.getDestination()==this.tileId){
                        try {
                            add(new JLabel(new ImageIcon(ImageIO.read(new File("sprites/green_dot.png")))));
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }


        private void highlightTileBorder(final Board board) {
            if(humanMovedPeice != null &&
                    humanMovedPeice.color == board.currentPlayer().getColor() &&
                    humanMovedPeice.color == this.tileId) {
                setBorder(BorderFactory.createLineBorder(Color.cyan));
            } else {
                setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
        }

        private Collection<Move> peiceLegalMoves(final  Board board){
            if(humanMovedPeice !=null && humanMovedPeice.color==board.currentPlayer().getColor()){
                return humanMovedPeice.LegalMoves(board);
            }
            return Collections.emptyList();
        }

        private void assignTileColor() {
            if(BoardUtils.FIRST_ROW.get(this.tileId) || BoardUtils.THIRD_ROW.get(this.tileId) || BoardUtils.FIFTH_ROW.get(this.tileId) ||BoardUtils.SEVENTH_ROW[this.tileId])
               {
                    setBackground(this.tileId%2==0?lightTileColor:darkTileColor);
                }
            else if(BoardUtils.SECOND_ROW[this.tileId]|| BoardUtils.FOURTH_ROW.get(this.tileId) || BoardUtils.SIXTH_ROW.get(this.tileId) || BoardUtils.EIGHTH_ROW.get(this.tileId) )
              {
                   setBackground(this.tileId%2!=0?lightTileColor:darkTileColor);
                }

        }
    }

}
