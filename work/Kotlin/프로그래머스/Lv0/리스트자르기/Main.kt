package 프로그래머스.Lv0.리스트자르기

import util.validate

class Solution {

  fun solution(n: Int, slicer: IntArray, num_list: IntArray): IntArray {
    val a = slicer[0]
    val b = slicer[1]
    val c = slicer[2]

    return when (n) {
      1 -> IntArray(b + 1) { num_list[it] }
      2 -> IntArray(num_list.size - a) { num_list[it + a] }
      3 -> IntArray(b - a + 1) { num_list[it + a] }
      else -> IntArray((b - a + c) / c) { num_list[a + it * c] }
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 60.1MB)
 * 테스트 2 〉	통과 (0.01ms, 60.3MB)
 * 테스트 3 〉	통과 (0.01ms, 59.8MB)
 * 테스트 4 〉	통과 (0.01ms, 60.1MB)
 * 테스트 5 〉	통과 (0.01ms, 60.1MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(n: Int, slicer: IntArray, numList: IntArray): IntArray {
 *         return generateSequence(
 *             if (n == 1) 0 else slicer[0],
 *             { it + if (n == 4) slicer[2] else 1 }
 *         )
 *             .takeWhile { it <= if (n == 2) numList.size - 1 else slicer[1] }
 *             .map { numList[it] }
 *             .toList()
 *             .toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (11.57ms, 64.6MB)
 * 테스트 2 〉	통과 (11.07ms, 64.8MB)
 * 테스트 3 〉	통과 (12.45ms, 64.8MB)
 * 테스트 4 〉	통과 (11.89ms, 63.9MB)
 * 테스트 5 〉	통과 (11.44ms, 64MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(n: Int, slicer: IntArray, num_list: IntArray): IntArray {
 *         val (a,b,c) = slicer
 *         return when (n) {
 *             1 -> num_list.slice(0..b)
 *             2 -> num_list.slice(a..num_list.lastIndex)
 *             3 -> num_list.slice(a..b)
 *             else -> (a..b step c).map { num_list[it] }
 *         }.toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (14.57ms, 63.6MB)
 * 테스트 2 〉	통과 (14.11ms, 64.9MB)
 * 테스트 3 〉	통과 (5.65ms, 60.5MB)
 * 테스트 4 〉	통과 (14.07ms, 64.6MB)
 * 테스트 5 〉	통과 (13.88ms, 64.6MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    1,
    intArrayOf(1, 5, 2),
    intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
  ),
    intArrayOf(1, 2, 3, 4, 5, 6)
  )

  validate(s.solution(
    2,
    intArrayOf(1, 5, 2),
    intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
  ),
    intArrayOf(2, 3, 4, 5, 6, 7, 8, 9)
  )

  validate(s.solution(
    3,
    intArrayOf(1, 5, 2),
    intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
  ),
    intArrayOf(2, 3, 4, 5, 6)
  )

  validate(s.solution(
    4,
    intArrayOf(1, 5, 1),
    intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
  ),
    intArrayOf(2, 3, 4, 5, 6)
  )

  validate(s.solution(
    4,
    intArrayOf(1, 5, 2),
    intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
  ),
    intArrayOf(2, 4, 6)
  )

  validate(s.solution(
    4,
    intArrayOf(1, 5, 3),
    intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
  ),
    intArrayOf(2, 5)
  )

}
