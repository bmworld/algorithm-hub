package 프로그래머스.Lv0.A강조하기

import util.validate

class Solution {
  companion object {

    const val A = 'A'
    const val ALPHABETS = 26
    const val CASE_DIST = 32
    val UPPERCASE = A until A + ALPHABETS
  }

  fun solution(myString: String): String =
    String(CharArray(myString.length) {
      val x = myString[it]
      when (x) {
        'a', A -> A
        in UPPERCASE -> (x.code + CASE_DIST).toChar()
        else -> x
      }
    })
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.9MB)
 * 테스트 2 〉	통과 (0.02ms, 59.1MB)
 * 테스트 3 〉	통과 (0.02ms, 60.4MB)
 * 테스트 4 〉	통과 (0.02ms, 60.1MB)
 *
 * [RIVAL 1]
 * 테스트 1 〉	통과 (5.62ms, 60.4MB)
 * 테스트 2 〉	통과 (5.35ms, 60.9MB)
 * 테스트 3 〉	통과 (5.77ms, 60.5MB)
 * 테스트 4 〉	통과 (5.16ms, 60.9MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("abstract algebra"), "")
  validate(s.solution("PrOgRaMmErS"), "progAammers")
}
