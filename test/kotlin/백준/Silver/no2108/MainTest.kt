package 백준.Silver.no2108

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import solution

class MainTest {

  @Test
  fun my() {
    assertEquals(
        "2\n" + "2\n" + "1\n" + "10",
        solution(getArr("1\n" + "3\n" + "8\n" + "-2\n" + "2")),
    )

    assertEquals(
        "4000\n" + "4000\n" + "4000\n" + "0",
        solution(getArr("4000")),
    )

    assertEquals(
        "0\n" + "0\n" + "0\n" + "1",
        solution(getArr("0\n" + "0\n" + "-1")),
    )

    assertEquals(
        "-2\n" + "-2\n" + "-1\n" + "2",
        solution(getArr("-1\n" + "-2\n" + "-3\n" + "-1\n" + "-2")),
    )

    assertEquals(
        "2\n" + "2\n" + "1\n" + "10",
        solution(getArr("1\n" + "3\n" + "8\n" + "-2\n" + "2")),
    )

    assertEquals(
        "1\n" + "0\n" + "0\n" + "6",
        solution(getArr("-1\n" + "0\n" + "1\n" + "-1\n" + "0\n" + "1\n" + "5")),
    )
  }

  private fun getArr(s: String): IntArray {
    return s.trim().split("\n").map { it.toInt() }.toIntArray()
  }
}
