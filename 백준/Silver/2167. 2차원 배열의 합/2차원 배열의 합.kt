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
  val M = i()
  val a = Array(N) { IntArray(M) }
  repeat(N) { r ->
    repeat(M) { c ->
      val v = i()
      val l = if (c > 0) a[r][c - 1] else 0
      val u = if (r > 0) a[r - 1][c] else 0
      val d = if (c > 0 && r > 0) a[r - 1][c - 1] else 0
      a[r][c] = v + l + u - d
    }
  }

  repeat(i()) {
    val i = i() - 1
    val j = i() - 1
    val x = i() - 1
    val y = i() - 1

    val l = if (j > 0) a[x][j - 1] else 0
    val u = if (i > 0) a[i - 1][y] else 0
    val d = if (i > 0 && j > 0) a[i - 1][j - 1] else 0
    w(a[x][y] - l - u + d)
  }
  O.flush()
}