package 백준.Silver.no14889

import java.io.*

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      val n = nextInt()
      val board = Array(n) { IntArray(n) { nextInt() } }

      BufferedWriter(OutputStreamWriter(System.out)).use { bw -> solveTo(board, bw) }
    }

/**
 * - i, j 능력치 = Sij, Sji 2가지 더해야함
 *
 * @param board 능력치판
 * @return 두 팀의 능력치 차이 최소값
 */
fun solveTo(
    board: Array<IntArray>,
    out: Appendable,
) {

  out.append()
}

/** 테스트 */
fun solution(board: Array<IntArray>): String {
  val sb = StringBuilder()
  solveTo(board, sb)
  return sb.trimEnd().toString()
}
