package 프로그래머스.Lv0.코드처리하기

import util.validate

class Solution {

  companion object {

    const val TOGGLE_MODE = '1'
    const val EMPTY = "EMPTY"
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

    if (len == 0) return EMPTY

    val ans = CharArray(len)
    System.arraycopy(tmp, 0, ans, 0, len)
    return String(ans)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (3.16ms, 60.8MB)
 * 테스트 2 〉	통과 (3.29ms, 59.2MB)
 * 테스트 3 〉	통과 (0.67ms, 60.6MB)
 * 테스트 4 〉	통과 (0.46ms, 58.8MB)
 * 테스트 5 〉	통과 (1.98ms, 60.8MB)
 * 테스트 6 〉	통과 (1.88ms, 60.6MB)
 * 테스트 7 〉	통과 (1.96ms, 58.3MB)
 * 테스트 8 〉	통과 (2.42ms, 60.9MB)
 * 테스트 9 〉	통과 (2.55ms, 60.2MB)
 * 테스트 10 〉	통과 (2.71ms, 62MB)
 * 테스트 11 〉	통과 (2.22ms, 61.1MB)
 * 테스트 12 〉	통과 (0.01ms, 60.4MB)
 * 테스트 13 〉	통과 (0.01ms, 60.3MB)
 *
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(code: String): String {
 *         var mode = 0
 *         var answer = ""
 *         code.forEachIndexed { i, v ->
 *             if (v == '1') mode = mode xor 1
 *             else if (mode == 0 && i % 2 == 0) answer += v
 *             else if (mode == 1 && i % 2 != 0) answer += v
 *         }
 *         return if (answer.isNotEmpty()) answer else "EMPTY"
 *     }
 * }
 * 테스트 1 〉	통과 (172.96ms, 361MB)
 * 테스트 2 〉	통과 (131.03ms, 312MB)
 * 테스트 3 〉	통과 (13.86ms, 87.1MB)
 * 테스트 4 〉	통과 (9.02ms, 72.6MB)
 * 테스트 5 〉	통과 (47.20ms, 149MB)
 * 테스트 6 〉	통과 (69.53ms, 226MB)
 * 테스트 7 〉	통과 (38.25ms, 144MB)
 * 테스트 8 〉	통과 (122.25ms, 312MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("abc1abc1abc"), "acbac")
  validate(s.solution("abcd"), "ac")
  validate(s.solution("1bcd"), "bd")
  validate(s.solution("1a"), "a")
  validate(s.solution("1111"), "EMPTY")
}
