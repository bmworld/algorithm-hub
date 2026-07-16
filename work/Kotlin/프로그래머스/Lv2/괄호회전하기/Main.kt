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

    val N = s.length
    val stack = CharArray(N)
    var stacked = 0

    fun push(c: Char) {
      stack[stacked++] = c
    }

    fun isOp(c: Char): Boolean = c == op1 || c == op2 || c == op3
    fun isCp(c: Char): Boolean = c == cp1 || c == cp2 || c == cp3
    fun isPair(op: Char, cp: Char): Boolean =
      op == op1 && cp == cp1 ||
        op == op2 && cp == cp2 ||
        op == op3 && cp == cp3

    var cur = 0
    var end = N - 1

    while (cur <= end) {
      val c = s[cur]
      when {
        isOp(c) -> push(c)
        else -> {
          val prv = if (stacked == 0) end else stacked - 1
          if (!isPair(prvChar(stack, stacked, s, end), c)) return INVALID
          else if (prv < cur) stacked-- else end--

          if (isCp(prvChar(stack, stacked, s, end))) cnt++
        }
      }

      cur++
    }

    return if (stacked > 0) INVALID else cnt
  }

  private fun prvChar(stack: CharArray, stacked: Int, str: String,
    end: Int): Char = if (stacked == 0) str[end] else stack[stacked - 1]
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.33ms, 59.7MB)
 * 테스트 2 〉	실패 (0.19ms, 60.3MB)
 * 테스트 3 〉	실패 (0.17ms, 60.3MB)
 * 테스트 4 〉	실패 (0.14ms, 59.7MB)
 * 테스트 5 〉	실패 (0.17ms, 60MB)
 * 테스트 6 〉	실패 (0.15ms, 59.8MB)
 * 테스트 7 〉	실패 (0.18ms, 60MB)
 * 테스트 8 〉	실패 (0.17ms, 60.4MB)
 * 테스트 9 〉	실패 (0.16ms, 59.3MB)
 * 테스트 10 〉	통과 (0.81ms, 60MB)
 * 테스트 11 〉	통과 (0.51ms, 60.5MB)
 * 테스트 12 〉	통과 (0.17ms, 60.2MB)
 * 테스트 13 〉	통과 (0.16ms, 59.9MB)
 * 테스트 14 〉	통과 (0.15ms, 59.5MB)
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
  validate(s.solution("(()())"), 1)
  validate(s.solution("()()"), 2)
  validate(s.solution("()"), 1)
  validate(s.solution("())))((("), 1)
  validate(s.solution("())))(((("), 0)
  validate(s.solution("()))))((("), 0)
  validate(s.solution(")("), 1)
  validate(s.solution("))("), 0)
  validate(s.solution(")(("), 0)
  validate(s.solution("[](){}"), 3)
  validate(s.solution("[)(]"), 0)
  validate(s.solution("}}}"), 0)
  validate(s.solution("}]()[{"), 2)
  validate(s.solution("}()["), 0)
  validate(s.solution("{{}]"), 0)
  validate(s.solution("{{}}()"), 2)
  validate(s.solution("}{{}}[()]{"), 3)
}
