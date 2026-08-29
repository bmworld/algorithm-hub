package 프로그래머스.Lv0.배열에서문자열대소문자변환하기

import util.validate

class Solution {

  fun solution(strArr: Array<String>): Array<String> =
    Array(
      strArr.size) { if (it % 2 == 0) strArr[it].lowercase() else strArr[it].uppercase() }
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
  validate(s.solution(arrayOf("AAA", "BBB", "CCC", "DDD")), arrayOf("aaa", "BBB", "ccc", "DDD"))
  validate(s.solution(arrayOf("aBc", "Abc")), arrayOf("abc", "ABC"))
}
