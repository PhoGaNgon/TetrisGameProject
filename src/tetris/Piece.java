package tetris;

import java.awt.*;

import static util.Constants.BoardConstants.*;

public class Piece {

    private Board board;
    private int[][] boardContents;
    private int xTile, yTile;
    private int pieceType, fallSpeed = 150, fallTick = 0;
    private int placeDelay = 150, placeTick = 0; // How long it takes to place a block
    private boolean active = true;
    private int[][] pieceTiles;

    public Piece(Board board, int pieceType) {
        this.board = board;
        this.boardContents = board.getBoardContents();
        this.pieceType = pieceType;
        xTile = 0;
        yTile = 0;
    }

    private void initPieces(int pieceType) {
        if (pieceType == 1) {

        }
    }

    public void update() {
        fall();
    }

    public void draw(Graphics g) {
        if (pieceType == 1) {
            int xPos = (int) (board.getX() + xTile * TILE_SIZE) + 1;
            int yPos = (int) (board.getY() + yTile * TILE_SIZE) + 1;
            g.setColor(Color.RED);
            g.fillRect(xPos, yPos, PIECE_TILE_SIZE, PIECE_TILE_SIZE);
        }
    }

    private void fall() {
        fallTick++;

        if (fallTick >= fallSpeed)
            moveDown();

        if (!canMoveHere(xTile, yTile + 1)) {
            fallTick = 0;
            placeTick++;

            if (placeTick >= placeDelay) {
                placePiece();
                placeTick = 0;
            }
        }
    }

    // Moves the piece in x direction by dir tiles
    public void move(int dir) {
        if (canMoveHere(xTile + dir, yTile))
            xTile += dir;
    }

    // Moves the piece down a tile
    public void moveDown() {
        if (canMoveHere(xTile, yTile + 1)) {
            yTile++;
            fallTick = 0;
        }
    }

    private void placePiece() {
        boardContents[yTile][xTile] = pieceType;
        active = false;
    }

    // Checks if the piece can be moved to the newXTile and newYTile positions
    private boolean canMoveHere(int newXTile, int newYTile) {
        if (newXTile >= 0 && newXTile < NUM_TILES_WIDTH && newYTile < NUM_TILES_HEIGHT) {
            return boardContents[newYTile][newXTile] == 0;
        }
        return false;
    }

    public boolean isActive() {
        return active;
    }

}
