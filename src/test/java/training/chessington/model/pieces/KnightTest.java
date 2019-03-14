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

public class KnightTest {
    @Test
    public void whiteKnightCanMoveInLShape() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).containsExactlyInAnyOrder(
                //Forwards - BLACKS
                new Move(coords, coords.plus(2,1)),
                new Move(coords, coords.plus(2,-1)),
                new Move(coords, coords.plus(1,2)),
                new Move(coords, coords.plus(1,-2)),
                //Backwards - BLACKS
                new Move(coords, coords.plus(-2,1)),
                new Move(coords, coords.plus(-2,-1)),
                new Move(coords, coords.plus(-1,2)),
                new Move(coords, coords.plus(-1,-2))
                );
    }
}
