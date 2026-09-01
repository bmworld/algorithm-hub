package 프로그래머스.Lv0.소문자로바꾸기

import util.validate

class Solution {
  companion object {

    val UPPERCASE = 65..90
    const val dist = 32
  }

  fun solution(myString: String): String =
    String(CharArray(myString.length) {
      val code = myString[it].code
      (code + if (code in UPPERCASE) dist else 0).toChar()
    })
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.4MB)
 * 테스트 2 〉	통과 (0.02ms, 61.4MB)
 * 테스트 3 〉	통과 (0.01ms, 59.8MB)
 * 테스트 4 〉	통과 (0.02ms, 59.7MB)
 *
 * [RIVAL]
 * 테스트 1 〉	통과 (0.02ms, 60.8MB)
 * 테스트 2 〉	통과 (0.02ms, 59.9MB)
 * 테스트 3 〉	통과 (0.02ms, 60.6MB)
 * 테스트 4 〉	통과 (0.02ms, 60MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("aBcDeFg"), "abcdefg")
  validate(s.solution("aAa"), "aaa")
}
