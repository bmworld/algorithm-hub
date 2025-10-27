package 백준.Bronze.no10989

import java.io.*

private val IN = BufferedReader(InputStreamReader(System.`in`), 1 shl 20)
private val OUT = BufferedWriter(OutputStreamWriter(System.`out`), 1 shl 20)

fun main() {
  val size = 10001
  val cntArr = IntArray(size)
  val n = IN.readLine().toInt()
  var max = 0
  repeat(n) {
    val v = IN.readLine().toInt()
    cntArr[v]++
    if (v > max) max = v
  }

  val cache = Array(size) { "$it\n" }
  for (v in 1..max) {
    val cnt = cntArr[v]
    if (cnt == 0) continue
    OUT.write(cache[v].repeat(cnt))
  }

  OUT.flush()
}
