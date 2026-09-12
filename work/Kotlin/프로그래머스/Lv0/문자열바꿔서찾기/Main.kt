package 프로그래머스.Lv0.문자열바꿔서찾기

import util.validate

class Solution {

  fun solution(myString: String, pat: String): Int {
    val N = myString.length
    val M = pat.length

    val a = CharArray(N) { if (myString[it] == 'A') 'B' else 'A' }

    l@ for (i in 0 until N - M + 1) {
      for (j in 0 until M) if (a[i + j] != pat[j]) continue@l
      return 1
    }
    return 0
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
  validate(s.solution("ABBAA", "AABB"), 1)
  validate(s.solution("ABAB", "ABAB"), 0)
}
