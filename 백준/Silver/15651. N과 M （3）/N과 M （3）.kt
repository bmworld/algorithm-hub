import java.io.*

fun main() {
  val n = readInt()
  val m = readInt()
  BufferedWriter(OutputStreamWriter(System.out)).use { bw ->
    solveTo(n, m, bw)
    bw.flush()
  }
}

val IN = BufferedInputStream(System.`in`)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = IN.read()
  }
  return n
}

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