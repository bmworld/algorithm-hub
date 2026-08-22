package 프로그래머스.Lv0.대문자로바꾸기

import util.validate

class Solution {
  companion object {

    val UPPERCASE = 65..90
    const val CASE_DIFF = 32
  }

  fun solution(myString: String): String {
    val ans = CharArray(myString.length)
    for (i in myString.indices) {
      val x = myString[i]
      ans[i] = if (x.code in UPPERCASE) x else x - CASE_DIFF
    }
    return String(ans)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 57.8MB)
 * 테스트 2 〉	통과 (0.02ms, 58.5MB)
 * 테스트 3 〉	통과 (0.02ms, 60.2MB)
 * 테스트 4 〉	통과 (0.02ms, 59MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(myString: String) = myString.uppercase()
 * }
 * 테스트 1 〉	통과 (0.02ms, 60.5MB)
 * 테스트 2 〉	통과 (0.02ms, 60.3MB)
 * 테스트 3 〉	통과 (0.02ms, 58.3MB)
 * 테스트 4 〉	통과 (0.02ms, 59.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("aBcDeFg"), "ABCDEFG")
}
