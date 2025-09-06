package 백준.Silver.no13241

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {

    assertEquals(1, solution(1, 1))
    assertEquals(15, solution(3, 5))
    assertEquals(30, solution(6, 10))
    assertEquals(24079, solution(121, 199))
  }

  @Test
  fun lcm() {

    assertEquals(1, lcm(1, 1))
    assertEquals(15, lcm(3, 5))
    assertEquals(30, lcm(6, 10))
    assertEquals(24079, lcm(121, 199))
  }
}
