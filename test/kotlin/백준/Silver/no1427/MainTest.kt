package 백준.Silver.no1427

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {

    assertEquals(4321, solution("2143"))
    assertEquals(64321, solution("61423"))
    assertEquals(965310000, solution("500613009"))
  }
}
