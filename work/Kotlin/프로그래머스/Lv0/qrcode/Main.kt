package 프로그래머스.Lv0.qrcode

import util.validate

class Solution {

  fun solution(q: Int, r: Int, code: String): String {
    val N = code.length
    val g = N / q
    return String(CharArray(g + if (g * q + r < N) 1 else 0) { code[r + it * q] })
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 58.4MB)
 * 테스트 2 〉	통과 (0.02ms, 58.1MB)
 * 테스트 3 〉	통과 (0.02ms, 58.9MB)
 * 테스트 4 〉	통과 (0.02ms, 58.5MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(q: Int, r: Int, code: String): String {
 *         return code.indices.filter { it % q == r }.joinToString("") { code[it].toString() }
 *     }
 * }
 * 테스트 1 〉	통과 (14.85ms, 61.5MB)
 * 테스트 2 〉	통과 (10.84ms, 61.2MB)
 * 테스트 3 〉	통과 (12.46ms, 61.6MB)
 * 테스트 4 〉	통과 (10.75ms, 62.5MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(q: Int, r: Int, code: String) =
 *         (r until code.length step q).joinToString("") { code[it].toString() }
 * }
 * 테스트 1 〉	통과 (11.37ms, 62.4MB)
 * 테스트 2 〉	통과 (14.12ms, 60.6MB)
 * 테스트 3 〉	통과 (11.29ms, 62.8MB)
 * 테스트 4 〉	통과 (10.68ms, 62.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(3, 1, "qjnwezgrpirldywt"), "jerry")
  validate(s.solution(1, 0, "programmers"), "programmers")
  validate(s.solution(3, 0, "abcd"), "ad")
  validate(s.solution(3, 1, "abcd"), "b")
  validate(s.solution(3, 2, "abcd"), "c")
}
