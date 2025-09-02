package 백준.Silver.no2839

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionTest {

  @Test fun solution() {
    assertEquals(4, solution(18))
    assertEquals(-1, solution(4))
    assertEquals(2, solution(6))
    assertEquals(3, solution(9))
    assertEquals(3, solution(11))
    assertEquals(5, solution(21))
    assertEquals(1000, solution(4996))
  }
}
