package 프로그래머스.Lv0.수조작하기1

import util.validate

class Solution {

  fun solution(n: Int, control: String): Int {
    var ans = n.toLong()

    for (x in control) {
      when (x) {
        'w' -> ans += 1
        's' -> ans -= 1
        'd' -> ans += 10
        'a' -> ans -= 10
      }
    }
    return ans.toInt()
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
  validate(s.solution(0, "wsdawsdassw"), -1)
}
