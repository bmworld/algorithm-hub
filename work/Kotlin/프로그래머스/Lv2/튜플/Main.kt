package 프로그래머스.Lv2.튜플

import util.validate

class Solution {

  companion object {

    const val comma = ','.code
    const val close = '}'.code
    const val ZERO = 48
    val NUM = ZERO..ZERO + 9

    const val ADDED_SKIP = 2
  }

  fun solution(s: String): IntArray {
    var len = 0
    val cnter = HashMap<Int, Int>()

    var fr = 2
    val to = s.length - 2
    var x = 0

    fun cnt() {
      cnter[x] = (cnter[x] ?: 0) + 1
      x = 0
    }

    while (fr <= to) {
      val c = s[fr++].code
      when (c) {
        close -> {
          cnt()
          len++
          fr += ADDED_SKIP
        }
        comma -> cnt()
        in NUM -> x = x * 10 + (c - ZERO)
      }
    }

    var ans = IntArray(len)
    for ((x, cnt) in cnter) ans[len - cnt] = x

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.23ms, 60.4MB)
 * 테스트 2 〉	통과 (0.30ms, 59.5MB)
 * 테스트 3 〉	통과 (0.37ms, 59MB)
 * 테스트 4 〉	통과 (0.44ms, 58.6MB)
 * 테스트 5 〉	통과 (0.46ms, 59.8MB)
 * 테스트 6 〉	통과 (2.09ms, 59.7MB)
 * 테스트 7 〉	통과 (5.98ms, 60.3MB)
 * 테스트 8 〉	통과 (11.71ms, 59.6MB)
 * 테스트 9 〉	통과 (5.25ms, 61.1MB)
 * 테스트 10 〉	통과 (6.39ms, 64.6MB)
 * 테스트 11 〉	통과 (8.85ms, 65.1MB)
 * 테스트 12 〉	통과 (11.16ms, 64.8MB)
 * 테스트 13 〉	통과 (16.92ms, 64.4MB)
 * 테스트 14 〉	통과 (12.22ms, 64MB)
 * 테스트 15 〉	통과 (0.25ms, 60.7MB)
 *
 * v2:
 * 테스트 1 〉	통과 (0.18ms, 59.9MB)
 * 테스트 2 〉	통과 (0.17ms, 60MB)
 * 테스트 3 〉	통과 (0.17ms, 59.9MB)
 * 테스트 4 〉	통과 (0.31ms, 60.5MB)
 * 테스트 5 〉	통과 (0.64ms, 60.3MB)
 * 테스트 6 〉	통과 (0.79ms, 59.5MB)
 * 테스트 7 〉	통과 (6.23ms, 61.7MB)
 * 테스트 8 〉	통과 (10.08ms, 67.8MB)
 * 테스트 9 〉	통과 (21.24ms, 62.9MB)
 * 테스트 10 〉	통과 (11.37ms, 65.2MB)
 * 테스트 11 〉	통과 (18.08ms, 66.6MB)
 * 테스트 12 〉	통과 (16.51ms, 70.2MB)
 * 테스트 13 〉	통과 (19.14ms, 69.6MB)
 * 테스트 14 〉	통과 (26.94ms, 70.2MB)
 * 테스트 15 〉	통과 (0.20ms, 58.6MB)
 *
 * v3:
 * 테스트 1 〉	통과 (0.27ms, 58.5MB)
 * 테스트 2 〉	통과 (0.28ms, 60.5MB)
 * 테스트 3 〉	통과 (0.30ms, 60.6MB)
 * 테스트 4 〉	통과 (0.41ms, 59.8MB)
 * 테스트 5 〉	통과 (0.44ms, 59.2MB)
 * 테스트 6 〉	통과 (0.59ms, 61.3MB)
 * 테스트 7 〉	통과 (4.71ms, 59.1MB)
 * 테스트 8 〉	통과 (6.63ms, 60.8MB)
 * 테스트 9 〉	통과 (5.21ms, 61.4MB)
 * 테스트 10 〉	통과 (6.62ms, 61.5MB)
 * 테스트 11 〉	통과 (8.03ms, 60.3MB)
 * 테스트 12 〉	통과 (13.42ms, 62.7MB)
 * 테스트 13 〉	통과 (13.81ms, 63.5MB)
 * 테스트 14 〉	통과 (14.83ms, 63.2MB)
 * 테스트 15 〉	통과 (0.34ms, 60.8MB)
 *
 * v4:
 * 테스트 1 〉	통과 (0.31ms, 60.5MB)
 * 테스트 2 〉	통과 (0.29ms, 61.3MB)
 * 테스트 3 〉	통과 (0.32ms, 60.3MB)
 * 테스트 4 〉	통과 (0.32ms, 60.3MB)
 * 테스트 5 〉	통과 (0.45ms, 59.8MB)
 * 테스트 6 〉	통과 (0.57ms, 59.4MB)
 * 테스트 7 〉	통과 (4.47ms, 59.8MB)
 * 테스트 8 〉	통과 (8.46ms, 61MB)
 * 테스트 9 〉	통과 (5.62ms, 59.5MB)
 * 테스트 10 〉	통과 (6.67ms, 61.4MB)
 * 테스트 11 〉	통과 (7.82ms, 61.9MB)
 * 테스트 12 〉	통과 (10.65ms, 62.4MB)
 * 테스트 13 〉	통과 (13.06ms, 62.9MB)
 * 테스트 14 〉	통과 (12.32ms, 63MB)
 * 테스트 15 〉	통과 (0.27ms, 60.9MB)
 *
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String): IntArray {
 *         return s.substring(2 until s.length-2)
 *             .split("},{")
 *             .asSequence()
 *             .map { it.split(",").map { num -> num.toInt() } }
 *             .toList()
 *             .sortedBy { it.size }
 *             .fold(setOf<Int>()) { acc, list -> acc.union(list) }
 *             .toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (36.49ms, 65.5MB)
 * 테스트 2 〉	통과 (35.40ms, 65MB)
 * 테스트 3 〉	통과 (15.76ms, 62.3MB)
 * 테스트 4 〉	통과 (29.81ms, 66.3MB)
 * 테스트 5 〉	통과 (30.18ms, 66.6MB)
 * 테스트 6 〉	통과 (28.63ms, 66.7MB)
 * 테스트 7 〉	통과 (46.23ms, 71.6MB)
 * 테스트 8 〉	통과 (49.59ms, 79.4MB)
 * 테스트 9 〉	통과 (39.98ms, 75.6MB)
 * 테스트 10 〉	통과 (53.83ms, 81MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"), intArrayOf(2, 1, 3, 4))
  validate(s.solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"), intArrayOf(2, 1, 3, 4))
  validate(s.solution("{{20,111},{111}}"), intArrayOf(111, 20))
  validate(s.solution("{{123}}"), intArrayOf(123))
  validate(s.solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"), intArrayOf(3, 2, 4, 1))
}
