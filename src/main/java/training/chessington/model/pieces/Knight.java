package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Knight extends AbstractPiece {
    public Knight(PlayerColour colour) {
        super(PieceType.KNIGHT, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMove = new ArrayList<>();
        List<Move> possibleMoves = new ArrayList<>();

        if (colour.equals(PlayerColour.WHITE) || colour.equals(PlayerColour.BLACK)) {
            possibleMoves.add(new Move(from, from.plus(2, 1)));
            possibleMoves.add(new Move(from, from.plus(2, -1)));
            possibleMoves.add(new Move(from, from.plus(1, 2)));
            possibleMoves.add(new Move(from, from.plus(1, -2)));
            possibleMoves.add(new Move(from, from.plus(-2, 1)));
            possibleMoves.add(new Move(from, from.plus(-2, -1)));
            possibleMoves.add(new Move(from, from.plus(-1, 2)));
            possibleMoves.add(new Move(from, from.plus(-1, -2)));

            for (Move possibleMove : possibleMoves) {
                if (isAllowed(possibleMove, board)) {
                    allowedMove.add(possibleMove);
                }
            }
        }
        return allowedMove;

    }

    private boolean isAllowed(Move move, Board board) {
        if (!(move.getTo().getCol() >= 0 && move.getTo().getCol() <= 7 && move.getTo().getRow() >= 0 && move.getTo().getRow() <= 7)) {
            return false;
        }
        return board.get(move.getTo()) == null || !board.get(move.getTo()).getColour().equals(colour);
    }
}
