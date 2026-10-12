package 프로그래머스.Lv0.특이한이차원배열1

import util.validate

class Solution {

  fun solution(n: Int): Array<IntArray> =
    Array(n) { i -> IntArray(n) { j -> if (i == j) 1 else 0 } }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.8MB)
 * 테스트 2 〉	통과 (0.17ms, 60.8MB)
 * 테스트 3 〉	통과 (0.05ms, 59.3MB)
 * 테스트 4 〉	통과 (0.10ms, 61.7MB)
 * 테스트 5 〉	통과 (0.14ms, 61.1MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1), arrayOf(intArrayOf(1)))
  validate(s.solution(3),
    arrayOf(
      intArrayOf(1, 0, 0),
      intArrayOf(0, 1, 0),
      intArrayOf(0, 0, 1)
    )
  )
  validate(s.solution(6), arrayOf(
    intArrayOf(1, 0, 0, 0, 0, 0),
    intArrayOf(0, 1, 0, 0, 0, 0),
    intArrayOf(0, 0, 1, 0, 0, 0),
    intArrayOf(0, 0, 0, 1, 0, 0),
    intArrayOf(0, 0, 0, 0, 1, 0),
    intArrayOf(0, 0, 0, 0, 0, 1),
  ))
}
