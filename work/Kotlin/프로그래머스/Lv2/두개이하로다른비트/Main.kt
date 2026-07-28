package 프로그래머스.Lv2.두개이하로다른비트

import util.validate

class Solution {
  companion object {

    val LONG = 1L
  }

  fun solution(numbers: LongArray): LongArray {
    return LongArray(numbers.size) {
      val x = numbers[it]
      val lastZeroPos = x.inv().countTrailingZeroBits()

      var add = LONG shl lastZeroPos
      val subt = if (lastZeroPos == 0) 0L else LONG shl (lastZeroPos - 1)
      x + add - subt
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.11ms, 59.9MB)
 * 테스트 2 〉	통과 (4.62ms, 80.1MB)
 * 테스트 3 〉	통과 (0.02ms, 60.4MB)
 * 테스트 4 〉	통과 (0.10ms, 61.2MB)
 * 테스트 5 〉	통과 (0.11ms, 59.8MB)
 * 테스트 6 〉	통과 (0.11ms, 60.6MB)
 * 테스트 7 〉	통과 (3.97ms, 86MB)
 * 테스트 8 〉	통과 (4.08ms, 82.7MB)
 * 테스트 9 〉	통과 (4.41ms, 82.3MB)
 * 테스트 10 〉	통과 (6.15ms, 87.8MB)
 * 테스트 11 〉	통과 (7.39ms, 87.1MB)
 * v2:
 * 테스트 1 〉	통과 (0.18ms, 59.7MB)
 * 테스트 2 〉	통과 (3.91ms, 80.6MB)
 * 테스트 3 〉	통과 (0.03ms, 61.3MB)
 * 테스트 4 〉	통과 (0.18ms, 60MB)
 * 테스트 5 〉	통과 (0.16ms, 61MB)
 * 테스트 6 〉	통과 (0.18ms, 59.9MB)
 * 테스트 7 〉	통과 (3.71ms, 86.1MB)
 * 테스트 8 〉	통과 (3.62ms, 82.9MB)
 * 테스트 9 〉	통과 (3.88ms, 82.6MB)
 * 테스트 10 〉	통과 (4.08ms, 87.8MB)
 * 테스트 11 〉	통과 (4.18ms, 88.4MB)
 *
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(numbers: LongArray): LongArray {
 *         return numbers.map { num -> (num.inv() and num + 1).let { num or it and (it shr 1).inv() } }.toLongArray()
 *     }
 * }
 * 테스트 1 〉	통과 (3.91ms, 61.3MB)
 * 테스트 2 〉	통과 (11.64ms, 84.4MB)
 * 테스트 3 〉	통과 (3.72ms, 60.1MB)
 * 테스트 4 〉	통과 (3.91ms, 61.5MB)
 * 테스트 5 〉	통과 (4.04ms, 60.8MB)
 * 테스트 6 〉	통과 (3.83ms, 61.5MB)
 * 테스트 7 〉	통과 (10.67ms, 89.7MB)
 * 테스트 8 〉	통과 (11.40ms, 86.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(longArrayOf(2, 5, 7, 11, 11, 1_000_000_000_000_000)),
    longArrayOf(3, 6, 11, 13, 13, 1_000_000_000_000_001),
  )

  validate(
    s.solution(longArrayOf((1L shl 31) - 1)),
    longArrayOf(3_221_225_471L),
  )
}
