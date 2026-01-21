import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 16
private const val OBS = 1 shl 10
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

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
private fun w(
  num: Int
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
  O.write(WB, ++pos, WS - pos + 1)
}

private const val PUSH = 1
private const val POP = 2
private const val SIZE = 3
private const val EMPTY = 4
private const val FRONT = 5
private const val BACK = 6

private val OPReader = IntArray(5)
private fun op(): Int {
  var i = 0
  var b: Int
  while (r().also { b = it.toInt() } in 97..122 || b == 32) {
    if (b == 32) break
    OPReader[i++] = b
  }

  val c1 = OPReader[0]
  val c2 = OPReader[1]
  return when {
    c1 == 112 && c2 == 117 -> PUSH
    c1 == 112 && c2 == 111 -> POP
    c1 == 115 -> SIZE
    c1 == 101 -> EMPTY
    c1 == 102 -> FRONT
    c1 == 98 -> BACK
    else -> throw Exception()
  }
}

fun main() {
  val N = i()
  val a = IntArray(N)
  var h = 0
  var t = 0
  repeat(N) {
    when (op()) {
      PUSH -> a[t++] = i()
      POP -> w(if (isEmpty(h, t)) -1 else a[h++])
      SIZE -> w(getSize(h, t))
      EMPTY -> w(if (isEmpty(h, t)) 1 else 0)
      FRONT -> w(if (isEmpty(h, t)) -1 else a[h])
      BACK -> w(if (isEmpty(h, t)) -1 else a[t - 1])
    }
  }
  O.flush()
}

private fun isEmpty(
  head: Int,
  tail: Int
) = getSize(head, tail) == 0

private fun getSize(
  head: Int,
  tail: Int
) = tail - head