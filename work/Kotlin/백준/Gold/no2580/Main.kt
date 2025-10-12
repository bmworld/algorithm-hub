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
  //  for ((row, ints) in board.withIndex()) {
  //    for ((col, i) in ints.withIndex()) {
  //      println("row=$row, col = ${col}, i=$i")
  //    }
  //  }

  out.append("")
}

/* 테스트용 */
fun solution(board: Array<IntArray>): String {
  val sb = StringBuilder()
  solveTo(board, sb)
  return sb.toString().trim()
}
