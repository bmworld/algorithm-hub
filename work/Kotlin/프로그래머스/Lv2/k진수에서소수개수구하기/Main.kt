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
    var num = 0L
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

  fun isPrime(x: Long): Boolean {
    if (x <= 1) return false
    if (x == 2L || x == 3L) return true
    if (x % 2 == 0L || x % 3 == 0L) return false

    var d = 5
    while (d <= x / d) {
      if (x % d == 0L || x % (d + 2) == 0L) return false
      d += 6
    }

    return true
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (2.68ms, 59.8MB)
 * 테스트 2 〉	통과 (0.01ms, 60.6MB)
 * 테스트 3 〉	통과 (0.02ms, 60.4MB)
 * 테스트 4 〉	통과 (0.01ms, 60.1MB)
 * 테스트 5 〉	통과 (0.01ms, 61MB)
 * 테스트 6 〉	통과 (0.01ms, 59.8MB)
 * 테스트 7 〉	통과 (0.01ms, 60.4MB)
 * 테스트 8 〉	통과 (0.01ms, 60.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import java.math.*
 * class Solution {
 *
 *
 *
 *     fun solution(n: Int, k: Int): Int {
 *         var answer: Int =0
 *         val newN = n.toString(k).split("0")
 *         for(i in newN) {
 *             if(i == "" || i == "0" || i == "1") continue
 *             if(BigInteger(i).isProbablePrime(1)) answer ++
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (8.27ms, 62.1MB)
 * 테스트 2 〉	통과 (8.23ms, 62.6MB)
 * 테스트 3 〉	통과 (5.32ms, 61.1MB)
 * 테스트 4 〉	통과 (5.61ms, 61.1MB)
 * 테스트 5 〉	통과 (5.39ms, 61.5MB)
 * 테스트 6 〉	통과 (5.39ms, 60.1MB)
 * 테스트 7 〉	통과 (5.58ms, 60.8MB)
 * 테스트 8 〉	통과 (5.34ms, 61.7MB)
 *
 * [RIVAL 2]
 * import kotlin.math.sqrt
 * import java.math.BigInteger
 *
 * class Solution {
 *     fun solution(n: Int, k: Int): Int {
 *         val s = n.toString(k)
 *         return s.split("0")
 *             .filter { p ->
 *                 p.isNotEmpty() // swift에서는 없을수도 있음 체크
 *                         && p != "1"
 *                         && isPrimeNum(p.toBigInteger())
 *                         && (s.contains("0${p}0")
 *                         || s.contains("${p}0")
 *                         || s.contains("0${p}")
 *                         || s.contains(p))
 *             }
 *             .size
 *     }
 *
 *     private fun isPrimeNum(num: BigInteger): Boolean {
 *         var i = 2
 *
 *         while (i <= sqrt(num.toDouble())) {
 *             if (num % i.toBigInteger() == BigInteger.ZERO) return false
 *             i++
 *         }
 *         return true
 *     }
 * }
 * 테스트 1 〉	통과 (42.30ms, 107MB)
 * 테스트 2 〉	통과 (15.99ms, 60.7MB)
 * 테스트 3 〉	통과 (8.18ms, 61.7MB)
 * 테스트 4 〉	통과 (8.39ms, 60.5MB)
 * 테스트 5 〉	통과 (7.19ms, 61.5MB)
 * 테스트 6 〉	통과 (8.37ms, 61.4MB)
 * 테스트 7 〉	통과 (7.59ms, 61.9MB)
 * 테스트 8 〉	통과 (7.32ms, 60.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(305013015, 10), 3)
  validate(s.solution(160170190, 10), 2)
  validate(s.solution(23025, 10), 1)
  validate(s.solution(111, 10), 0)
  validate(s.solution(437674, 3), 3)
  validate(s.solution(110011, 10), 2)
  validate(s.solution(1_000_000, 3), 2)
  validate(s.solution(1_000_000, 9), 0)

  validate(s.solution(797161, 3), 0) // int 범위 초과하는 num = 1111111111111(3)
}
