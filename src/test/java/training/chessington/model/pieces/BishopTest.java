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

public class BishopTest {
    @Test
    public void bishopCanMoveDiagonallyLines() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        List<Move> possibleMoves = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            possibleMoves.add(new Move(coords, coords.plus(-i, -i))); //UP LEFT
            possibleMoves.add(new Move(coords, coords.plus(i, i))); //DOWN RIGHT
            possibleMoves.add(new Move(coords, coords.plus(-i, i))); //UP RIGHT
            possibleMoves.add(new Move(coords, coords.plus(i, -i))); //DOWN LEFT
        }
        possibleMoves.add(new Move(coords, coords.plus(-4, -4))); //UP LEFT exception move

        assertThat(moves).containsExactlyInAnyOrder(possibleMoves.toArray(new Move[0]));
    }
    @Test
    public void whiteBishopCannotMovePastFriendlyPiece() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Piece friendlyPiece = new Knight(PlayerColour.WHITE);
        Coordinates bishopCoords = new Coordinates(4, 4);
        board.placePiece(bishopCoords, bishop);

        Coordinates knightUpLeftCoords = new Coordinates(1, 1);
        board.placePiece(knightUpLeftCoords, friendlyPiece);

        Coordinates knightUpRightCoords = new Coordinates(2, 6);
        board.placePiece(knightUpRightCoords, friendlyPiece);

        Coordinates knightDownRightCoords = new Coordinates(6, 6);
        board.placePiece(knightDownRightCoords, friendlyPiece);

        Coordinates knightDownLeftCoords = new Coordinates(6, 2);
        board.placePiece(knightDownLeftCoords, friendlyPiece);

        // Act
        List<Move> moves = bishop.getAllowedMoves(bishopCoords, board);

        // Assert
        List <Move> impossibleMoves = new ArrayList<>();

        //knightUpLeftCoords
        impossibleMoves.add(new Move(bishopCoords, knightUpLeftCoords));
        impossibleMoves.add(new Move(bishopCoords, knightUpLeftCoords.plus(-1,-1)));
        //knightUpRightCoords
        impossibleMoves.add(new Move(bishopCoords, knightUpRightCoords));
        impossibleMoves.add(new Move(bishopCoords, knightUpRightCoords.plus(-1,1)));
        //knightDownLeftCoords
        impossibleMoves.add(new Move(bishopCoords, knightDownLeftCoords));
        impossibleMoves.add(new Move(bishopCoords, knightDownLeftCoords.plus(1,-1)));
        //knightDownRightCoords
        impossibleMoves.add(new Move(bishopCoords, knightDownRightCoords));
        impossibleMoves.add(new Move(bishopCoords, knightDownRightCoords.plus(1,1)));

        assertThat(moves).doesNotContain(impossibleMoves.toArray(new Move[0]));
    }

    @Test
    public void whiteRookCanTakeEnemyPiece() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Piece enemyPiece = new Knight(PlayerColour.BLACK);
        Coordinates bishopCoords = new Coordinates(4, 4);
        board.placePiece(bishopCoords, bishop);

        Coordinates knightUpLeftCoords = new Coordinates(1, 1);
        board.placePiece(knightUpLeftCoords, enemyPiece);

        Coordinates knightUpRightCoords = new Coordinates(2, 6);
        board.placePiece(knightUpRightCoords, enemyPiece);

        Coordinates knightDownRightCoords = new Coordinates(6, 6);
        board.placePiece(knightDownRightCoords, enemyPiece);

        Coordinates knightDownLeftCoords = new Coordinates(6, 2);
        board.placePiece(knightDownLeftCoords, enemyPiece);

        // Act
        List<Move> moves = bishop.getAllowedMoves(bishopCoords, board);

        // Assert
        List <Move> impossibleMoves = new ArrayList<>();

        //knightUpLeftCoords
        impossibleMoves.add(new Move(bishopCoords, knightUpLeftCoords.plus(0,-1)));
        //knightUpRightCoords
        impossibleMoves.add(new Move(bishopCoords, knightUpRightCoords.plus(0,1)));
        //knightDownLeftCoords
        impossibleMoves.add(new Move(bishopCoords, knightDownLeftCoords.plus(-1,0)));
        //knightDownRightCoords
        impossibleMoves.add(new Move(bishopCoords, knightDownRightCoords.plus(1,0)));

        assertThat(moves).doesNotContain(impossibleMoves.toArray(new Move[0]));
    }
}
