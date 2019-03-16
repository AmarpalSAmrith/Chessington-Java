package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPiece implements Piece {

    protected final Piece.PieceType type;
    protected final PlayerColour colour;

    protected AbstractPiece(Piece.PieceType type, PlayerColour colour) {
        this.type = type;
        this.colour = colour;
    }

    @Override
    public Piece.PieceType getType() {
        return type;
    }

    @Override
    public PlayerColour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return colour.toString() + " " + type.toString();
    }
    private boolean isInBoundaries(Coordinates to) {
        return to.getCol() >= 0 && to.getCol() < 8 && to.getRow() >= 0 && to.getRow() < 8;
    }

    protected boolean isEmpty(Coordinates to, Board board) {
        return board.get(to) == null;
    }

    protected boolean isEnemy(Coordinates to, Board board) {
        return !board.get(to).getColour().equals(colour);
    }

    protected boolean isFriendly(Coordinates to, Board board) {
        return board.get(to).getColour().equals(colour);
    }

    protected List<Move> allowedMoveConstructor(Coordinates from, Board board, int rowBase, int colBase) {
        List <Move> allowedMove = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            Coordinates to = from.plus(rowBase * i, colBase * i);

            if (isInBoundaries(to)) {
                if (isEmpty(to, board)) {
                    allowedMove.add(new Move(from, to));
                } else if (isEnemy(to, board)) {
                    allowedMove.add(new Move(from, to));
                    break;
                } else if (isFriendly(to, board)) {
                    break;
                }
            } else {
                break;
            }
        }
        return allowedMove;
    }
}
