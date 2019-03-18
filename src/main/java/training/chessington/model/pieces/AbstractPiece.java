package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPiece implements Piece {

    protected final Piece.PieceType type;
    protected final PlayerColour colour;

    protected AbstractPiece(Piece.PieceType type, PlayerColour colour) {
        this.type = type;
        this.colour = colour;
    }

    @Override
    public Piece.PieceType getType() {
        return type;
    }

    @Override
    public PlayerColour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return colour.toString() + " " + type.toString();
    }

    private boolean isInBoundaries(Coordinates to) {
        return to.getCol() >= 0 && to.getCol() < 8 && to.getRow() >= 0 && to.getRow() < 8;
    }

    protected boolean isEmpty(Coordinates to, Board board) {
        return board.get(to) == null;
    }

    protected boolean isEnemy(Coordinates to, Board board) {
        return !board.get(to).getColour().equals(colour);
    }

    protected boolean isFriendly(Coordinates to, Board board) {
        return board.get(to).getColour().equals(colour);
    }

    protected List<Move> allowedMoveConstructor(Coordinates from, Board board, int rowBase, int colBase) {
        List<Move> allowedMove = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            Coordinates to = from.plus(rowBase * i, colBase * i);

            if (isInBoundaries(to)) {
                if (isEmpty(to, board)) {
                    allowedMove.add(new Move(from, to));
                } else if (isEnemy(to, board)) {
                    allowedMove.add(new Move(from, to));
                    break;
                } else if (isFriendly(to, board)) {
                    break;
                }
            } else {
                break;
            }
        }
        return allowedMove;
    }

    protected List<Move> diagonalMoves(Coordinates from, Board board) {
        List<Move> allowedMove = new ArrayList<>();
        if (colour.equals(PlayerColour.WHITE) || colour.equals(PlayerColour.BLACK)) {
            allowedMove.addAll(allowedMoveConstructor(from, board, -1, -1)); // UP LEFT
            allowedMove.addAll(allowedMoveConstructor(from, board, 1, 1)); // DOWN RIGHT
            allowedMove.addAll(allowedMoveConstructor(from, board, 1, -1)); // DOWN LEFT
            allowedMove.addAll(allowedMoveConstructor(from, board, -1, 1)); // UP RIGHT
        }
        return allowedMove;
    }

    protected List<Move> straightMoves(Coordinates from, Board board) {

        List<Move> allowedMove = new ArrayList<>();

        if (colour.equals(PlayerColour.WHITE) || colour.equals(PlayerColour.BLACK)) {
            allowedMove.addAll(allowedMoveConstructor(from, board, 0, 1)); // RIGHT
            allowedMove.addAll(allowedMoveConstructor(from, board, 0, -1)); // LEFT
            allowedMove.addAll(allowedMoveConstructor(from, board, -1, 0)); // UP
            allowedMove.addAll(allowedMoveConstructor(from, board, 1, 0)); // DOWN
        }
        return allowedMove;
    }
    protected List <Move> allowedIndividualMove(Coordinates from, List <Coordinates> to, Board board) {
        List <Move> allowedMoves = new ArrayList<>();
        for (int i = 0; i < to.size(); i++) {
            Coordinates toCoords = to.get(i);
            if (isInBoundaries(toCoords)) {
                if (isEmpty(toCoords, board)) {
                    allowedMoves.add(new Move(from, toCoords));
                } else if (isEnemy(toCoords, board)) {
                    allowedMoves.add(new Move(from, toCoords));
                }
                }
            }
        return allowedMoves;
        }
    protected boolean isAllowed(Move move, Board board) {
        if (!(move.getTo().getCol() >= 0 && move.getTo().getCol() <= 7 && move.getTo().getRow() >= 0 && move.getTo().getRow() <= 7)) {
            return false;
        }
        return board.get(move.getTo()) == null || !board.get(move.getTo()).getColour().equals(colour);
    }
    }

