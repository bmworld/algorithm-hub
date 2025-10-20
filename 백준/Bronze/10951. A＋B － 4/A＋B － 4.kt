import java.util.*
fun main() {
  val br = System.`in`.bufferedReader()
  val sb = StringBuilder()
  while (true) {
    val line = br.readLine() ?: break
    val t = StringTokenizer(line)
    sb.append(t.nextToken().toInt() + t.nextToken().toInt()).append('\n')
  }
  print(sb)
}