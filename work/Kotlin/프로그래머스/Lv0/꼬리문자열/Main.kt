package 프로그래머스.Lv0.꼬리문자열

import util.validate

class Solution {
  companion object {

    const val MAX_LEN = 100
  }

  fun solution(str_list: Array<String>, ex: String): String {
    val tmp = CharArray(MAX_LEN)
    val M = ex.length
    var len = 0

    l@ for (str in str_list) {

      val N = str.length
      validator@ for (i in 0..N - M) {
        for (j in 0 until M) if (str[i + j] != ex[j]) continue@validator
        continue@l
      }
      str.toCharArray(tmp, len, 0, N)
      len += N
    }

    return String(tmp.copyOf(len))
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.5MB)
 * 테스트 2 〉	통과 (0.02ms, 60.4MB)
 * 테스트 3 〉	통과 (0.03ms, 60.5MB)
 * 테스트 4 〉	통과 (0.02ms, 60.1MB)
 * 테스트 5 〉	통과 (0.03ms, 60MB)
 * 테스트 6 〉	통과 (0.02ms, 59.9MB)
 * 테스트 7 〉	통과 (0.02ms, 60.3MB)
 * v2:
 * 테스트 1 〉	통과 (0.02ms, 59MB)
 * 테스트 2 〉	통과 (0.03ms, 59.4MB)
 * 테스트 3 〉	통과 (0.02ms, 58MB)
 * 테스트 4 〉	통과 (0.03ms, 58.3MB)
 * 테스트 5 〉	통과 (0.02ms, 60.4MB)
 * 테스트 6 〉	통과 (0.03ms, 59.5MB)
 * 테스트 7 〉	통과 (0.03ms, 60.7MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(strList: Array<String>, ex: String) = strList.filter { !it.contains(ex) }.joinToString("")
 * }
 * 테스트 1 〉	통과 (8.03ms, 61.6MB)
 * 테스트 2 〉	통과 (8.96ms, 61.4MB)
 * 테스트 3 〉	통과 (8.88ms, 61.5MB)
 * 테스트 4 〉	통과 (8.95ms, 60.8MB)
 * 테스트 5 〉	통과 (8.01ms, 61.4MB)
 * 테스트 6 〉	통과 (9.12ms, 61.4MB)
 * 테스트 7 〉	통과 (8.25ms, 61.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf("abc", "def", "ghi"), "ef"), "abcghi")
  validate(s.solution(arrayOf("abc", "bbc", "cbc"), "c"), "")
  validate(s.solution(arrayOf("kabc", "dkef", "a"), "k"), "a")
}
