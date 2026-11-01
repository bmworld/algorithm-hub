package 프로그래머스.Lv0.수열과구간쿼리3

import util.validate

class Solution {

  fun solution(arr: IntArray, qs: Array<IntArray>): IntArray {
    for (q in qs) {
      val a = q[0]
      val b = q[1]
      val tmp = arr[a]
      arr[a] = arr[b]
      arr[b] = tmp
    }

    return arr
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.07ms, 60.7MB)
 * 테스트 2 〉	통과 (0.04ms, 59.4MB)
 * 테스트 3 〉	통과 (0.05ms, 60.7MB)
 * 테스트 4 〉	통과 (0.02ms, 61.3MB)
 * 테스트 5 〉	통과 (0.01ms, 60.3MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(arr: IntArray, queries: Array<IntArray>): IntArray {
 *         queries.forEach { (first, second) ->
 *             arr[first] = arr[second].also { arr[second] = arr[first] }
 *         }
 *         return arr
 *     }
 * }
 * 테스트 1 〉	통과 (0.26ms, 60.8MB)
 * 테스트 2 〉	통과 (0.16ms, 59.4MB)
 * 테스트 3 〉	통과 (0.16ms, 60MB)
 * 테스트 4 〉	통과 (0.16ms, 59.6MB)
 * 테스트 5 〉	통과 (0.17ms, 60.5MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(0, 1, 2, 3, 4),
    arrayOf(
      intArrayOf(0, 3),
      intArrayOf(1, 2),
      intArrayOf(1, 4),
    )
  ), intArrayOf(3, 4, 1, 0, 2))
}
