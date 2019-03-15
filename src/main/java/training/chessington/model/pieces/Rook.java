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
        PlayerColour enemyPieceColour = colour.equals(PlayerColour.WHITE) ? PlayerColour.BLACK : PlayerColour.WHITE;


        if (colour.equals(PlayerColour.WHITE) || colour.equals(PlayerColour.BLACK)) {
            for (int i = 1; i < 8; i++) {
                if (isAllowed(from.plus(i, 0))) {
                    allowedMove.add(new Move(from, from.plus(i, 0)));
                }
                if (isAllowed(from.plus(0, i))) {
                    allowedMove.add(new Move(from, from.plus(0, i)));
                }
                if (isAllowed(from.plus(-i, 0))) {
                    allowedMove.add(new Move(from, from.plus(-i, 0)));
                }
                if (isAllowed(from.plus(0, -i))) {
                    allowedMove.add(new Move(from, from.plus(0, -i)));
                }
            }
        }
        return allowedMove;
    }

    private boolean isAllowed(Coordinates to) {
        if (to.getCol() >= 0 && to.getCol() < 8 && to.getRow() >= 0 && to.getRow() < 8) {
            return true;
        }
        return false;
    }
}
