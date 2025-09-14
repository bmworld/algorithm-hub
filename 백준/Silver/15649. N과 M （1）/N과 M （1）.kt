import java.io.*

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      val n = nextInt()
      val m = nextInt()

      BufferedWriter(OutputStreamWriter(System.out)).use { bw ->
        solveTo(n, m, bw)
        bw.flush()
      }
    }

fun solveTo(n: Int, m: Int, out: Appendable) {
  val used = BooleanArray(n)
  val arr = IntArray(m)

  fun writeLine() {
    for (i in 0 until m) {
      out.append(arr[i].toString())
      if (i + 1 < m) out.append(' ')
    }
    out.append('\n')
  }

  fun dfs(order: Int) {
    if (order == m) {
      writeLine()
      return
    }
    for (i in 0 until n) {
      if (used[i]) continue
      used[i] = true
      arr[order] = i + 1
      dfs(order + 1)
      used[i] = false
    }
  }

  dfs(0)
}