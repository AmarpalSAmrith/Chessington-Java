package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMove = new ArrayList<>();
        List<Coordinates> toList = new ArrayList<>();
        toList.add(from.plus(-1, -1)); //UP LEFT
        toList.add(from.plus(1, 1)); //DOWN RIGHT
        toList.add(from.plus(-1, 1)); //UP RIGHT
        toList.add(from.plus(1, -1)); //DOWN LEFT
        for (int i = -1; i < 2; i++) {
            if (i != 0) {
                toList.add(from.plus(i, 0)); //VERTICAL
                toList.add(from.plus(0, i)); //HORIZONTAL
            }
        }
        allowedMove.addAll(allowedIndividualMove(from, toList, board));
        return allowedMove;

    }
}
