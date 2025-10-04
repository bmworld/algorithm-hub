package 백준.Silver.no14889

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {

    assertEquals(
        0,
        solution(getArr("0 1 2 3\n" + "4 0 5 6\n" + "7 1 0 2\n" + "3 4 5 0")),
    )

    assertEquals(
        2,
        solution(
            getArr(
                "0 1 2 3 4 5\n" +
                    "1 0 2 3 4 5\n" +
                    "1 2 0 3 4 5\n" +
                    "1 2 3 0 4 5\n" +
                    "1 2 3 4 0 5\n" +
                    "1 2 3 4 5 0"
            )
        ),
    )

    assertEquals(
        1,
        solution(
            getArr(
                "0 5 4 5 4 5 4 5\n" +
                    "4 0 5 1 2 3 4 5\n" +
                    "9 8 0 1 2 3 1 2\n" +
                    "9 9 9 0 9 9 9 9\n" +
                    "1 1 1 1 0 1 1 1\n" +
                    "8 7 6 5 4 0 3 2\n" +
                    "9 1 9 1 9 1 0 9\n" +
                    "6 5 4 3 2 1 9 0"
            )
        ),
    )
  }

  fun getArr(s: String): Array<IntArray> {
    return s.trim()
        .split("\n")
        .map { it.split(" ").map { it.toInt() }.toIntArray() }
        .toTypedArray<IntArray>()
  }
}
