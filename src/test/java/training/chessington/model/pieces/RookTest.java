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

public class RookTest {
    @Test
    public void rookCanMoveInStraightLines() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        List <Move> possibleMoves = new ArrayList<>();
        for (int i = -4; i < 4; i++) {
            if (i != 0) {
                possibleMoves.add(new Move(coords, coords.plus(i, 0)));
                possibleMoves.add(new Move(coords, coords.plus(0, i)));
            }
        }
        assertThat(moves).containsExactlyInAnyOrder(possibleMoves.toArray(new Move[0]));
    }

    @Test
    public void whiteRookCannotMovePastAPiece() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Piece friendlyPiece = new Knight(PlayerColour.WHITE);
        Coordinates rookCoords = new Coordinates(4, 4);
        board.placePiece(rookCoords, rook);

        Coordinates knightLeftCoords = rookCoords.plus(4, 1);
        board.placePiece(knightLeftCoords, friendlyPiece);

        Coordinates knightRightCoords = rookCoords.plus(4, 6);
        board.placePiece(knightRightCoords, friendlyPiece);

        Coordinates knightUpCoords = rookCoords.plus(1, 4);
        board.placePiece(knightUpCoords, friendlyPiece);

        Coordinates knightDownCoords = rookCoords.plus(6, 4);
        board.placePiece(knightDownCoords, friendlyPiece);

        // Act
        List<Move> moves = rook.getAllowedMoves(rookCoords, board);

        // Assert
        List <Move> impossibleMoves = new ArrayList<>();

        //knightLeftCoords
        impossibleMoves.add(new Move(rookCoords, knightLeftCoords));
        impossibleMoves.add(new Move(rookCoords, knightLeftCoords.plus(0,-1)));
        //knightRightCoords
        impossibleMoves.add(new Move(rookCoords, knightRightCoords));
        impossibleMoves.add(new Move(rookCoords, knightRightCoords.plus(0,1)));
        //knightUpCoords
        impossibleMoves.add(new Move(rookCoords, knightUpCoords));
        impossibleMoves.add(new Move(rookCoords, knightUpCoords.plus(-1,0)));
        //knightDownCoords
        impossibleMoves.add(new Move(rookCoords, knightDownCoords));
        impossibleMoves.add(new Move(rookCoords, knightDownCoords.plus(1,0)));

        assertThat(moves).doesNotContain(impossibleMoves.toArray(new Move[0]));
    }
}
