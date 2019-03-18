package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.List;

public class Queen extends AbstractPiece {
    public Queen(PlayerColour colour) {
        super(PieceType.QUEEN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Game game) {

        List<Move> allowedMove = new ArrayList<>();
        allowedMove.addAll(straightMoves(from, game.getBoard()));
        allowedMove.addAll(diagonalMoves(from, game.getBoard()));
        return allowedMove;
    }
}
