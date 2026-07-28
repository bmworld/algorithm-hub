package 프로그래머스.Lv2.두개이하로다른비트

import util.validate

class Solution {

  fun solution(numbers: LongArray): LongArray {
    return LongArray(numbers.size) {
      val x = numbers[it]
      var lastZeroPos = 0
      var t = x
      while (t % 2 != 0L) {
        t = t shr 1
        lastZeroPos++
      }

      var add = 1 shl lastZeroPos
      val subt = maxOf(0, 1 shl (lastZeroPos - 1))
      x + add - subt
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.08ms, 60.4MB)
 * 테스트 2 〉	통과 (3.94ms, 80.9MB)
 * 테스트 3 〉	통과 (0.02ms, 60.1MB)
 * 테스트 4 〉	통과 (0.10ms, 61.9MB)
 * 테스트 5 〉	통과 (0.11ms, 60.9MB)
 * 테스트 6 〉	통과 (0.08ms, 59.9MB)
 * 테스트 7 〉	통과 (4.06ms, 85.7MB)
 * 테스트 8 〉	통과 (3.67ms, 82.7MB)
 * 테스트 9 〉	통과 (4.17ms, 82.6MB)
 * 테스트 10 〉	실패 (6.18ms, 88MB)
 * 테스트 11 〉	실패 (6.08ms, 87.7MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(longArrayOf(2, 5, 7, 11, 11, 1_000_000_000_000_000)),
    longArrayOf(3, 6, 11, 13, 13, 1_000_000_000_000_001),
  )
}
