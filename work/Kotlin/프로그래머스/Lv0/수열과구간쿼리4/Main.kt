package 프로그래머스.Lv0.수열과구간쿼리4

import util.validate

class Solution {

  fun solution(arr: IntArray, queries: Array<IntArray>): IntArray {
    for (q in queries) {
      val s = q[0]
      val e = q[1]
      val k = q[2]

      if (s == 0) arr[0]++
      if (k > 0) for (i in maxOf(s, k)..e step k) arr[i]++
    }
    return arr
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	실패 (0.26ms, 60.8MB)
 * 테스트 2 〉	통과 (0.24ms, 59.9MB)
 * 테스트 3 〉	통과 (0.22ms, 59.9MB)
 * 테스트 4 〉	통과 (0.23ms, 60.4MB)
 * 테스트 5 〉	통과 (0.18ms, 58.9MB)
 * 테스트 6 〉	통과 (0.21ms, 58.9MB)
 * 테스트 7 〉	통과 (0.30ms, 60MB)
 * 테스트 8 〉	통과 (0.25ms, 60.8MB)
 * 테스트 9 〉	통과 (0.25ms, 61MB)
 * 테스트 10 〉	통과 (0.32ms, 60.3MB)
 * 테스트 11 〉	통과 (0.26ms, 62.5MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(0, 1, 2, 4, 3),
    arrayOf(
      intArrayOf(0, 4, 1),
      intArrayOf(0, 3, 2),
      intArrayOf(0, 3, 3),
    )
  ), intArrayOf(3, 2, 4, 6, 4))

  validate(s.solution(intArrayOf(0, 1, 2, 3, 4),
    arrayOf(
      intArrayOf(0, 4, 0),
      intArrayOf(3, 4, 0),
      intArrayOf(3, 4, 1),
    )
  ), intArrayOf(1, 1, 2, 4, 5))
}
