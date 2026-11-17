package 프로그래머스.Lv0.두수의합

import util.validate

class Solution {
  companion object {

    const val ZERO = 48
  }

  fun solution(a: String, b: String): String {
    val al = a.length
    val bl = b.length
    val size = maxOf(al, bl) + 1
    val tmp = CharArray(size)

    var carry = 0
    for (i in 0 until size) {
      var sum = carry
      val ai = al - (i + 1)
      if (ai >= 0) sum += a[ai].code - ZERO
      val bi = bl - (i + 1)
      if (bi >= 0) sum += b[bi].code - ZERO

      val d = if (sum >= 10) {
        carry = 1
        sum - 10
      } else {
        carry = 0
        sum
      }

      tmp[size - (i + 1)] = (d + ZERO).toChar()
    }

    return String(
      if (tmp[0] != '0') tmp
      else tmp.copyOfRange(1, size)
    )
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (7.01ms, 62.7MB)
 * 테스트 2 〉	통과 (6.21ms, 63.4MB)
 * 테스트 3 〉	통과 (9.12ms, 62.9MB)
 * 테스트 4 〉	통과 (8.16ms, 64.2MB)
 * 테스트 5 〉	통과 (10.42ms, 64.8MB)
 * 테스트 6 〉	통과 (6.35ms, 61.7MB)
 * 테스트 7 〉	통과 (6.35ms, 61.8MB)
 * 테스트 8 〉	통과 (6.82ms, 63.4MB)
 *
 * [RIVAL 1]
 * import java.math.*;
 *
 * class Solution {
 *     fun solution(a: String, b: String) = BigInteger(a).add(BigInteger(b)).toString()
 * }
 * 테스트 1 〉	통과 (0.43ms, 59.2MB)
 * 테스트 2 〉	통과 (1.86ms, 59.4MB)
 * 테스트 3 〉	통과 (12.91ms, 61.9MB)
 * 테스트 4 〉	통과 (40.95ms, 69.4MB)
 * 테스트 5 〉	통과 (155.18ms, 98.7MB)
 * 테스트 6 〉	통과 (0.51ms, 59.5MB)
 * 테스트 7 〉	통과 (2.07ms, 57.5MB)
 * 테스트 8 〉	통과 (14.32ms, 60.4MB)
 *
 * [RIVAL 2]
 * import java.math.*
 *
 * class Solution {
 *     fun solution(a: String, b: String): String {
 *         return BigDecimal(a).plus(BigDecimal(b)).toString()
 *     }
 * }
 * 테스트 1 〉	통과 (9.35ms, 63.3MB)
 * 테스트 2 〉	통과 (10.27ms, 63.2MB)
 * 테스트 3 〉	통과 (19.73ms, 63.2MB)
 * 테스트 4 〉	통과 (47.30ms, 73.5MB)
 * 테스트 5 〉	통과 (161.52ms, 102MB)
 * 테스트 6 〉	통과 (9.71ms, 62.9MB)
 * 테스트 7 〉	통과 (11.67ms, 62.2MB)
 * 테스트 8 〉	통과 (20.02ms, 64.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("582", "734"), "1316")
  validate(s.solution("18446744073709551615", "287346502836570928366"), "305793246910280479981")
  validate(s.solution("0", "0"), "0")
  validate(s.solution("9999", "1"), "10000")
}
