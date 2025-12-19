import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 500
private const val OBS = 100
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
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
private val WB = ByteArray(WS + 1).also { it[WS] = '\n'.code.toByte() }
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
  pos++
  O.write(WB, pos, WS - pos + 1)
}

private const val SEP = 10_000_000_000u
private const val MAX_NUM = 1_000_000_000u

@OptIn(ExperimentalUnsignedTypes::class)
fun main() {
  val fr = i().toULong()
  val to = i().toULong()

  val q = ULongArray(100_000)
  var qh = 0
  var qt = 0
  q[qt++] = 1u * SEP + fr

  var min = ULong.MAX_VALUE
  bfs@ while (qh < qt) {
    val t = q[qh++]
    val c = (t / SEP)
    val v = t % SEP
    for (op in 0..1) {
      val nv = when (op) {
        0 -> v * 10u + 1u
        else -> v * 2u
      }
      if (nv > MAX_NUM) continue
      val nc = c + 1u
      if (nv == to) {
        if (min > nc) min = nc
        break@bfs
      }

      q[qt++] = nc * SEP + nv
    }
  }

  w(if (min == ULong.MAX_VALUE) -1 else min.toInt())
  O.flush()
}