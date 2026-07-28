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

      (1L shl lastZeroPos) +
        if (lastZeroPos == x.countOneBits()) x shr 1
        else x
    }
  }
}

/**
 * ```
 * [ME]
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
    s.solution(longArrayOf(2, 7, 11, 11)),
    longArrayOf(3, 11, 15, 15),
  )
}
