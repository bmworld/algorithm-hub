package 프로그래머스.Lv1.약수의개수와덧셈

import util.validate

class Solution {

  fun solution(left: Int, right: Int): Int {
    var ans = rangeSum(right) - rangeSum(left - 1)

    var d = 1
    while (d <= right / d) {
      val pow = d * d
      if (pow in left..right) ans -= pow * 2
      d++
    }
    return ans
  }

  fun rangeSum(end: Int): Int = end * (end + 1) / 2
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.6MB)
 * 테스트 2 〉	통과 (0.01ms, 58.4MB)
 * 테스트 3 〉	통과 (0.01ms, 59.8MB)
 * 테스트 4 〉	통과 (0.01ms, 57.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(left: Int, right: Int): Int {
 *         return (left..right).map { i -> if ((1..i).filter { i % it == 0 }.size % 2 == 0) i else -i }.sum()
 *     }
 * }
 * 테스트 1 〉	통과 (14.18ms, 65.7MB)
 * 테스트 2 〉	통과 (9.46ms, 60.9MB)
 * 테스트 3 〉	통과 (10.09ms, 60.5MB)
 * 테스트 4 〉	통과 (7.08ms, 58.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1, 1), -1)
  validate(s.solution(13, 17), 43)
  validate(s.solution(24, 27), 52)
}
