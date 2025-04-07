package janggi.domain.board;

import janggi.domain.Turn;
import janggi.domain.move.Route;
import janggi.domain.piece.Piece;
import janggi.domain.piece.PieceType;
import java.util.List;
import java.util.Map;

public class PathFinder {

    private final JanggiBoard board;

    public PathFinder(JanggiBoard board) {
        this.board = board;
    }

    public boolean isOccupied(Position position) {
        Map<Position, Piece> pieceBoard = this.board.getBoard();
        return pieceBoard.get(position).getType() != PieceType.EMPTY;
    }

    public List<Position> getReachablePositionIfValid( final Route route, final Turn currentSide) {
        if (isInvalidRoute(route, currentSide)) {
            return List.of();
        }
        return List.of(route.getLastPosition());
    }

    public boolean isInvalidRoute(final Route route, final Turn currentSide) {
        Position destination = route.getLastPosition();
        if (isAlly(destination, currentSide)) {
            return true;
        }
        return checkInvalidIntermediatePositions(route);
    }

    public List<Position> filterValidDestination(final Position position, final Turn currentSide) {
        if(!isOccupied(position) || isEnemy(position, currentSide)) {
            return List.of(position);
        }
        return List.of();
    }

    public boolean isAlly(Position position, Turn currentSide) {
        Piece positionPiece = getPositionPiece(position);
        return isOccupied(position) && currentSide == positionPiece.getSide();
    }

    public boolean isEnemy(Position position, Turn currentSide) {
        Piece positionPiece = getPositionPiece(position);
        return isOccupied(position) && currentSide != positionPiece.getSide();
    }

    public boolean isCannon(Position position) {
        return getPositionPiece(position).isCannon();
    }

    private boolean checkInvalidIntermediatePositions(final Route route) {
        List<Position> intermediatePositions = route.getIntermediatePositions();
        for (Position intermediatePosition : intermediatePositions) {
            if(isOccupied(intermediatePosition)) {
                return true;
            }
        }
        return false;
    }

    private Piece getPositionPiece(Position position) {
        return board.getPositionPiece(position);
    }
}
