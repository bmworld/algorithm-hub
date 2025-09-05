package 백준.Silver.no18870

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionTest {

  @Test
  fun solution() {

    assertEquals("2 3 0 3 1", solution(getArr("2 4 -10 4 -9")))
    assertEquals("1 0 1 0 1 0", solution(getArr("1000 999 1000 999 1000 999")))
  }

  fun getArr(v: String): IntArray {
    return v.trim().split(" ").map { it.toInt() }.toIntArray()
  }
}
