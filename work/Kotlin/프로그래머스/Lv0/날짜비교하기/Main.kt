package 프로그래머스.Lv0.날짜비교하기

import util.validate

class Solution {

  fun solution(date1: IntArray, date2: IntArray): Int {
    for (i in date1.indices) {
      val d1 = date1[i]
      val d2 = date2[i]
      when {
        d1 < d2 -> return 1
        d1 > d2 -> return 0
      }
    }

    return 0
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 60.4MB)
 * 테스트 2 〉	통과 (0.01ms, 60MB)
 * 테스트 3 〉	통과 (0.01ms, 61MB)
 * 테스트 4 〉	통과 (0.01ms, 59.5MB)
 * 테스트 5 〉	통과 (0.01ms, 60.5MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(date1: IntArray, date2: IntArray) = if (date1[0] * 10000 + date1[1] * 100 + date1[2] < date2[0] * 10000 + date2[1] * 100 + date2[2]) 1 else 0
 * }
 * 테스트 1 〉	통과 (0.01ms, 60.3MB)
 * 테스트 2 〉	통과 (0.01ms, 59.5MB)
 * 테스트 3 〉	통과 (0.01ms, 58.9MB)
 * 테스트 4 〉	통과 (0.01ms, 59.7MB)
 * 테스트 5 〉	통과 (0.01ms, 57.9MB)
 *
 * [RIVAL 2]
 * import java.time.*
 * class Solution {
 *     fun solution(date1: IntArray, date2: IntArray): Int {
 *         val date11 = LocalDate.of(date1[0],date1[1],date1[2])
 *         val date21 = LocalDate.of(date2[0],date2[1],date2[2])
 *         return if (date11 < date21) 1 else 0
 *     }
 * }
 * 테스트 1 〉	통과 (0.43ms, 58.9MB)
 * 테스트 2 〉	통과 (0.33ms, 59.9MB)
 * 테스트 3 〉	통과 (0.34ms, 59.3MB)
 * 테스트 4 〉	통과 (0.31ms, 59.9MB)
 * 테스트 5 〉	통과 (0.32ms, 59.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    intArrayOf(2021, 12, 28),
    intArrayOf(2021, 12, 29),
  ), 1)

  validate(s.solution(
    intArrayOf(1024, 10, 24),
    intArrayOf(1024, 10, 24),
  ), 0)

  validate(s.solution(
    intArrayOf(1024, 10, 24),
    intArrayOf(1024, 10, 23),
  ), 0)

  validate(s.solution(
    intArrayOf(1001, 2, 29),
    intArrayOf(1000, 3, 1),
  ), 0)
}
