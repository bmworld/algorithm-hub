package 프로그래머스.Lv0.문자열로변환

import util.validate

class Solution {
  companion object {

    const val MAX_LEN = 10
    const val ZERO = 48
  }

  fun solution(n: Int): String {
    val ans = CharArray(MAX_LEN)
    var i = MAX_LEN

    var x = n
    while (x > 0) {
      ans[--i] = (x % 10 + ZERO).toChar()
      x /= 10
    }

    return String(ans.copyOfRange(i, MAX_LEN))
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (6.30ms, 64.3MB)
 * 테스트 2 〉	통과 (6.62ms, 63.7MB)
 * 테스트 3 〉	통과 (6.29ms, 63.4MB)
 * 테스트 4 〉	통과 (6.32ms, 63.9MB)
 * 테스트 5 〉	통과 (7.08ms, 63.9MB)
 * 테스트 6 〉	통과 (7.20ms, 62MB)
 * 테스트 7 〉	통과 (11.53ms, 61.9MB)
 * 테스트 8 〉	통과 (7.56ms, 61MB)
 * 테스트 9 〉	통과 (6.44ms, 63.3MB)
 * 테스트 10 〉	통과 (6.56ms, 62.3MB)
 *
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1), "1")
  validate(s.solution(123), "123")
  validate(s.solution(2553), "2553")
}
