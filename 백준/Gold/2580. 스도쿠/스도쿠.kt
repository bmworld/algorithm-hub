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
    for (col in 0 until n) {
      used[board[r][col]] = board[r][col] > 0
    }
    // Check: Col
    for (row in 0 until n) {
      used[board[row][c]] = board[row][c] > 0
    }
    // Check: Box
    val startRow = (r / 3) * 3
    val startCol = (c / 3) * 3
    for (r in startRow until startRow + 3) {
      for (c in startCol until startCol + 3) {
        used[board[r][c]] = board[r][c] > 0
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