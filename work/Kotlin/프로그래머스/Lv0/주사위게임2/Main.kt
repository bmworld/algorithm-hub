package 프로그래머스.Lv0.주사위게임2

import util.validate

class Solution {

  fun solution(a: Int, b: Int, c: Int): Int =
    when {
      a != b && b != c && a != c -> a + b + c
      a == b && b == c -> (a + b + c) * (a * a + b * b + c * c) * (a * a * a + b * b * b + c * c * c)
      else -> (a + b + c) * (a * a + b * b + c * c)
    }
}

/**
 * ```
 * [ME]
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(2, 6, 1), 9)
  validate(s.solution(5, 3, 3), 473)
  validate(s.solution(4, 4, 4), 110592)
  validate(s.solution(6, 6, 6), 1259712)
}
