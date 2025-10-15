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
  val rowUsed = Array(9) { BooleanArray(10) }
  val colUsed = Array(9) { BooleanArray(10) }
  val boxUsed = Array(9) { BooleanArray(10) }

  val todos = IntArray(81)
  var todoCnt = 0
  val rowPos = IntArray(81)
  val colPos = IntArray(81)
  val boxPos = IntArray(81)

  for (r in 0 until n) {
    for (c in 0 until n) {
      val num = board[r][c]
      if (num == 0) {
        // 조회대상 지정
        val pos = r * 9 + c
        todos[todoCnt++] = pos
        rowPos[pos] = pos / 9
        colPos[pos] = pos % 9
        boxPos[pos] = (rowPos[pos] / 3) * 3 + colPos[pos] / 3
      } else {
        // Check: 사용 중인 숫자
        val b = (r / 3) * 3 + (c / 3)
        boxUsed[b][num] = true
        rowUsed[r][num] = true
        colUsed[c][num] = true
      }
    }
  } // r: 10의 자리, c: 1의 자리

  fun dfs(k: Int): Boolean {
    if (k == todoCnt) { // DONE
      val sb = StringBuilder()
      for (r in 0 until n) {
        for (c in 0 until n) {
          sb.append(board[r][c])
          if (c != n - 1) sb.append(' ')
        }
        if (r != n - 1) sb.append("\n")
      }
      out.append(sb)
      return true
    }

    // 최적화 - 숫자 후보적은 것 선택
    var best = -1
    var bestRemaining = 10
    for (i in k until todoCnt) {
      val rc = todos[i]
      val r = rowPos[rc]
      val c = colPos[rc]
      val b = boxPos[rc]
      var remaining = 0
      for (num in 1..9) {
        if (rowUsed[r][num] || colUsed[c][num] || boxUsed[b][num]) continue
        remaining++
      }
      if (remaining < bestRemaining) {
        bestRemaining = remaining
        best = i
        if (remaining == 1) break
      }
    }
    if (bestRemaining == 0) return false

    if (best != k) {
      swap(todos, k, best)
      val result = dfs(k)
      swap(todos, k, best)
      return result
    }

    val rc = todos[k]
    val r = rowPos[rc]
    val c = colPos[rc]
    val b = boxPos[rc]
    for (num in 1..9) {
      val row = rowUsed[r]
      val col = colUsed[c]
      val box = boxUsed[b]
      if (row[num] || col[num] || box[num]) continue

      row[num] = true
      col[num] = true
      box[num] = true
      board[r][c] = num
      if (dfs(k + 1)) return true
      board[r][c] = 0
      row[num] = false
      col[num] = false
      box[num] = false
    }

    return false
  }

  dfs(0)
}

fun swap(arr: IntArray, i: Int, j: Int) {
  val t = arr[i]
  arr[i] = arr[j]
  arr[j] = t
}

/* 테스트용 */
fun solution(board: Array<IntArray>): String {
  val sb = StringBuilder()
  solveTo(board, sb)
  return sb.toString().trim()
}
