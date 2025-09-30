package 백준.Silver.no14888

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {
    assertEquals("30\n30", solution(getArr("5 6"), 0, 0, 1, 0))
    assertEquals("35\n17", solution(getArr("3 4 5"), 1, 0, 1, 0))
    assertEquals("54\n-24", solution(getArr("1 2 3 4 5 6"), 2, 1, 1, 1))
  }

  fun getArr(v: String): IntArray {
    return v.trim().split(" ").map { it.toInt() }.toIntArray()
  }
}
