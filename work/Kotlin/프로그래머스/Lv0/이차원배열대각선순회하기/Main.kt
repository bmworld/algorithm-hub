package 프로그래머스.Lv0.이차원배열대각선순회하기

import util.validate

class Solution {

  fun solution(board: Array<IntArray>, k: Int): Int {
    var ans = 0
    val R = board.size
    val C = board[0].size

    l@ for (r in 0 until R) {
      val row = board[r]
      for (c in 0 until C) {
        if (r + c > k) continue@l
        ans += row[c]
      }
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 60.7MB)
 * 테스트 2 〉	통과 (0.01ms, 59.2MB)
 * 테스트 3 〉	통과 (0.01ms, 60.1MB)
 * 테스트 4 〉	통과 (0.01ms, 60.7MB)
 * 테스트 5 〉	통과 (0.01ms, 59.6MB)
 * 테스트 6 〉	통과 (0.02ms, 60.2MB)
 * 테스트 7 〉	통과 (0.04ms, 58.6MB)
 * 테스트 8 〉	통과 (0.01ms, 59.9MB)
 * 테스트 9 〉	통과 (0.01ms, 59.4MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(board: Array<IntArray>, k: Int) = board.indices.filter { it <= k }.sumOf { i -> (0 until board[i].size).filter { it <= k - i }.sumOf { j -> board[i][j] } }
 * }
 * 테스트 1 〉	통과 (14.53ms, 62.9MB)
 * 테스트 2 〉	통과 (11.00ms, 63.2MB)
 * 테스트 3 〉	통과 (14.87ms, 63.6MB)
 * 테스트 4 〉	통과 (10.99ms, 61.4MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      intArrayOf(0, 1, 2),
      intArrayOf(1, 2, 3),
      intArrayOf(2, 3, 4),
      intArrayOf(3, 4, 5),
    ),
    2
  ), 8)
}
