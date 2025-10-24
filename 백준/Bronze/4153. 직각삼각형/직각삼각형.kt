import java.io.BufferedInputStream
fun main() {
  val sb = StringBuilder()
  while (true) {
    var a = readInt()
    var b = readInt()
    var max = readInt()
    if (a == 0 && b == 0 && max == 0) break

    if (a > b && a > max) {
      val tmp = max
      max = a
      a = tmp
    } else if (b > a && b > max) {
      val tmp = max
      max = b
      b = tmp
    }
    sb.append(if (a * a + b * b == max * max) "right" else "wrong").append('\n')
  }
  print(sb)
}
val IN = BufferedInputStream(System.`in`)
private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = IN.read()
  }
  return n
}