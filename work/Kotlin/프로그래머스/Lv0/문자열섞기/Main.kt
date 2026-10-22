package 프로그래머스.Lv0.문자열섞기

import util.validate

class Solution {

  fun solution(str1: String, str2: String): String =
    String(CharArray(str1.length + str2.length) {
      val i = it / 2
      val j = it % 2
      if (j == 0) str1[i] else str2[i]
    })
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.1MB)
 * 테스트 2 〉	통과 (0.01ms, 57.9MB)
 * 테스트 3 〉	통과 (0.02ms, 58.6MB)
 * 테스트 4 〉	통과 (0.02ms, 59.6MB)
 * 테스트 5 〉	통과 (0.01ms, 60.1MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(str1: String, str2: String) = str1.zip(str2).joinToString("") { (a, b) -> "$a$b" }
 * }
 * 테스트 1 〉	통과 (13.09ms, 62.2MB)
 * 테스트 2 〉	통과 (12.90ms, 61.8MB)
 * 테스트 3 〉	통과 (12.98ms, 60.5MB)
 * 테스트 4 〉	통과 (12.92ms, 60.4MB)
 * 테스트 5 〉	통과 (11.87ms, 62.1MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("aaaaa", "bbbbb"), "ababababab")
  validate(s.solution("a", "k"), "ak")
}
