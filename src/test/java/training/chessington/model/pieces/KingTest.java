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
    @Test
    public void whiteKingCannotMovePastFriendlyPieceDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Piece friendlyPiece = new Knight(PlayerColour.WHITE);
        Coordinates kingCoords = new Coordinates(4, 4);
        board.placePiece(kingCoords, king);

        Coordinates knightUpLeftCoords = new Coordinates(3, 3);
        board.placePiece(knightUpLeftCoords, friendlyPiece);

        Coordinates knightUpRightCoords = new Coordinates(3, 5);
        board.placePiece(knightUpRightCoords, friendlyPiece);

        Coordinates knightDownRightCoords = new Coordinates(5, 5);
        board.placePiece(knightDownRightCoords, friendlyPiece);

        Coordinates knightDownLeftCoords = new Coordinates(5, 3);
        board.placePiece(knightDownLeftCoords, friendlyPiece);

        // Act
        List<Move> moves = king.getAllowedMoves(kingCoords, board);

        // Assert
        List <Move> impossibleMoves = new ArrayList<>();

        //knightUpLeftCoords
        impossibleMoves.add(new Move(kingCoords, knightUpLeftCoords));
        //knightUpRightCoords
        impossibleMoves.add(new Move(kingCoords, knightUpRightCoords));
        //knightDownLeftCoords
        impossibleMoves.add(new Move(kingCoords, knightDownLeftCoords));
        //knightDownRightCoords
        impossibleMoves.add(new Move(kingCoords, knightDownRightCoords));

        assertThat(moves).doesNotContain(impossibleMoves.toArray(new Move[0]));
    }

    @Test
    public void whiteKingCanTakeEnemyPieceDiagonallyAndStraight() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Piece enemyPiece = new Knight(PlayerColour.BLACK);
        Coordinates kingCoords = new Coordinates(4, 4);
        board.placePiece(kingCoords, king);
        // Diagonal Moves
        Coordinates knightUpLeftCoords = new Coordinates(3, 3);
        board.placePiece(knightUpLeftCoords, enemyPiece);

        Coordinates knightUpRightCoords = new Coordinates(3, 5);
        board.placePiece(knightUpRightCoords, enemyPiece);

        Coordinates knightDownRightCoords = new Coordinates(5, 5);
        board.placePiece(knightDownRightCoords, enemyPiece);

        Coordinates knightDownLeftCoords = new Coordinates(5, 3);
        board.placePiece(knightDownLeftCoords, enemyPiece);
        // Straight Moves
        Coordinates knightLeftCoords = new Coordinates(4, 3);
        board.placePiece(knightLeftCoords, enemyPiece);

        Coordinates knightRightCoords = new Coordinates(4, 5);
        board.placePiece(knightRightCoords, enemyPiece);

        Coordinates knightUpCoords = new Coordinates(3, 4);
        board.placePiece(knightUpCoords, enemyPiece);

        Coordinates knightDownCoords = new Coordinates(5, 4);
        board.placePiece(knightDownCoords, enemyPiece);

        // Act
        List<Move> moves = king.getAllowedMoves(kingCoords, board);

        // Assert
        List <Move> possibleMoves = new ArrayList<>();

        //knightUpLeftCoords
        possibleMoves.add(new Move(kingCoords, knightUpLeftCoords));
        //knightUpRightCoords
        possibleMoves.add(new Move(kingCoords, knightUpRightCoords));
        //knightDownLeftCoords
        possibleMoves.add(new Move(kingCoords, knightDownLeftCoords));
        //knightDownRightCoords
        possibleMoves.add(new Move(kingCoords, knightDownRightCoords));
        //knightLeftCoords
        possibleMoves.add(new Move(kingCoords, knightLeftCoords));
        //knightRightCoords
        possibleMoves.add(new Move(kingCoords, knightRightCoords));
        //knightUpCoords
        possibleMoves.add(new Move(kingCoords, knightUpCoords));
        //knightDownCoords
        possibleMoves.add(new Move(kingCoords, knightDownCoords));
        assertThat(moves).containsExactlyInAnyOrder(possibleMoves.toArray(new Move[0]));
    }
    //                                       Straight Lines TESTS

    @Test
    public void whiteKingCannotMovePastAFriendlyPieceStraight() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Piece friendlyPiece = new Knight(PlayerColour.WHITE);
        Coordinates kingCoords = new Coordinates(4, 4);
        board.placePiece(kingCoords, king);

        Coordinates knightLeftCoords = new Coordinates(4, 3);
        board.placePiece(knightLeftCoords, friendlyPiece);

        Coordinates knightRightCoords = new Coordinates(4, 5);
        board.placePiece(knightRightCoords, friendlyPiece);

        Coordinates knightUpCoords = new Coordinates(3, 4);
        board.placePiece(knightUpCoords, friendlyPiece);

        Coordinates knightDownCoords = new Coordinates(5, 4);
        board.placePiece(knightDownCoords, friendlyPiece);

        // Act
        List<Move> moves = king.getAllowedMoves(kingCoords, board);

        // Assert
        List <Move> impossibleMoves = new ArrayList<>();

        //knightLeftCoords
        impossibleMoves.add(new Move(kingCoords, knightLeftCoords));
        //knightRightCoords
        impossibleMoves.add(new Move(kingCoords, knightRightCoords));
        //knightUpCoords
        impossibleMoves.add(new Move(kingCoords, knightUpCoords));
        //knightDownCoords
        impossibleMoves.add(new Move(kingCoords, knightDownCoords));

        assertThat(moves).doesNotContain(impossibleMoves.toArray(new Move[0]));
    }
//    @Test
//    public void whiteKingCanTakeEnemyPieceStraight() {
//        // Arrange
//        Board board = Board.empty();
//        Piece king = new King(PlayerColour.WHITE);
//        Piece enemyPiece = new Knight(PlayerColour.BLACK);
//        Coordinates kingCoords = new Coordinates(4, 4);
//        board.placePiece(kingCoords, king);
//
//        Coordinates knightLeftCoords = new Coordinates(4, 3);
//        board.placePiece(knightLeftCoords, enemyPiece);
//
//        Coordinates knightRightCoords = new Coordinates(4, 5);
//        board.placePiece(knightRightCoords, enemyPiece);
//
//        Coordinates knightUpCoords = new Coordinates(3, 4);
//        board.placePiece(knightUpCoords, enemyPiece);
//
//        Coordinates knightDownCoords = new Coordinates(5, 4);
//        board.placePiece(knightDownCoords, enemyPiece);
//
//        // Act
//        List<Move> moves = king.getAllowedMoves(kingCoords, board);
//
//        // Assert
//        List <Move> impossibleMoves = new ArrayList<>();
//
//        //knightLeftCoords
//        impossibleMoves.add(new Move(kingCoords, knightLeftCoords));
//        //knightRightCoords
//        impossibleMoves.add(new Move(kingCoords, knightRightCoords));
//        //knightUpCoords
//        impossibleMoves.add(new Move(kingCoords, knightUpCoords));
//        //knightDownCoords
//        impossibleMoves.add(new Move(kingCoords, knightDownCoords));
//
//        assertThat(moves).containsExactlyInAnyOrder(impossibleMoves.toArray(new Move[0]));
//    }
}
