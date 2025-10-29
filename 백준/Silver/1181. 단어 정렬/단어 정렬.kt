import java.io.BufferedInputStream
import java.util.*
fun main() {
  val n = readInt()
  val sb = StringBuilder(50)
  val set = TreeSet(compareBy<String> { it.length }.thenBy { it })
  var totalLen = 0
  repeat(n) {
    sb.clear()
    var curLen = 0
    var c = IN.read()
    while (c <= 32) c = IN.read()
    while (c in 97..122) {
      val toChar = c.toChar()
      sb.append(toChar)
      c = IN.read()
      curLen++
    }
    val cur = sb.toString()
    val uniq = set.add(cur)
    if (uniq) totalLen += curLen + 1
  }
  val r = StringBuilder(totalLen)
  for (s in set) r.appendLine(s)
  print(r)
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