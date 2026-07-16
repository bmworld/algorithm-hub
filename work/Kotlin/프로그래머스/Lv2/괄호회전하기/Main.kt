package 프로그래머스.Lv2.괄호회전하기

import util.validate

class Solution {

  val op1 = '('
  val cp1 = ')'

  val op2 = '['
  val cp2 = ']'

  val op3 = '{'
  val cp3 = '}'
  val INVALID = 0
  fun solution(s: String): Int {
    var cnt = 0

    val stack = CharArray(s.length)
    var si = 0
    var cracked = 0

    fun push(c: Char) {
      stack[si++] = c
    }

    fun pushCracked(c: Char) {
      cracked++
      push(c)
    }


    for (i in 0 until s.length) {
      val cur = s[i]
      when {
        cur == op1 ||
          cur == op2 ||
          cur == op3 -> push(cur)
        si == 0 -> pushCracked(cur)
        else -> {
          val prv = stack[si - 1]
          when {
            prv == cp1 ||
              prv == cp2 ||
              prv == cp3 -> pushCracked(cur)
            isPair(prv, cur) -> if (--si == cracked) cnt++
            else -> return INVALID
          }
        }
      }
    }

    for (i in 0 until cracked) {
      val j = si - (i + 1)
      val cp = stack[i]
      val op = stack[j]
      if (isPair(op, cp)) cracked--
      else return INVALID
    }

    if (si > 0 && cracked == 0) cnt++


    return if (cracked > 0) INVALID else cnt
  }

  private fun isPair(op: Char, cp: Char): Boolean =
    op == op1 && cp == cp1 ||
      op == op2 && cp == cp2 ||
      op == op3 && cp == cp3
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	실패 (0.25ms, 59.7MB)
 * 테스트 2 〉	통과 (0.23ms, 59.7MB)
 * 테스트 3 〉	실패 (0.24ms, 60.7MB)
 * 테스트 4 〉	실패 (0.22ms, 59.3MB)
 * 테스트 5 〉	실패 (0.24ms, 59.7MB)
 * 테스트 6 〉	통과 (0.24ms, 58.9MB)
 * 테스트 7 〉	통과 (0.28ms, 59.8MB)
 * 테스트 8 〉	통과 (0.27ms, 60MB)
 * 테스트 9 〉	실패 (0.23ms, 59.5MB)
 * 테스트 10 〉	실패 (0.24ms, 59.6MB)
 * 테스트 11 〉	통과 (0.26ms, 59.9MB)
 * 테스트 12 〉	통과 (0.16ms, 61.1MB)
 * 테스트 13 〉	실패 (0.17ms, 60.4MB)
 * 테스트 14 〉	통과 (0.16ms, 61.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(")"), 0)
  validate(s.solution("[](){}"), 3)
  validate(s.solution("[)(]"), 0)
  validate(s.solution("}}}"), 0)
  validate(s.solution("}]()[{"), 2)
  validate(s.solution("}()["), 0)
  validate(s.solution("{{}]"), 0)
  validate(s.solution("{{}}()"), 2)
  validate(s.solution("}{{}}[()]{"), 3)
}
