package 프로그래머스.Lv0.코드처리하기

import util.validate

class Solution {

  companion object {

    const val TOGGLE_MODE = '1'
  }

  fun solution(code: String): String {
    val N = code.length
    val tmp = CharArray(N)
    var len = 0
    var mode = 0
    for (i in 0 until N) {
      val x = code[i]
      when {
        x == TOGGLE_MODE -> mode = if (mode == 0) 1 else 0
        else -> {
          val rmn = i % 2
          if (mode == 0 && rmn == 0 || mode == 1 && rmn == 1)
            tmp[len++] = x
        }
      }
    }

    val ans = CharArray(len)
    System.arraycopy(tmp, 0, ans, 0, len)
    return String(ans)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (3.01ms, 61.3MB)
 * 테스트 2 〉	통과 (2.85ms, 60.5MB)
 * 테스트 3 〉	통과 (0.69ms, 59.8MB)
 * 테스트 4 〉	통과 (0.45ms, 60.7MB)
 * 테스트 5 〉	통과 (1.50ms, 60.2MB)
 * 테스트 6 〉	통과 (1.86ms, 60.1MB)
 * 테스트 7 〉	통과 (1.45ms, 59.7MB)
 * 테스트 8 〉	통과 (2.64ms, 61.1MB)
 * 테스트 9 〉	통과 (2.00ms, 60.8MB)
 * 테스트 10 〉	통과 (2.81ms, 59.3MB)
 * 테스트 11 〉	통과 (2.31ms, 61.8MB)
 * 테스트 12 〉	실패 (0.01ms, 60.5MB)
 * 테스트 13 〉	실패 (0.01ms, 60.2MB)
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
  validate(s.solution("abc1abc1abc"), "acbac")
  validate(s.solution("abcd"), "ac")
  validate(s.solution("1bcd"), "bd")
  validate(s.solution("1111"), "")
  validate(s.solution("1a"), "a")
  validate(s.solution("1a"), "")
}
