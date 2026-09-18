package 프로그래머스.Lv2.점찍기

import util.validate
import java.lang.Math.sqrt

class Solution {

  fun solution(k: Int, d: Int): Long {
    var ans = 0L

    val k = k.toLong()
    val sqd = d.toLong() * d
    val r = sqrt(sqd / 2.0).toLong()
    ans += (r / k + 1) * (r / k + 1)

    for (a in r + k..d step k) {
      val b = sqrt(sqd.toDouble() - (a * a)).toLong()
      ans += (b + 1) * 2
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.8MB)
 * 테스트 2 〉	실패 (0.02ms, 60MB)
 * 테스트 3 〉	실패 (0.05ms, 58.3MB)
 * 테스트 4 〉	실패 (0.06ms, 58.3MB)
 * 테스트 5 〉	실패 (0.08ms, 57.9MB)
 * 테스트 6 〉	실패 (0.05ms, 58.2MB)
 * 테스트 7 〉	실패 (0.03ms, 60.1MB)
 * 테스트 8 〉	실패 (0.38ms, 59.9MB)
 * 테스트 9 〉	실패 (0.07ms, 58.3MB)
 * 테스트 10 〉	실패 (0.12ms, 60.1MB)
 * 테스트 11 〉	통과 (3.15ms, 60.1MB)
 * 테스트 12 〉	통과 (0.02ms, 60.1MB)
 * 테스트 13 〉	통과 (2.99ms, 60MB)
 * 테스트 14 〉	실패 (2.18ms, 60MB)
 * 테스트 15 〉	통과 (0.02ms, 59.3MB)
 * 테스트 16 〉	통과 (0.02ms, 61.2MB)
 * WA2:
 * 테스트 1 〉	통과 (0.20ms, 58.5MB)
 * 테스트 2 〉	실패 (0.27ms, 60.2MB)
 * 테스트 3 〉	실패 (0.47ms, 57.8MB)
 * 테스트 4 〉	실패 (0.28ms, 57.3MB)
 * 테스트 5 〉	실패 (0.26ms, 60.5MB)
 * 테스트 6 〉	실패 (0.22ms, 60.7MB)
 * 테스트 7 〉	실패 (0.27ms, 60.3MB)
 * 테스트 8 〉	실패 (0.74ms, 59.8MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
//  validate(s.solution(2, 1), 1)
//  validate(s.solution(1, 1), 3)
//  validate(s.solution(1, 10), 90)
  validate(s.solution(2, 4), 6)
  validate(s.solution(1, 5), 26)
  validate(s.solution(1, 1_000_000), 785_399_162_407)
}

////      println("[$a] ans+=$b*2 = $ans")
//  fun sqrt(n: Long, k: Int): Long {
//    if (n <= 1L) return n
//
//    var l = 1L
//    var r = n / 2
//    while (l <= r) {
//      val m = (l + r) shr 1
//      val sq = m * m
//      when {
//
//      }
//    }
//
//
//    return 1L
//  }
