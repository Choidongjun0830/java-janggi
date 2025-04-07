package janggi.domain.piece.limit;

import janggi.domain.Turn;
import janggi.domain.board.PathFinder;
import janggi.domain.board.Position;
import janggi.domain.move.Route;
import janggi.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public abstract class LimitMovable implements Piece {

    private final Turn side;

    public LimitMovable(final Turn side) {
        this.side = side;
    }

    @Override
    public List<Position> computeReachableDestinations(final Position position, final PathFinder pathFinder) {
        List<Route> candidateRoutes = computeCandidatePositions(position);

        List<Position> reachablePositions = new ArrayList<>();
        for (Route route : candidateRoutes) {
            reachablePositions.addAll(pathFinder.getReachablePositionIfValid(route, getSide()));
        }
        return reachablePositions;
    }

    abstract List<Route> computeCandidatePositions(final Position position);

    @Override
    public boolean isCho() {
        return side == Turn.CHO;
    }

    @Override
    public boolean isHan() {
        return side == Turn.HAN;
    }

    @Override
    public Turn getSide() {
        return side;
    }
}
