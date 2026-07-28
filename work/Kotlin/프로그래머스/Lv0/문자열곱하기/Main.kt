package 프로그래머스.Lv0.문자열곱하기

import util.validate

class Solution {

  fun solution(my_string: String, k: Int): String {
    val N = my_string.length
    return String(CharArray(N * k) { my_string[it % N] })
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.2MB)
 * 테스트 2 〉	통과 (0.02ms, 60.9MB)
 * 테스트 3 〉	통과 (0.02ms, 60.2MB)
 * 테스트 4 〉	통과 (0.03ms, 59.5MB)
 * 테스트 5 〉	통과 (0.04ms, 60.5MB)
 * 테스트 6 〉	통과 (0.01ms, 60.3MB)
 * 테스트 7 〉	통과 (0.03ms, 58.6MB)
 * 테스트 8 〉	통과 (0.04ms, 59.9MB)
 *
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(my_string: String, k: Int): String {
 *         return my_string.repeat(k)
 *     }
 * }
 * 테스트 1 〉	통과 (4.57ms, 61.2MB)
 * 테스트 2 〉	통과 (4.48ms, 60.4MB)
 * 테스트 3 〉	통과 (4.98ms, 60.9MB)
 * 테스트 4 〉	통과 (4.11ms, 61.2MB)
 * 테스트 5 〉	통과 (4.24ms, 61.1MB)
 * 테스트 6 〉	통과 (4.16ms, 61.7MB)
 * 테스트 7 〉	통과 (4.52ms, 60.9MB)
 * 테스트 8 〉	통과 (4.06ms, 60.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution("str", 3),
    "strstrstr",
  )

  validate(
    s.solution("love", 1), "love",
  )

  validate(
    s.solution("love", 10), "lovelovelovelovelovelovelovelovelovelove",
  )
}
