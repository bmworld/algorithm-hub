package 백준.Silver.no14425

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
    set: HashSet<String>,
    strings: Array<String>,
    out: Appendable,
) {
  var cnt = 0
  for (t in strings) if (set.contains(t)) cnt++
  out.append(cnt.toString())
}

/** 테스트 */
fun solution(
    set: HashSet<String>,
    strings: Array<String>,
): Int {
  val sb = StringBuilder()
  solveTo(set, strings, sb)
  return sb.trim().toString().toInt()
}
