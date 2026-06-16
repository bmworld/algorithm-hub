package 프로그래머스.Lv1.나머지가1이되는수찾기

import util.validate

class Solution {

  fun solution(n: Int): Int {
    if (n % 2 == 1) return 2
    if (n % 3 == 1) return 3

    val answer = n - 1
    var d = 5
    while (d <= answer / d) {
      if (answer % d == 0) return d
      if (answer % (d + 2) == 0) return d + 2
      d += 6
    }

    return answer
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 57.6MB)
 * 테스트 2 〉	통과 (0.01ms, 58.3MB)
 * 테스트 3 〉	통과 (0.01ms, 58.6MB)
 * 테스트 4 〉	통과 (0.01ms, 58.6MB)
 * 테스트 5 〉	통과 (0.01ms, 57.7MB)
 * 테스트 6 〉	통과 (0.01ms, 59.3MB)
 * 테스트 7 〉	통과 (0.01ms, 59MB)
 * 테스트 8 〉	통과 (0.01ms, 59.2MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Int) = (1..n).first { n % it == 1 }
 * }
 * 테스트 1 〉	통과 (5.78ms, 60MB)
 * 테스트 2 〉	통과 (1.29ms, 58.8MB)
 * 테스트 3 〉	통과 (1.55ms, 60.5MB)
 * 테스트 4 〉	통과 (1.39ms, 58.4MB)
 * 테스트 5 〉	통과 (1.24ms, 59.8MB)
 * 테스트 6 〉	통과 (1.56ms, 58MB)
 * 테스트 7 〉	통과 (1.57ms, 58MB)
 * 테스트 8 〉	통과 (1.56ms, 58MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(3), 2)
  validate(s.solution(4), 3)
  validate(s.solution(6), 5)
  validate(s.solution(8), 7)
  validate(s.solution(10), 3)
  validate(s.solution(12), 11)
  validate(s.solution(34), 3)
  validate(s.solution(78), 7)
}
