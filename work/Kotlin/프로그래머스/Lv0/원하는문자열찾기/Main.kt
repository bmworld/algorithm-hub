package 프로그래머스.Lv0.원하는문자열찾기

import util.validate

class Solution {

  fun solution(str: String, pat: String): Int {
    val a = str.lowercase()
    val b = pat.lowercase()
    val N = str.length
    val M = pat.length

    l@ for (i in 0 until N - M + 1) {
      for (j in 0 until M) if (a[i + j] != b[j]) continue@l
      return 1
    }

    return 0
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.03ms, 59.4MB)
 * 테스트 2 〉	통과 (0.03ms, 60MB)
 * 테스트 3 〉	통과 (0.03ms, 57.8MB)
 * 테스트 4 〉	통과 (0.03ms, 58.8MB)
 * 테스트 5 〉	통과 (0.02ms, 57.8MB)
 * 테스트 6 〉	통과 (0.06ms, 59.6MB)
 * 테스트 7 〉	통과 (0.04ms, 59.8MB)
 * 테스트 8 〉	통과 (0.02ms, 59.6MB)
 * 테스트 9 〉	통과 (0.03ms, 60.5MB)
 * 테스트 10 〉	통과 (0.02ms, 59.3MB)
 * 테스트 11 〉	통과 (0.06ms, 60.4MB)
 * 테스트 12 〉	통과 (0.06ms, 58.5MB)
 * 테스트 13 〉	통과 (0.09ms, 59.2MB)
 * 테스트 14 〉	통과 (3.62ms, 60.2MB)
 * 테스트 15 〉	통과 (0.75ms, 60.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(myString: String, pat: String) = if (myString.lowercase().contains(pat.lowercase())) 1 else 0
 * }
 * 테스트 1 〉	통과 (5.92ms, 61.1MB)
 * 테스트 2 〉	통과 (5.53ms, 60.7MB)
 * 테스트 3 〉	통과 (4.51ms, 61.1MB)
 * 테스트 4 〉	통과 (4.33ms, 60.3MB)
 * 테스트 5 〉	통과 (4.41ms, 60.8MB)
 * 테스트 6 〉	통과 (4.64ms, 60.5MB)
 * 테스트 7 〉	통과 (6.09ms, 62MB)
 * 테스트 8 〉	통과 (4.34ms, 61.1MB)
 * 테스트 9 〉	통과 (4.37ms, 61.5MB)
 * 테스트 10 〉	통과 (4.29ms, 61.3MB)
 * 테스트 11 〉	통과 (4.57ms, 60.3MB)
 * 테스트 12 〉	통과 (4.38ms, 61MB)
 * 테스트 13 〉	통과 (6.30ms, 60.2MB)
 * 테스트 14 〉	통과 (8.56ms, 61.6MB)
 * 테스트 15 〉	통과 (5.14ms, 60.4MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("AbCdEfG", "aBc"), 1)
  validate(s.solution("aaAA", "aaaaa"), 0)
  validate(s.solution("abc", "c"), 1)
  validate(s.solution("abc", "C"), 1)
  validate(s.solution("abcol", "COL"), 1)
  validate(s.solution("abco", "CJ"), 0)
}
