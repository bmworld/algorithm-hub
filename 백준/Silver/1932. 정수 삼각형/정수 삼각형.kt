import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1_000
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
  pos++
  O.write(WB, pos, WS - pos)
}

fun main() {
  val n = i()
  val a = Array(n) { mutableListOf<Int>() }
  var max = 0
  repeat(n) { i ->
    val len = i + 1
    var cnt = 0
    var v = 0
    var c: Byte
    while (r().also { c = it } in NUM || c == 32.toByte() || c == 10.toByte()) {
      when (c) {
        in NUM -> v = v * 10 + c - 48

        else -> {
          var acc = v
          cnt++

          val first = cnt == 1
          val last = cnt == len

          val j = cnt - 1
          if (!first) {
            val l = a[i - 1][j - 1]
            val lAcc = l + v
            if (lAcc > acc) acc = lAcc
          }

          if (!last) {
            val r = a[i - 1][j]
            val rAcc = r + v
            if (rAcc > acc) acc = rAcc
          }

          a[i] += acc

          if (acc > max) max = acc
          v = 0
          if (c == 10.toByte()) return@repeat
        }
      }
    }
  }

  w(max)
  O.flush()
}