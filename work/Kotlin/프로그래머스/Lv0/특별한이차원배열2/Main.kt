package 프로그래머스.Lv0.특별한이차원배열2

import util.validate

class Solution {

  fun solution(arr: Array<IntArray>): Int {
    val R = arr.size
    val C = arr[0].size
    for (r in 0 until R)
      for (c in r + 1 until C)
        if (arr[r][c] != arr[c][r]) return 0
    return 1
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.4MB)
 * 테스트 2 〉	통과 (0.01ms, 60.9MB)
 * 테스트 3 〉	통과 (0.01ms, 60.2MB)
 * 테스트 4 〉	통과 (0.01ms, 59.7MB)
 * 테스트 5 〉	통과 (0.01ms, 58.2MB)
 * 테스트 6 〉	통과 (0.04ms, 60.5MB)
 * 테스트 7 〉	통과 (0.01ms, 60MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(arr: Array<IntArray>) = if ((arr.indices).all { i -> (i + 1 until arr.size).all { j -> arr[i][j] == arr[j][i] } }) 1 else 0
 * }
 * class Solution {
 *     fun solution(arr: Array<IntArray>) = if ((arr.indices).all { i -> (i + 1 until arr.size).all { j -> arr[i][j] == arr[j][i] } }) 1 else 0
 * }
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      intArrayOf(5, 192, 33),
      intArrayOf(192, 72, 95),
      intArrayOf(33, 95, 999),
    )
  ), 1)
  validate(s.solution(
    arrayOf(
      intArrayOf(19, 498, 258, 587),
      intArrayOf(63, 93, 7, 754),
      intArrayOf(258, 7, 1000, 723),
      intArrayOf(587, 754, 723, 81)
    )
  ), 0)
}
