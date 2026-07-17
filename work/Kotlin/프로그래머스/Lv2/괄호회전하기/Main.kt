package 프로그래머스.Lv2.괄호회전하기

import util.validate

class Solution {

  companion object {

    const val op1 = '('
    const val cp1 = ')'

    const val op2 = '['
    const val cp2 = ']'

    const val op3 = '{'
    const val cp3 = '}'

    const val INVALID = 0

    const val PAIR = 'X'

  }


  fun solution(s: String): Int {
    val N = s.length
    val stack = Stack(N)

    var l = 0
    var r = N - 1
    while (l <= r) {
      val c1 = s[l++]
      when (c1) {
        op1, op2, op3 -> stack.push(c1)
        else -> {
          var c2 = PAIR
          while (stack.isNotEmpty() && c2 == PAIR) c2 = stack.pop()
          if (c2 == PAIR) c2 = s[r--]
          if (isPair(c2, c1)) stack.push(PAIR)
          else return INVALID
        }
      }
    }

    return if (stack.isValidStack()) stack.size() else INVALID
  }

  fun isPair(op: Char, cp: Char): Boolean =
    op == op1 && cp == cp1 ||
      op == op2 && cp == cp2 ||
      op == op3 && cp == cp3

  class Stack(N: Int) {

    val stack = CharArray(N)
    var len = 0

    fun size(): Int = len
    fun isNotEmpty(): Boolean = len > 0
    fun push(c: Char) {
      stack[len++] = c
    }

    fun pop(): Char = if (isNotEmpty()) stack[--len]
    else throw Exception("Empty Stack")

    fun isValidStack(): Boolean {
      for (i in 0 until len)
        if (stack[i] != PAIR) return false

      return true
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.41ms, 58.8MB)
 * 테스트 2 〉	실패 (0.23ms, 59.3MB)
 * 테스트 3 〉	실패 (0.22ms, 59.9MB)
 * 테스트 4 〉	실패 (0.22ms, 57.9MB)
 * 테스트 5 〉	실패 (0.24ms, 59.2MB)
 * 테스트 6 〉	실패 (0.22ms, 59.8MB)
 * 테스트 7 〉	실패 (0.19ms, 59.8MB)
 * 테스트 8 〉	실패 (0.23ms, 60.1MB)
 * 테스트 9 〉	실패 (0.24ms, 59.8MB)
 * 테스트 10 〉	통과 (0.40ms, 59.5MB)
 * 테스트 11 〉	통과 (0.33ms, 60.7MB)
 * 테스트 12 〉	통과 (0.18ms, 59.6MB)
 * 테스트 13 〉	통과 (0.19ms, 60.4MB)
 * 테스트 14 〉	통과 (0.19ms, 60.7MB)
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
  validate(s.solution("][])[({{}{}})({{}{}})](["), 2)
}

//      println("[${l - 1}, $r] $c1")
