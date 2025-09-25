package 백준.Silver.no15652

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {
    assertEquals("1\n" + "2\n" + "3", solution(3, 1))
    assertEquals(
        "1 1\n" +
            "1 2\n" +
            "1 3\n" +
            "1 4\n" +
            "2 2\n" +
            "2 3\n" +
            "2 4\n" +
            "3 3\n" +
            "3 4\n" +
            "4 4",
        solution(4, 2),
    )
  }
}
