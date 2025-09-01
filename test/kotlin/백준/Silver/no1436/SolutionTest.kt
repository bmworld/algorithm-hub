package 백준.Silver.no1436

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionTest {

  @Test
  fun solution() {
    assertEquals(666, solution(1))
    assertEquals(1666, solution(2))
    assertEquals(2666, solution(3))
    assertEquals(66666, solution(187))
    assertEquals(166699, solution(500))
    //    assertEquals(166699, solution(10000))
  }
}
