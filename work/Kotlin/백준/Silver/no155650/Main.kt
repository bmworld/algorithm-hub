package 백준.Silver.no155650

import java.io.*

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      val n = nextInt()
      val m = nextInt()

      BufferedWriter(OutputStreamWriter(System.out)).use { bw -> solveTo(n, m, bw) }
    }

// nCm 조합
fun solveTo(n: Int, m: Int, out: Appendable) {
  val used = BooleanArray(n)
  val arr = IntArray(m)

  val line = StringBuilder(m * 2)
  fun writeLine() {
    line.setLength(0) // 초기화
    for (i in 0 until m) {
      line.append(arr[i])
      if (i + 1 < m) line.append(' ')
    }
    out.append(line)
    out.append('\n')
  }

  fun dfs(l: Int, stt: Int) {
    if (l == m) {
      writeLine()
      return
    }
    for (i in stt..n) {
      arr[l] = i
      dfs(l + 1, i + 1)
    }
  }

  dfs(0, 1)
}

/** 테스트 코드용 */
fun solution(n: Int, m: Int): String {
  val sb = StringBuilder()
  solveTo(n, m, sb)
  return sb.trimEnd().toString()
}
