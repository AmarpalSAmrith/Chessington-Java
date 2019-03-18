package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Game game) {
        List<Move> allowedMove = new ArrayList<>();
        if (colour.equals(PlayerColour.WHITE) && from.getRow() != 0) { //WHITE
            if (game.getBoard().get(from.plus(-1, 0)) == null) {
                allowedMove.add(new Move(from, from.plus(-1, 0)));
            }

            if (from.getRow() == 6 && game.getBoard().get(from.plus(-2, 0)) == null && game.getBoard().get(from.plus(-1, 0)) == null) {
                allowedMove.add(new Move(from, from.plus(-2, 0)));
            }

            if (from.getCol() != 0 && game.getBoard().get(from.plus(-1, -1)) != null && game.getBoard().get(from.plus(-1, -1)).getColour().equals(PlayerColour.BLACK)) {
                allowedMove.add(new Move(from, from.plus(-1, -1)));
            }

            if (from.getCol() != 7 && game.getBoard().get(from.plus(-1, 1)) != null && game.getBoard().get(from.plus(-1, 1)).getColour().equals(PlayerColour.BLACK)) {
                allowedMove.add(new Move(from, from.plus(-1, 1)));
            }
        }
        if (colour.equals(PlayerColour.BLACK) && from.getRow() != 7) { //BLACK

            if (game.getBoard().get(from.plus(1, 0)) == null) {
                allowedMove.add(new Move(from, from.plus(1, 0)));
            }

            if (from.getRow() == 1 && game.getBoard().get(from.plus(2, 0)) == null && game.getBoard().get(from.plus(1, 0)) == null) {
                allowedMove.add(new Move(from, from.plus(2, 0)));
            }
            if (from.getCol() != 0 && game.getBoard().get(from.plus(1, -1)) != null && game.getBoard().get(from.plus(1, -1)).getColour().equals(PlayerColour.WHITE)) {
                allowedMove.add(new Move(from, from.plus(1, -1)));
            }

            if (from.getCol() != 7 && game.getBoard().get(from.plus(1, 1)) != null && game.getBoard().get(from.plus(1, 1)).getColour().equals(PlayerColour.WHITE)) {
                allowedMove.add(new Move(from, from.plus(1, 1)));
            }
//            if (isEnPassant(from, game)) {
//                allowedMove.add(new Move(from, from.plus(-1, -1)));
//            }

        }
        Move enPassantMoveAllowed = enPassantMove(from, game);
        if (enPassantMoveAllowed != null) {
            allowedMove.add(enPassantMoveAllowed);
        }
        return allowedMove;
    }

    private boolean isEnPassant(Coordinates from, Game game) {
        if (game.getGameHistory().size() > 0) {
            MoveWithPiece lastMove = game.getGameHistory().get(game.getGameHistory().size() - 1);
            int squaresMoved = Math.abs(lastMove.getMove().getFrom().getRow() - lastMove.getMove().getTo().getRow());
            return (Math.abs(lastMove.getMove().getTo().getCol() - from.getCol()) == 1 &&
                    lastMove.getMove().getTo().getRow() == from.getRow() &&
                    lastMove.getPiece().getType().equals(PieceType.PAWN) &&
                    squaresMoved == 2);
        }
        return false;
    }

    private Move enPassantMove(Coordinates from, Game game) {
        if (isEnPassant(from, game)) {
            Coordinates pawnLastMoved = game.getGameHistory().get(game.getGameHistory().size() - 1).getMove().getTo();
            int numberByWhichDirectionToMovePiece = from.getCol() < pawnLastMoved.getCol() ? 1 : -1;
            int colUpOrDown = colour.equals(PlayerColour.WHITE) ? -1 : 1;
            return new Move(from, from.plus(colUpOrDown, numberByWhichDirectionToMovePiece));
        }
        return null;
    }
}
