package 프로그래머스.Lv1.정수제곱근판별

import util.validate

class Solution {

  fun solution(n: Long): Long {
    var l = 1L
    var r = n
    while (l <= r) {
      val m = (l + r) shr 1
      val rmn = n / m

      when {
        m == rmn && n % m == 0L -> return (m + 1) * (m + 1)
        m > rmn -> r = m - 1
        else -> l = m + 1
      }
    }

    return -1L
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.8MB)
 * 테스트 2 〉	통과 (0.02ms, 57.7MB)
 * 테스트 3 〉	통과 (0.02ms, 58MB)
 * 테스트 4 〉	통과 (0.01ms, 59.8MB)
 * 테스트 5 〉	통과 (0.01ms, 59.4MB)
 * 테스트 6 〉	통과 (0.02ms, 58MB)
 * 테스트 7 〉	통과 (0.01ms, 59.8MB)
 * 테스트 8 〉	통과 (0.01ms, 59.2MB)
 * 테스트 9 〉	통과 (0.01ms, 58.2MB)
 * 테스트 10 〉	통과 (0.01ms, 59.7MB)
 * 테스트 11 〉	통과 (0.01ms, 58.5MB)
 * 테스트 12 〉	통과 (0.01ms, 59.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Long): Long {
 *         val sqrt = Math.sqrt(n.toDouble())
 *         return if(sqrt % 1.0 == 0.0) {
 *             Math.pow(sqrt + 1, 2.0).toLong()
 *         } else {
 *             -1L
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (0.02ms, 59.3MB)
 * 테스트 2 〉	통과 (0.02ms, 58.7MB)
 * 테스트 3 〉	통과 (0.03ms, 59.5MB)
 * 테스트 4 〉	통과 (0.02ms, 58.8MB)
 * 테스트 5 〉	통과 (0.02ms, 59.7MB)
 * 테스트 6 〉	통과 (0.02ms, 58.7MB)
 * 테스트 7 〉	통과 (0.02ms, 58.1MB)
 * 테스트 8 〉	통과 (0.02ms, 59.6MB)
 * 테스트 9 〉	통과 (0.02ms, 57.7MB)
 * 테스트 10 〉	통과 (0.04ms, 58.4MB)
 * 테스트 11 〉	통과 (0.02ms, 59.3MB)
 * 테스트 12 〉	통과 (0.03ms, 60MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(121), 144)
  validate(s.solution(1), 4)
  validate(s.solution(2), -1)
  validate(s.solution(3), -1)
  validate(s.solution(5), -1)
  validate(s.solution(6), -1)
  validate(s.solution(7), -1)
  validate(s.solution(8), -1)
  validate(s.solution(9), 16)
  validate(s.solution(100), 121)
  validate(s.solution(998_001), 1_000_000)
  validate(s.solution(7_000_000L * 7_000_000L), 7_000_001L * 7_000_001L)
  validate(s.solution(50_000_000_000_000L), -1L)
}

//      println("[$n] $dm vs $rmn")
