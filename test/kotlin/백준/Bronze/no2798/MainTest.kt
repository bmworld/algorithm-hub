package 백준.Bronze.no2798

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MainTest {

  @Test fun solution() {

    assertEquals(21, solution(getArr("5 6 7 8 9"), 21))
    assertEquals(497, solution(getArr("93 181 245 214 315 36 185 138 216 295"), 500))


  }

  fun getArr(s: String): Array<Int> {

    return s.trim()
        .split(" ")
        .map { it.toInt() }
        .toTypedArray()
  }

}
