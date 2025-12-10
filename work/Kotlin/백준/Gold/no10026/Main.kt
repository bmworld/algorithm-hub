package 백준.Gold.no10026

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 10_100
private const val OBS = 1_000
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

private fun b(): Int {
  var c: Byte
  while (r().also { c = it } <= 32) {
  }
  return when (c) {
    82.toByte() -> R
    71.toByte() -> G
    else -> B
  }
}

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = ' '.code.toByte() }
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

private val dr = intArrayOf(0, 1, 0, -1)
private val dc = intArrayOf(1, 0, -1, 0)
private const val RG = -1
private const val EMPTY = 0
private const val R = 1
private const val G = 2
private const val B = 3

fun main() {
  val n = i()
  val a = IntArray(n * n + n)
  val qr = IntArray(n * n)
  val qc = IntArray(n * n)
  repeat(n) { r ->
    repeat(n) { c ->
      a[r * n + c] = b()
    }
  }

  var rZoneCnt = 0
  var gZoneCnt = 0
  var bZoneCnt = 0
  var rgZoneCnt = 0

  fun bfs(
    row: Int,
    col: Int,
    rgbMode: Boolean,
  ) {
    val pos = row * n + col
    val v = a[pos]
    if (v == EMPTY || rgbMode && v < EMPTY) return

    a[pos] = when (v) {
      R -> {
        rZoneCnt++
        RG
      }

      G -> {
        gZoneCnt++
        RG
      }

      B -> {
        bZoneCnt++
        EMPTY
      }

      else -> {
        rgZoneCnt++
        EMPTY
      }
    }

    var qh = 0
    var qt = 0
    qr[qt] = row
    qc[qt++] = col
    while (qh < qt) {
      val r = qr[qh]
      val c = qc[qh++]
      repeat(4) { i ->
        val nr = r + dr[i]
        val nc = c + dc[i]
        val nextPos = nr * n + nc
        if (nr in 0 until n && nc in 0 until n && a[nextPos] == v) {
          val nv = a[nextPos]
          a[nextPos] = when (nv) {
            R, G -> RG
            else -> EMPTY
          }
          qr[qt] = nr
          qc[qt++] = nc
        }
      }
    }
  }

  repeat(n) { r ->
    repeat(n) { c ->
      bfs(r, c, true)
    }
  }

  repeat(n) { r ->
    repeat(n) { c ->
      bfs(r, c, false)
    }
  }

  w(bZoneCnt + rZoneCnt + gZoneCnt)
  w(bZoneCnt + rgZoneCnt)
  O.flush()
}
