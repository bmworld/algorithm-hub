package 백준.Silver.no1010

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {

    assertEquals(1, solution(2, 2))
    assertEquals(5, solution(5, 1))
    assertEquals(67863915, solution(29, 13))
  }

  @Test
  fun solution2() {
    buildPascal()
    assertEquals(1, C[2][2])
    assertEquals(5, C[5][1])
    assertEquals(67863915, C[29][13])
  }
}
