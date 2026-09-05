package 프로그래머스.Lv2.PCCP기출문제_2번_퍼즐게임챌린지

import util.validate

class Solution {

  fun solution(diffs: IntArray, times: IntArray, limit: Long): Int {
    var minLev = 1
    for (x in diffs) if (x > minLev) minLev = x

    val N = diffs.size
    val defT = times[0].toLong()

    var l = 1
    var r = minLev

    binSearch@ while (l <= r) {
      val m = (l + r) shr 1
      var t = defT

      for (i in 1 until N) {
        val d = diffs[i]
        t += when {
          d <= m -> times[i]
          else -> {
            val cur = times[i]
            val prv = times[i - 1]
            (cur + prv) * (d - m) + cur
          }
        }

        if (t > limit) {
          l = m + 1
          continue@binSearch
        }
      }

      if (m < minLev) {
        minLev = m
        r = m - 1
      } else break
    }

    return minLev
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.9MB)
 * 테스트 2 〉	통과 (0.01ms, 59.8MB)
 * 테스트 3 〉	통과 (0.02ms, 58.9MB)
 * 테스트 4 〉	통과 (0.02ms, 59.2MB)
 * 테스트 5 〉	통과 (0.02ms, 59.2MB)
 * 테스트 6 〉	통과 (0.02ms, 59.4MB)
 * 테스트 7 〉	통과 (0.02ms, 60.7MB)
 * 테스트 8 〉	통과 (0.51ms, 60.3MB)
 * 테스트 9 〉	통과 (0.63ms, 59.6MB)
 * 테스트 10 〉	통과 (0.39ms, 60.3MB)
 * 테스트 11 〉	통과 (0.58ms, 59.8MB)
 * 테스트 12 〉	통과 (0.52ms, 60.7MB)
 * 테스트 13 〉	통과 (0.48ms, 61.1MB)
 * 테스트 14 〉	통과 (0.54ms, 60.2MB)
 * 테스트 15 〉	통과 (23.11ms, 86.9MB)
 * 테스트 16 〉	통과 (30.56ms, 88.2MB)
 *
 * [RIVAL]
 * class Solution {
 *     fun solution(diffs: IntArray, times: IntArray, limit: Long): Int {
 *         var start = 1
 *         var end = 100_000
 *
 *         if(diffs.size == 1) {
 *             return 1
 *         }
 *
 *         while(start < end) {
 *             val level = (start + end) / 2
 *
 *             if(canSolved(diffs, times, limit, level)) { //숙련도 낮춰도 됨
 *                 end = level
 *             } else {
 *                 start = level + 1
 *             }
 *         }
 *
 *         return start
 *     }
 *
 *     fun canSolved(diffs: IntArray, times: IntArray, limit: Long, level: Int) : Boolean{
 *         var totalTime: Long = 0L
 *
 *         for(i in diffs.indices) {
 *             totalTime += if(diffs[i] <= level) { // 현재 난이도가 숙련도보다 낮으면 제한 시간 내에 풀 수 있음
 *                 times[i]
 *             } else {
 *                 // 틀리는 횟수 = (diffs[i] - level), 다시 푸는 데 걸리는 시간 = (times[i] + times[i-1])
 *                 (diffs[i] - level) * (times[i] + times[i-1]) + times[i]
 *             }
 *
 *             if(totalTime > limit)
 *                 return false
 *         }
 *
 *         return true
 *     }
 * }
 * 테스트 1 〉	통과 (0.02ms, 59.7MB)
 * 테스트 2 〉	통과 (0.02ms, 60.6MB)
 * 테스트 3 〉	통과 (0.02ms, 59.8MB)
 * 테스트 4 〉	통과 (0.05ms, 60.1MB)
 * 테스트 5 〉	통과 (0.02ms, 60.9MB)
 * 테스트 6 〉	통과 (0.02ms, 60.1MB)
 * 테스트 7 〉	통과 (0.02ms, 61.3MB)
 * 테스트 8 〉	통과 (0.49ms, 61.4MB)
 * 테스트 9 〉	통과 (0.55ms, 60.3MB)
 * 테스트 10 〉	통과 (0.36ms, 59.7MB)
 * 테스트 11 〉	통과 (0.54ms, 60.9MB)
 * 테스트 12 〉	통과 (0.55ms, 61.1MB)
 * 테스트 13 〉	통과 (0.48ms, 60.4MB)
 * 테스트 14 〉	통과 (0.57ms, 60.1MB)
 * 테스트 15 〉	통과 (17.53ms, 86.3MB)
 * 테스트 16 〉	통과 (26.94ms, 87.6MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(diffs: IntArray, times: IntArray, limit: Long): Int {
 *         var left = diffs[0]
 *         var right = diffs.maxOf { it }
 *         while (left < right) {
 *             val mid = (left + right) / 2
 *             val time = calc(mid, diffs, times)
 *             if (time > limit) left = mid + 1
 *             else right = mid
 *         }
 *         return left
 *     }
 *
 *     private fun calc(level: Int, diffs: IntArray, times: IntArray): Long {
 *         var time = 0L
 *         diffs.forEachIndexed { idx, diff ->
 *             time += times[idx]
 *             val prevTime = times.getOrElse(idx - 1) { 0 }
 *             if (diff > level) time += (diff - level) * (times[idx] + prevTime)
 *         }
 *         return time
 *     }
 * }
 * 테스트 1 〉	통과 (10.08ms, 64.1MB)
 * 테스트 2 〉	통과 (10.00ms, 63.1MB)
 * 테스트 3 〉	통과 (8.75ms, 64.4MB)
 * 테스트 4 〉	통과 (8.90ms, 64MB)
 * 테스트 5 〉	통과 (9.48ms, 63.8MB)
 * 테스트 6 〉	통과 (10.85ms, 63.6MB)
 * 테스트 7 〉	통과 (8.54ms, 64.3MB)
 * 테스트 8 〉	통과 (9.52ms, 63.4MB)
 * 테스트 9 〉	통과 (11.05ms, 64.4MB)
 * 테스트 10 〉	통과 (11.70ms, 63.8MB)
 * 테스트 11 〉	통과 (9.47ms, 64.9MB)
 * 테스트 12 〉	통과 (10.28ms, 64.6MB)
 * 테스트 13 〉	통과 (13.24ms, 63.9MB)
 * 테스트 14 〉	통과 (9.64ms, 63.6MB)
 * 테스트 15 〉	통과 (36.44ms, 92.1MB)
 * 테스트 16 〉	통과 (45.32ms, 90.7MB)
 *
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      intArrayOf(1, 5, 3),
      intArrayOf(2, 4, 7),
      30
    ), 3
  )

  validate(
    s.solution(
      intArrayOf(1, 4, 4, 2),
      intArrayOf(6, 3, 8, 2),
      59
    ), 2
  )

  validate(
    s.solution(
      intArrayOf(1, 328, 467, 209, 54),
      intArrayOf(2, 7, 1, 4, 3),
      1723
    ), 294
  )

  validate(
    s.solution(
      intArrayOf(1, 99999, 100000, 99995),
      intArrayOf(9999, 9001, 9999, 9001),
      3_456_789_012L
    ),
    39_354
  )

}

//      println("[limit=$limit] l=$l, m=$m, r=$r (min=$minLev)")
