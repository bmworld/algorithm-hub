import java.io.*

fun main() {
  val n = readInt()
  val half = 1_000_000
  val arr = BooleanArray(half * 2 + 1)
  var max = Int.MIN_VALUE
  repeat(n) {
    val v = readInt()
    val i = v + half
    if (i > max) {
      max = i
    }
    arr[i] = true
  }
  for (i in 0..max) {
    if (arr[i]) {
      OUT.write((i - half).toString())
      OUT.write('\n'.code)
    }
  }
  OUT.flush()
}

val IN = BufferedInputStream(System.`in`, 1 shl 20)
val OUT = BufferedWriter(OutputStreamWriter(System.`out`), 1 shl 20)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var sign = 1
  if (c == '-'.code) { // 부호 처리
    sign = -1
    c = IN.read()
  }
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = IN.read()
  }
  return n * sign
}