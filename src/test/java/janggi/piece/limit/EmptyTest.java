package janggi.piece.limit;

import janggi.domain.board.JanggiBoard;
import janggi.domain.board.PathFinder;
import janggi.domain.board.Position;
import janggi.domain.piece.Empty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmptyTest {

    @Test
    @DisplayName("빈 셀에서 이동 후보군 계산시 예외를 발생시킨다")
    void test() {
        Empty empty = new Empty();
        JanggiBoard janggiBoard = JanggiBoard.initializeWithPieces();
        assertThatThrownBy(() -> empty.computeReachableDestinations(new Position(3, 10), new PathFinder(janggiBoard)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 프로그램에 오류가 발생했습니다.");
    }

}
