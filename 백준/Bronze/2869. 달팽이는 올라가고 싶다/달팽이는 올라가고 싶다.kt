import java.io.BufferedInputStream
fun main() {
  val up = readInt()
  val dn = readInt()
  val v = readInt()
  if (up >= v) return print(1)
  val size = v - up
  val move = up - dn
  var eve = size / move // 올림
  if (size % move != 0) eve++
  print(eve + 1)
}
val IN = BufferedInputStream(System.`in`)
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