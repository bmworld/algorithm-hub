package 백준.Gold.no11729

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {
    assertEquals("1\n" + "1 3", solution(1))
    assertEquals("3\n" + "1 2\n" + "1 3\n" + "2 3", solution(2))
    assertEquals(
        "7\n" + "1 3\n" + "1 2\n" + "3 2\n" + "1 3\n" + "2 1\n" + "2 3\n" + "1 3",
        solution(3),
    )

    assertEquals(
        "15\n" +
            "1 2\n" +
            "1 3\n" +
            "2 3\n" +
            "1 2\n" +
            "3 1\n" +
            "3 2\n" +
            "1 2\n" +
            "1 3\n" +
            "2 3\n" +
            "2 1\n" +
            "3 1\n" +
            "2 3\n" +
            "1 2\n" +
            "1 3\n" +
            "2 3",
        solution(4),
    )
  }
}
