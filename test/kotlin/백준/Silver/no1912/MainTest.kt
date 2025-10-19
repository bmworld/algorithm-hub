package 백준.Silver.no1912

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.getIntArr

class MainTest {

  @Test
  fun solution() {

    assertEquals(33, solution(getIntArr("10 -4 3 1 5 6 -35 12 21 -1"))) // 예제1
    assertEquals(14, solution(getIntArr("2 1 -4 3 4 -4 6 5 -5 1"))) // 예제2
    assertEquals(-1, solution(getIntArr("-1 -2 -3 -4 -5"))) // 예제3

    assertEquals(15, solution(getIntArr("1 2 3 4 5"))) // 모두 양수
    assertEquals(-1, solution(getIntArr("-5 -4 -3 -2 -1"))) // 모두 음수
    assertEquals(3, solution(getIntArr("-1 -2 -3 3 -1 -2"))) // 양수 하나만 존재
    assertEquals(10, solution(getIntArr("5 5 -20 1 2 3"))) // 큰 음수로 끊김
    assertEquals(1, solution(getIntArr("1 -1 1 -1 1 -1 1 -1 1 -1"))) // 교차 패턴
    assertEquals(11, solution(getIntArr("-5 -1 3 4 2 2 -3"))) // 초반 음수 후 회복
    assertEquals(9, solution(getIntArr("-5 -4 -3 2 3 4"))) // 끝부분에서 최대
    assertEquals(1000, solution(getIntArr("-1 -1 -1 1000"))) // 긴 중립 후 극단적 양수
    assertEquals(15, solution(getIntArr("5 -1 6 -2 7"))) // 음수 포함해도 전체합 큼
    assertEquals(0, solution(getIntArr("0 0 0 0 0"))) // 전부 0
  }
}
