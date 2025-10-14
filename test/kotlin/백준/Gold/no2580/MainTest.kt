package 백준.Gold.no2580

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {
    assertEquals(
        "1 3 5 4 6 9 2 7 8\n" +
            "7 8 2 1 3 5 6 4 9\n" +
            "4 6 9 2 7 8 1 3 5\n" +
            "3 2 1 5 4 6 8 9 7\n" +
            "8 7 4 9 1 3 5 2 6\n" +
            "5 9 6 8 2 7 4 1 3\n" +
            "9 1 7 6 5 2 3 8 4\n" +
            "6 4 3 7 8 1 9 5 2\n" +
            "2 5 8 3 9 4 7 6 1",
        solution(
            getArr(
                "0 3 5 4 6 9 2 7 8\n" +
                    "7 8 2 1 0 5 6 0 9\n" +
                    "0 6 0 2 7 8 1 3 5\n" +
                    "3 2 1 0 4 6 8 9 7\n" +
                    "8 0 4 9 1 3 5 0 6\n" +
                    "5 9 6 8 2 0 4 1 3\n" +
                    "9 1 7 6 5 2 0 8 0\n" +
                    "6 0 3 7 0 1 9 5 2\n" +
                    "2 5 8 3 9 4 7 6 0"
            )
        ),
    )

    assertEquals(
        "4 8 3 9 2 1 6 5 7\n" +
            "9 6 7 3 4 5 8 2 1\n" +
            "2 5 1 8 7 6 4 9 3\n" +
            "5 4 8 1 3 2 9 7 6\n" +
            "7 2 9 5 6 4 1 3 8\n" +
            "1 3 6 7 9 8 2 4 5\n" +
            "3 7 2 6 8 9 5 1 4\n" +
            "8 1 4 2 5 3 7 6 9\n" +
            "6 9 5 4 1 7 3 8 2",
        solution(
            getArr(
                "0 0 3 0 2 0 6 0 0\n" +
                    "9 0 0 3 0 5 0 0 1\n" +
                    "0 0 1 8 0 6 4 0 0\n" +
                    "0 0 8 1 0 2 9 0 0\n" +
                    "7 0 0 0 0 0 0 0 8\n" +
                    "0 0 6 7 0 8 2 0 0\n" +
                    "0 0 2 6 0 9 5 0 0\n" +
                    "8 0 0 2 0 3 0 0 9\n" +
                    "0 0 5 0 1 0 3 0 0"
            )
        ),
    )
  }

  fun getArr(s: String): Array<IntArray> {
    return s.trim()
        .lines()
        .map { line -> line.split(" ").map { it.toInt() }.toIntArray() }
        .toTypedArray()
  }
}
