package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KnightTest {
    @Test
    public void knightCanMoveInLShape() {
        // Arrange
        Board board = Board.empty();
        Game game = new Game(board);
        Piece knight = new Knight(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, game);

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
    @Test
    public void knightCannotMoveOffTheBoard() {

//        Arrange
        Board board = Board.empty();
        Game game = new Game(board);
        Piece knight = new Knight(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(1, 0);
        board.placePiece(coords, knight);
//        Action
        List<Move> moves = knight.getAllowedMoves(coords, game);

//        Act
        assertThat(moves).containsExactlyInAnyOrder(
                new Move(coords, coords.plus(2,1)),
                new Move(coords, coords.plus(1,2)),
                new Move(coords, coords.plus(-1,2))
                );
    }
    @Test
    public void whiteKnightCannotCaptureOwnPiece() {
        // Arrange
        Board board = Board.empty();
        Game game = new Game(board);
        Piece knight = new Knight(PlayerColour.WHITE);
        Piece friendlyPiece = new Rook(PlayerColour.WHITE);
        Coordinates knightCoords = new Coordinates(4, 4);
        board.placePiece(knightCoords, knight);

        Coordinates rookCoords = knightCoords.plus(-1, 2);
        board.placePiece(rookCoords, friendlyPiece);

        // Act
        List<Move> moves = knight.getAllowedMoves(knightCoords, game);

        // Assert
        assertThat(moves).doesNotContain(new Move(knightCoords, rookCoords));
    }
    @Test
    public void whiteKnightCanCaptureEnemyPiece() {
        // Arrange
        Board board = Board.empty();
        Game game = new Game(board);
        Piece knight = new Knight(PlayerColour.WHITE);
        Piece enemyPiece = new Rook(PlayerColour.BLACK);
        Coordinates knightCoords = new Coordinates(4, 4);
        board.placePiece(knightCoords, knight);

        Coordinates rookCoords = knightCoords.plus(-1, 2);
        board.placePiece(rookCoords, enemyPiece);

        // Act
        List<Move> moves = knight.getAllowedMoves(knightCoords, game);

        // Assert
        assertThat(moves).contains(new Move(knightCoords, rookCoords));
    }
}
