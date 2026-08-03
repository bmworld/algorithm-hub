package 프로그래머스.Lv2.마법의엘리베이터

import util.validate

class Solution {

  fun solution(storey: Int): Int {
    val digits = IntArray(9)
    var x = storey
    var i = 0
    while (x > 0) {
      digits[i++] = x % 10
      x /= 10
    }

    var ans = 0

    repeat(i) {
      val x = digits[it]
      ans += if (x <= 5) x else 11 - x
    }

    return ans
  }
}

/**
 * ```
 * [ME]
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
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(16), 6)
  validate(s.solution(2554), 16)
}
