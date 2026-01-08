import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 17
private const val OBS = 1 shl 11
private val O = BufferedOutputStream(System.`out`, OBS)
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

private const val A: Byte = 97
private const val LETTERS_LEN = 26

private fun getChar(): Int {
  var b: Byte
  var char = 0
  while (r().also { b = it } >= A) char = b - A
  return char
}

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
private fun w(
  num: Int,
) {
  var v = num
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  O.write(WB, ++pos, WS - pos + 1)
}

private const val MAX = 200_000
private const val NL: Byte = 10
fun main() {
  val a = Array(LETTERS_LEN) { IntArray(MAX + 1) }
  var len = 0
  var tmp: Byte
  while (r().also { tmp = it } >= NL) {
    if (tmp == NL) break
    val typed = tmp - A
    repeat(LETTERS_LEN) { char ->
      a[char][len + 1] = a[char][len] + if (char == typed) 1 else 0
    }
    len++
  }

  repeat(i()) {
    val l = getChar()
    val fr = i() + 1
    val to = i() + 1
    w(a[l][to] - a[l][fr - 1])
  }

  O.flush()
}