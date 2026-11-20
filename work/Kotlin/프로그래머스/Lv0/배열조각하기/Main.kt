package 프로그래머스.Lv0.배열조각하기

import util.validate

class Solution {

  fun solution(arr: IntArray, query: IntArray): IntArray {
    var l = 0
    var r = arr.size - 1
    for (i in query.indices)
      if (i % 2 == 0) r = l + query[i] else l += query[i]
    return arr.copyOfRange(l, r + 1)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (6.33ms, 68.4MB)
 * 테스트 2 〉	통과 (6.67ms, 66.6MB)
 * 테스트 3 〉	통과 (6.49ms, 64.4MB)
 * 테스트 4 〉	통과 (6.44ms, 64.4MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(arr: IntArray, query: IntArray): IntArray {
 *         var answer = arr
 *         query.forEachIndexed { index, i ->
 *             answer = if(index % 2 == 0) {
 *                 answer.slice(0..i).toIntArray()
 *             } else {
 *                 answer.slice(i..answer.lastIndex).toIntArray()
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (19.19ms, 70MB)
 * 테스트 2 〉	통과 (20.77ms, 70.9MB)
 * 테스트 3 〉	통과 (18.64ms, 66.1MB)
 * 테스트 4 〉	통과 (16.47ms, 64.9MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(arr: IntArray, query: IntArray): IntArray {
 *         val result = query.withIndex()
 *             .fold(arr) { acc, (i, e) -> if (i % 2 == 0) acc.copyOfRange(0, e+1) else acc.copyOfRange(e, acc.size) }
 *         return if (result.isEmpty()) intArrayOf(-1) else result
 *     }
 * }
 * 테스트 1 〉	통과 (11.86ms, 67.9MB)
 * 테스트 2 〉	통과 (11.61ms, 65.2MB)
 * 테스트 3 〉	통과 (11.25ms, 65.2MB)
 * 테스트 4 〉	통과 (10.82ms, 64MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(0, 1, 2, 3, 4, 5), intArrayOf(4, 1, 2)), intArrayOf(1, 2, 3))
  validate(s.solution(intArrayOf(0, 1, 2, 3, 4, 5), intArrayOf(0, 0)), intArrayOf(0))
  validate(s.solution(intArrayOf(0, 1, 2, 3, 4, 5), intArrayOf(2, 0)), intArrayOf(0, 1, 2))
}
