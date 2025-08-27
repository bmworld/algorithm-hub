package 백준.Silver.no1316

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {
        assertEquals(3, solution(getArr("happy\n" + "new\n" + "year")))
    assertEquals(1, solution(getArr("aba\n" + "abab\n" + "abcabc\n" + "a")))
    assertEquals(4, solution(getArr("ab\n" + "aa\n" + "aca\n" + "ba\n" + "bb")))
    assertEquals(
        2,
        solution(
            getArr(
                    "aaa\n" +
                    "aaazbz\n" +
                    "babb\n" +
                    "aazz\n" +
                    "azbz\n" +
                    "aabbaa\n" +
                    "abacc\n" +
                    "aba\n" +
                    "zzaz"
            )
        ),
    )
  }

  fun getArr(s: String): Array<String> {

    return s.trim().lines().toTypedArray<String>()
  }
}
