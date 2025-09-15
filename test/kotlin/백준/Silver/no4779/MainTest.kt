package 백준.Silver.no4779

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {
    assertEquals("-", solution(0))
    assertEquals("- -", solution(1))
    assertEquals("- -   - -", solution(2))
    assertEquals("- -   - -         - -   - -", solution(3))
    assertEquals(
        "- -   - -         - -   - -                           - -   - -         - -   - -                                                                                 - -   - -         - -   - -                           - -   - -         - -   - -",
        solution(5),
    )
  }
}
