package training.chessington.model.pieces;

import training.chessington.model.Move;

public class MoveWithPiece {
    private Move move;
    private Piece piece;

    public MoveWithPiece(Move move, Piece piece) {
        this.move = move;
        this.piece = piece;
    }

    public Move getMove() {
        return move;
    }

    public Piece getPiece() {
        return piece;
    }
}
