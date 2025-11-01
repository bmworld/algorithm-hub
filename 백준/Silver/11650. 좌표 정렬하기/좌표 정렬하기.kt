import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val CODE_MINUS = '-'.code
private const val HALF = 100_000
private const val SPACE = ' '.code
private const val LN = '\n'.code
private const val CODE_0 = '0'.code
private const val CODE_9 = '9'.code

fun main() {
  val n = readInt()
  val arr = Array(HALF * 2 + 1) { mutableListOf<Int>() }

  var xMin = HALF * 2
  var xMax = 0
  repeat(n) {
    val x = readInt()
    val y = readInt()
    arr[x + HALF].add(y)
    if (x > xMax) xMax = x
    if (x < xMin) xMin = x
  }

  for (x in xMin..xMax) {
    val yArr = arr[x + HALF]
    yArr.sort()
    for (y in yArr) {
      writeln(x)
      OUT.write(SPACE)
      writeln(y)
      OUT.write(LN)
    }
  }

  OUT.flush()
}

private const val MAX_NUM_LEN = 6 // 100_000
private val buf = ByteArray(MAX_NUM_LEN)

private fun writeln(num: Int) {
  var x = num
  if (x < 0) { // 부호 처리
    OUT.write(CODE_MINUS)
    x = -x
  }
  var endIdx = MAX_NUM_LEN - 1
  do {
    buf[endIdx--] = ((x % 10) + CODE_0).toByte()
    x /= 10
  } while (x > 0)
  val stt = endIdx + 1
  val len = MAX_NUM_LEN - stt
  OUT.write(buf, stt, len)
}

val IN = BufferedInputStream(System.`in`, 1 shl 20)
val OUT = BufferedOutputStream(System.`out`, 1 shl 20)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var sign = 1
  if (c == CODE_MINUS) { // 부호 처리
    sign = -1
    c = IN.read()
  }
  var n = 0
  while (c in CODE_0..CODE_9) {
    n = n * 10 + (c - CODE_0)
    c = IN.read()
  }
  return n * sign
}