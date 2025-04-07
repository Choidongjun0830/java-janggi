package janggi.domain.piece.unlimit;

import janggi.domain.Turn;
import janggi.domain.board.PathFinder;
import janggi.domain.board.Position;
import janggi.domain.piece.Piece;
import janggi.domain.piece.PieceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cannon extends UnLimitMovable {

    public Cannon(final Turn side) {
        super(side);
    }

    @Override
    public List<Position> addValidDestination(final List<Position> positions, final PathFinder pathFinder) {
        List<Position> reachableDestinations = new ArrayList<>();
        boolean isJumped = false;
        for (Position position : positions) {
            if (pathFinder.isCannon(position)) {
                break;
            }
            if (!isJumped && pathFinder.isOccupied(position)) {
                isJumped = true;
                continue;
            }
            if(isJumped) {
                reachableDestinations.addAll(pathFinder.filterValidDestination(position, getSide()));
                if(pathFinder.isOccupied(position)) {
                    break;
                }
            }
        }
        return reachableDestinations;
    }

    @Override
    public PieceType getType() {
        return PieceType.CANNON;
    }
}
