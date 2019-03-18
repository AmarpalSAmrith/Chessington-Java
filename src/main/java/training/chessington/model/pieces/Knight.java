package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.List;

public class Knight extends AbstractPiece {
    public Knight(PlayerColour colour) {
        super(PieceType.KNIGHT, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Game game) {
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
                if (isAllowed(possibleMove, game.getBoard())) {
                    allowedMove.add(possibleMove);
                }
            }
        }
        return allowedMove;
    }
}
