import java.io.StreamTokenizer

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      print(solution(nextInt(), nextInt()))
    }

fun solution(n: Int, m: Int): String {
  val out = StringBuilder()
  val used = BooleanArray(n)
  val arr = IntArray(m)

  fun dfs(order: Int) {
    if (order == m) {

      for (i in 0 until m) {
        out.append(arr[i])
        if (i + 1 < m) out.append(" ")
      }
      out.append("\n")
      return
    }
    //
    for (i in 0 until n) {
      if (used[i]) continue
      used[i] = true
      arr[order] = i + 1
      dfs(order + 1)
      used[i] = false
    }
  }

  dfs(0)

  return out.trim().toString()
}
