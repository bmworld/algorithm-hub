import java.io.*
import java.util.*

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val str = StringTokenizer(br.readLine())
  val n = str.nextToken().toInt()
  val m = str.nextToken().toInt()

  val set = LinkedHashSet<String>()
  repeat(n) { set.add(br.readLine()) }

  val strs = Array(m) { br.readLine() }

  BufferedWriter(OutputStreamWriter(System.out)).use { bw -> solveTo(set, strs, bw) }
}

fun solveTo(
    set: LinkedHashSet<String>,
    strings: Array<String>,
    out: Appendable,
) {
  var answer = 0

  for (t in strings) {
    if (set.contains(t)) answer++
  }

  out.append(answer.toString())
}