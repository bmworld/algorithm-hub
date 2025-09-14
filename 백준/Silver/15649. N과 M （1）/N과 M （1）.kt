
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
  val builder = StringBuilder()

  val ch = IntArray(n + 1) { 0 }
  val combi = IntArray(m) { 0 }

  dfs(0, m, n, ch, builder, combi)

  return builder.trim().toString()
}

fun dfs(depth: Int, m: Int, n: Int, ch: IntArray, builder: StringBuilder, combi: IntArray) {
  if (depth > combi.size) return

  if (depth == combi.size) {
    builder.append(combi.joinToString(" ")).append("\n")
  } else {
    for (i in 1..n) {
      if (ch[i] == 1) continue
      ch[i] = 1
      combi[depth] = i
      dfs(depth + 1, m, n, ch, builder, combi)
      ch[i] = 0
    }
  }
}
