package 프로그래머스.Lv2.혼자서하는틱택토

import util.validate

class Solution {
  companion object {

    const val O = 'O'
    const val X = 'X'
    const val SIZE = 3

    const val VALID = 1
    const val INVALID = 0
  }

  fun solution(board: Array<String>): Int {

    var oc = 0
    var xc = 0

    repeat(SIZE) { r ->
      val row = board[r]
      repeat(SIZE) { c ->
        when (row[c]) {
          O -> oc++
          X -> xc++
        }
      }
    }

    val diff = oc - xc
    if (diff !in 0..1) return INVALID

    var ol = 0
    var xl = 0

    fun checkLine(a: Char, b: Char, c: Char) {
      if (a != b || b != c) return
      if (a == O) ol++
      if (a == X) xl++
    }

    checkLine(board[0][0], board[0][1], board[0][2])
    checkLine(board[1][0], board[1][1], board[1][2])
    checkLine(board[2][0], board[2][1], board[2][2])
    checkLine(board[0][0], board[1][0], board[2][0])
    checkLine(board[0][1], board[1][1], board[2][1])
    checkLine(board[0][2], board[1][2], board[2][2])
    checkLine(board[0][0], board[1][1], board[2][2])
    checkLine(board[0][2], board[1][1], board[2][0])

    return if (diff == 0 && ol != 0 || diff == 1 && xl != 0) INVALID
    else VALID
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.16ms, 59.2MB)
 * 테스트 2 〉	통과 (0.19ms, 60.9MB)
 * 테스트 3 〉	통과 (0.18ms, 59.6MB)
 * 테스트 4 〉	통과 (0.16ms, 60.5MB)
 * 테스트 5 〉	통과 (0.20ms, 58.1MB)
 * 테스트 6 〉	통과 (0.16ms, 60MB)
 * 테스트 7 〉	통과 (0.15ms, 59.3MB)
 * 테스트 8 〉	통과 (0.15ms, 60.4MB)
 * 테스트 9 〉	통과 (0.15ms, 61.2MB)
 * 테스트 10 〉	통과 (0.15ms, 60MB)
 * v2:
 * 테스트 1 〉	통과 (0.21ms, 60.4MB)
 * 테스트 2 〉	통과 (0.02ms, 60.5MB)
 * 테스트 3 〉	통과 (0.19ms, 60.1MB)
 * 테스트 4 〉	통과 (0.22ms, 60MB)
 * 테스트 5 〉	통과 (0.01ms, 60.4MB)
 * 테스트 6 〉	통과 (0.15ms, 59.4MB)
 * 테스트 7 〉	통과 (0.16ms, 59.7MB)
 * 테스트 8 〉	통과 (0.17ms, 58.7MB)
 * 테스트 9 〉	통과 (0.02ms, 59.5MB)
 * 테스트 10 〉	통과 (0.01ms, 59.3MB)
 *
 *
 *
 * [RIVAL 1]
 * class Solution {
 *
 *     fun getCount(board: Array<String>, c: Char): Int {
 *         return board.sumOf {
 *             it.count {
 *                 it == c
 *             }
 *         }
 *     }
 *
 *     fun isWin(board: Array<String>, c: Char): Boolean {
 *         // 가로로 체크
 *         for (y in 0 until 3) {
 *             var cnt = 0
 *             for (x in 0 until 3) {
 *                 if (board[y][x] == c)
 *                     cnt++
 *             }
 *             if (cnt == 3)
 *                 return true
 *         }
 *
 *         // 세로로 체크
 *         for (x in 0 until 3) {
 *             var cnt = 0
 *             for (y in 0 until 3) {
 *                 if (board[y][x] == c)
 *                     cnt++
 *             }
 *             if (cnt == 3)
 *                 return true
 *         }
 *
 *         // 대각선 체크
 *         if (board[1][1] == c && board[0][0] == c && board[2][2] == c)
 *             return true
 *         if (board[1][1] == c && board[0][2] == c && board[2][0] == c)
 *             return true
 *         return false
 *     }
 *
 *     fun solution(board: Array<String>): Int {
 *         val oCount = getCount(board, 'O')
 *         val xCount = getCount(board, 'X')
 *
 *         if (xCount > oCount || oCount > xCount + 1)
 *             return 0
 *         if (isWin(board, 'O') && oCount != xCount + 1)
 *             return 0
 *         if (isWin(board, 'X') && xCount != oCount)
 *             return 0
 *         return 1
 *     }
 * }
 * 테스트 1 〉	통과 (0.02ms, 59.5MB)
 * 테스트 2 〉	통과 (0.01ms, 59.8MB)
 * 테스트 3 〉	통과 (0.02ms, 60MB)
 * 테스트 4 〉	통과 (0.02ms, 58.2MB)
 * 테스트 5 〉	통과 (0.02ms, 59.8MB)
 * 테스트 6 〉	통과 (0.02ms, 60.2MB)
 * 테스트 7 〉	통과 (0.02ms, 60.1MB)
 * 테스트 8 〉	통과 (0.02ms, 59.9MB)
 * 테스트 9 〉	통과 (0.02ms, 60.2MB)
 * 테스트 10 〉	통과 (0.02ms, 59.6MB)
 *
 *
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(board: Array<String>): Int {
 *         var oCount = 0;
 *         var xCount = 0;
 *
 *         board.forEach { row ->
 *             row.forEach { c ->
 *                 if (c == 'O') {
 *                     oCount++
 *                 } else if (c == 'X') {
 *                     xCount++
 *                 }
 *             }
 *         }
 *
 *         if (xCount + 1 < oCount || oCount < xCount || (xCount == oCount && isWinner('O', board)) || (xCount < oCount && isWinner('X', board)))  {
 *             return 0
 *         } else  {
 *             return 1
 *         }
 *     }
 *
 *     private fun isWinner(c: Char, board: Array<String>): Boolean {
 *         // 행 검사
 *         for (i in 0 until 3) {
 *             if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][1] == c) return true;
 *         }
 *         // 열 검사
 *         for (i in 0 until 3) {
 *             if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[1][i] == c) return true;
 *         }
 *         // 대각선 검사
 *         if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] == c) return true;
 *         if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[1][1] == c) return true;
 *
 *         return false
 *     }
 * }
 * 테스트 1 〉	통과 (0.02ms, 60MB)
 * 테스트 2 〉	통과 (0.01ms, 60.1MB)
 * 테스트 3 〉	통과 (0.02ms, 59MB)
 * 테스트 4 〉	통과 (0.02ms, 59.1MB)
 * 테스트 5 〉	통과 (0.02ms, 59.5MB)
 * 테스트 6 〉	통과 (0.02ms, 60.2MB)
 * 테스트 7 〉	통과 (0.01ms, 59.9MB)
 * 테스트 8 〉	통과 (0.02ms, 59.5MB)
 * 테스트 9 〉	통과 (0.02ms, 57.2MB)
 * 테스트 10 〉	통과 (0.02ms, 58.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      "O.X",
      ".O.",
      "..X")
  ), 1)

  validate(s.solution(
    arrayOf(
      "OOO",
      "...",
      "XXX")
  ), 0)

  validate(s.solution(
    arrayOf(
      "...",
      "...",
      "...")
  ), 1)

  validate(s.solution(
    arrayOf(
      "...",
      ".X.",
      "...")
  ), 0)

  validate(s.solution(
    arrayOf(
      ".X.",
      ".X.",
      "..O")
  ), 0)

  validate(s.solution(
    arrayOf(
      ".X.",
      ".XO",
      "..O")
  ), 1)

  validate(s.solution(
    arrayOf(
      ".XO",
      ".XO",
      "..O")
  ), 1)


  validate(s.solution(
    arrayOf(
      ".XO",
      ".XO",
      ".OO")
  ), 0)


  validate(s.solution(
    arrayOf(
      ".XO",
      ".XO",
      "XOO")
  ), 1)

  validate(s.solution(
    arrayOf(
      ".OX",
      ".XO",
      "XOO")
  ), 0)
}
