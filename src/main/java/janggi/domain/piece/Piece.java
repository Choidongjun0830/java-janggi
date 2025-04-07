package janggi.domain.piece;

import janggi.domain.Turn;
import janggi.domain.board.PathFinder;
import janggi.domain.board.Position;

import java.util.List;

public interface Piece {

    PieceType getType();

    List<Position> computeReachableDestinations(final Position position, final PathFinder pathFinder);

    boolean isHan();

    boolean isCho();

    Turn getSide();

    default boolean isAlly(Piece piece) {
        return getSide() == piece.getSide();
    }

    default boolean isOccupied() {
        return !(getType() == PieceType.EMPTY);
    }

    default boolean isCannon() {
        return getType() == PieceType.CANNON;
    }

    default boolean isKing() {
        return getType() == PieceType.KING;
    }

    default boolean isSameSide(final Turn turn) {
        return turn == getSide();
    }
}
