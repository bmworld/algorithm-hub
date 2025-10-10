package 백준.Silver.no1904

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {

    assertEquals(1, solution(1))
    assertEquals(2, solution(2))
    assertEquals(3, solution(3))
    assertEquals(5, solution(4))
    assertEquals(8, solution(5))
    assertEquals(55, solution(9))
    assertEquals(89, solution(10))
    assertEquals(6231, solution(100))
    assertEquals(5329, solution(1000))
    assertEquals(1979, solution(100000))
    assertEquals(7871, solution(1_000_000))
  }
}
