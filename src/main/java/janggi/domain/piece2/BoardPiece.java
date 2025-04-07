package janggi.domain.piece2;

import janggi.domain.Turn;
import janggi.domain.board.Position;
import java.util.List;
import java.util.Map;

public class BoardPiece {

    private final PieceType pieceType;
    private final Movable piece;

    public BoardPiece(PieceType pieceType, Movable piece) {
        this.pieceType = pieceType;
        this.piece = piece;
    }

    public List<Position> getReachableDestinations(final Position position, final Map<Position, BoardPiece> board) {
        return piece.computeReachableDestinations(position, board);
    }

    public boolean isOccupied() {
        return !(pieceType == PieceType.EMPTY);
    }

    public boolean isCannon() {
        return pieceType == PieceType.CANNON;
    }

    public boolean isKing() {
        return pieceType == PieceType.KING;
    }

    public Turn getTurn() {
        return piece.getTurn();
    }
}
