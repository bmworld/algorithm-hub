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

  for (r in 0 until n) {
    for (c in 0 until n) {
      val num = board[r][c]
      if (num == 0) {
        // 조회대상 지정
        todos[todoCnt++] = r * 10 + c
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

    val rc = todos[k]
    val r = rc / 10
    val c = rc % 10
    val box = (r / 3) * 3 + (c / 3)

    // 최적화 - 숫자 후보적은 것 선택
    var best = -1
    var bestRemaining = 10
    for (i in k until todoCnt) {
      val rc = todos[i]
      val r = rc / 10
      val c = rc % 10
      val box = (r / 3) * 3 + (c / 3)
      var remaining = 0
      for (num in 1..9) {
        if (rowUsed[r][num] || colUsed[c][num] || boxUsed[box][num]) continue
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

    for (num in 1..9) {
      if (rowUsed[r][num] || colUsed[c][num] || boxUsed[box][num]) continue

      rowUsed[r][num] = true
      colUsed[c][num] = true
      boxUsed[box][num] = true
      board[r][c] = num
      if (dfs(k + 1)) return true
      board[r][c] = 0
      rowUsed[r][num] = false
      colUsed[c][num] = false
      boxUsed[box][num] = false
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