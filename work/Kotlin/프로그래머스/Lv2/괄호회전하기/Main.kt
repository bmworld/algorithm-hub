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
  val NOT_FOUND = -1

  fun solution(s: String): Int {
    var cnt = 0

    val N = s.length
    val stack = CharArray(N)
    var stacked = 0

    fun push(c: Char) {
      if (stacked == 0) cnt++
      stack[stacked++] = c
    }

    fun isOp(c: Char): Boolean = c == op1 || c == op2 || c == op3
    fun isCp(c: Char): Boolean = c == cp1 || c == cp2 || c == cp3
    fun isPair(op: Char, cp: Char): Boolean =
      op == op1 && cp == cp1 ||
        op == op2 && cp == cp2 ||
        op == op3 && cp == cp3

    var fr = NOT_FOUND
    for (i in 1 until N) {
      val cur = s[i]
      if (isOp(cur) && isCp(s[i - 1])) {
        fr = i
        break
      }
    }

    if (fr == NOT_FOUND) fr = 0
    val to = N - 1 + fr
    for (i in fr..to) {
      val j = i - if (i >= N) N else 0
      val cur = s[j]
      when {
        isOp(cur) -> push(cur)
        stacked > 0 && isPair(stack[stacked - 1], cur) -> stacked--
        else -> return INVALID
      }
    }
    return if (stacked > 0) INVALID else cnt
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.27ms, 59.4MB)
 * 테스트 2 〉	통과 (0.34ms, 60.6MB)
 * 테스트 3 〉	실패 (0.16ms, 59.7MB)
 * 테스트 4 〉	실패 (0.14ms, 60.3MB)
 * 테스트 5 〉	실패 (0.20ms, 60.3MB)
 * 테스트 6 〉	통과 (0.32ms, 60.2MB)
 * 테스트 7 〉	통과 (0.33ms, 59.6MB)
 * 테스트 8 〉	통과 (0.26ms, 61.3MB)
 * 테스트 9 〉	실패 (0.16ms, 59.8MB)
 * 테스트 10 〉	실패 (0.14ms, 59.9MB)
 * 테스트 11 〉	통과 (0.24ms, 59.5MB)
 * 테스트 12 〉	통과 (0.19ms, 60.3MB)
 * 테스트 13 〉	통과 (0.15ms, 60.1MB)
 * 테스트 14 〉	통과 (0.17ms, 59.9MB)
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

//      println("s[$j] = $cur")
