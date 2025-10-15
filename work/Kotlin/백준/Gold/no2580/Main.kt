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

  val rowCh = BooleanArray(n)

  fun dfs(k: Int): Boolean {
    // Check: 0 only
    val r = k / 10
    val c = k % 10

    if (board[r][c] != 0) return false

    // Validation
    val numCh = BooleanArray(10)
    var v = 0
    // Guess: Row
    // or Guess: Col
    // or Guess: Sec
    //////////// 확정-> return
    // or else: 임의의수 + 롤백 + 섹션에 포함된 항목을 [for clause] dfs()

    // when todos is empty -> Solved
    if (k == todos.size) return true
    else {
      dfs(k + 1)
      return false // TODO 변경하기
    }
  }

  dfs(0)

  for (i in 0 until n) {
    out.append(board[i].joinToString(" "))
    if (i != n - 1) out.append("\n")
  }
}

// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
/**
 * @param board Sudoku 판 (0: 빈칸)
 * @return 최종모습
 */
// fun solveTo2(board: Array<IntArray>, out: Appendable) {
//
//  val n = board.size
//  val numCh = BooleanArray(10)
//  val rowCh = BooleanArray(n)
//  val colCh = BooleanArray(n)
//  val secCh = BooleanArray(n)
//  val dx =
//      IntArray(3).also {
//        it[0] = -1
//        it[1] = 0
//        it[2] = 1
//      }
//  val dy =
//      IntArray(3).also {
//        it[0] = -1
//        it[1] = 0
//        it[2] = 1
//      }
//
//  fun fill() {
//
//    var filledCount = 0
//    // Check by Row
//    for (row in 0 until n) {
//      if (rowCh[row]) continue
//      // 초기화
//      numCh.apply { for (i in 0..9) this[i] = false }
//      var zeroCnt = 0
//      var r = 0
//      var c = 0
//      for (col in 0 until n) {
//        if (board[row][col] == 0) {
//          zeroCnt++
//
//          r = row
//          c = col
//        } else numCh[board[row][col]] = true
//      }
//
//      if (zeroCnt == 0) {
//        rowCh[row] = true
//      } else if (zeroCnt == 1) {
//        filledCount++
//        var t = Int.MIN_VALUE
//        for (i in 1..9) {
//          if (!numCh[i]) {
//            t = i
//            break
//          }
//        }
//
//        board[r][c] = t
//      }
//    }
//
//    // Check by Row
//    for (col in 0 until n) {
//      if (colCh[col]) continue
//      // 초기화
//      numCh.apply { for (i in 0..9) this[i] = false }
//      var zeroCnt = 0
//      var tr = 0
//      var tc = 0
//      for (row in 0 until n) {
//        if (board[row][col] == 0) {
//          zeroCnt++
//          tr = row
//          tc = col
//        } else numCh[board[row][col]] = true
//      }
//
//      if (zeroCnt == 0) {
//        colCh[col] = true
//      } else if (zeroCnt == 1) {
//        filledCount++
//        var t = Int.MIN_VALUE
//        for (i in 1..9) {
//          if (!numCh[i]) {
//            t = i
//            break
//          }
//        }
//        board[tr][tc] = t
//      }
//    }
//
//    // Check by Sector
//    var secIdx = 0
//    for (secRow in 1 until n step 3) {
//      for (secCol in 1 until n step 3) {
//        if (secCh[secIdx]) continue
//        numCh.apply { for (i in 0..9) this[i] = false }
//
//        var zeroCnt = 0
//        var r = 0
//        var c = 0
//        for (row in dy) {
//          for (col in dx) {
//            if (board[secRow + row][secCol + col] == 0) {
//              zeroCnt++
//              r = secRow + row
//              c = secCol + col
//            } else numCh[board[secRow + row][secCol + col]] = true
//          }
//        }
//
//        if (zeroCnt == 0) {
//          secCh[secIdx] = true
//        } else if (zeroCnt == 1) {
//          filledCount++
//          var t = Int.MIN_VALUE
//          for (i in 1..9) {
//            if (!numCh[i]) {
//              t = i
//              break
//            }
//          }
//          board[r][c] = t
//        }
//
//        secIdx++
//      }
//    }
//
//    if (filledCount == 0) return else fill()
//  }
//
//  fill()
//
//  for (i in 0 until n) {
//    out.append(board[i].joinToString(" "))
//    if (i != n - 1) out.append("\n")
//  }
// }

/* 테스트용 */
fun solution(board: Array<IntArray>): String {
  val sb = StringBuilder()
  solveTo(board, sb)
  return sb.toString().trim()
}
