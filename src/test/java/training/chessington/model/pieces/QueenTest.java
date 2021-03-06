package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.*;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class QueenTest {
    //Diagonal TESTS
    @Test
    public void queenCanMoveDiagonallyAndInStraightLines() {
        // Arrange
        Board board = Board.empty();
        Game game = new Game(board);
        Piece queen = new Queen(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, game);

        // Assert
        List<Move> possibleMoves = new ArrayList<>();

//        Diagonal MOVES
        for (int i = 1; i < 4; i++) {
            possibleMoves.add(new Move(coords, coords.plus(-i, -i))); //UP LEFT
            possibleMoves.add(new Move(coords, coords.plus(i, i))); //DOWN RIGHT
            possibleMoves.add(new Move(coords, coords.plus(-i, i))); //UP RIGHT
            possibleMoves.add(new Move(coords, coords.plus(i, -i))); //DOWN LEFT
        }
        possibleMoves.add(new Move(coords, coords.plus(-4, -4))); //UP LEFT exception move

//        Straight LINES

        for (int i = -4; i < 4; i++) {
            if (i != 0) {
                possibleMoves.add(new Move(coords, coords.plus(i, 0)));
                possibleMoves.add(new Move(coords, coords.plus(0, i)));
            }
        }

        assertThat(moves).containsExactlyInAnyOrder(possibleMoves.toArray(new Move[0]));
    }
    @Test
    public void whiteQueenCannotMovePastFriendlyPieceDiagonally() {
        // Arrange
        Board board = Board.empty();
        Game game = new Game(board);
        Piece queen = new Queen(PlayerColour.WHITE);
        Piece friendlyPiece = new Knight(PlayerColour.WHITE);
        Coordinates queenCoords = new Coordinates(4, 4);
        board.placePiece(queenCoords, queen);

        Coordinates knightUpLeftCoords = new Coordinates(1, 1);
        board.placePiece(knightUpLeftCoords, friendlyPiece);

        Coordinates knightUpRightCoords = new Coordinates(2, 6);
        board.placePiece(knightUpRightCoords, friendlyPiece);

        Coordinates knightDownRightCoords = new Coordinates(6, 6);
        board.placePiece(knightDownRightCoords, friendlyPiece);

        Coordinates knightDownLeftCoords = new Coordinates(6, 2);
        board.placePiece(knightDownLeftCoords, friendlyPiece);

        // Act
        List<Move> moves = queen.getAllowedMoves(queenCoords, game);

        // Assert
        List <Move> impossibleMoves = new ArrayList<>();

        //knightUpLeftCoords
        impossibleMoves.add(new Move(queenCoords, knightUpLeftCoords));
        impossibleMoves.add(new Move(queenCoords, knightUpLeftCoords.plus(-1,-1)));
        //knightUpRightCoords
        impossibleMoves.add(new Move(queenCoords, knightUpRightCoords));
        impossibleMoves.add(new Move(queenCoords, knightUpRightCoords.plus(-1,1)));
        //knightDownLeftCoords
        impossibleMoves.add(new Move(queenCoords, knightDownLeftCoords));
        impossibleMoves.add(new Move(queenCoords, knightDownLeftCoords.plus(1,-1)));
        //knightDownRightCoords
        impossibleMoves.add(new Move(queenCoords, knightDownRightCoords));
        impossibleMoves.add(new Move(queenCoords, knightDownRightCoords.plus(1,1)));

        assertThat(moves).doesNotContain(impossibleMoves.toArray(new Move[0]));
    }

    @Test
    public void whiteQueenCanTakeEnemyPieceDiagonally() {
        // Arrange
        Board board = Board.empty();
        Game game = new Game(board);
        Piece queen = new Queen(PlayerColour.WHITE);
        Piece enemyPiece = new Knight(PlayerColour.BLACK);
        Coordinates queenCoords = new Coordinates(4, 4);
        board.placePiece(queenCoords, queen);

        Coordinates knightUpLeftCoords = new Coordinates(1, 1);
        board.placePiece(knightUpLeftCoords, enemyPiece);

        Coordinates knightUpRightCoords = new Coordinates(2, 6);
        board.placePiece(knightUpRightCoords, enemyPiece);

        Coordinates knightDownRightCoords = new Coordinates(6, 6);
        board.placePiece(knightDownRightCoords, enemyPiece);

        Coordinates knightDownLeftCoords = new Coordinates(6, 2);
        board.placePiece(knightDownLeftCoords, enemyPiece);

        // Act
        List<Move> moves = queen.getAllowedMoves(queenCoords, game);

        // Assert
        List <Move> impossibleMoves = new ArrayList<>();

        //knightUpLeftCoords
        impossibleMoves.add(new Move(queenCoords, knightUpLeftCoords.plus(0,-1)));
        //knightUpRightCoords
        impossibleMoves.add(new Move(queenCoords, knightUpRightCoords.plus(0,1)));
        //knightDownLeftCoords
        impossibleMoves.add(new Move(queenCoords, knightDownLeftCoords.plus(-1,0)));
        //knightDownRightCoords
        impossibleMoves.add(new Move(queenCoords, knightDownRightCoords.plus(1,0)));

        assertThat(moves).doesNotContain(impossibleMoves.toArray(new Move[0]));
    }
    //                                       Straight Lines TESTS

    @Test
    public void whiteQueenCannotMovePastAPieceStraight() {
        // Arrange
        Board board = Board.empty();
        Game game = new Game(board);
        Piece queen = new Queen(PlayerColour.WHITE);
        Piece friendlyPiece = new Knight(PlayerColour.WHITE);
        Coordinates queenCoords = new Coordinates(4, 4);
        board.placePiece(queenCoords, queen);

        Coordinates knightLeftCoords = new Coordinates(4, 1);
        board.placePiece(knightLeftCoords, friendlyPiece);

        Coordinates knightRightCoords = new Coordinates(4, 6);
        board.placePiece(knightRightCoords, friendlyPiece);

        Coordinates knightUpCoords = new Coordinates(1, 4);
        board.placePiece(knightUpCoords, friendlyPiece);

        Coordinates knightDownCoords = new Coordinates(6, 4);
        board.placePiece(knightDownCoords, friendlyPiece);

        // Act
        List<Move> moves = queen.getAllowedMoves(queenCoords, game);

        // Assert
        List <Move> impossibleMoves = new ArrayList<>();

        //knightLeftCoords
        impossibleMoves.add(new Move(queenCoords, knightLeftCoords));
        impossibleMoves.add(new Move(queenCoords, knightLeftCoords.plus(0,-1)));
        //knightRightCoords
        impossibleMoves.add(new Move(queenCoords, knightRightCoords));
        impossibleMoves.add(new Move(queenCoords, knightRightCoords.plus(0,1)));
        //knightUpCoords
        impossibleMoves.add(new Move(queenCoords, knightUpCoords));
        impossibleMoves.add(new Move(queenCoords, knightUpCoords.plus(-1,0)));
        //knightDownCoords
        impossibleMoves.add(new Move(queenCoords, knightDownCoords));
        impossibleMoves.add(new Move(queenCoords, knightDownCoords.plus(1,0)));

        assertThat(moves).doesNotContain(impossibleMoves.toArray(new Move[0]));
    }
    @Test
    public void whiteQueenCanTakeEnemyPieceStraight() {
        // Arrange
        Board board = Board.empty();
        Game game = new Game(board);
        Piece queen = new Queen(PlayerColour.WHITE);
        Piece enemyPiece = new Knight(PlayerColour.BLACK);
        Coordinates queenCoords = new Coordinates(4, 4);
        board.placePiece(queenCoords, queen);

        Coordinates knightLeftCoords = new Coordinates(4, 1);
        board.placePiece(knightLeftCoords, enemyPiece);

        Coordinates knightRightCoords = new Coordinates(4, 6);
        board.placePiece(knightRightCoords, enemyPiece);

        Coordinates knightUpCoords = new Coordinates(1, 4);
        board.placePiece(knightUpCoords, enemyPiece);

        Coordinates knightDownCoords = new Coordinates(6, 4);
        board.placePiece(knightDownCoords, enemyPiece);

        // Act
        List<Move> moves = queen.getAllowedMoves(queenCoords, game);

        // Assert
        List <Move> impossibleMoves = new ArrayList<>();

        //knightLeftCoords
        impossibleMoves.add(new Move(queenCoords, knightLeftCoords.plus(0,-1)));
        //knightRightCoords
        impossibleMoves.add(new Move(queenCoords, knightRightCoords.plus(0,1)));
        //knightUpCoords
        impossibleMoves.add(new Move(queenCoords, knightUpCoords.plus(-1,0)));
        //knightDownCoords
        impossibleMoves.add(new Move(queenCoords, knightDownCoords.plus(1,0)));

        assertThat(moves).doesNotContain(impossibleMoves.toArray(new Move[0]));
    }

}
