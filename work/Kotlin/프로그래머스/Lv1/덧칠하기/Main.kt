package 프로그래머스.Lv1.덧칠하기

import util.validate

class Solution {

  fun solution(n: Int, m: Int, section: IntArray): Int {
    var ans = 1
    var fr = section[0]
    for (i in 1 until section.size) {
      val to = section[i]
      val dist = to - fr + 1
      if (dist > m) {
        fr = to
        ans++
      }
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.69ms, 62MB)
 * 테스트 2 〉	통과 (1.03ms, 62.9MB)
 * 테스트 3 〉	통과 (0.67ms, 62.4MB)
 * 테스트 4 〉	통과 (0.02ms, 59.2MB)
 * 테스트 5 〉	통과 (0.65ms, 61.5MB)
 * 테스트 6 〉	통과 (0.01ms, 60MB)
 * 테스트 7 〉	통과 (0.02ms, 59.1MB)
 * 테스트 8 〉	통과 (0.44ms, 61.6MB)
 * 테스트 9 〉	통과 (0.01ms, 59.6MB)
 * 테스트 10 〉	통과 (0.53ms, 61.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Int, m: Int, section: IntArray): Int {
 *         var answer = 0
 *         var nextSection = 0
 *         section.forEach {
 *             if (it >= nextSection) {
 *                 answer++
 *                 nextSection = it + m
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.71ms, 62.2MB)
 * 테스트 2 〉	통과 (1.05ms, 62.8MB)
 * 테스트 3 〉	통과 (0.69ms, 61.9MB)
 * 테스트 4 〉	통과 (0.02ms, 59.5MB)
 * 테스트 5 〉	통과 (0.70ms, 61.6MB)
 * 테스트 6 〉	통과 (0.01ms, 59.6MB)
 * 테스트 7 〉	통과 (0.02ms, 58.9MB)
 * 테스트 8 〉	통과 (0.54ms, 60.8MB)
 * 테스트 9 〉	통과 (0.01ms, 59MB)
 * 테스트 10 〉	통과 (0.55ms, 62.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(8, 4, intArrayOf(2, 3, 6)), 2)
  validate(s.solution(5, 4, intArrayOf(1, 3)), 1)
  validate(s.solution(4, 1, intArrayOf(1, 2, 3, 4)), 4)
}
