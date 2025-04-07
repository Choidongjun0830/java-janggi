package janggi.domain.piece2.limit;

import janggi.domain.Turn;
import janggi.domain.board.Position;
import janggi.domain.move.Route;
import janggi.domain.piece2.BoardPiece;
import janggi.domain.piece2.Movable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class LimitMovable extends Movable {

    public LimitMovable(Turn side) {
        super(side);
    }

    @Override
    public List<Position> computeReachableDestinations(final Position position, final Map<Position, BoardPiece> board) {
        List<Route> candidateRoutes = computeCandidatePositions(position);

        List<Position> reachablePositions = new ArrayList<>();
        for (Route route : candidateRoutes) {
            reachablePositions.addAll(getReachablePositionIfValid(board, route));
        }
        return reachablePositions;
    }

    protected abstract List<Route> computeCandidatePositions(final Position position);

    private List<Position> getReachablePositionIfValid(final Map<Position, BoardPiece> board, final Route route) {
        if (isInvalidRoute(route, board)) {
            return List.of();
        }
        return List.of(route.getLastPosition());
    }

    private boolean isInvalidRoute(final Route route, final Map<Position, BoardPiece> board) {
        Position destination = route.getLastPosition();
        if (isAlly(board.get(destination))) {
            return true;
        }
        return checkInvalidIntermediatePositions(route, board);
    }

    private boolean checkInvalidIntermediatePositions(final Route route, final Map<Position, BoardPiece> board) {
        return route.getIntermediatePositions().stream()
                .map(board::get)
                .anyMatch(BoardPiece::isOccupied);
    }
}
