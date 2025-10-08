package 백준.Silver.no9184

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {

    assertEquals("w(1, 1, 1) = 2", solution(1, 1, 1))
    assertEquals("w(2, 2, 2) = 4", solution(2, 2, 2))
    assertEquals("w(10, 4, 6) = 523", solution(10, 4, 6))
    assertEquals("w(20, 20, 21) = 1048576", solution(20, 20, 21))
  }
}
