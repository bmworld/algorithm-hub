import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 16
private const val OBS = 4
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

private const val WS = 2
private val WB = ByteArray(WS + 1).also { it[WS] = '\n'.code.toByte() }
private fun w(
  num: Int,
) {
  var v = num
  var end = WS - 1
  do {
    WB[end--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  val stt = end + 1
  O.write(WB, stt, -stt + WS + 1)
}

fun main() {
  val a2 = intArrayOf(6, 2, 4, 8)
  val a3 = intArrayOf(1, 3, 9, 7)
  val a4 = intArrayOf(6, 4)
  val a7 = intArrayOf(1, 7, 9, 3)
  val a8 = intArrayOf(6, 8, 4, 2)
  val a9 = intArrayOf(1, 9)

  fun getNum(
    square: Int,
    cycle: IntArray,
  ): Int = cycle[square % cycle.size]

  repeat(i()) {
    val a = i()
    val b = i()
    val units = a % 10
    w(
      when (units) {
        2 -> getNum(b, a2)
        3 -> getNum(b, a3)
        4 -> getNum(b, a4)
        7 -> getNum(b, a7)
        8 -> getNum(b, a8)
        9 -> getNum(b, a9)
        0 -> 10
        else -> units
      }
    )
  }
  O.flush()
}