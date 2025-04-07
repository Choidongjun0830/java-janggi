package janggi.domain.piece2;

import janggi.domain.Turn;
import janggi.domain.board.Position;
import java.util.List;
import java.util.Map;

public abstract class Movable {

    private final Turn side;

    public Movable(Turn side) {
        this.side = side;
    }

    public abstract List<Position> computeReachableDestinations(final Position position, final Map<Position, BoardPiece> board);

    public boolean isCho() {
        return side == Turn.CHO;
    }

    public boolean isAlly(BoardPiece boardPiece) {
        return getTurn() == boardPiece.getTurn();
    }

    public Turn getTurn() {
        return side;
    }
}
