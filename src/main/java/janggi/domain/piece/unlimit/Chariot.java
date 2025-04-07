package janggi.domain.piece.unlimit;

import janggi.domain.Turn;
import janggi.domain.board.PathFinder;
import janggi.domain.board.Position;
import janggi.domain.piece.Piece;
import janggi.domain.piece.PieceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Chariot extends UnLimitMovable {

    public Chariot(final Turn side) {
        super(side);
    }

    @Override
    public List<Position> addValidDestination(final List<Position> positions, final PathFinder pathFinder) {
        List<Position> reachableDestinations = new ArrayList<>();
        for (Position position : positions) {
            if (pathFinder.isAlly(position, getSide())) {
                break;
            }
            reachableDestinations.add(position);
            if (pathFinder.isEnemy(position, getSide())) {
                break;
            }
        }
        return reachableDestinations;
    }

    @Override
    public PieceType getType() {
        return PieceType.CHARIOT;
    }
}
