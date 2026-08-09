package 프로그래머스.Lv0.부분문자열

import util.validate

class Solution {

  fun solution(str1: String, str2: String): Int {
    val N = str1.length
    val M = str2.length
    val times = M - N + 1
    l@ for (i in 0 until times) {
      if (str2[i] != str1[0]) continue
      for (j in 1 until N) {
        if (str2[i + j] != str1[j]) continue@l
      }
      return 1
    }
    return 0
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 59.6MB)
 * 테스트 2 〉	통과 (0.01ms, 57.4MB)
 * 테스트 3 〉	통과 (0.01ms, 59.5MB)
 * 테스트 4 〉	통과 (0.01ms, 59.8MB)
 * 테스트 5 〉	통과 (0.01ms, 59.7MB)
 * 테스트 6 〉	통과 (0.01ms, 59.9MB)
 * 테스트 7 〉	통과 (0.01ms, 60.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(str1: String, str2: String) = if (str2.contains(str1)) 1 else 0
 * }
 * 테스트 1 〉	통과 (4.70ms, 60.7MB)
 * 테스트 2 〉	통과 (4.45ms, 61.2MB)
 * 테스트 3 〉	통과 (4.60ms, 60.5MB)
 * 테스트 4 〉	통과 (4.49ms, 60.3MB)
 * 테스트 5 〉	통과 (4.53ms, 61.3MB)
 * 테스트 6 〉	통과 (4.32ms, 60.6MB)
 * 테스트 7 〉	통과 (4.37ms, 60.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("abc", "aabcc"), 1)
  validate(s.solution("abc", "aabbcc"), 0)
  validate(s.solution("abc", "aababc"), 1)
  validate(s.solution("abc", "aabaac"), 0)
}
