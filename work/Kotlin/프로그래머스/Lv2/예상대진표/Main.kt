package 프로그래머스.Lv2.예상대진표

import util.validate

class Solution {

  fun solution(n: Int, a: Int, b: Int): Int {
    var round = 1
    var x = minOf(a, b)
    var y = maxOf(a, b)
    while (x % 2 == 0 || y - x != 1) {
      x = nxt(x)
      y = nxt(y)
      round++
    }

    return round
  }

  private fun nxt(i: Int): Int = (if (i % 2 == 0) i else i + 1) shr 1
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
  validate(s.solution(8, 4, 7), 3)
  validate(s.solution(1, 1 shl 18, 1 shl 2), 18)
}

//       println("[$round] ${x}, $y")
