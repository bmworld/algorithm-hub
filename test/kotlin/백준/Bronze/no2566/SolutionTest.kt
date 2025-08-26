package 백준.Bronze.no2566

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.StringTokenizer

class SolutionTest {

  @Test fun solution() {

    assertEquals("90\n" + "5 7", solution(getArr("3 23 85 34 17 74 25 52 65\n" + "10 7 39 42 88 52 14 72 63\n" + "87 42 18 78 53 45 18 84 53\n" + "34 28 64 85 12 16 75 36 55\n" + "21 77 45 35 28 75 90 76 1\n" + "25 87 65 15 28 11 37 28 74\n" + "65 27 75 41 7 89 78 64 39\n" + "47 47 70 45 23 65 3 41 44\n" + "87 13 82 38 31 12 29 29 80")))
  }


  fun getArr(s: String): Array<IntArray> {
    val lines = s.trim()
        .split("\n")

    val size = 9
    val arr = Array(size) { rowIdx ->
      val st = StringTokenizer(lines[rowIdx]," ")
      IntArray(size) {st.nextToken().toInt()}
    }


    return arr

  }
}
