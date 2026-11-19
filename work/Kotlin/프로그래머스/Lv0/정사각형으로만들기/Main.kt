package 프로그래머스.Lv0.정사각형으로만들기

import util.validate

class Solution {

  fun solution(arr: Array<IntArray>): Array<IntArray> {
    val R = arr.size
    val C = arr[0].size
    val N = maxOf(R, C)
    return Array(N) { r ->
      if (r < R) IntArray(N) { c -> if (c < C) arr[r][c] else 0 }
      else IntArray(N)
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.3MB)
 * 테스트 2 〉	통과 (0.02ms, 59.8MB)
 * 테스트 3 〉	통과 (0.05ms, 60.5MB)
 * 테스트 4 〉	통과 (0.04ms, 60.6MB)
 *
 * [RIVAL 1]
 * import kotlin.math.max
 *
 * class Solution {
 *     fun solution(arr: Array<IntArray>): Array<IntArray> {
 *         val max = max(arr.size, arr[0].size)
 *         val array = Array(max) { IntArray(max) }
 *
 *         for (i in arr.indices) {
 *             System.arraycopy(arr[i], 0, array[i], 0, arr[0].size)
 *         }
 *         return array
 *     }
 * }
 * 테스트 1 〉	통과 (0.05ms, 60.3MB)
 * 테스트 2 〉	통과 (0.02ms, 59.5MB)
 * 테스트 3 〉	통과 (0.02ms, 60.9MB)
 * 테스트 4 〉	통과 (0.06ms, 59.6MB)
 *
 * [RIVAL 2]
 * import kotlin.math.max
 * class Solution {
 *     fun solution(arr: Array<IntArray>): Array<IntArray> {
 *         val width = arr.first().size
 *         val height = arr.size
 *         val size = max(height, width)
 *
 *         val answer = Array<IntArray>(size) { IntArray(size) }
 *         for (i in 0 until height) {
 *             for (j in 0 until width) {
 *                 answer[i][j] = arr[i][j]
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (8.88ms, 63.7MB)
 * 테스트 2 〉	통과 (8.77ms, 64.5MB)
 * 테스트 3 〉	통과 (9.31ms, 63.9MB)
 * 테스트 4 〉	통과 (9.14ms, 63.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      intArrayOf(572, 22, 37),
      intArrayOf(287, 726, 384),
      intArrayOf(85, 137, 292),
      intArrayOf(487, 13, 876)
    )),
    arrayOf(
      intArrayOf(572, 22, 37, 0),
      intArrayOf(287, 726, 384, 0),
      intArrayOf(85, 137, 292, 0),
      intArrayOf(487, 13, 876, 0)
    ))

  validate(s.solution(
    arrayOf(
      intArrayOf(57, 192, 534, 2),
      intArrayOf(9, 345, 192, 999),
    )),
    arrayOf(
      intArrayOf(57, 192, 534, 2),
      intArrayOf(9, 345, 192, 999),
      intArrayOf(0, 0, 0, 0),
      intArrayOf(0, 0, 0, 0),
    ))

  validate(s.solution(
    arrayOf(
      intArrayOf(1, 2),
      intArrayOf(3, 4)
    )),
    arrayOf(
      intArrayOf(1, 2),
      intArrayOf(3, 4)
    ))

  validate(s.solution(
    arrayOf(
      intArrayOf(1, 2, 3),
    )),
    arrayOf(
      intArrayOf(1, 2, 3),
      intArrayOf(0, 0, 0),
      intArrayOf(0, 0, 0),
    ))


  validate(s.solution(
    arrayOf(
      intArrayOf(1),
      intArrayOf(2),
      intArrayOf(3),
    )),
    arrayOf(
      intArrayOf(1, 0, 0),
      intArrayOf(2, 0, 0),
      intArrayOf(3, 0, 0),
    ))
}
