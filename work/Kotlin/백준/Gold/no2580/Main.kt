package 백준.Gold.no2580

import java.io.*

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      val board = Array(9) { IntArray(9) { nextInt() } }

      BufferedWriter(OutputStreamWriter(System.out)).use { bw -> solveTo(board, bw) }
    }

fun solveTo(board: Array<IntArray>, out: Appendable) {
  var done = false
  val n = board.size
  val todos =
      mutableListOf<Int>().apply {
        for (r in 0 until n) {
          for (c in 0 until n) {
            if (board[r][c] == 0) {
              this.add(r * 10 + c)
            }
          }
        }
      } // r: 10의 자리, c: 1의 자리

  val dCol =
      IntArray(3).also {
        it[0] = -1
        it[1] = 0
        it[2] = 1
      }
  val dRow =
      IntArray(3).also {
        it[0] = -1
        it[1] = 0
        it[2] = 1
      }

  fun dfs(k: Int) {
    if (done) return
    if (k == todos.size) {
      for (i in 0 until n) {
        out.append(board[i].joinToString(" "))
        if (i != n - 1) out.append("\n")
      }
      done = true
      return // End
    }

    // Start
    val todo = todos[k]
    val r = todo / 10
    val c = todo % 10

    // Check used Number
    val used = BooleanArray(10)
    // Check: Row
    for (col in 0 until n) if (board[r][col] > 0) used[board[r][col]] = true
    // Check: Col
    for (row in 0 until n) if (board[row][c] > 0) used[board[row][c]] = true
    // Check: Box
    var searchBox = true
    for (row in 2 until n step 3) {
      if (!searchBox) break
      for (col in 2 until n step 3) {
        if (!searchBox) break
        if (r <= row && c <= col) {
          // 중앙값
          val centerRow = row - 1
          val centerCol = col - 1
          // 순회
          for (row in dRow) {
            for (col in dCol) {
              if (board[centerRow + row][centerCol + col] > 0)
                  used[board[centerRow + row][centerCol + col]] = true
            }
          }
          searchBox = false
        }
      }
    }
    // DFS
    for (num in 1..9) {
      if (used[num]) continue
      board[r][c] = num
      dfs(k + 1)
      board[r][c] = 0
    }
  }

  dfs(0)
}

/* 테스트용 */
fun solution(board: Array<IntArray>): String {
  val sb = StringBuilder()
  solveTo(board, sb)
  return sb.toString().trim()
}
