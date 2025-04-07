package janggi.domain.piece2;

import janggi.domain.Turn;
import janggi.domain.board.Position;
import java.util.List;
import java.util.Map;

public class Empty extends Movable {

    public Empty(Turn side) {
        super(side);
    }

    @Override
    public boolean isCho() {
        return false;
    }

    @Override
    public List<Position> computeReachableDestinations(Position position, Map<Position, BoardPiece> board) {
        throw new IllegalArgumentException("[ERROR] 프로그램에 오류가 발생했습니다.");
    }

    @Override
    public Turn getTurn() {
        return Turn.NONE;
    }
}
