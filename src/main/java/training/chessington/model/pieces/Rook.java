package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Rook extends AbstractPiece {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        List<Move> allowedMove = new ArrayList<>();

        if (colour.equals(PlayerColour.WHITE) || colour.equals(PlayerColour.BLACK)) {
            for (int i = 1; i < 8; i++) {
                if (isInBoundries(from.plus(i, 0))) {

                    if (isEmpty(from.plus(i, 0), board)) {
                        allowedMove.add(new Move(from, from.plus(i, 0)));
                    } else if (isEnemy(from.plus(i, 0), board)) {
                        allowedMove.add(new Move(from, from.plus(i, 0)));
                        break;
                    } else if (isFriendly(from.plus(i, 0), board)) {
                        break;
                    }

                } else {
                    break;
                }
            }

            for (int i = 1; i < 8; i++) {

                if (isInBoundries(from.plus(0, i))) {

                    if (isEmpty(from.plus(0, i), board)) {
                        allowedMove.add(new Move(from, from.plus(0, i)));
                    } else if (isEnemy(from.plus(0, i), board)) {
                        allowedMove.add(new Move(from, from.plus(0, i)));
                        break;
                    } else if (isFriendly(from.plus(0, i), board)) {
                        break;
                    }

                } else {
                    break;
                }
            }

            for (int i = 1; i < 8; i++) {
                if (isInBoundries(from.plus(-i, 0))) {

                    if (isEmpty(from.plus(-i, 0), board)) {
                        allowedMove.add(new Move(from, from.plus(-i, 0)));
                    } else if (isEnemy(from.plus(-i, 0), board)) {
                        allowedMove.add(new Move(from, from.plus(-i, 0)));
                        break;
                    } else if (isFriendly(from.plus(-i, 0), board)) {
                        break;
                    }

                } else {
                    break;
                }
            }
            for (int i = 1; i < 8; i++) {

                if (isInBoundries(from.plus(0, -i))) {
                    if (isEmpty(from.plus(0, -i), board)) {
                        allowedMove.add(new Move(from, from.plus(0, -i)));
                    } else if (isEnemy(from.plus(0, -i), board)) {
                        allowedMove.add(new Move(from, from.plus(0, -i)));
                        break;
                    } else if (isFriendly(from.plus(0, -i), board)) {
                        break;
                    }
                } else {
                    break;
                }
            }

        }
        return allowedMove;
    }

    private boolean isInBoundries(Coordinates to) {
        return to.getCol() >= 0 && to.getCol() < 8 && to.getRow() >= 0 && to.getRow() < 8;
    }

    private boolean isEmpty(Coordinates to, Board board) {
        return board.get(to) == null;
    }

    private boolean isEnemy(Coordinates to, Board board) {
        return !board.get(to).getColour().equals(colour);
    }

    private boolean isFriendly(Coordinates to, Board board) {
        return board.get(to).getColour().equals(colour);
    }
}