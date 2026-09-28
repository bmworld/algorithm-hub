package 프로그래머스.Lv0.배열만들기3

import util.validate

class Solution {

  fun solution(arr: IntArray, intervals: Array<IntArray>): IntArray {
    val r1 = intervals[0]
    val r1s = r1[0]
    val r2 = intervals[1]
    val r2s = r2[0]

    val l1 = r1[1] - r1s + 1
    val l2 = r2[1] - r2s + 1
    return IntArray(l1 + l2) { i ->
      if (i < l1) arr[i + r1s]
      else arr[i - l1 + r2s]
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.03ms, 59.1MB)
 * 테스트 2 〉	통과 (0.09ms, 62.4MB)
 * 테스트 3 〉	통과 (0.79ms, 69.6MB)
 * 테스트 4 〉	통과 (0.99ms, 70.3MB)
 * 테스트 5 〉	통과 (0.65ms, 66.2MB)
 * 테스트 6 〉	통과 (0.20ms, 61.7MB)
 * 테스트 7 〉	통과 (0.76ms, 69.1MB)
 * 테스트 8 〉	통과 (1.22ms, 72MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(arr: IntArray, intervals: Array<IntArray>): List<Int> {
 *         return intervals.flatMap { ints -> arr.sliceArray(IntRange(ints[0], ints[1])).toList() }
 *     }
 * }
 * 테스트 1 〉	통과 (15.53ms, 63MB)
 * 테스트 2 〉	통과 (13.33ms, 65.8MB)
 * 테스트 3 〉	통과 (20.33ms, 72.2MB)
 * 테스트 4 〉	통과 (23.50ms, 75.1MB)
 * 테스트 5 〉	통과 (16.29ms, 71.2MB)
 * 테스트 6 〉	통과 (13.63ms, 66.4MB)
 * 테스트 7 〉	통과 (14.48ms, 72.2MB)
 * 테스트 8 〉	통과 (16.10ms, 75.1MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      intArrayOf(1, 2, 3, 4, 5),
      arrayOf(intArrayOf(1, 3), intArrayOf(0, 4))
    ),
    intArrayOf(2, 3, 4, 1, 2, 3, 4, 5)
  )
}
