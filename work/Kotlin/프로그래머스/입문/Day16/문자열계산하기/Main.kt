package 프로그래머스.입문.Day16.문자열계산하기

class Solution {

  val EMPTY = -1
  val SPACE = 32
  val PLUS = 43
  val MINUS = 45
  val ZERO = 48
  val NUM = ZERO..ZERO + 9
  fun solution(s: String): Int {
    var ans = 0

    var op = EMPTY
    var prv = EMPTY
    var cur = 0

    for (i in 0..s.length) {
      if (i == s.length) ans += op(prv, cur, op) else {
        val c = s[i].code
        when (c) {
          PLUS, MINUS -> {
            op = c
            prv = cur
            cur = 0
          }
          SPACE -> if (s[i - 1].code >= ZERO && prv != EMPTY) {
            ans += op(prv, cur, op)
            prv = ans
            cur = 0
          }
          in NUM -> cur = cur * 10 + c - ZERO
        }
      }
    }

    return ans
  }

  fun op(n1: Int, n2: Int, op: Int): Int = when (op) {
    PLUS -> n1 + n2
    else -> n1 - n2
  }
}

fun main() {
  val s = Solution()
  check(s.solution("3 + 4") == 7)
  check(s.solution("3 + 4 - 8") == -1)
  check(s.solution("190 - 100") == 90)
  check(s.solution("1 - 100 + 99 + 72") == 72)
}
