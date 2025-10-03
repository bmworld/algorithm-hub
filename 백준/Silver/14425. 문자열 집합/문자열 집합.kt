import java.io.*
import java.util.*

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val str = StringTokenizer(br.readLine())
  val n = str.nextToken().toInt()
  val m = str.nextToken().toInt()

  val s = Array(n) { br.readLine() }
  val strs = Array(m) { br.readLine() }

  BufferedWriter(OutputStreamWriter(System.out)).use { bw -> solveTo(s, strs, bw) }
}

fun solveTo(
    group: Array<String>,
    strings: Array<String>,
    out: Appendable,
) {
  var answer = 0

  for (t in strings) {
    for (g in group) {
      if (g == t) {
        answer++
        break
      }
    }
  }

  out.append(answer.toString())
}