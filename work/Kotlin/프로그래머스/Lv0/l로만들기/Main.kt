package 프로그래머스.Lv0.l로만들기

import util.validate

class Solution {

  fun solution(myString: String): String =
    String(CharArray(myString.length) {
      val c = myString[it]
      when {
        c.code <= 'l'.code -> 'l'
        else -> c
      }
    })
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (2.59ms, 61.7MB)
 * 테스트 2 〉	통과 (2.39ms, 60.9MB)
 * 테스트 3 〉	통과 (0.77ms, 61.3MB)
 * 테스트 4 〉	통과 (0.53ms, 60MB)
 * 테스트 5 〉	통과 (1.66ms, 61.1MB)
 * 테스트 6 〉	통과 (1.93ms, 59.4MB)
 * 테스트 7 〉	통과 (1.66ms, 60.3MB)
 * 테스트 8 〉	통과 (2.24ms, 60.1MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(myString: String) = myString.replace("[a-k]".toRegex(), "l")
 * }
 * 테스트 1 〉	통과 (6.93ms, 61.2MB)
 * 테스트 2 〉	통과 (6.72ms, 62.1MB)
 * 테스트 3 〉	통과 (3.40ms, 59.5MB)
 * 테스트 4 〉	통과 (2.95ms, 61.4MB)
 * 테스트 5 〉	통과 (4.72ms, 60.8MB)
 * 테스트 6 〉	통과 (5.26ms, 61.3MB)
 * 테스트 7 〉	통과 (5.67ms, 60.6MB)
 * 테스트 8 〉	통과 (6.03ms, 61.8MB)
 *
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("abcdevwxyz"), "lllllvwxyz")
  validate(s.solution("jjnnllkkmm"), "llnnllllmm")
}
