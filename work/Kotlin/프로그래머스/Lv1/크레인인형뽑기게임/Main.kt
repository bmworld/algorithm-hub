package 프로그래머스.Lv1.크레인인형뽑기게임

import util.validate

class Solution {

  val EMPTY = 0
  val INIT = -1
  val EXPLODED = 2
  fun solution(board: Array<IntArray>, moves: IntArray): Int {
    val h = board.size
    val w = board[0].size
    val top = IntArray(w) { INIT }
    for (r in 0 until h) for (c in 0 until w) if (board[r][c] != EMPTY && top[c] == INIT) top[c] = r

    val basket = IntArray(moves.size)
    var bi = 0
    var ans = 0
    for (x in moves) {
      val c = x - 1
      val r = top[c]
      if (r < h) {
        top[c]++
        val cur = board[r][c]

        if (bi > 0 && basket[bi - 1] == cur) {
          bi--
          ans += EXPLODED
        } else basket[bi++] = cur
      }
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * v1:
 * 테스트 1 〉	통과 (0.02ms, 59.5MB)
 * 테스트 2 〉	통과 (0.02ms, 57.6MB)
 * 테스트 3 〉	통과 (0.02ms, 59MB)
 * 테스트 4 〉	통과 (0.12ms, 59.2MB)
 * 테스트 5 〉	통과 (0.02ms, 58.6MB)
 * v2: board 1차원배열 제거 (굳이 없어도 됨)
 * 테스트 1 〉	통과 (0.02ms, 57.9MB)
 * 테스트 2 〉	통과 (0.02ms, 57.9MB)
 * 테스트 3 〉	통과 (0.02ms, 57.6MB)
 * 테스트 4 〉	통과 (0.07ms, 58MB)
 * 테스트 5 〉	통과 (0.02ms, 59.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import java.util.*
 *
 * class Solution {
 *     fun solution(board: Array<IntArray>, moves: IntArray): Int {
 *         var answer = 0
 *         val stack = Stack<Int>()
 *
 *         moves.forEach {
 *             for (i in board.indices) {
 *                 if (board[i][it - 1] != 0) {
 *                     if (stack.isNotEmpty() && stack.peek() == board[i][it - 1]) {
 *                         answer += 2
 *                         stack.pop()
 *                     } else {
 *                         stack.push(board[i][it - 1])
 *                     }
 *                     board[i][it - 1] = 0
 *
 *                     break
 *                 }
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.33ms, 57.8MB)
 * 테스트 2 〉	통과 (0.27ms, 59.7MB)
 * 테스트 3 〉	통과 (0.25ms, 58MB)
 * 테스트 4 〉	통과 (0.89ms, 57.6MB)
 * 테스트 5 〉	통과 (0.26ms, 58.2MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf(
    intArrayOf(0, 0, 0, 0, 0),
    intArrayOf(0, 0, 1, 0, 3),
    intArrayOf(0, 2, 5, 0, 1),
    intArrayOf(4, 2, 4, 4, 2),
    intArrayOf(3, 5, 1, 3, 1),
  ),
    intArrayOf(1, 5, 3, 5, 1, 2, 1, 4)
  ), 4)
}
