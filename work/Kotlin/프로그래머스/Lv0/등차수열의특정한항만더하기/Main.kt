package 프로그래머스.Lv0.등차수열의특정한항만더하기

import util.validate

class Solution {

  fun solution(a: Int, d: Int, included: BooleanArray): Int {
    var ans = 0
    for (i in included.indices)
      if (included[i]) ans += a + i * d
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.2MB)
 * 테스트 2 〉	통과 (0.01ms, 57.6MB)
 * 테스트 3 〉	통과 (0.01ms, 60.9MB)
 * 테스트 4 〉	통과 (0.01ms, 59MB)
 * 테스트 5 〉	통과 (0.01ms, 60.9MB)
 * 테스트 6 〉	통과 (0.01ms, 59.6MB)
 * 테스트 7 〉	통과 (0.01ms, 60MB)
 * 테스트 8 〉	통과 (0.01ms, 60.2MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(a: Int, d: Int, included: BooleanArray) = included.indices.filter { included[it] }.sumOf { a + d * it }
 * }
 * 테스트 1 〉	통과 (10.18ms, 63.6MB)
 * 테스트 2 〉	통과 (10.62ms, 62.6MB)
 * 테스트 3 〉	통과 (11.18ms, 63.3MB)
 * 테스트 4 〉	통과 (10.44ms, 63.8MB)
 * 테스트 5 〉	통과 (14.17ms, 63.1MB)
 * 테스트 6 〉	통과 (13.96ms, 62.5MB)
 * 테스트 7 〉	통과 (11.36ms, 61.9MB)
 * 테스트 8 〉	통과 (13.90ms, 63.6MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(a: Int, d: Int, included: BooleanArray): Int {
 *         return List(included.size) { a + d * it }
 *             .filterIndexed{ idx, value -> included[idx] }
 *             .sum()
 *     }
 * }
 * 테스트 1 〉	통과 (3.95ms, 60.2MB)
 * 테스트 2 〉	통과 (4.08ms, 61.6MB)
 * 테스트 3 〉	통과 (4.16ms, 60.6MB)
 * 테스트 4 〉	통과 (4.87ms, 59.9MB)
 * 테스트 5 〉	통과 (6.10ms, 60MB)
 * 테스트 6 〉	통과 (4.16ms, 60MB)
 * 테스트 7 〉	통과 (4.76ms, 60.2MB)
 * 테스트 8 〉	통과 (4.00ms, 58.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(3, 4, booleanArrayOf(true, false, false, true, true)), 37)
  validate(s.solution(7, 1, booleanArrayOf(false, false, false, true, false, false, false)), 10)
}
