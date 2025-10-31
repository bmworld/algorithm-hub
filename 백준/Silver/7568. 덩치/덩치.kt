import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val CODE_0 = 48
private const val CODE_9 = 57
private const val maxNumSize = 2
private val buf = ByteArray(maxNumSize + 1).also { it[maxNumSize] = ' '.code.toByte() }

fun main() {

  val n = readInt()
  val arr =
      Array(n) {
        IntArray(3).also {
          it[0] = readInt()
          it[1] = readInt()
          it[2] = 1
        }
      }

  repeat(n - 1) { i ->
    val a = arr[i]
    for (j in i + 1..<n) {
      val b = arr[j]
      if (a[0] > b[0] && a[1] > b[1]) b[2]++
      if (a[0] < b[0] && a[1] < b[1]) a[2]++
    }
  }

  for (p in arr) writeln(p[2])
  OUT.flush()
}

private fun writeln(num: Int) {
  var x = num
  var endIdx = 1
  do {
    buf[endIdx--] = ((x % 10) + CODE_0).toByte()
    x /= 10
  } while (x > 0)
  val stt = endIdx + 1
  val len = maxNumSize - stt + 1 // 띄어쓰기 포함
  OUT.write(buf, stt, len)
}

val IN = BufferedInputStream(System.`in`, 1 shl 20)
val OUT = BufferedOutputStream(System.`out`, 1 shl 20)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in CODE_0..CODE_9) {
    n = n * 10 + (c - CODE_0)
    c = IN.read()
  }
  return n
}