package 백준.Silver.no15649

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {
    assertEquals("1\n" + "2\n" + "3", solution(3, 1))
    assertEquals(
        "1 2\n" +
            "1 3\n" +
            "1 4\n" +
            "2 1\n" +
            "2 3\n" +
            "2 4\n" +
            "3 1\n" +
            "3 2\n" +
            "3 4\n" +
            "4 1\n" +
            "4 2\n" +
            "4 3",
        solution(4, 2),
    )
    assertEquals(
        "1 2 3 4\n" +
            "1 2 4 3\n" +
            "1 3 2 4\n" +
            "1 3 4 2\n" +
            "1 4 2 3\n" +
            "1 4 3 2\n" +
            "2 1 3 4\n" +
            "2 1 4 3\n" +
            "2 3 1 4\n" +
            "2 3 4 1\n" +
            "2 4 1 3\n" +
            "2 4 3 1\n" +
            "3 1 2 4\n" +
            "3 1 4 2\n" +
            "3 2 1 4\n" +
            "3 2 4 1\n" +
            "3 4 1 2\n" +
            "3 4 2 1\n" +
            "4 1 2 3\n" +
            "4 1 3 2\n" +
            "4 2 1 3\n" +
            "4 2 3 1\n" +
            "4 3 1 2\n" +
            "4 3 2 1",
        solution(4, 4),
    )
  }
}
