package 백준.Silver.no1149

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {
    assertEquals(3, solution(getArr("1 100 100\n" + "100 1 100\n" + "100 100 1")))
    assertEquals(96, solution(getArr("26 40 83\n" + "49 60 57\n" + "13 89 99")))
    assertEquals(102, solution(getArr("1 100 100\n" + "100 100 100\n" + "1 100 100")))
    assertEquals(
        208,
        solution(
            getArr(
                "30 19 5\n" + "64 77 64\n" + "15 19 97\n" + "4 71 57\n" + "90 86 84\n" + "93 32 91"
            )
        ),
    )
    assertEquals(
        254,
        solution(
            getArr(
                "71 39 44\n" +
                    "32 83 55\n" +
                    "51 37 63\n" +
                    "89 29 100\n" +
                    "83 58 11\n" +
                    "65 13 15\n" +
                    "47 25 29\n" +
                    "60 66 19"
            )
        ),
    )
  }

  private fun getArr(s: String): Array<Price> {
    return s.trim()
        .split("\n")
        .map {
          val split = it.split(' ')
          Price(split[0].toInt(), split[1].toInt(), split[2].toInt())
        }
        .toTypedArray()
  }
}
