package 백준.Silver.no4779

import java.io.*
import kotlin.math.pow

fun main() {
  val st = StreamTokenizer(System.`in`.bufferedReader())
  val bw = BufferedWriter(OutputStreamWriter(System.out))

  while (true) {
    val t = st.nextToken()
    if (t == StreamTokenizer.TT_EOF) break
    if (t != StreamTokenizer.TT_NUMBER) continue

    val n = st.nval.toInt()
    solveTo(n, bw) // BufferedWriter를 그대로 넘김
  }

  bw.flush()
}

/** 제출용 */
fun solveTo(n: Int, out: Appendable) {
  val cnt = 3.0.pow(n).toInt()

  val ogStr = "-".repeat(cnt)
  val line = StringBuilder(ogStr)

  fun writeLine() {
    out.append(line)
    out.append('\n')
    line.setLength(0) // 초기화
  }

  fun cantor(sttIdx: Int, endIdx: Int) {

    val secRange = (endIdx - sttIdx + 1) / 3
    if (secRange < 1) return
    // 3등분
    for (i in sttIdx..endIdx step secRange) {
      val isMiddle = i in sttIdx + secRange until sttIdx + secRange * 2
      // 섹션 처리 (가운데 섹션: 공란)
      for (j in i until i + secRange) {
        line[j] = if (isMiddle) ' ' else line[j]
      }

      if (secRange >= 3) {
        cantor(i, i + secRange - 1)
      }
    }
  }

  cantor(0, line.length - 1)
  writeLine()
}

/** 테스트용 */
fun solution(n: Int): String {
  val sb = StringBuilder()
  solveTo(n, sb)
  return sb.trimEnd().toString()
}
