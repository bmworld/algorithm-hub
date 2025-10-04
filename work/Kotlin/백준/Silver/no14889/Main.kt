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

  var answer = Int.MAX_VALUE
  val teamSize = board.size / 2
  val t1 = mutableListOf<Int>()
  val t2 = mutableListOf<Int>()
  val ch = BooleanArray(board.size)

  fun dfs(l: Int, stt: Int) {
    if (l == teamSize) {

      // 선택된 항목
      t1.clear()
      t2.clear()
      for ((idx, bool) in ch.withIndex()) {
        if (bool) t1.add(idx) else t2.add(idx)
      }

      val t1s = 0
      val t2s = 0

      // 점수 계산

      var diff = if (t1s < t2s) t2s - t1s else t1s - t2s
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

  // TODO 2차원 배열에 (i,j) 점수 캐싱??
  // TODO args -> 팀1,2 '누적 점수'사용?
}

/** 테스트 */
fun solution(board: Array<IntArray>): String {
  val sb = StringBuilder()
  solveTo(board, sb)
  return sb.trimEnd().toString()
}
