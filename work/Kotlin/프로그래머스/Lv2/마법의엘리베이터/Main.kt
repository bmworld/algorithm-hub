package 프로그래머스.Lv2.마법의엘리베이터

import util.validate

class Solution {
  companion object {

    const val MAX_LEN = 9
  }

  fun solution(storey: Int): Int {
    val digits = IntArray(MAX_LEN + 1)
    var x = storey
    var i = 0
    while (x > 0) {
      digits[i++] = x % 10
      x /= 10
    }

    var ans = 0
    var carry = 0

    fun down(x: Int) {
      ans += x
    }

    fun up(x: Int) {
      ans += 10 - x
      carry++
    }

    repeat(i) {
      var x = digits[it]
      if (carry > 0) {
        x += carry
        carry = 0
      }

      when {
        x < 5 -> down(x)
        x == 5 -> if (digits[it + 1] >= 5) up(x) else down(x)
        else -> up(x)
      }
    }

    if (carry > 0) down(carry)
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.28ms, 60.6MB)
 * 테스트 2 〉	통과 (0.27ms, 60.4MB)
 * 테스트 3 〉	통과 (0.22ms, 60.6MB)
 * 테스트 4 〉	통과 (0.15ms, 60.6MB)
 * 테스트 5 〉	통과 (0.16ms, 60.5MB)
 * 테스트 6 〉	통과 (0.25ms, 60.7MB)
 * 테스트 7 〉	통과 (0.22ms, 59.7MB)
 * 테스트 8 〉	통과 (0.32ms, 59.7MB)
 * 테스트 9 〉	통과 (0.24ms, 61.1MB)
 * 테스트 10 〉	통과 (0.19ms, 60.6MB)
 * 테스트 11 〉	통과 (0.15ms, 60.6MB)
 * 테스트 12 〉	통과 (0.16ms, 59.8MB)
 * 테스트 13 〉	통과 (0.26ms, 59.7MB)
 * ```
 *
 *
 *
 * ```
 * [RIVAL]
 * import kotlin.math.min
 *
 * class Solution {
 *     fun solution(storey: Int): Int = if (storey < 10) min(storey, 11 - storey) else min(storey % 10 + solution(storey / 10), 10 - storey % 10 + solution(storey / 10 + 1))
 * }
 * 테스트 1 〉	통과 (0.02ms, 58.4MB)
 * 테스트 2 〉	통과 (0.01ms, 60MB)
 * 테스트 3 〉	통과 (0.01ms, 59.7MB)
 * 테스트 4 〉	통과 (0.04ms, 59.3MB)
 * 테스트 5 〉	통과 (0.02ms, 59.5MB)
 * 테스트 6 〉	통과 (0.04ms, 60.3MB)
 * 테스트 7 〉	통과 (0.02ms, 60.5MB)
 * 테스트 8 〉	통과 (0.02ms, 60.5MB)
 * 테스트 9 〉	통과 (0.02ms, 60.4MB)
 * 테스트 10 〉	통과 (0.03ms, 60.7MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1), 1)
  validate(s.solution(5), 5)
  validate(s.solution(15), 6)
  validate(s.solution(95), 6)
  validate(s.solution(45), 9)
  validate(s.solution(55), 10)
  validate(s.solution(6), 5)
  validate(s.solution(16), 6)
  validate(s.solution(61), 6)
  validate(s.solution(98), 3)
  validate(s.solution(2554), 16)
  validate(s.solution(100_000_000), 1)
  validate(s.solution(99_999_999), 2)
  validate(s.solution(111), 3)

}
