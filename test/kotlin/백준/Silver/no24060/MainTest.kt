package 백준.Silver.no24060

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {
    assertEquals(3, solution(7, getArr("4 5 1 3 2")))
    assertEquals(-1, solution(13, getArr("4 5 1 3 2")))
  }

  fun getArr(s: String): IntArray = s.trim().split(" ").map { it.toInt() }.toIntArray()
}
