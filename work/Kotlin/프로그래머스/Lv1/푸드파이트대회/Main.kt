package 프로그래머스.Lv1.푸드파이트대회

import util.validate

class Solution {

  val ZERO = 48
  fun solution(food: IntArray): String {
    var len = 1
    for (i in 1 until food.size) food[i] = (food[i] / 2).also { len += it * 2 }

    val ans = CharArray(len) { ZERO.toChar() }
    var stt = 0
    for (i in 1 until food.size) {
      val cnt = food[i]
      val end = len - (stt + 1)
      repeat(cnt) {
        ans[stt + it] = (i + ZERO).toChar()
        ans[end - it] = (i + ZERO).toChar()
      }

      stt += cnt
    }

    return String(ans)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.25ms, 58.1MB)
 * 테스트 2 〉	통과 (0.38ms, 57.5MB)
 * 테스트 3 〉	통과 (0.28ms, 59.1MB)
 * 테스트 4 〉	통과 (0.53ms, 58MB)
 * 테스트 5 〉	통과 (0.23ms, 57.9MB)
 * 테스트 6 〉	통과 (0.40ms, 57.9MB)
 * 테스트 7 〉	통과 (0.41ms, 57.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(food: IntArray): String {
 *         val sb = StringBuilder()
 *         for (i in 1..food.lastIndex) {
 *             repeat(food[i] / 2) { sb.append(i) }
 *         }
 *         val reversed = sb.reversed()
 *         sb.append(0)
 *         sb.append(reversed)
 *         return sb.toString()
 *     }
 * }
 * 테스트 1 〉	통과 (13.88ms, 64.1MB)
 * 테스트 2 〉	통과 (13.80ms, 63.7MB)
 * 테스트 3 〉	통과 (14.42ms, 63.4MB)
 * 테스트 4 〉	통과 (13.68ms, 62.8MB)
 * 테스트 5 〉	통과 (13.51ms, 63.7MB)
 * 테스트 6 〉	통과 (13.35ms, 62.8MB)
 * 테스트 7 〉	통과 (13.82ms, 62.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(1, 3, 4, 6)), "1223330333221")
  validate(s.solution(intArrayOf(1, 7, 1, 2)), "111303111")
}
