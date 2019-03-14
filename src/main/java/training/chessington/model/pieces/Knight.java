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
        List <Move> allowedMove = new ArrayList<>();

        if (colour.equals(PlayerColour.WHITE) || colour.equals(PlayerColour.BLACK)) {
            allowedMove.add(new Move(from, from.plus(2,1)));
            allowedMove.add(new Move(from, from.plus(2,-1)));
            allowedMove.add(new Move(from, from.plus(1,2)));
            allowedMove.add(new Move(from, from.plus(1,-2)));
            allowedMove.add(new Move(from, from.plus(-2,1)));
            allowedMove.add(new Move(from, from.plus(-2,-1)));
            allowedMove.add(new Move(from, from.plus(-1,2)));
            allowedMove.add(new Move(from, from.plus(-1,-2)));

        }

        return allowedMove;

    }
}
