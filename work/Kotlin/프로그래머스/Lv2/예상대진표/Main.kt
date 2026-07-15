package 프로그래머스.Lv2.예상대진표

import util.validate

class Solution {

  val INT_MAX_BITS = 32
  val ZERO_BASED = 1
  fun solution(n: Int, a: Int, b: Int): Int {
    val x = toZeroBaseNum(a)
    val y = toZeroBaseNum(b)
    val rounds = INT_MAX_BITS - (x xor y).countLeadingZeroBits()
    return rounds
  }

  private fun toZeroBaseNum(a: Int): Int = a - ZERO_BASED
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.7MB)
 * 테스트 2 〉	통과 (0.02ms, 60.5MB)
 * 테스트 3 〉	통과 (0.02ms, 60.1MB)
 * 테스트 4 〉	통과 (0.02ms, 60.5MB)
 * 테스트 5 〉	통과 (0.01ms, 59.7MB)
 * 테스트 6 〉	통과 (0.02ms, 61.1MB)
 * 테스트 7 〉	통과 (0.01ms, 60.6MB)
 * 테스트 8 〉	통과 (0.02ms, 59MB)
 * 테스트 9 〉	통과 (0.01ms, 60.5MB)
 * 테스트 10 〉	통과 (0.01ms, 59.6MB)
 * v2:
 * 테스트 1 〉	통과 (0.01ms, 60.5MB)
 * 테스트 2 〉	통과 (0.01ms, 60.8MB)
 * 테스트 3 〉	통과 (0.01ms, 60.3MB)
 * 테스트 4 〉	통과 (0.01ms, 59.5MB)
 * 테스트 5 〉	통과 (0.01ms, 58.7MB)
 * 테스트 6 〉	통과 (0.01ms, 60.1MB)
 * 테스트 7 〉	통과 (0.01ms, 60.4MB)
 * 테스트 8 〉	통과 (0.01ms, 59.5MB)
 * 테스트 9 〉	통과 (0.01ms, 59.8MB)
 * 테스트 10 〉	통과 (0.02ms, 60.2MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Int, a: Int, b: Int) = ((a - 1) xor (b - 1)).toString(2).length
 * }
 * 테스트 1 〉	통과 (0.66ms, 59.4MB)
 * 테스트 2 〉	통과 (0.51ms, 61.1MB)
 * 테스트 3 〉	통과 (0.55ms, 58MB)
 * 테스트 4 〉	통과 (0.65ms, 59.4MB)
 * 테스트 5 〉	통과 (0.53ms, 60.9MB)
 * 테스트 6 〉	통과 (0.55ms, 59.5MB)
 * 테스트 7 〉	통과 (0.59ms, 61.1MB)
 * 테스트 8 〉	통과 (0.54ms, 58MB)
 * 테스트 9 〉	통과 (0.70ms, 60.9MB)
 * 테스트 10 〉	통과 (0.58ms, 59.7MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(5, 2, 3), 2)
  validate(s.solution(5, 1, 2), 1)
  validate(s.solution(6, 2, 1), 1)
  validate(s.solution(6, 3, 1), 2)
  validate(s.solution(6, 3, 4), 1)
  validate(s.solution(8, 4, 7), 3)
  validate(s.solution(1, 1 shl 18, 1 shl 2), 18)
}

//       println("[$round] ${x}, $y")
