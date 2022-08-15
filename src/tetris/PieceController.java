package tetris;

import gamestates.Playing;

public class PieceController {

    private Board board;
    private Playing playing;
    private Piece piece;

    public PieceController(Board board, Playing playing) {
        this.board = board;
        this.playing = playing;
    }

}
