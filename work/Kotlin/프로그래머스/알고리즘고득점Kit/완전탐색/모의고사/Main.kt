package 프로그래머스.알고리즘고득점Kit.완전탐색.모의고사

import util.validate

class Solution {

  val SUPOJA = 3

  val SUPOJA1 = intArrayOf(1, 2, 3, 4, 5)
  val SUPOJA2 = intArrayOf(2, 1, 2, 3, 2, 4, 2, 5)
  val SUPOJA3 = intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)

  fun solution(answers: IntArray): IntArray {
    var record = IntArray(SUPOJA)

    val s1 = SUPOJA1.size
    val s2 = SUPOJA2.size
    val s3 = SUPOJA3.size

    for (i in 0 until answers.size) {
      val ans = answers[i]
      if (ans == SUPOJA1[i % s1]) record[0]++
      if (ans == SUPOJA2[i % s2]) record[1]++
      if (ans == SUPOJA3[i % s3]) record[2]++
    }

    var maxCnt = 0
    var winners = 0
    for (cnt in record) if (cnt > maxCnt) {
      maxCnt = cnt
      winners = 1
    } else if (cnt == maxCnt) winners++

    val r = IntArray(winners)
    var ri = 0
    for (i in 0 until SUPOJA) if (record[i] == maxCnt) r[ri++] = i + 1

    return r
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (0.01ms, 65.2MB)
 * 테스트 2 〉	통과 (0.01ms, 62.4MB)
 * 테스트 3 〉	통과 (0.01ms, 61.7MB)
 * 테스트 4 〉	통과 (0.02ms, 61.9MB)
 * 테스트 5 〉	통과 (0.01ms, 65.1MB)
 * 테스트 6 〉	통과 (0.02ms, 63.8MB)
 * 테스트 7 〉	통과 (0.24ms, 62.2MB)
 * 테스트 8 〉	통과 (0.08ms, 63.4MB)
 * 테스트 9 〉	통과 (0.39ms, 62.9MB)
 * 테스트 10 〉	통과 (0.26ms, 62.5MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * class Solution {
 *         fun solution(answers: IntArray): IntArray {
 *
 *             val studentA = listOf(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
 *             val studentB = listOf(2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5)
 *             val studentC = listOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
 *
 *             val r = listOf(
 *                 Pair(1, answers.filterIndexed { index, i -> studentA[index % studentA.size] == i }.count()),
 *                 Pair(2, answers.filterIndexed { index, i -> studentB[index % studentB.size] == i }.count()),
 *                 Pair(3, answers.filterIndexed { index, i -> studentC[index % studentC.size] == i }.count())
 *             )
 *                 .also { println(it) }
 *                 .sortedByDescending { it.second }
 *
 *             return when {
 *                 r[0].second == r[1].second && r[1].second == r[2].second -> intArrayOf(r[0].first, r[1].first, r[2].first)
 *                 r[0].second == r[1].second -> intArrayOf(r[0].first, r[1].first)
 *                 else -> intArrayOf(r[0].first)
 *             }
 *         }
 *     }
 *
 * 테스트 1 〉	통과 (14.23ms, 66.4MB)
 * 테스트 2 〉	통과 (11.38ms, 67.4MB)
 * 테스트 3 〉	통과 (14.45ms, 66.5MB)
 * 테스트 4 〉	통과 (13.61ms, 66.1MB)
 * 테스트 5 〉	통과 (13.55ms, 66.8MB)
 * 테스트 6 〉	통과 (12.05ms, 67.1MB)
 * 테스트 7 〉	통과 (14.24ms, 67.3MB)
 * 테스트 8 〉	통과 (15.35ms, 67MB)
 * 테스트 9 〉	통과 (18.31ms, 67.1MB)
 * 테스트 10 〉	통과 (12.77ms, 67MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    intArrayOf(1, 2, 3, 4, 5)
  ), intArrayOf(1))
  validate(s.solution(
    intArrayOf(1, 3, 2, 4, 2)
  ), intArrayOf(1, 2, 3))


}
