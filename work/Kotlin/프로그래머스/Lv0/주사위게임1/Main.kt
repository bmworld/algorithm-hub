package 프로그래머스.Lv0.주사위게임1

import util.validate

class Solution {

  fun solution(a: Int, b: Int): Int =
    when {
      a % 2 == 1 && b % 2 == 1 -> a * a + b * b
      a % 2 == 1 || b % 2 == 1 -> 2 * (a + b)
      else -> if (a > b) a - b else b - a
    }
}

/**
 * ```
 * [ME]
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(3, 5), 34)
  validate(s.solution(6, 1), 14)
  validate(s.solution(3, 6), 18)
  validate(s.solution(2, 4), 2)
  validate(s.solution(4, 2), 2)
}
