package 프로그래머스.Lv1.기사단원의무기

import util.validate

class Solution {

  fun solution(number: Int, limit: Int, power: Int): Int {
    var ans = 1
    val cnts = IntArray(number + 1)
    cnts[1] = 1
    if (number >= 2) cnts[2] = 2.also { ans += if (it > limit) power else it }

    for (x in 3..number) {
      var cnt: Int
      if (x % 2 == 0) {
        var d = x / 2
        cnt = if (d % 2 == 1) cnts[d] * 2 else {
          var dCnt = cnts[d]
          var evenPow = 0
          while (d % 2 == 0) {
            d /= 2
            evenPow++
          }
          val oddCnt = dCnt / (evenPow + 1)
          val evenCnt = evenPow + 2
          oddCnt * evenCnt
        }
      } else {
        cnt = 0
        var d = 1
        while (d <= x / d) {
          if (x % d == 0) cnt += if (d == x / d) 1 else 2
          d += 2
        }
      }
      cnts[x] = cnt.also { ans += if (it > limit) power else it }
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (2.38ms, 58.7MB)
 * 테스트 2 〉	통과 (0.67ms, 59.7MB)
 * 테스트 3 〉	통과 (0.52ms, 59.9MB)
 * 테스트 4 〉	통과 (0.85ms, 59.3MB)
 * 테스트 5 〉	통과 (0.39ms, 58.3MB)
 * 테스트 6 〉	통과 (2.06ms, 58.4MB)
 * 테스트 7 〉	통과 (0.65ms, 58.7MB)
 * 테스트 8 〉	통과 (0.50ms, 59.8MB)
 * 테스트 9 〉	통과 (1.96ms, 58.2MB)
 * 테스트 10 〉	통과 (0.38ms, 57.9MB)
 * 테스트 11 〉	통과 (18.72ms, 59.9MB)
 * 테스트 12 〉	통과 (19.06ms, 59.6MB)
 * 테스트 13 〉	통과 (19.20ms, 58.3MB)
 * 테스트 14 〉	통과 (19.24ms, 58.3MB)
 * 테스트 15 〉	통과 (19.97ms, 60.4MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(number: Int, limit: Int, power: Int): Int {
 *         return IntArray(number) { getCount(it + 1) }.fold(0) { acc, v ->
 *             if (v > limit) acc + power
 *             else acc + v
 *         }
 *     }
 *
 *     private fun getCount(n: Int): Int {
 *         var count = 0
 *         var i = 1
 *         while (i * i < n) {
 *             if (n % i++ == 0) count += 2
 *         }
 *         if (i * i == n) count++
 *         return count
 *     }
 * }
 * 테스트 1 〉	통과 (3.66ms, 59.4MB)
 * 테스트 2 〉	통과 (0.57ms, 59.8MB)
 * 테스트 3 〉	통과 (0.40ms, 57.9MB)
 * 테스트 4 〉	통과 (0.76ms, 59.7MB)
 * 테스트 5 〉	통과 (0.23ms, 57.9MB)
 * 테스트 6 〉	통과 (3.45ms, 59.1MB)
 * 테스트 7 〉	통과 (0.52ms, 59.2MB)
 * 테스트 8 〉	통과 (0.41ms, 59.7MB)
 * 테스트 9 〉	통과 (3.27ms, 58.3MB)
 * 테스트 10 〉	통과 (0.31ms, 59.6MB)
 * 테스트 11 〉	통과 (36.03ms, 60.1MB)
 * 테스트 12 〉	통과 (36.93ms, 59.5MB)
 * 테스트 13 〉	통과 (38.86ms, 58.2MB)
 * 테스트 14 〉	통과 (37.27ms, 59.6MB)
 * 테스트 15 〉	통과 (38.52ms, 59.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(5, 3, 2), 10)
  validate(s.solution(10, 3, 2), 21)
  validate(s.solution(10, 100, 2), 27)
}
//      println("cnts[$x] = ${cnts[x]}")
// divors by num = [1, 2, 2, 3, 2, 4, 2, 4, 3, 4]
//  for (i in 1 until cnts.size) {
//      println("cnts[$i] = ${cnts[i]}")
//    }
