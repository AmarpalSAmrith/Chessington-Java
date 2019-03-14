package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List <Move> allowedMove = new ArrayList<>();

        allowedMove.add(new Move(from, from.plus(-1, 0)));
        allowedMove.add(new Move(from, from.plus(1, 0)));
        if (from.getRow() == 6) {
            allowedMove.add(new Move(from, from.plus(-2, 0)));
        }
        if (from.getRow() == 1) {
            allowedMove.add(new Move(from, from.plus(2, 0)));
        }
        return allowedMove;
    }
}
