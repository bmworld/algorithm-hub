package 프로그래머스.Lv2.괄호변환

import util.validate

class Solution {
  companion object {

    const val O = '('
    const val C = ')'
  }

  fun solution(str: String): String {
    val a = str.toCharArray()

    fun op(l: Int, r: Int) {
      if (l >= r) return

      var o = 0
      var c = 0
      val uStt = a[l]
      if (uStt == O) o++ else c++
      for (i in l + 1..r) {
        if (a[i] == O) o++ else c++
        if (o == c) break
      }

      val vStt = l + o + c

      if (uStt == O) return op(vStt, r)

      val tmp = a.copyOfRange(vStt, r + 1)
      val vCnt = r - vStt + 1
      for (i in l + 1..vStt - 2) a[i + vCnt + 1] = if (a[i] == O) C else O

      a[l] = O
      System.arraycopy(tmp, 0, a, l + 1, vCnt)
      a[l + vCnt + 1] = C

      op(l + 1, l + vCnt)
    }

    op(0, a.size - 1)
    return String(a)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 60.2MB)
 * 테스트 2 〉	통과 (6.59ms, 63.6MB)
 * 테스트 3 〉	통과 (0.01ms, 60.3MB)
 * 테스트 4 〉	통과 (6.72ms, 63.1MB)
 * 테스트 5 〉	통과 (0.04ms, 59.6MB)
 * 테스트 6 〉	통과 (8.42ms, 62.6MB)
 * 테스트 7 〉	실패 (6.16ms, 62.8MB)
 * 테스트 8 〉	통과 (0.02ms, 60MB)
 * 테스트 9 〉	통과 (8.50ms, 63.5MB)
 * 테스트 10 〉	통과 (6.13ms, 63.7MB)
 * 테스트 11 〉	통과 (6.32ms, 61.2MB)
 * 테스트 12 〉	실패 (6.18ms, 63.5MB)
 * 테스트 13 〉	실패 (7.79ms, 63.2MB)
 * 테스트 14 〉	통과 (6.17ms, 63.2MB)
 * 테스트 15 〉	통과 (6.29ms, 64.2MB)
 * 테스트 16 〉	실패 (6.35ms, 63.9MB)
 * 테스트 17 〉	실패 (6.27ms, 64.5MB)
 * 테스트 18 〉	통과 (6.31ms, 64MB)
 * 테스트 19 〉	실패 (6.39ms, 63.9MB)
 * 테스트 20 〉	통과 (7.04ms, 62.8MB)
 * 테스트 21 〉	실패 (6.18ms, 63.1MB)
 * 테스트 22 〉	실패 (6.21ms, 63.8MB)
 * 테스트 23 〉	실패 (6.31ms, 63.6MB)
 * 테스트 24 〉	통과 (6.36ms, 63.7MB)
 * 테스트 25 〉	통과 (6.33ms, 62.4MB)
 * ```
 *
 *
 * ```
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(")"), ")")
  validate(s.solution("()"), "()")
  validate(s.solution(")("), "()")
  validate(s.solution("(()())()"), "(()())()")
  validate(s.solution("()))((()"), "()(())()")
  validate(s.solution("))(("), "()()")

  validate(s.solution("(((()())))()"), "(((()())))()")
  validate(s.solution("()(((()())))"), "()(((()())))")
  validate(s.solution(")))(((()"), "(())(())")
  validate(s.solution("))()()((()"), "(())()()()")
}
