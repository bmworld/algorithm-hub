package 프로그래머스.Lv0.문자개수세기

import util.validate

class Solution {
  private companion object {

    const val A = 65
    const val a = 97
    const val ALPHABETS = 26
    const val TOTAL = ALPHABETS * 2
  }

  fun solution(my_string: String): IntArray {
    val ans = IntArray(TOTAL)
    for (x in my_string) {
      val code = x.code
      when {
        code >= a -> ans[ALPHABETS + code - a]++
        else -> ans[code - A]++
      }
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.8MB)
 * 테스트 2 〉	통과 (0.01ms, 59.3MB)
 * 테스트 3 〉	통과 (0.01ms, 58.8MB)
 * 테스트 4 〉	통과 (0.01ms, 60.1MB)
 * 테스트 5 〉	통과 (0.02ms, 59.2MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(my_string: String): IntArray {
 *         return (('A'..'Z').map { alpha -> my_string.count { it == alpha } } +
 *         ('a'..'z').map { alpha -> my_string.count { it == alpha } }).toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (4.95ms, 60.9MB)
 * 테스트 2 〉	통과 (5.35ms, 59.2MB)
 * 테스트 3 〉	통과 (5.39ms, 59.5MB)
 * 테스트 4 〉	통과 (5.06ms, 60.1MB)
 * 테스트 5 〉	통과 (5.08ms, 60.7MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(myString: String): IntArray {
 *         val counts = IntArray(52)
 *
 *         myString.forEach { c ->
 *             if (c in 'A'..'Z') {
 *                 counts[c - 'A']++
 *             } else if (c in 'a'..'z') {
 *                 counts[c - 'a' + 26]++
 *             }
 *         }
 *         return counts
 *     }
 * }
 * 테스트 1 〉	통과 (0.02ms, 60MB)
 * 테스트 2 〉	통과 (0.03ms, 59.6MB)
 * 테스트 3 〉	통과 (0.02ms, 58.9MB)
 * 테스트 4 〉	통과 (0.02ms, 59.7MB)
 * 테스트 5 〉	통과 (0.02ms, 60.3MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("Programmers"),
    intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
      0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 2, 0, 1, 0, 0, 3, 1, 0, 0, 0, 0, 0, 0, 0)
  )

}
