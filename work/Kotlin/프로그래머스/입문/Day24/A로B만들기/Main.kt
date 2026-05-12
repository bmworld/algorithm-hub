package 프로그래머스.입문.Day24.A로B만들기

import util.validate

class Solution {

  val POSSIBLE = 1
  val IMPOSSIBLE = 0

  fun solution(before: String, after: String): Int {
    val len = before.length
    val half = (len + 1) / 2
    for (i in 0..half) if (before[i] != after[len - (i + 1)]) return IMPOSSIBLE
    return POSSIBLE
  }
}

/**
 * ```
 * ME:

 * ```
 * ```
 * RIVAL:

 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("aak", "aka"), 1)
  validate(s.solution("a", "b"), 0)
  validate(s.solution("abc", "cia"), 0)
  validate(s.solution("abc", "cba"), 1)
  validate(s.solution("olleh", "hello"), 1)
  validate(s.solution("hello", "helln"), 0)
}

//      println("[${ansLen - (it + 1)}] $d1 + $d2 + $caret -> ${a[ansLen - (it + 1)]}")
