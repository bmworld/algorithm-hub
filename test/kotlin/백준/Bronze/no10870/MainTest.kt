package 백준.Bronze.no10870

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {
    assertEquals(0, solution(0))
    assertEquals(1, solution(1))
    assertEquals(987, solution(16))
    assertEquals(1597, solution(17))
    assertEquals(2584, solution(18))
  }
}
