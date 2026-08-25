package 프로그래머스.Lv0.특정한문자를대문자로바꾸기

import util.validate

class Solution {
  companion object {

    const val DISTANCE = 32
  }

  fun solution(my_string: String, alp: String): String {
    val x = alp[0]
    val ans = CharArray(my_string.length) {
      var char = my_string[it]
      if (char == x) char -= DISTANCE
      char
    }

    return String(ans)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.9MB)
 * 테스트 2 〉	통과 (0.01ms, 60.2MB)
 * 테스트 3 〉	통과 (0.02ms, 60.4MB)
 * 테스트 4 〉	통과 (0.04ms, 60.9MB)
 * 테스트 5 〉	통과 (0.02ms, 60.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(myString: String, alp: String): String {
 *         return myString.replace(alp, alp.uppercase())
 *     }
 * }
 * 테스트 1 〉	통과 (5.22ms, 60.8MB)
 * 테스트 2 〉	통과 (6.13ms, 60.5MB)
 * 테스트 3 〉	통과 (4.51ms, 60.2MB)
 * 테스트 4 〉	통과 (6.68ms, 60.5MB)
 * 테스트 5 〉	통과 (6.08ms, 60.7MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution("programmers", "p"),
    "Programmers"
  )

  validate(
    s.solution("programmers", "m"),
    "prograMMers"
  )
}
