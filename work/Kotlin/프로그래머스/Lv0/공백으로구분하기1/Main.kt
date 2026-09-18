package 프로그래머스.Lv0.공백으로구분하기1

import util.validate

class Solution {
  companion object {

    const val MAX_LEN = 1_000
    const val SPACE = 32
  }

  fun solution(my_string: String): Array<String> {
    val tmp = Array(MAX_LEN / 2) { "" }
    var ai = 0

    val word = CharArray(MAX_LEN)
    var wi = 0
    for (x in my_string) {
      when (x.code) {
        SPACE -> if (wi > 0) {
          tmp[ai++] = String(word, 0, wi)
          wi = 0
        }
        else -> word[wi++] = x
      }
    }
    if (wi > 0) tmp[ai++] = String(word, 0, wi)

    return Array(ai) { tmp[it] }
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
  validate(s.solution(" i    love  you"), arrayOf("i", "love", "you"))
  validate(s.solution("    programmers  "), arrayOf("programmers"))
  validate(s.solution("   i  s "), arrayOf("i", "s"))
  validate(s.solution("i kk "), arrayOf("i", "kk"))
  validate(s.solution(" hello "), arrayOf("hello"))
}
