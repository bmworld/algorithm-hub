package 프로그래머스.Lv0.홀짝에따라다른값반환하기

import util.validate

class Solution {

  fun solution(n: Int): Int {
    if (n % 2 == 1) {
      var pair = n + 1
      val order = (n + 1) / 2
      return pair * (order / 2) + if (order % 2 == 0) 0 else pair / 2
    } else {
      var r = 0
      var x = 2
      while (x <= n) {
        r += x * x
        x += 2
      }
      return r
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 60.7MB)
 * 테스트 2 〉	통과 (0.01ms, 58.3MB)
 * 테스트 3 〉	통과 (0.01ms, 59.5MB)
 * 테스트 4 〉	통과 (0.01ms, 61.5MB)
 * 테스트 5 〉	통과 (0.01ms, 57.4MB)
 * 테스트 6 〉	통과 (0.01ms, 59MB)
 * 테스트 7 〉	통과 (0.01ms, 61.1MB)
 * 테스트 8 〉	통과 (0.01ms, 59.7MB)
 * 테스트 9 〉	통과 (0.01ms, 60MB)
 * 테스트 10 〉	통과 (0.01ms, 58.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Int): Int {
 *         return if(n % 2 == 0) {
 *             (n downTo 1 step 2).sumOf { it * it }
 *         } else {
 *             (n downTo 1 step 2).sum()
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (1.68ms, 60MB)
 * 테스트 2 〉	통과 (5.41ms, 60.6MB)
 * 테스트 3 〉	통과 (5.56ms, 59.7MB)
 * 테스트 4 〉	통과 (6.02ms, 61.1MB)
 * 테스트 5 〉	통과 (2.49ms, 60.1MB)
 * 테스트 6 〉	통과 (1.67ms, 60.2MB)
 * 테스트 7 〉	통과 (5.88ms, 60.7MB)
 * 테스트 8 〉	통과 (1.62ms, 60.8MB)
 * 테스트 9 〉	통과 (5.76ms, 61.3MB)
 * 테스트 10 〉	통과 (5.08ms, 61.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1), 1)
  validate(s.solution(2), 4)
  validate(s.solution(3), 4)
  validate(s.solution(4), 20)
  validate(s.solution(5), 9)
  validate(s.solution(6), 56)
  validate(s.solution(7), 16)
  validate(s.solution(10), 220)

}
