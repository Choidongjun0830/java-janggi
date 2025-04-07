package janggi.domain.piece2.unlimit;

import janggi.domain.Turn;
import janggi.domain.board.Position;
import janggi.domain.piece2.BoardPiece;
import janggi.domain.piece2.Movable;
import janggi.domain.piece2.PieceType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Chariot extends UnLimitMovable {

    public Chariot(Turn side) {
        super(side);
    }

    @Override
    public List<Position> addValidDestination(final List<Position> positions, final Map<Position, BoardPiece> board) {
        List<Position> reachableDestinations = new ArrayList<>();
        for (Position position : positions) {
            BoardPiece targetPiece = board.get(position);
            if (isAlly(targetPiece)) {
                break;
            }
            reachableDestinations.add(position);
            if (targetPiece.isOccupied() && !isAlly(targetPiece)) {
                break;
            }
        }
        return reachableDestinations;
    }
}
