package 프로그래머스.알고리즘고득점Kit.StackAndQueue.기능개발

import util.validate

class Solution {

  val DONE = 100
  fun solution(p: IntArray, s: IntArray): IntArray {
    var tmp = IntArray(p.size)
    var deployed = -1

    var cnt = 0
    var days = 0
    for (i in 0 until p.size) {
      val pgrs = p[i]
      val spd = s[i]
      if (pgrs + spd * days < DONE) {
        if (deployed++ >= 0) tmp[deployed - 1] = cnt

        days = (DONE - pgrs + spd - 1) / spd
        cnt = 1
      } else cnt++
    }
    tmp[deployed++] = cnt

    var ans = IntArray(deployed)
    System.arraycopy(tmp, 0, ans, 0, deployed)
    return ans
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (0.01ms, 64.1MB)
 * 테스트 2 〉	통과 (0.02ms, 62.1MB)
 * 테스트 3 〉	통과 (0.02ms, 64.3MB)
 * 테스트 4 〉	통과 (0.02ms, 62.5MB)
 * 테스트 5 〉	통과 (0.01ms, 63.9MB)
 * 테스트 6 〉	통과 (0.01ms, 63.9MB)
 * 테스트 7 〉	통과 (0.02ms, 62.5MB)
 * 테스트 8 〉	통과 (0.02ms, 62.2MB)
 * 테스트 9 〉	통과 (0.02ms, 63.2MB)
 * 테스트 10 〉	통과 (0.01ms, 62.6MB)
 * 테스트 11 〉	통과 (0.01ms, 63.8MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * class Solution {
 *     fun solution(progresses: IntArray, speeds: IntArray): IntArray {
 *         var answer = intArrayOf()
 *
 *         var lastDay = 0
 *         var cnt = 0
 *         progresses
 *                 .mapIndexed {idx, progress -> Pair(progress, speeds[idx].toDouble())}
 *                 .map { (100 - it.first) / it.second }
 *                 .map { Math.ceil(it) }
 *                 .map { it.toInt() }
 *                 .asSequence()
 *                 .forEach { curDay ->
 *                     if (lastDay >= curDay) {
 *                         cnt++
 *                     } else {
 *                         if (lastDay != 0)
 *                             answer = answer.plus(cnt)
 *                         lastDay = curDay
 *                         cnt = 1
 *                     }
 *                 }
 *         answer = answer.plus(cnt)
 *
 *         return answer
 *     }
 * }
 *
 * 테스트 1 〉	통과 (13.94ms, 65.5MB)
 * 테스트 2 〉	통과 (11.74ms, 66.4MB)
 * 테스트 3 〉	통과 (10.62ms, 67.6MB)
 * 테스트 4 〉	통과 (11.25ms, 67.2MB)
 * 테스트 5 〉	통과 (14.12ms, 65.6MB)
 * 테스트 6 〉	통과 (10.74ms, 67MB)
 * 테스트 7 〉	통과 (10.76ms, 66.3MB)
 * 테스트 8 〉	통과 (14.19ms, 65.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(intArrayOf(93, 30, 55), intArrayOf(1, 30, 5)),
    intArrayOf(2, 1)
  )
  validate(
    s.solution(intArrayOf(95, 90, 99, 99, 80, 99), intArrayOf(1, 1, 1, 1, 1, 1)),
    intArrayOf(1, 3, 2)
  )
}
