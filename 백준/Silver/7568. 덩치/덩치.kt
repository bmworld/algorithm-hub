import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val CODE_0 = 48
private const val CODE_9 = 57
private const val maxNumSize = 2
private val buf = ByteArray(maxNumSize + 1).also { it[maxNumSize] = ' '.code.toByte() }

fun main() {

  val n = readInt()
  val arr = Array(n) { Person(readInt(), readInt(), 1) }

  for (i in 0..<n - 1) {
    val a = arr[i]
    for (j in i + 1..<n) {
      val b = arr[j]
      if (a.w > b.w && a.h > b.h) b.rank++
      if (a.w < b.w && a.h < b.h) a.rank++
    }
  }

  for (p in arr) writeln(p.rank)
  OUT.flush()
}

private class Person(val w: Int, val h: Int, var rank: Int)

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