package training.chessington.model;

import training.chessington.model.pieces.*;
import training.chessington.view.ChessApp;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int SIZE = 8;
    private final Board board;

    private PlayerColour nextPlayer = PlayerColour.WHITE;

    private boolean isEnded = false;

    private List<MoveWithPiece> gameHistory;

    private 

    public Game(Board board) {
        this.board = board;
        this.gameHistory = new ArrayList<>();
    }

    public Piece pieceAt(int row, int col) {
        return board.get(new Coordinates(row, col));
    }

    public List<Move> getAllowedMoves(Coordinates from) {
        if (isEnded) {
            return new ArrayList<>();
        }

        Piece piece = board.get(from);
        if (piece == null || piece.getColour() != nextPlayer) {
            return new ArrayList<>();
        }

        return piece.getAllowedMoves(from, this);
    }

    public void makeMove(Move move) throws InvalidMoveException {
        if (isEnded) {
            throw new InvalidMoveException("Game has ended!");
        }

        Coordinates from = move.getFrom();
        Coordinates to = move.getTo();

        Piece piece = board.get(from);
        if (piece == null) {
            throw new InvalidMoveException(String.format("No piece at %s", from));
        }

        if (piece.getColour() != nextPlayer) {
            throw new InvalidMoveException(String.format("Wrong colour piece - it is %s's turn", nextPlayer));
        }

        if (!piece.getAllowedMoves(move.getFrom(), this).contains(move)) {
            throw new InvalidMoveException(String.format("Cannot move piece %s from %s to %s", piece, from, to));
        }

        if (getGameHistory().size() > 0) {
            MoveWithPiece lastMove = getGameHistory().get(getGameHistory().size() - 1);
            if (lastMove.getPiece().getType() == Piece.PieceType.PAWN && Math.abs(lastMove.getMove().getFrom().getRow() - lastMove.getMove().getTo().getRow()) == 2) {
                Coordinates skippedSquare = lastMove.getPiece().getColour() == PlayerColour.WHITE ? lastMove.getMove().getTo().plus(1, 0) : lastMove.getMove().getTo().plus(-1, 0);
                if (to.equals(skippedSquare)) {
                    board.obliterate(lastMove.getMove().getTo());
                }
            }
        }


        board.move(from, to);
        gameHistory.add(new MoveWithPiece(new Move(from, to), piece));
        nextPlayer = nextPlayer == PlayerColour.WHITE ? PlayerColour.BLACK : PlayerColour.WHITE;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public String getResult() {
        return null;
    }

    public Board getBoard() {
        return board;
    }

    public List<MoveWithPiece> getGameHistory() {
        return gameHistory;
    }
}
