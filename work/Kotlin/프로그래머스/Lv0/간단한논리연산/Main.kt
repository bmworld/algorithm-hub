package 프로그래머스.Lv0.간단한논리연산

import util.validate

class Solution {

  fun solution(x1: Boolean, x2: Boolean, x3: Boolean, x4: Boolean): Boolean =
    (x1 or x2) and (x3 or x4)
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 60MB)
 * 테스트 2 〉	통과 (0.01ms, 60.5MB)
 * 테스트 3 〉	통과 (0.01ms, 59.9MB)
 * 테스트 4 〉	통과 (0.01ms, 58.6MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(false, false, true, true), false)
  validate(s.solution(false, true, true, true), true)
  validate(s.solution(false, true, false, true), false)
}
