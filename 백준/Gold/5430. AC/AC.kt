import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 45_000
private const val OBS = 3_000
private const val EOF = -1
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private val NUM = 48..57
private fun i(): Int {
  var v = 0
  var s = 1
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}

private fun clearLine() {
  while (r() != 10.toByte()) {
  }
}

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = ','.code.toByte() }
private fun w(
  num: Int,
  isEnd: Boolean,
) {
  var v = if (num >= 0) num
  else {
    O.write(45)
    -num
  }
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  pos++
  O.write(WB, pos, WS - pos + if (isEnd) 0 else 1)
}

private const val R = 82.toByte()
private const val D = 68.toByte()
private const val MAX_LEN = 100_000
private val ERROR = byteArrayOf(101, 114, 114, 111, 114)
fun main() {
  val q = BooleanArray(MAX_LEN)
  val a = IntArray(MAX_LEN)
  repeat(i()) {
    var qh = 0
    var qt = 0
    var c: Byte
    while (r().also { c = it } == R || c == D) q[qt++] = c == D

    val aLen = i()
    r()
    repeat(aLen) {
      a[it] = i()
    }
    clearLine()

    var l = 0
    var r = aLen - 1
    var reverse = false
    var hasError = false

    while (qh < qt) {
      val isD = q[qh++]
      when {
        isD -> {
          val len = r - l + 1
          hasError = len == 0
          if (!hasError) {
            if (reverse) r-- else l++
          }
        }

        else -> reverse = !reverse
      }
    }

    val len = r - l + 1
    when {
      hasError -> O.write(ERROR)

      else -> {
        O.write('['.code)
        repeat(len) {
          val i = if (reverse) r - it else l + it
          w(a[i], it + 1 >= len)
        }
        O.write(']'.code)
      }
    }
    O.write('\n'.code)
  }
  O.flush()
}