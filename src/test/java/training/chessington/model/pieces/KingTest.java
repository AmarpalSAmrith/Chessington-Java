package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KingTest {

    @Test
    public void kingCanMoveOneSpaceDiagonallyAndInStraightLines() {

        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, king);

        // Act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // Assert
        List<Move> possibleMoves = new ArrayList<>();
        possibleMoves.add(new Move(coords, coords.plus(-1, -1))); //UP LEFT
        possibleMoves.add(new Move(coords, coords.plus(1, 1))); //DOWN RIGHT
        possibleMoves.add(new Move(coords, coords.plus(-1, 1))); //UP RIGHT
        possibleMoves.add(new Move(coords, coords.plus(1, -1))); //DOWN LEFT
        for (int i = -1; i < 2; i++) {
            if (i != 0) {
                possibleMoves.add(new Move(coords, coords.plus(i, 0))); //VERTICAL
                possibleMoves.add(new Move(coords, coords.plus(0, i))); //HORIZONTAL
            }
        }
        assertThat(moves).containsExactlyInAnyOrder(possibleMoves.toArray(new Move[0]));
    }
}
