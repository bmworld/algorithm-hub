package 프로그래머스.Lv1.두개뽑아서더하기

import util.validate

class Solution {

  val MAX_SUM = 200
  fun solution(a: IntArray): IntArray {
    val ch = BooleanArray(MAX_SUM + 1)
    var len = 0
    for (i in 0 until a.size)
      for (j in i + 1 until a.size) {
        val sum = a[i] + a[j]
        if (!ch[sum]) {
          ch[sum] = true
          len++
        }
      }

    val ans = IntArray(len)
    var i = 0
    repeat(ch.size) {
      if (ch[it]) ans[i++] = it
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 59.6MB)
 * 테스트 2 〉	통과 (0.02ms, 59.7MB)
 * 테스트 3 〉	통과 (0.01ms, 58.7MB)
 * 테스트 4 〉	통과 (0.01ms, 59MB)
 * 테스트 5 〉	통과 (0.01ms, 58.8MB)
 * 테스트 6 〉	통과 (0.02ms, 58MB)
 * 테스트 7 〉	통과 (0.08ms, 57.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(numbers: IntArray): IntArray {
 *         val list = numbers.toList()
 *         return list.withIndex().flatMap { i -> list.withIndex().map { j -> i to j } }
 *             .filter { it.first.index != it.second.index }
 *             .map { it.first.value + it.second.value }
 *             .toSortedSet()
 *             .toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (14.88ms, 62.5MB)
 * 테스트 2 〉	통과 (15.49ms, 63.7MB)
 * 테스트 3 〉	통과 (14.95ms, 64.3MB)
 * 테스트 4 〉	통과 (14.52ms, 62.7MB)
 * 테스트 5 〉	통과 (14.05ms, 62.9MB)
 * 테스트 6 〉	통과 (14.90ms, 62.8MB)
 * 테스트 7 〉	통과 (18.79ms, 63.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(2, 1, 3, 4, 1)), intArrayOf(2, 3, 4, 5, 6, 7))
  validate(s.solution(intArrayOf(5, 0, 2, 7)), intArrayOf(2, 5, 7, 9, 12))
}

//          println("sum = ${sum}, $len")
