import java.io.*

fun main() {
  val br = BufferedReader(InputStreamReader(System.`in`))
  val n = br.readLine().toInt()
  repeat(n) {
    val sb = StringBuilder()
    val line = br.readLine()
    val split = line.split(' ')
    val cnt = split[0].toInt()
    val s = split[1]
    for (c in s) {
      repeat(cnt) { sb.append(c) }
    }
    println(sb)
  }
}