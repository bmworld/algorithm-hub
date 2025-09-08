package 백준.Silver.no12789

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {

    assertEquals("Nice", solution(getArr("5 4 1 3 2")))
  }

  fun getArr(s: String): IntArray {
    return s.trim().split(" ").map { it.toInt() }.toIntArray()
  }
}
