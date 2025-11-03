import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val CODE_MINUS = '-'.code
private const val CODE_NL = '\n'.code
private const val CODE_0 = '0'.code
private const val CODE_1 = '1'.code
private const val CODE_9 = '9'.code

private fun main() {
  val n = readInt()
  val ch = HashSet<Int>(n)

  var min = Int.MAX_VALUE
  var max = Int.MIN_VALUE
  repeat(n) {
    val v = readInt()
    ch.add(v)
    if (v < min) min = v
    if (v > max) max = v
  }

  val m = readInt()
  repeat(m) {
    val v = readInt()
    val isIn = if (v !in min..max) false else v in ch
    OUT.write(if (isIn) CODE_1 else CODE_0)
    OUT.write(CODE_NL)
  }

  OUT.flush()
}

val IN = BufferedInputStream(System.`in`, 1 shl 20)
val OUT = BufferedOutputStream(System.`out`, 1 shl 20)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  var sign = 1
  if (c == CODE_MINUS) {
    sign = -1
    c = IN.read()
  }
  while (c in CODE_0..CODE_9) {
    n = n * 10 + (c - CODE_0)
    c = IN.read()
  }
  return n * sign
}