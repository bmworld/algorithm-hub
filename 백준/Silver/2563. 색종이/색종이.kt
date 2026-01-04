import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 10
private const val OBS = 1 shl 6
private val O = BufferedOutputStream(System.out, OBS)
private val I = BufferedInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0
private const val EOF = -1

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

private const val WS = 10
private val WB = ByteArray(WS)
private fun w(
  num: Int,
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
  O.write(WB, ++pos, WS - pos)
}

private const val LEN = 100
fun main() {
  val a = BooleanArray(LEN * LEN)

  repeat(i()) {
    val x = i()
    val y = i()
    repeat(10) { ty ->
      repeat(10) { tx ->
        val nx = x + tx
        val ny = y + ty
        a[ny * LEN + nx] = true
      }
    }
  }

  var cnt = 0
  for (b in a) if (b) cnt++
  w(cnt)
  O.flush()
}