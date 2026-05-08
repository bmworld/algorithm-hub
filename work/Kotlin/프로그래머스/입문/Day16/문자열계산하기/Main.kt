package 프로그래머스.입문.Day16.문자열계산하기

class Solution {

  val EMPTY = -1
  val SPACE = 32
  val PLUS = 43
  val MINUS = 45
  val ZERO = 48
  val NUM = ZERO..ZERO + 9
  fun solution(str: String): Int {
    var ans = 0

    var s = 1
    var prv = EMPTY
    var cur = 0

    for (i in 0..str.length) {
      if (i == str.length) ans += prv + s * cur else {
        val c = str[i].code
        when (c) {
          PLUS, MINUS -> {
            if (c == MINUS) s = -1
            prv = cur
            cur = 0
          }
          SPACE -> if (str[i - 1].code >= ZERO && prv != EMPTY) {
            ans += prv + s * cur
            prv = ans
            cur = 0
            s = 1
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

/**
 * AS IS
 * 테스트 1 〉	통과 (0.03ms, 60.5MB)
 * 테스트 2 〉	통과 (0.03ms, 60.6MB)
 * 테스트 3 〉	통과 (0.03ms, 61.5MB)
 * 테스트 4 〉	통과 (0.03ms, 61MB)
 * 테스트 5 〉	통과 (0.03ms, 60.8MB)
 * 테스트 6 〉	통과 (0.04ms, 63.1MB)
 * 테스트 7 〉	통과 (0.03ms, 62.5MB)
 * 테스트 8 〉	통과 (0.02ms, 60.9MB)
 * 테스트 9 〉	통과 (0.02ms, 62.2MB)
 * 테스트 10 〉	통과 (0.03ms, 62.1MB)
 *
 * TO BE
 * 테스트 1 〉	통과 (0.02ms, 64.6MB)
 * 테스트 2 〉	통과 (0.03ms, 61.7MB)
 * 테스트 3 〉	통과 (0.03ms, 60.6MB)
 * 테스트 4 〉	통과 (0.03ms, 60.9MB)
 * 테스트 5 〉	통과 (0.03ms, 61MB)
 * 테스트 6 〉	통과 (0.02ms, 61.7MB)
 * 테스트 7 〉	통과 (0.02ms, 61.9MB)
 * 테스트 8 〉	통과 (0.02ms, 63MB)
 * 테스트 9 〉	통과 (0.02ms, 62.8MB)
 * 테스트 10 〉	통과 (0.02ms, 61.3MB)
 *
 */
