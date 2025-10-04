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

  var answer = Int.MAX_VALUE
  val teamSize = board.size / 2
  val t1 = mutableListOf<Int>()
  val t2 = mutableListOf<Int>()
  val ch = BooleanArray(board.size)

  fun dfs(l: Int, stt: Int) {
    if (l == teamSize) {
      // 초기화
      t1.clear()
      t2.clear()
      // 선택항목 점수계산
      for ((idx, bool) in ch.withIndex()) {
        if (bool) t1.add(idx) else t2.add(idx)
      }

      var t1Sum = 0
      var t1L = 0
      while (t1L < teamSize) {
        val t1m1 = t1[t1L]
        for (t1R in t1L + 1 until teamSize) {
          val t1m2 = t1[t1R]
          t1Sum += board[t1m1][t1m2] + board[t1m2][t1m1]
        }

        t1L++
      }

      var t2Sum = 0
      var t2L = 0
      while (t2L < teamSize) {
        val t2m1 = t2[t2L]
        for (t2R in t2L + 1 until teamSize) {
          val t2m2 = t2[t2R]
          t2Sum += board[t2m1][t2m2] + board[t2m2][t2m1]
        }

        t2L++
      }

      // 차이값
      val diff = if (t1Sum < t2Sum) t2Sum - t1Sum else t1Sum - t2Sum
      if (diff < answer) answer = diff
      return
    }

    for (i in stt until board.size) {
      ch[i] = true
      dfs(l + 1, i + 1)
      ch[i] = false
    }
  }

  dfs(0, 0)

  out.append(answer.toString())
}