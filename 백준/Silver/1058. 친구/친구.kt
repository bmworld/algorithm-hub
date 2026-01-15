import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 2_600
private const val OBS = 1 shl 4
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

private const val N = 78.toByte()
private const val Y = 89.toByte()
private fun yn(): Boolean {
  var c: Byte
  while (r().also { c = it } != Y && c != N) {
  }
  return c == Y
}

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
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
  O.write(WB, ++pos, WS - pos + 1)
}

fun main() {
  val N = i()
  val SIZE = N + 1
  val cnts = IntArray(SIZE)
  val g = Array(SIZE) { ArrayList<Int>() }
  val ch = BooleanArray(SIZE * SIZE)

  repeat(N) { i ->
    val me = i + 1
    repeat(N) { j ->
      val frd = j + 1
      val isFrd = yn()
      if (me == frd || !isFrd) return@repeat
      g[me] += frd
      cnts[me]++
      ch[encodePos(me, frd, SIZE)] = true
    }
  }

  var max = 0
  repeat(N) { i ->
    val me = i + 1
    for (f1 in g[me]) for (f2 in g[f1]) {
      val pos = encodePos(me, f2, SIZE)
      if (f2 != me && !ch[pos]) {
        cnts[me]++
        ch[pos] = true
      }
    }
    val cnt = cnts[me]
    if (max < cnt) max = cnt
  }

  w(max)
  O.flush()
}

private fun encodePos(
  r: Int,
  c: Int,
  CAP: Int
): Int = r * CAP + c