package 프로그래머스.Lv2.마법의엘리베이터

import util.validate

class Solution {
  companion object {

    const val MAX_LEN = 9
  }

  fun solution(storey: Int): Int {
    val digits = IntArray(MAX_LEN)
    var x = storey
    var i = 0
    while (x > 0) {
      digits[i++] = x % 10
      x /= 10
    }

    var ans = 0
    var carry = 0
    repeat(i) {
      var x = digits[it]
      if (carry > 0) {
        x += carry
        carry = 0
      }

      ans += if (x <= 5) x else {
        carry++
        10 - x
      }
    }

    if (carry > 0) ans += carry

    return ans
  }
}

/**
 * ```
 * [ME]
 * WA 1
 * 테스트 1 〉	실패 (0.01ms, 59.9MB)
 * 테스트 2 〉	통과 (0.01ms, 61MB)
 * 테스트 3 〉	실패 (0.01ms, 59.6MB)
 * 테스트 4 〉	실패 (0.01ms, 59.5MB)
 * 테스트 5 〉	실패 (0.01ms, 60.4MB)
 * 테스트 6 〉	통과 (0.01ms, 60.1MB)
 * 테스트 7 〉	실패 (0.01ms, 59.4MB)
 * 테스트 8 〉	통과 (0.01ms, 59.7MB)
 * 테스트 9 〉	실패 (0.01ms, 59MB)
 * 테스트 10 〉	실패 (0.01ms, 60MB)
 * 테스트 11 〉	실패 (0.01ms, 59.7MB)
 * 테스트 12 〉	실패 (0.01ms, 59.1MB)
 * 테스트 13 〉	통과 (0.01ms, 59.5MB)
 * WA 2
 * 테스트 1 〉	실패 (0.01ms, 61.4MB)
 * 테스트 2 〉	통과 (0.01ms, 59.9MB)
 * 테스트 3 〉	실패 (0.01ms, 58.4MB)
 * 테스트 4 〉	통과 (0.01ms, 59.3MB)
 * 테스트 5 〉	통과 (0.01ms, 60.2MB)
 * 테스트 6 〉	통과 (0.01ms, 59.2MB)
 * 테스트 7 〉	통과 (0.01ms, 58.3MB)
 * 테스트 8 〉	통과 (0.01ms, 60.2MB)
 * 테스트 9 〉	통과 (0.01ms, 61.3MB)
 * 테스트 10 〉	통과 (0.01ms, 61.3MB)
 * 테스트 11 〉	통과 (0.01ms, 60.9MB)
 * 테스트 12 〉	실패 (0.01ms, 59.9MB)
 * 테스트 13 〉	통과 (0.01ms, 60.5MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1), 1)
  validate(s.solution(5), 5)
  validate(s.solution(6), 5)
  validate(s.solution(16), 6)
  validate(s.solution(61), 6)
  validate(s.solution(98), 3)
  validate(s.solution(2554), 16)
  validate(s.solution(100_000_000), 1)
  validate(s.solution(99_999_999), 2)
}
