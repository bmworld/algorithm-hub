package 프로그래머스.Lv0._5명씩

import util.validate

class Solution {

  fun solution(names: Array<String>): Array<String> =
    Array((names.size + 4) / 5) { names[it * 5] }
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
  validate(s.solution(
    arrayOf("nami", "ahri", "jayce", "garen", "ivern", "vex", "jinx")),
    arrayOf("nami", "vex")
  )

  validate(s.solution(
    arrayOf("nami", "ahri")),
    arrayOf("nami")
  )
}
