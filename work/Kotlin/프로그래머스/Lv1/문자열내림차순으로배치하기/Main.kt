package 프로그래머스.Lv1.문자열내림차순으로배치하기

import util.validate

class Solution {

  private val a = 97
  private val A = 65
  val ALPHABETS = 26
  val lowercase = a until a + ALPHABETS
  fun solution(s: String): String {
    val lower = IntArray(ALPHABETS)
    val upper = IntArray(ALPHABETS)
    for (x in s) {
      val code = x.code
      when {
        code in lowercase -> lower[code - a]++
        else -> upper[code - A]++
      }
    }
    var i = 0
    val chars = CharArray(s.length)
    repeat(ALPHABETS) {
      val j = ALPHABETS - (it + 1)
      val cnt = lower[j]
      repeat(cnt) {
        chars[i++] = (j + a).toChar()
      }
    }
    repeat(ALPHABETS) {
      val j = ALPHABETS - (it + 1)
      val cnt = upper[j]
      repeat(cnt) {
        chars[i++] = (j + A).toChar()
      }
    }

    return String(chars)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 58.4MB)
 * 테스트 2 〉	통과 (0.03ms, 59.6MB)
 * 테스트 3 〉	통과 (0.02ms, 60.1MB)
 * 테스트 4 〉	통과 (0.02ms, 60.1MB)
 * 테스트 5 〉	통과 (0.08ms, 58.5MB)
 * 테스트 6 〉	통과 (0.06ms, 57.8MB)
 * 테스트 7 〉	통과 (0.02ms, 61.1MB)
 * 테스트 8 〉	통과 (0.03ms, 58.8MB)
 * 테스트 9 〉	통과 (0.03ms, 59.1MB)
 * 테스트 10 〉	통과 (0.03ms, 59.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String): String = String(s.toCharArray().sortedArrayDescending())
 * }
 * 테스트 1 〉	통과 (9.19ms, 63MB)
 * 테스트 2 〉	통과 (8.83ms, 61.6MB)
 * 테스트 3 〉	통과 (8.93ms, 62.2MB)
 * 테스트 4 〉	통과 (9.19ms, 61.5MB)
 * 테스트 5 〉	통과 (9.34ms, 62MB)
 * 테스트 6 〉	통과 (8.78ms, 61.6MB)
 * 테스트 7 〉	통과 (11.69ms, 62.4MB)
 * 테스트 8 〉	통과 (8.83ms, 61.6MB)
 * 테스트 9 〉	통과 (9.78ms, 61.8MB)
 * 테스트 10 〉	통과 (8.88ms, 62.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("Zbcdefg"), "gfedcbZ")
  validate(s.solution("aAZz"), "zaZA")
  validate(s.solution("aAhZZz"), "zhaZZA")
}
