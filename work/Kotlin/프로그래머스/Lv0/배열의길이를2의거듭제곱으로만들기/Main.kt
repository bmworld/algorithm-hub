package 프로그래머스.Lv0.배열의길이를2의거듭제곱으로만들기

import util.validate

class Solution {
  companion object {

    const val BIN_MAX_LEN = 32
  }

  fun solution(arr: IntArray): IntArray {
    val N = arr.size
    val ans = IntArray(
      1 shl (BIN_MAX_LEN - N.countLeadingZeroBits() - if (N.countOneBits() == 1) 1 else 0))
    System.arraycopy(arr, 0, ans, 0, N)
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 60.1MB)
 * 테스트 2 〉	통과 (0.01ms, 61.1MB)
 * 테스트 3 〉	통과 (0.02ms, 58.7MB)
 * 테스트 4 〉	통과 (0.01ms, 59.6MB)
 * 테스트 5 〉	통과 (0.01ms, 59.7MB)
 *
 * [RIVAL 1]
 * import kotlin.math.*
 * class Solution {
 *     fun solution(arr: IntArray): IntArray {
 *         val len = arr.size
 *         var n = 1
 *         while (n < len) { n*=2 }
 *         return arr + IntArray(n-len){ 0 }
 *     }
 * }
 * 테스트 1 〉	통과 (8.60ms, 62.8MB)
 * 테스트 2 〉	통과 (6.11ms, 62.8MB)
 * 테스트 3 〉	통과 (6.58ms, 62.3MB)
 * 테스트 4 〉	통과 (6.43ms, 63.6MB)
 * 테스트 5 〉	통과 (7.04ms, 64.9MB)
 *
 * [RIVAL 2]
 * import kotlin.math.*
 *
 * class Solution {
 *     fun solution(arr: IntArray): IntArray {
 *         return arr.copyOf(2.0.pow(ceil(ln(arr.size.toDouble()) / ln(2.0))).toInt())
 *     }
 * }
 * 테스트 1 〉	통과 (0.03ms, 59.2MB)
 * 테스트 2 〉	통과 (0.02ms, 58.8MB)
 * 테스트 3 〉	통과 (0.02ms, 60.4MB)
 * 테스트 4 〉	통과 (0.02ms, 60.8MB)
 * 테스트 5 〉	통과 (0.02ms, 61.2MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(1)), intArrayOf(1))
  validate(s.solution(intArrayOf(1, 3)), intArrayOf(1, 3))
  validate(s.solution(intArrayOf(1, 2, 3, 4)), intArrayOf(1, 2, 3, 4))
  validate(s.solution(intArrayOf(1, 2, 3, 4, 5)), intArrayOf(1, 2, 3, 4, 5, 0, 0, 0))
}
