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

/**
 * @param board Sudoku 판 (0: 빈칸)
 * @return 최종모습
 */
fun solveTo(board: Array<IntArray>, out: Appendable) {

  val n = board.size
  val numCh = BooleanArray(10)
  val rowCh = BooleanArray(n)
  val colCh = BooleanArray(n)
  val secCh = BooleanArray(n)
  val dx = listOf(-1, 0, 1)
  val dy = listOf(-1, 0, 1)

  fun fill() {

    var totalZeoCnt = 0
    // Check by Row
    for (row in 0 until n) {
      if (rowCh[row]) continue
      // 초기화
      numCh.apply { for (i in 0..9) this[i] = false }
      var zeroCnt = 0
      var r = 0
      var c = 0
      for (col in 0 until n) {
        if (board[row][col] == 0) {
          zeroCnt++
          totalZeoCnt++
          r = row
          c = col
        } else numCh[board[row][col]] = true
      }

      if (zeroCnt == 0) {
        rowCh[row] = true
      } else if (zeroCnt == 1) {
        var t = Int.MIN_VALUE
        for (i in 1..9) {
          if (!numCh[i]) {
            t = i
          }
        }

        board[r][c] = t
      }
    }

    // Check by Row
    for (col in 0 until n) {
      if (colCh[col]) continue
      // 초기화
      numCh.apply { for (i in 0..9) this[i] = false }
      var zeroCnt = 0
      var tr = 0
      var tc = 0
      for (row in 0 until n) {
        if (board[row][col] == 0) {
          zeroCnt++
          totalZeoCnt++
          tr = row
          tc = col
        } else numCh[board[row][col]] = true
      }

      if (zeroCnt == 0) {
        colCh[col] = true
      } else if (zeroCnt == 1) {
        var t = Int.MIN_VALUE
        for (i in 1..9) {
          if (!numCh[i]) {
            t = i
          }
        }
        board[tr][tc] = t
      }
    }

    // Check by Sector
    var secIdx = 0
    for (secRow in 1 until n step 3) {
      for (secCol in 1 until n step 3) {
        if (secCh[secCol]) continue
        numCh.apply { for (i in 0..9) this[i] = false }

        var zeroCnt = 0
        var r = 0
        var c = 0
        for (row in dy) {
          for (col in dx) {
            if (board[secRow + row][secCol + col] == 0) {
              zeroCnt++
              totalZeoCnt++
              r = secRow + row
              c = secCol + col
            } else numCh[board[secRow + row][secCol + col]] = true
          }
        }

        if (zeroCnt == 0) {
          secCh[secIdx] = true
        } else if (zeroCnt == 1) {
          var t = Int.MIN_VALUE
          for (i in 1..9) {
            if (!numCh[i]) {
              t = i
            }
          }
          board[r][c] = t
        }

        secIdx++
      }
    }

    if (totalZeoCnt > 0) fill()
  }

  fill()

  for (i in 0 until n) {
    out.append(board[i].joinToString(" "))
    if (i != n - 1) out.append("\n")
  }
}

/* 테스트용 */
fun solution(board: Array<IntArray>): String {
  val sb = StringBuilder()
  solveTo(board, sb)
  return sb.toString().trim()
}
