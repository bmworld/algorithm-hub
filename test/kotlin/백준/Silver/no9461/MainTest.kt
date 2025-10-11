package 백준.Silver.no9461

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {
    assertEquals(3, solution(6))
    assertEquals(7, solution(9))
    assertEquals(9, solution(10))
    assertEquals(16, solution(12))
    assertEquals(888855064897L, solution(100))
  }
}
