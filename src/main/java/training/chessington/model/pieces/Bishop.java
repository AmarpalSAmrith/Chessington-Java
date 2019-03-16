package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends AbstractPiece {
    public Bishop(PlayerColour colour) {
        super(PieceType.BISHOP, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List <Move> allowedMove = new ArrayList<>();
        allowedMove.addAll(allowedMoveConstructor(from, board,-1,-1)); // UP LEFT
        allowedMove.addAll(allowedMoveConstructor(from, board,1,1)); // DOWN RIGHT
        allowedMove.addAll(allowedMoveConstructor(from, board,1,-1)); // DOWN LEFT
        allowedMove.addAll(allowedMoveConstructor(from, board,-1,1)); // UP RIGHT

        return allowedMove;
    }
}
