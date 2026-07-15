package 프로그래머스.Lv2.예상대진표

import util.validate

class Solution {

  fun solution(n: Int, a: Int, b: Int): Int {
    var round = 1
    var x = minOf(a, b)
    var y = maxOf(a, b)
    while (y - x != 1) {
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
 * 테스트 1 〉	통과 (0.02ms, 59.6MB)
 * 테스트 2 〉	통과 (0.02ms, 59.8MB)
 * 테스트 3 〉	통과 (0.01ms, 59.8MB)
 * 테스트 4 〉	통과 (0.01ms, 59.6MB)
 * 테스트 5 〉	통과 (0.02ms, 60.9MB)
 * 테스트 6 〉	통과 (0.02ms, 58.4MB)
 * 테스트 7 〉	실패 (0.01ms, 60.5MB)
 * 테스트 8 〉	통과 (0.01ms, 60.1MB)
 * 테스트 9 〉	실패 (0.02ms, 60.4MB)
 * 테스트 10 〉	통과 (0.02ms, 59MB)
 * 테스트 11 〉	통과 (0.02ms, 59.3MB)
 * 테스트 12 〉	통과 (0.01ms, 59.2MB)
 * 테스트 13 〉	통과 (0.02ms, 61.7MB)
 * 테스트 14 〉	통과 (0.01ms, 60.4MB)
 * 테스트 15 〉	통과 (0.02ms, 60.3MB)
 * 테스트 16 〉	통과 (0.02ms, 60MB)
 * 테스트 17 〉	통과 (0.01ms, 60.6MB)
 * 테스트 18 〉	통과 (0.02ms, 59.4MB)
 * 테스트 19 〉	통과 (0.02ms, 60.6MB)
 * 테스트 20 〉	통과 (0.01ms, 59MB)
 * 테스트 21 〉	통과 (0.01ms, 60.9MB)
 * 테스트 22 〉	통과 (0.01ms, 60.4MB)
 * 테스트 23 〉	통과 (0.02ms, 60.7MB)
 * 테스트 24 〉	통과 (0.04ms, 60.4MB)
 * 테스트 25 〉	통과 (0.01ms, 59.5MB)
 * 테스트 26 〉	통과 (0.02ms, 60.9MB)
 * 테스트 27 〉	실패 (0.02ms, 59.8MB)
 * 테스트 28 〉	통과 (0.02ms, 60.4MB)
 * 테스트 29 〉	통과 (0.02ms, 59.1MB)
 * 테스트 30 〉	통과 (0.02ms, 60.1MB)
 * 테스트 31 〉	통과 (0.02ms, 60.4MB)
 * 테스트 32 〉	통과 (0.02ms, 59.9MB)
 * 테스트 33 〉	실패 (0.01ms, 60.3MB)
 * 테스트 34 〉	통과 (0.02ms, 59.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(5, 1, 2), 1)
  validate(s.solution(6, 2, 1), 1)
  validate(s.solution(8, 4, 7), 3)
  validate(s.solution(1, 1 shl 18, 1 shl 2), 18)
}

//       println("[$round] ${x}, $y")
