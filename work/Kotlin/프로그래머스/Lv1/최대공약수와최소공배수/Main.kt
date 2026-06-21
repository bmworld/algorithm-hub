package 프로그래머스.Lv1.최대공약수와최소공배수

import util.validate

class Solution {

  fun solution(n: Int, m: Int): IntArray {
    val gcd = getGCD(n, m)
    return intArrayOf(gcd, n * m / gcd)
  }

  private fun getGCD(a: Int, b: Int): Int = if (b == 0) a else getGCD(b, a % b)
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 57.6MB)
 * 테스트 2 〉	통과 (0.01ms, 59MB)
 * 테스트 3 〉	통과 (0.01ms, 59.8MB)
 * 테스트 4 〉	통과 (0.01ms, 59.9MB)
 * 테스트 5 〉	통과 (0.01ms, 58.1MB)
 * 테스트 6 〉	통과 (0.01ms, 58.5MB)
 * 테스트 7 〉	통과 (0.01ms, 59MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(3, 12), intArrayOf(3, 12))
  validate(s.solution(2, 5), intArrayOf(1, 10))
}
