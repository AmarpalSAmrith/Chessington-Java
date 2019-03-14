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

        if (colour.equals(PlayerColour.WHITE) && from.getRow() != 0) { //WHITE

            if (board.get(from.plus(-1, 0)) == null) {
                allowedMove.add(new Move(from, from.plus(-1, 0)));
            }

            if (from.getRow() == 6 && board.get(from.plus(-2,0)) == null && board.get(from.plus(-1,0)) == null) {
                allowedMove.add(new Move(from, from.plus(-2, 0)));
            }

            if (from.getCol() != 0 && board.get(from.plus(-1, -1)) != null && board.get(from.plus(-1, -1)).getColour().equals(PlayerColour.BLACK)) {
                allowedMove.add(new Move(from, from.plus(-1, -1)));
            }

            if (from.getCol() != 7 && board.get(from.plus(-1, 1)) != null && board.get(from.plus(-1, 1)).getColour().equals(PlayerColour.BLACK)) {
                allowedMove.add(new Move(from, from.plus(-1, 1)));
            }
        }

        if (colour.equals(PlayerColour.BLACK) && from.getRow() != 7) { //BLACK

            if (board.get(from.plus(1, 0)) == null) {
                allowedMove.add(new Move(from, from.plus(1, 0)));
            }

            if (from.getRow() == 1 && board.get(from.plus(2, 0)) == null && board.get(from.plus(1, 0)) == null) {
                allowedMove.add(new Move(from, from.plus(2, 0)));
            }
            if (from.getCol() != 0 && board.get(from.plus(1, -1)) != null && board.get(from.plus(1, -1)).getColour().equals(PlayerColour.WHITE)) {
                allowedMove.add(new Move(from, from.plus(1, -1)));
            }

            if (from.getCol() != 7 && board.get(from.plus(1, 1)) != null && board.get(from.plus(1, 1)).getColour().equals(PlayerColour.WHITE)) {
                allowedMove.add(new Move(from, from.plus(1, 1)));
            }
        }

        return allowedMove;
    }
}
