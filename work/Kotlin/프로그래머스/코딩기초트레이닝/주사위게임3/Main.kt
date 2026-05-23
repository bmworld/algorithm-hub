package 프로그래머스.코딩기초트레이닝.주사위게임3

import util.validate

class Solution {

  val MAX = 6
  fun solution(a: Int, b: Int, c: Int, d: Int): Int {

    val dice = IntArray(MAX + 1)
    dice[a]++
    dice[b]++
    dice[c]++
    dice[d]++

    var dup1 = -1
    var p1 = 0
    var dup2 = -1
    var p2 = 0
    for (n in 1..MAX) {
      val dup = dice[n]
      if (dup > dup1) {
        dup2 = dup1
        p2 = p1
        dup1 = dup
        p1 = n
      } else if (dup > dup2) {
        dup2 = dup
        p2 = n
      }
    }

    return when (dup1) {
      4 -> 1111 * p1
      3 -> {
        val sum = a + b + c + d
        val q = sum - p1 * 3
        (10 * p1 + q).let { it * it }
      }
      2 -> {
        if (dup2 == 2) (p1 + p2) * abs(p1 - p2) else {
          var q = 0
          var r = 0
          for (n in 1..MAX) {
            val dup = dice[n]
            if (dup != 1) continue
            if (q == 0) q = n
            else r = n
          }
          q * r
        }
      }
      else -> minOf(a, b, c, d)
    }
  }

  fun abs(v: Int): Int = if (v < 0) -v else v
}

/**
 * ```
 * ME v1:
 * 테스트 1 〉	통과 (0.01ms, 62MB)
 * 테스트 2 〉	통과 (0.01ms, 63.9MB)
 * 테스트 3 〉	통과 (0.01ms, 63.2MB)
 * 테스트 4 〉	통과 (0.01ms, 63MB)
 * 테스트 5 〉	통과 (0.01ms, 61.8MB)
 * 테스트 6 〉	통과 (0.01ms, 65.4MB)
 * 테스트 7 〉	통과 (0.01ms, 62.1MB)
 * 테스트 8 〉	통과 (0.02ms, 64MB)
 * 테스트 9 〉	통과 (0.01ms, 64.8MB)
 * 테스트 10 〉	통과 (0.01ms, 61.9MB)
 * 테스트 11 〉	통과 (0.01ms, 64.5MB)
 * 테스트 12 〉	통과 (0.01ms, 62.6MB)
 * 테스트 13 〉	통과 (0.71ms, 64.6MB)
 * 테스트 14 〉	통과 (0.01ms, 61.3MB)
 * 테스트 15 〉	통과 (0.01ms, 64.2MB)
 * 테스트 16 〉	통과 (0.01ms, 62.5MB)
 * 테스트 17 〉	통과 (0.71ms, 64.8MB)
 * 테스트 18 〉	통과 (0.01ms, 63.5MB)
 * 테스트 19 〉	통과 (0.01ms, 63.9MB)
 * 테스트 20 〉	통과 (0.69ms, 65.3MB)
 * 테스트 21 〉	통과 (0.01ms, 62.2MB)
 * 테스트 22 〉	통과 (0.01ms, 64.2MB)
 * 테스트 23 〉	통과 (0.64ms, 61.7MB)
 * 테스트 24 〉	통과 (0.01ms, 63.2MB)
 * 테스트 25 〉	통과 (0.01ms, 64.1MB)
 * 테스트 26 〉	통과 (0.01ms, 63.3MB)
 * 테스트 27 〉	통과 (0.03ms, 62.6MB)
 * 테스트 28 〉	통과 (0.01ms, 64.4MB)
 * 테스트 29 〉	통과 (0.01ms, 63.7MB)
 * 테스트 30 〉	통과 (0.01ms, 65.5MB)
 * 테스트 31 〉	통과 (0.01ms, 62.4MB)
 * 테스트 32 〉	통과 (0.01ms, 63.6MB)
 * 테스트 33 〉	통과 (0.01ms, 62.8MB)
 * 테스트 34 〉	통과 (0.01ms, 64.2MB)
 * 테스트 35 〉	통과 (0.97ms, 62.1MB)
 * 테스트 36 〉	통과 (0.01ms, 62.9MB)
 * 테스트 37 〉	통과 (0.01ms, 62.7MB)
 * 테스트 38 〉	통과 (0.01ms, 64.1MB)
 * 테스트 39 〉	통과 (0.01ms, 63.7MB)
 * 테스트 40 〉	통과 (0.01ms, 62.6MB)
 * 테스트 41 〉	통과 (0.01ms, 62.7MB)
 * 테스트 42 〉	통과 (0.01ms, 65.3MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * import kotlin.math.*
 *
 * class Solution {
 *     fun solution(a: Int, b: Int, c: Int, d: Int): Int {
 *         val map = listOf(a, b, c, d).groupingBy { it }.eachCount()
 *         return when (map.maxOf { it.value }) {
 *             4 -> a * 1111
 *
 *             3 -> {
 *                 val p = map.entries.first { it.value == 3 }.key
 *                 val q = map.entries.first { it.value == 1 }.key
 *                 (10 * p + q).let { it * it }
 *             }
 *
 *             2 -> {
 *                 if (map.size == 2) {
 *                     val p = map.entries.first { it.value == 2 }.key
 *                     val q = map.entries.last { it.value == 2 }.key
 *                     (p + q) * abs(p - q)
 *                 } else {
 *                     map.entries.filter { it.value != 2 }
 *                         .map { it.key }
 *                         .reduce { acc, n -> acc * n }
 *                 }
 *             }
 *
 *             else -> map.minOf { it.key }
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (10.14ms, 66.5MB)
 * 테스트 2 〉	통과 (11.07ms, 66.5MB)
 * 테스트 3 〉	통과 (10.27ms, 66.9MB)
 * 테스트 4 〉	통과 (12.10ms, 66.3MB)
 * 테스트 5 〉	통과 (10.33ms, 66.5MB)
 * 테스트 6 〉	통과 (10.06ms, 67MB)
 * 테스트 7 〉	통과 (10.55ms, 66.7MB)
 * 테스트 8 〉	통과 (10.61ms, 66.2MB)
 * 테스트 9 〉	통과 (11.07ms, 66.2MB)
 * 테스트 10 〉	통과 (10.52ms, 66.2MB)
 * 테스트 11 〉	통과 (10.39ms, 66.2MB)
 * 테스트 12 〉	통과 (10.41ms, 66.9MB)
 * 테스트 13 〉	통과 (11.20ms, 66.6MB)
 * 테스트 14 〉	통과 (10.96ms, 66.3MB)
 * 테스트 15 〉	통과 (10.10ms, 67.4MB)
 * 테스트 16 〉	통과 (10.08ms, 67MB)
 * 테스트 17 〉	통과 (12.04ms, 66.6MB)
 * 테스트 18 〉	통과 (12.12ms, 66MB)
 * 테스트 19 〉	통과 (13.66ms, 66.2MB)
 * 테스트 20 〉	통과 (13.87ms, 65.8MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(2, 2, 2, 2), 2222
  )

  validate(
    s.solution(1, 4, 4, 4), 1681
  )

  validate(
    s.solution(4, 1, 4, 4), 1681
  )

  validate(
    s.solution(4, 4, 1, 4), 1681
  )


  validate(
    s.solution(4, 4, 4, 1), 1681
  )

  validate(
    s.solution(6, 6, 3, 3), 27
  )

  validate(
    s.solution(2, 2, 5, 6), 30
  )

  validate(
    s.solution(2, 4, 5, 6), 2
  )

}
