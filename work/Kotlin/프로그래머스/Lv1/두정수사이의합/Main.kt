package 프로그래머스.Lv1.두정수사이의합

import util.validate

class Solution {

  fun solution(a: Int, b: Int): Long {
    val x = minOf(a, b).toLong()
    val y = maxOf(a, b).toLong()
    return (y * (y + 1) - (x - 1) * x) / 2
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.5MB)
 * 테스트 2 〉	통과 (0.02ms, 57.6MB)
 * 테스트 3 〉	통과 (0.02ms, 59.4MB)
 * 테스트 4 〉	통과 (0.02ms, 58.1MB)
 * 테스트 5 〉	통과 (0.02ms, 59.5MB)
 * 테스트 6 〉	통과 (0.02ms, 60MB)
 * 테스트 7 〉	통과 (0.02ms, 59MB)
 * 테스트 8 〉	통과 (0.02ms, 60MB)
 * 테스트 9 〉	통과 (0.02ms, 58.3MB)
 * 테스트 10 〉	통과 (0.02ms, 59.5MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(a: Int, b: Int): Long {
 *         var answer = 0L
 *
 *         if(a > b) {
 *             for(n in b.. a) {
 *                 answer += n
 *             }
 *         } else {
 *             for(n in a.. b) {
 *                 answer += n
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.05ms, 59.8MB)
 * 테스트 2 〉	통과 (0.03ms, 59.6MB)
 * 테스트 3 〉	통과 (0.03ms, 59.5MB)
 * 테스트 4 〉	통과 (8.32ms, 57.6MB)
 * 테스트 5 〉	통과 (6.32ms, 60.6MB)
 * 테스트 6 〉	통과 (5.62ms, 59.3MB)
 * 테스트 7 〉	통과 (4.03ms, 58.5MB)
 * 테스트 8 〉	통과 (5.38ms, 57.9MB)
 * 테스트 9 〉	통과 (5.86ms, 58.1MB)
 * 테스트 10 〉	통과 (3.28ms, 57.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(3, 5), 12)
  validate(s.solution(3, 3), 3)
  validate(s.solution(5, 3), 12)

}
