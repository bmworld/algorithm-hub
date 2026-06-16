package 프로그래머스.Lv1.하샤드수

import util.validate

class Solution {

  fun solution(x: Int): Boolean {
    var sum = 0
    var y = x
    while (y > 0) {
      sum += y % 10
      y /= 10
    }

    return x % sum == 0
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
  validate(s.solution(10), true)
  validate(s.solution(12), true)
  validate(s.solution(11), false)
  validate(s.solution(13), false)

}
