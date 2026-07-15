package 프로그래머스.Lv2.연속부분수열합의개수

import util.validate

class Solution {

  val sumOfAllElements = 1
  fun solution(elements: IntArray): Int {
    val N = elements.size
    val a = HashSet<Int>()

    for (i in elements.indices) {
      var seq = 0
      repeat(N - 1) {
        seq = (seq + elements[getIdx(i, it, N)]).also { a.add(it) }
      }
    }

    return a.size + sumOfAllElements
  }

  private fun getIdx(stt: Int, delta: Int, len: Int): Int {
    var idx = stt + delta
    if (idx >= len) idx -= len
    return idx
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.19ms, 59.5MB)
 * 테스트 2 〉	통과 (11.03ms, 61.1MB)
 * 테스트 3 〉	통과 (12.56ms, 64.6MB)
 * 테스트 4 〉	통과 (25.30ms, 68.4MB)
 * 테스트 5 〉	통과 (41.29ms, 71.6MB)
 * 테스트 6 〉	통과 (54.45ms, 73.7MB)
 * 테스트 7 〉	통과 (64.98ms, 77.5MB)
 * 테스트 8 〉	통과 (70.65ms, 81.5MB)
 * 테스트 9 〉	통과 (71.31ms, 89.1MB)
 * 테스트 10 〉	통과 (71.22ms, 96.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *      fun solution(elements: IntArray): Int {
 *         val doubling = elements+elements
 *         val result = HashSet<Int>()
 *         for (i in 1..elements.size) {
 *             result.addAll((elements.indices).map {
 *                 doubling.slice(it until it + i).sum()
 *             })
 *         }
 *         return result.size
 *     }
 * }
 * 테스트 1 〉	통과 (15.72ms, 64.2MB)
 * 테스트 2 〉	통과 (39.16ms, 87.8MB)
 * 테스트 3 〉	통과 (62.89ms, 111MB)
 * 테스트 4 〉	통과 (86.60ms, 169MB)
 * 테스트 5 〉	통과 (132.37ms, 280MB)
 * 테스트 6 〉	통과 (159.94ms, 282MB)
 * 테스트 7 〉	통과 (202.88ms, 330MB)
 * 테스트 8 〉	통과 (275.28ms, 383MB)
 * 테스트 9 〉	통과 (326.98ms, 388MB)
 * 테스트 10 〉	통과 (474.49ms, 504MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(7, 9, 1, 1, 4)), 18)
}
