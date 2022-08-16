package tetris;

import gamestates.Playing;

import static util.Constants.UPS_CAP;

public class PieceController {

    private Playing playing;
    private Piece piece;
    private Board board;

    private int fallTick = 0, fallSpeed = UPS_CAP;
    private int lockTick = 0, lockDelay = UPS_CAP / 2;
    private int extLockTick = 0, extLockDelay = (int) (UPS_CAP * 1.5);
    private boolean left = false, right = false, down = false;
    private int leftTick = 0, rightTick = 0, downTick = 0, delaySpeed = 30;

    public PieceController(Playing playing, Board board, Piece piece) {
        this.playing = playing;
        this.board = board;
        this.piece = piece;
    }

    public void update() {
        fallPiece();

        if (!(left && right)) {
            leftController();
            rightController();
        }

        if (down) {
            if (downTick >= 10) {
                downTick = 0;
                move(0, 1);
                fallTick = 0;
            }
            downTick++;
        } else {
            downTick = 10;
        }
    }

    private void leftController() {
        if (left) {
            if (leftTick == 0) {
                move(-1, 0);
            } else if (leftTick >= delaySpeed) {
                move(-1, 0);
                leftTick -= 5;
            }
            leftTick++;
        } else {
            leftTick = 0;
        }
    }

    private void rightController() {
        if (right) {
            if (rightTick == 0) {
                move(1, 0);
            } else if (rightTick >= delaySpeed) {
                move(1, 0);
                rightTick -= 5;
            }
            rightTick++;
        } else {
            rightTick = 0;
        }
    }

    private void move(int dX, int dY) {
        boolean successfulMove = piece.move(piece.getX() + dX, piece.getY() + dY);

        if (successfulMove) {
            lockTick = 0;
        }
    }

    // Pushes the piece down over time and locks it if it cannot be lowered for some time.
    private void fallPiece() {
        if (piece.canMoveDown()) {
            fallTick++;

            if (fallTick >= fallSpeed) {
                fallTick = 0;
                move(0, 1);
            }
        } else {
            lockTick++;
            extLockTick++;
            fallTick = 0;

            if (lockTick >= lockDelay || extLockTick >= extLockDelay) {
                placePiece();
            }
        }
    }

    // Places/locks the current piece to the lowest possible position
    public void placePiece() {
        fallTick = 0;
        lockTick = 0;
        extLockTick = 0;
        piece.lock();
        board.clearRows();
        piece.newPiece(1);
        if (!piece.isValidSpawn()) {
            playing.setGameOver();
        }
    }

    public void setLeft(boolean left) {
        lockTick = 0;
        this.left = left;
    }

    public void setRight(boolean right) {
        lockTick = 0;
        this.right = right;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void rotate(int dir) {
        boolean successfulRotate = piece.rotate(dir);

        if (successfulRotate) {
            lockTick = 0;
        }
    }
}
