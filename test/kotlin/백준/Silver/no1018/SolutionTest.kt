package 백준.Silver.no1018

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionTest {

  @Test
  fun solution() {

    assertEquals(
      1,
      solution(
        getArr(
          "WBWBWBWB\n" +
              "BWBWBWBW\n" +
              "WBWBWBWB\n" +
              "BWBBBWBW\n" +
              "WBWBWBWB\n" +
              "BWBWBWBW\n" +
              "WBWBWBWB\n" +
              "BWBWBWBW"
        )
      ),
    )
    assertEquals(
      12,
      solution(
        getArr(
          "BBBBBBBBWBWBW\n" +
              "BBBBBBBBBWBWB\n" +
              "BBBBBBBBWBWBW\n" +
              "BBBBBBBBBWBWB\n" +
              "BBBBBBBBWBWBW\n" +
              "BBBBBBBBBWBWB\n" +
              "BBBBBBBBWBWBW\n" +
              "BBBBBBBBBWBWB\n" +
              "WWWWWWWWWWBWB\n" +
              "WWWWWWWWWWBWB"
        )
      ),
    )

    assertEquals(
      0,
      solution(
        getArr(
          "BWBWBWBW\n" +
              "WBWBWBWB\n" +
              "BWBWBWBW\n" +
              "WBWBWBWB\n" +
              "BWBWBWBW\n" +
              "WBWBWBWB\n" +
              "BWBWBWBW\n" +
              "WBWBWBWB"
        )
      ),
    )

    assertEquals(
      31,
      solution(
        getArr(
          "BBBBBBBBBBBBBBBBBBBBBBB\n" +
              "BBBBBBBBBBBBBBBBBBBBBBB\n" +
              "BBBBBBBBBBBBBBBBBBBBBBB\n" +
              "BBBBBBBBBBBBBBBBBBBBBBB\n" +
              "BBBBBBBBBBBBBBBBBBBBBBB\n" +
              "BBBBBBBBBBBBBBBBBBBBBBB\n" +
              "BBBBBBBBBBBBBBBBBBBBBBB\n" +
              "BBBBBBBBBBBBBBBBBBBBBBB\n" +
              "BBBBBBBBBBBBBBBBBBBBBBW"
        )
      ),
    )

    assertEquals(
      0,
      solution(
        getArr(
          "BBBBBBBBBB\n" +
              "BBWBWBWBWB\n" +
              "BWBWBWBWBB\n" +
              "BBWBWBWBWB\n" +
              "BWBWBWBWBB\n" +
              "BBWBWBWBWB\n" +
              "BWBWBWBWBB\n" +
              "BBWBWBWBWB\n" +
              "BWBWBWBWBB\n" +
              "BBBBBBBBBB"
        )
      ),
    )

    assertEquals(
      2,
      solution(
        getArr(
          "WBWBWBWB\n" +
              "BWBWBWBW\n" +
              "WBWBWBWB\n" +
              "BWBBBWBW\n" +
              "WBWBWBWB\n" +
              "BWBWBWBW\n" +
              "WBWBWWWB\n" +
              "BWBWBWBW"
        )
      ),
    )

    assertEquals(
      15,
      solution(
        getArr(
          "BWWBWWBWWBWW\n" +
              "BWWBWBBWWBWW\n" +
              "WBWWBWBBWWBW\n" +
              "BWWBWBBWWBWW\n" +
              "WBWWBWBBWWBW\n" +
              "BWWBWBBWWBWW\n" +
              "WBWWBWBBWWBW\n" +
              "BWWBWBWWWBWW\n" +
              "WBWWBWBBWWBW\n" +
              "BWWBWBBWWBWW\n" +
              "WBWWBWBBWWBW"
        )
      ),
    )
  }

  fun getArr(s: String): Array<List<Int>> {
    return s.trim()
        .lines()
        .map { line -> line.trim().map { ch -> if (ch == 'W') 1 else 0 } }
        .toTypedArray()

  }
}
