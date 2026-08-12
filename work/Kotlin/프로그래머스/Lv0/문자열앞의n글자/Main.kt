package 프로그래머스.Lv0.문자열앞의n글자

import util.validate

class Solution {

  fun solution(my_string: String, n: Int): String =
    String(CharArray(n) { my_string[it] })
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
  validate(s.solution("ProgrammerS123", 11), "ProgrammerS")
  validate(s.solution("HAD13", 1), "H")
  validate(s.solution("HAD13", 2), "HA")
}
