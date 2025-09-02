package 백준.Bronze.no19532

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionTest {

  @Test
  fun solution() {
    assertEquals("2 -1", solution(getArr("1 3 -1 4 1 7")))
    assertEquals("-1 2", solution(getArr("2 5 8 3 -4 -11")))
  }

  private fun getArr(v: String): IntArray {
    return v.trim().split(" ").map { it.toInt() }.toIntArray()
  }
}
