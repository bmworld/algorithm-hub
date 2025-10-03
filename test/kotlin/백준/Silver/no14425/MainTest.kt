package 백준.Silver.no14425

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.getStringArr
import util.getStringSet

class MainTest {

  @Test
  fun solution() {

    assertEquals(
        4,
        solution(
            getStringSet(
                "baekjoononlinejudge\n" +
                    "startlink\n" +
                    "codeplus\n" +
                    "sundaycoding\n" +
                    "codingsh"
            ),
            getStringArr(
                "baekjoon\n" +
                    "codeplus\n" +
                    "codeminus\n" +
                    "startlink\n" +
                    "starlink\n" +
                    "sundaycoding\n" +
                    "codingsh\n" +
                    "codinghs\n" +
                    "sondaycoding\n" +
                    "startrink\n" +
                    "icerink\n"
            ),
        ),
    )
  }
}
