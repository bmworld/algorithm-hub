package 프로그래머스.Lv0.rny_string

import util.validate

class Solution {
  companion object {

    val rn = charArrayOf('r', 'n')
    const val replaced = 2
  }

  fun solution(str: String): String {
    val tmp = CharArray(str.length * replaced)
    var len = 0
    for (x in str) {
      when (x) {
        'm' -> {
          System.arraycopy(rn, 0, tmp, len, replaced)
          len += replaced
        }
        else -> tmp[len++] = x
      }
    }

    return String(tmp.copyOf(len))
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.1MB)
 * 테스트 2 〉	통과 (0.02ms, 59.5MB)
 * 테스트 3 〉	통과 (0.02ms, 60.4MB)
 * 테스트 4 〉	통과 (0.02ms, 60.1MB)
 * 테스트 5 〉	통과 (0.02ms, 58.2MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(rnyString: String) = rnyString.replace("m", "rn")
 * }
 * 테스트 1 〉	통과 (5.23ms, 61.2MB)
 * 테스트 2 〉	통과 (4.48ms, 59.6MB)
 * 테스트 3 〉	통과 (4.90ms, 61MB)
 * 테스트 4 〉	통과 (7.32ms, 61.6MB)
 * 테스트 5 〉	통과 (5.74ms, 59.7MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("masterpiece"), "rnasterpiece")
  validate(s.solution("hello"), "hello")
}
