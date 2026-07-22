package 프로그래머스.Lv2.k진수에서소수개수구하기

import util.validate

class Solution {

  companion object {

    const val MAX_LEN = 15
  }

  fun solution(n: Int, k: Int): Int {
    val tmp = IntArray(MAX_LEN)
    var len = 0
    var x = n
    while (x > 0) {
      tmp[len++] = x % k
      x /= k
    }

    var ans = 0
    var num = 0
    repeat(len) {
      val i = len - (it + 1)
      val t = tmp[i]

      if (t != 0) num = num * 10 + t

      if (t == 0 || i == 0) {
        if (isPrime(num)) ans++
        num = 0
      }
    }

    return ans
  }

  fun isPrime(x: Int): Boolean {
    if (x <= 1) return false
    if (x == 2 || x == 3) return true
    if (x % 2 == 0 || x % 3 == 0) return false

    var d = 5
    while (d <= x / d) {
      if (x % d == 0 || x % (d + 2) == 0) return false
      d += 6
    }

    return true
  }
}

/**
 * ```
 * [ME]
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1, 3), 0)
  validate(s.solution(1, 10), 0)
  validate(s.solution(2, 10), 0)
  validate(s.solution(437674, 3), 3)
  validate(s.solution(110011, 10), 2)
  validate(s.solution(1_000_000, 3), 2)
  validate(s.solution(1_000_000, 9), 0)
}
