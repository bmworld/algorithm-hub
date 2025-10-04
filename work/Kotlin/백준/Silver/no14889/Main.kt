package 백준.Silver.no14889

import java.io.*
import kotlin.math.abs
import kotlin.math.min

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
 * @param board 능력치판
 * @return 두 팀의 능력치 차이 최소값
 */
fun solveTo(
    board: Array<IntArray>,
    out: Appendable,
) {

  var answer = Int.MAX_VALUE
  val n = board.size
  val half = n / 2

  // 전처리: 능력치
  val pair = Array(n) { IntArray(n) }
  for (i in 0 until n) {
    for (j in i + 1 until n) {
      pair[i][j] = board[i][j] + board[j][i]
    }
  }

  // 팀 할당
  val t1 = IntArray(half)
  t1[0] = 0 // 0번은 팀1에 고정
  val t2 = IntArray(half)

  // 합
  fun sumPairs(team: IntArray): Int {
    var sum = 0
    for (i1 in 0 until half) {
      val ti1 = team[i1]
      for (i2 in i1 + 1 until half) {
        val ti2 = team[i2]
        val i = min(ti1, ti2)
        val j = if (i == ti1) ti2 else ti1
        sum += pair[i][j]
      }
    }

    return sum
  }

  fun dfs(idx: Int, start: Int) {
    if (idx == half) {
      // 상대팀 할당
      var t1i = 0
      var t2i = 0
      var num = 0
      while (num < n) {
        if (t1i < half && t1[t1i] == num) {
          t1i++
        } else {
          t2[t2i++] = num
        }
        num++
      }

      // 능력치 비교
      val s1 = sumPairs(t1)
      val s2 = sumPairs(t2)

      val diff = abs(s1 - s2)
      if (diff < answer) answer = diff
      return
    }

    for (num in start until n) {
      t1[idx] = num
      dfs(idx + 1, num + 1)
    }
  }

  dfs(1, 1)

  out.append(answer.toString())
}

/** 테스트 */
fun solution(board: Array<IntArray>): Int {
  val sb = StringBuilder()
  solveTo(board, sb)
  return sb.toString().toInt()
}
