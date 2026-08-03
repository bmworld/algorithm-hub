package 프로그래머스.Lv2.마법의엘리베이터

import util.validate

class Solution {

  fun solution(storey: Int): Int {
    var ans = 0

    var x = storey
    var carry = 0

    while (x > 0) {
      var d = x % 10 + carry
      carry = 0

      x /= 10

      when {
        d < 5 -> ans += d
        d > 5 || x % 10 >= 5 -> {
          ans += 10 - d
          carry++
        }
        else -> ans += d
      }
    }

    if (carry > 0) ans++
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
 * v2:
 * 테스트 1 〉	통과 (0.01ms, 60.8MB)
 * 테스트 2 〉	통과 (0.01ms, 58.6MB)
 * 테스트 3 〉	통과 (0.01ms, 59.9MB)
 * 테스트 4 〉	통과 (0.01ms, 60.1MB)
 * 테스트 5 〉	통과 (0.01ms, 59.6MB)
 * 테스트 6 〉	통과 (0.01ms, 59.4MB)
 * 테스트 7 〉	통과 (0.01ms, 60.1MB)
 * 테스트 8 〉	통과 (0.01ms, 60.4MB)
 * 테스트 9 〉	통과 (0.01ms, 60.4MB)
 * 테스트 10 〉	통과 (0.01ms, 60.2MB)
 * 테스트 11 〉	통과 (0.01ms, 61.4MB)
 * 테스트 12 〉	통과 (0.01ms, 61.4MB)
 * 테스트 13 〉	통과 (0.01ms, 59.3MB)
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
