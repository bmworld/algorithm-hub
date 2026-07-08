package 프로그래머스.Lv1.유연근무제

import util.validate

class Solution {

  val SAT = 6
  val SUN = 0
  val MARGIN_MIN = 10
  val UNIT_OF_MIN = 60
  val UNIT_OF_HOUR = 100
  val DAYS_IN_WEEK = 7
  fun solution(s: IntArray, timelogs: Array<IntArray>, startDay: Int): Int {
    var ans = 0

    l@ for (i in timelogs.indices) {
      val log = timelogs[i]
      val t = getDeadline(s[i])

      for (j in log.indices) {
        val day = (startDay + j) % DAYS_IN_WEEK
        if (day == SAT || day == SUN) continue
        if (log[j] > t) continue@l
      }

      ans++
    }

    return ans
  }

  fun getDeadline(time: Int): Int {
    var hh = time / UNIT_OF_HOUR
    var mm = time % UNIT_OF_HOUR + MARGIN_MIN
    if (mm >= UNIT_OF_MIN) {
      hh++
      mm %= UNIT_OF_MIN
    }
    return hh * UNIT_OF_HOUR + mm
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 57.9MB)
 * 테스트 2 〉	통과 (0.01ms, 58MB)
 * 테스트 3 〉	통과 (0.01ms, 60.1MB)
 * 테스트 4 〉	통과 (0.01ms, 58.4MB)
 * 테스트 5 〉	통과 (0.04ms, 59.1MB)
 * 테스트 6 〉	통과 (0.01ms, 59.1MB)
 * 테스트 7 〉	통과 (0.25ms, 61.1MB)
 * 테스트 8 〉	통과 (0.20ms, 61MB)
 * 테스트 9 〉	통과 (0.27ms, 61MB)
 * 테스트 10 〉	통과 (0.23ms, 61.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(schedules: IntArray, timeLogs: Array<IntArray>, startDay: Int) = timeLogs
 *         .zip(schedules.map { it + if (it % 100 > 49) 50 else 10 })
 *         .filter { (array, limit) -> array.filterIndexed { idx, i -> i > limit && !(idx == 6 - startDay % 7 || idx == 7 - startDay) }.isEmpty() }
 *         .size
 * }
 * 테스트 1 〉	통과 (11.95ms, 62.5MB)
 * 테스트 2 〉	통과 (13.39ms, 62.1MB)
 * 테스트 3 〉	통과 (11.94ms, 63.8MB)
 * 테스트 4 〉	통과 (14.56ms, 64.1MB)
 * 테스트 5 〉	통과 (15.29ms, 61.6MB)
 * 테스트 6 〉	통과 (11.84ms, 63MB)
 * 테스트 7 〉	통과 (15.48ms, 65.6MB)
 * 테스트 8 〉	통과 (14.91ms, 66.3MB)
 * 테스트 9 〉	통과 (13.38ms, 64.9MB)
 * 테스트 10 〉	통과 (16.63ms, 65.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      intArrayOf(700, 800, 1100),
      arrayOf(
        intArrayOf(710, 2359, 1050, 700, 650, 631, 659),
        intArrayOf(800, 801, 805, 800, 759, 810, 809),
        intArrayOf(1105, 1001, 1002, 600, 1059, 1001, 1100),
      ),
      5
    ),
    3
  )

  validate(
    s.solution(
      intArrayOf(730, 855, 700, 720),
      arrayOf(
        intArrayOf(710, 700, 650, 735, 700, 931, 912),
        intArrayOf(908, 901, 805, 815, 800, 831, 835),
        intArrayOf(705, 701, 702, 705, 710, 710, 711),
        intArrayOf(707, 731, 859, 913, 934, 931, 905),
      ),
      1
    ),
    2
  )

}

//      println("s[$i] = ${s[i]} -> $t")
