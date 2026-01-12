package 백준.Gold.no10993

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 8
private const val OBS = 1 shl 20
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

private const val NL: Byte = 10
private const val STAR: Byte = 42
private const val SPACE: Byte = 32

fun main() {
  val cnt = i()
  val H = getH(cnt)
  val W = getW(H)
  val CENTER = W shr 1
  val CAP = W + 1
  val SIZE = H * CAP
  val a = ByteArray(SIZE) {
    if (it % CAP == CAP - 1) NL else SPACE
  }

  fun twinkle(
    r: Int,
    w: Int,
    h: Int,
    seq: Int,
  ) {
    if (seq < 1) return

    var lastRow = r
    repeat(h) { delta ->
      val nr = r + delta * if (seq % 2 == 0) -1 else 1
      a[encodePos(nr, CENTER + delta, CAP)] = STAR
      a[encodePos(nr, CENTER - delta, CAP)] = STAR
      if (delta == h - 1) lastRow = nr
    }

    repeat(w / 2) { delta ->
      a[encodePos(lastRow, CENTER + delta, CAP)] = STAR
      a[encodePos(lastRow, CENTER - delta, CAP)] = STAR
    }

    val nh = h shr 1
    twinkle(nextVertextRow(r, h, seq), getW(nh), nh, seq - 1)
  }

  val evenSeq = cnt % 2 == 0
  twinkle(if (evenSeq) H - 1 else 0, W, H, cnt)

  var stt = 0
  val initLineLen = if (evenSeq) W else CENTER + 1
  repeat(H) {
    val len = initLineLen + it * if (evenSeq) -1 else 1
    val rest = CAP - len
    O.write(a, stt, len)
    O.write(10)
    stt += len + rest
  }

  O.flush()
}

private fun nextVertextRow(
  r: Int,
  h: Int,
  seq: Int,
): Int = r + (h - 2) * if (seq % 2 == 0) -1 else 1

private fun getH(cnt: Int): Int = (1 shl cnt) - 1

private fun getW(h: Int) = 2 * h - 1

private fun encodePos(
  r: Int,
  c: Int,
  CAP: Int,
): Int = r * CAP + c
