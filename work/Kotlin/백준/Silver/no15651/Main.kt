package 백준.Silver.no15651

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val CODE_0 = 48
const val CODE_9 = 57

fun main() {
  val n = readInt()
  val m = readInt()
  val c = ByteArray(m * 2 - 1) { if (it % 2 == 1) ' '.code.toByte() else 0 }

  fun dfs(order: Int, pos: Int) {
    if (order == m) {
      OUT.write(c)
      OUT.write('\n'.code)
    } else {
      for (i in 1..n) {
        c[pos] = (i + CODE_0).toByte()
        dfs(order + 1, pos + 2)
      }
    }
  }
  dfs(0, 0)
  OUT.flush()
}

val IN = BufferedInputStream(System.`in`)
val OUT = BufferedOutputStream(System.`out`)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in CODE_0..CODE_9) {
    n = n * 10 + (c - CODE_0)
    c = IN.read()
  }
  return n
}

// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------

fun solveTo(n: Int, m: Int, out: Appendable) {
  val arr = IntArray(m)

  val line = StringBuilder(m * 2) // 줄 단위, 재사용 버퍼
  fun writeLine() {
    line.setLength(0) // 초기화
    for (i in 0 until m) {
      line.append(arr[i])
      if (i + 1 < m) line.append(' ')
    }
    out.append(line)
    out.append('\n')
  }

  fun dfs(order: Int) {
    if (order == m) {
      writeLine()
      return
    }
    for (i in 0 until n) {
      arr[order] = i + 1
      dfs(order + 1)
    }
  }

  dfs(0)
}

/** 테스트 코드용 */
fun solution(n: Int, m: Int): String {
  val sb = StringBuilder()
  solveTo(n, m, sb)
  return sb.trimEnd().toString()
}
