package 프로그래머스.Lv0.세로읽기

import util.validate

class Solution {

  fun solution(str: String, C: Int, c: Int): String {
    val R = (str.length + C - 1) / C
    return String(CharArray(R) { str[it * C + (c - 1)] })
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.5MB)
 * 테스트 2 〉	통과 (0.02ms, 59.6MB)
 * 테스트 3 〉	통과 (0.02ms, 58.1MB)
 * 테스트 4 〉	통과 (0.01ms, 60.5MB)
 * 테스트 5 〉	통과 (0.02ms, 59.8MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(my_string: String, m: Int, c: Int): String {
 *         return my_string.chunked(m).map { it[c - 1] }.joinToString("")
 *     }
 * }
 * 테스트 1 〉	통과 (9.36ms, 61.3MB)
 * 테스트 2 〉	통과 (9.94ms, 61.1MB)
 * 테스트 3 〉	통과 (12.56ms, 60.9MB)
 * 테스트 4 〉	통과 (9.51ms, 61.1MB)
 * 테스트 5 〉	통과 (9.03ms, 60.6MB)
 *
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("ihrhbakrfpndopljhygc", 4, 2), "happy")
  validate(s.solution("programmers", 1, 1), "programmers")
  validate(s.solution("aHabObcCc", 3, 2), "HOC")
}
