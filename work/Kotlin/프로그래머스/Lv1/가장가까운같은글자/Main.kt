package 프로그래머스.Lv1.가장가까운같은글자

import util.validate

class Solution {

  val a = 97
  val EMPTY = -1
  val ALPHBETS = 26
  fun solution(s: String): IntArray {
    val ch = IntArray(ALPHBETS) { EMPTY }
    var ans = IntArray(s.length)
    for (cur in 0 until s.length) {
      val x = s[cur].code - a
      val prv = ch[x]
      ans[cur] = if (prv == EMPTY) EMPTY else cur - prv
      ch[x] = cur
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.2MB)
 * 테스트 2 〉	통과 (0.01ms, 58.3MB)
 * 테스트 3 〉	통과 (0.02ms, 59.5MB)
 * 테스트 4 〉	통과 (0.04ms, 59.6MB)
 * 테스트 5 〉	통과 (0.34ms, 59.8MB)
 * 테스트 6 〉	통과 (0.12ms, 59.1MB)
 * 테스트 7 〉	통과 (0.32ms, 60.4MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String): List<Int> {
 *         return s.withIndex().map { (i, c) -> s.slice(0 until i).lastIndexOf(c).let { if (it >= 0) i - it else -1 } }
 *     }
 * }
 * 테스트 1 〉	통과 (10.57ms, 60.5MB)
 * 테스트 2 〉	통과 (10.98ms, 62MB)
 * 테스트 3 〉	통과 (10.96ms, 60.7MB)
 * 테스트 4 〉	통과 (11.90ms, 61.8MB)
 * 테스트 5 〉	통과 (30.96ms, 106MB)
 * 테스트 6 〉	통과 (15.37ms, 71.5MB)
 * 테스트 7 〉	통과 (30.85ms, 106MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("banana"), intArrayOf(-1, -1, -1, 2, 2, 2))
  validate(s.solution("foobar"), intArrayOf(-1, -1, 1, -1, -1, -1))
}
