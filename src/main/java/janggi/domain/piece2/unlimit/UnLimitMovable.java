package janggi.domain.piece2.unlimit;

import janggi.domain.Turn;
import janggi.domain.board.Position;
import janggi.domain.piece2.BoardPiece;
import janggi.domain.piece2.Movable;
import janggi.domain.move.Direction;
import janggi.domain.move.Route;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class UnLimitMovable extends Movable {
    private static final int MOVE_LIMIT = 10;

    public UnLimitMovable(Turn side) {
        super(side);
    }

    @Override
    public List<Position> computeReachableDestinations(final Position position, final Map<Position, BoardPiece> board) {
        List<Route> candidateRoutes = computeCandidateDirections(position);
        List<Position> reachableDestinations = new ArrayList<>();
        for (Route route : candidateRoutes) {
            List<Position> positions = route.getPositions();
            reachableDestinations.addAll(addValidDestination(positions, board));
        }
        return reachableDestinations;
    }

    protected abstract List<Position> addValidDestination(final List<Position> positions, final Map<Position, BoardPiece> board);

    private List<Route> computeCandidateDirections(final Position position) {
        List<Route> movableDirections = new ArrayList<>(Arrays.asList(createCandidateDirections(position, Direction.UP),
                    createCandidateDirections(position, Direction.DOWN),
                    createCandidateDirections(position, Direction.LEFT),
                    createCandidateDirections(position, Direction.RIGHT)));

        if(position.isDiagonalMovable()) {
            movableDirections.addAll(computeCandidateDirectionsInPalace(position));
        }
        movableDirections.removeIf(route -> route.getPositions().isEmpty());
        return movableDirections;
    }

    private Route createCandidateDirections(final Position position, final Direction direction) {
        Route route = new Route(position);

        for (int i = 0; i < MOVE_LIMIT; i++) {
            Position lastPosition = route.getLastPosition();
            Position movedPosition = lastPosition.move(direction);

            if (movedPosition.isInBoardRange()) {
                route.addRoute(lastPosition.move(direction));
            }
        }
        route.deleteFirstPosition();
        return route;
    }

    private List<Route> computeCandidateDirectionsInPalace(final Position position) {
        return new ArrayList<>(Arrays.asList(createPalaceRoute(position, Direction.LEFT_UP),
                createPalaceRoute(position, Direction.LEFT_DOWN),
                createPalaceRoute(position, Direction.RIGHT_UP),
                createPalaceRoute(position, Direction.RIGHT_DOWN)));
    }

    private Route createPalaceRoute(final Position position, final Direction direction) {
        Route route = new Route(position);

        for (int i = 0; i < MOVE_LIMIT; i++) {
            Position lastPosition = route.getLastPosition();
            Position movedPosition = lastPosition.move(direction);
            if (movedPosition.isDiagonalMovable()) {
                route.addRoute(movedPosition);
            }
        }
        route.deleteFirstPosition();
        return route;
    }
}
