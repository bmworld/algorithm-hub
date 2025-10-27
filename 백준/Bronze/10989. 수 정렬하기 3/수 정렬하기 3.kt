import java.io.BufferedInputStream

fun main() {
  val OUT = System.out.bufferedWriter()
  val arr = IntArray(10001)
  val n = readInt()
  var max = 0
  repeat(n) {
    val v = readInt()
    arr[v]++
    if (v > max) max = v
  }

  for (v in 1..max) {
    val cnt = arr[v]
    if (cnt == 0) continue
    OUT.write("$v\n".repeat(cnt))
  }
  OUT.flush()
}

val IN = BufferedInputStream(System.`in`, 1 shl 20)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read() // filter
  var n = 0
  while (c in '0'.code..'9'.code) {
    n = n * 10 + (c - '0'.code)
    c = IN.read()
  }
  return n
}