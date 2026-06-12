package 프로그래머스.Lv1.정수내림차순으로배치하기

import util.validate

class Solution {

  val DIGITS = 10
  fun solution(n: Long): Long {
    val CNTS = IntArray(DIGITS)
    var x = n
    while (x > 0) {
      CNTS[(x % 10).toInt()]++
      x /= 10
    }

    repeat(DIGITS) {
      val d = DIGITS - (it + 1)
      repeat(CNTS[d]) {
        x = x * 10 + d
      }
    }
    return x
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 57.9MB)
 * 테스트 2 〉	통과 (0.01ms, 59.4MB)
 * 테스트 3 〉	통과 (0.01ms, 58.5MB)
 * 테스트 4 〉	통과 (0.01ms, 58.8MB)
 * 테스트 5 〉	통과 (0.01ms, 60.2MB)
 * 테스트 6 〉	통과 (0.01ms, 59.2MB)
 * 테스트 7 〉	통과 (0.01ms, 58.1MB)
 * 테스트 8 〉	통과 (0.01ms, 59.1MB)
 * 테스트 9 〉	통과 (0.01ms, 58.2MB)
 * 테스트 10 〉	통과 (0.01ms, 57.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Long): Long = String(n.toString().toCharArray().sortedArrayDescending()).toLong()
 * }
 * 테스트 1 〉	통과 (8.96ms, 61.5MB)
 * 테스트 2 〉	통과 (9.04ms, 61.8MB)
 * 테스트 3 〉	통과 (9.10ms, 63.2MB)
 * 테스트 4 〉	통과 (9.07ms, 61.3MB)
 * 테스트 5 〉	통과 (8.93ms, 63.7MB)
 * 테스트 6 〉	통과 (9.04ms, 62.6MB)
 * 테스트 7 〉	통과 (8.77ms, 61.9MB)
 * 테스트 8 〉	통과 (8.97ms, 62.9MB)
 * 테스트 9 〉	통과 (9.44ms, 61.4MB)
 * 테스트 10 〉	통과 (8.96ms, 62.7MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(118372), 873211)
  validate(s.solution(108), 810)
  validate(s.solution(1), 1)
  validate(s.solution(1_234_567_809), 9876543210)
  validate(s.solution(1_111_222), 2_221_111)

}
