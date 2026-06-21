package 프로그래머스.Lv1.크기가작은부분문자열

import util.validate

class Solution {

  fun solution(t: String, p: String): Int {
    var ans = 0
    val len = p.length
    repeat(t.length - len + 1) { i ->
      for (j in 0 until len) {
        val tc = t[i + j].code
        val pc = p[j].code
        if (tc == pc) continue
        if (tc < pc) ans++
        return@repeat
      }
      ans++
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.10ms, 57.8MB)
 * 테스트 2 〉	통과 (0.31ms, 59.1MB)
 * 테스트 3 〉	통과 (0.33ms, 58.4MB)
 * 테스트 4 〉	통과 (0.17ms, 58.8MB)
 * 테스트 5 〉	통과 (0.07ms, 58.4MB)
 * 테스트 6 〉	통과 (0.34ms, 58.8MB)
 * 테스트 7 〉	통과 (0.33ms, 58.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(t: String, p: String): Int {
 *         return (0..t.length - p.length)
 *             .map{ t.substring(it until it + p.length) }
 *             .count { it <= p }
 *     }
 * }
 * 테스트 1 〉	통과 (11.16ms, 60.2MB)
 * 테스트 2 〉	통과 (12.83ms, 62.9MB)
 * 테스트 3 〉	통과 (13.02ms, 63.3MB)
 * 테스트 4 〉	통과 (10.84ms, 61.4MB)
 * 테스트 5 〉	통과 (14.81ms, 60.9MB)
 * 테스트 6 〉	통과 (12.28ms, 64MB)
 * 테스트 7 〉	통과 (12.21ms, 64.3MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("3141592", "271"), 2)
  validate(s.solution("500220839878", "7"), 8)
  validate(s.solution("10203", "15"), 3)
}
