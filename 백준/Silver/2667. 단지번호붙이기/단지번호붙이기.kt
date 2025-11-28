import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.util.*

private const val IBS = 1 shl 11
private const val OBS = 1 shl 11
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

private fun i(): Int {
  var v = 0
  var c: Byte
  while (r().also { c = it } in 48..57) v = v * 10 + (c - 48)
  return v
}

private fun b(): Boolean {
  var c = r()
  while (c !in 48..49) c = r()
  return c == 49.toByte()
}

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = '\n'.code.toByte() }

private fun w(
  num: Int,
) {
  var x = num
  var end = WS - 1
  do {
    WB[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  O.write(WB, stt, WS - stt + 1)
}

private const val SEP = 100
private val dx = intArrayOf(-1, 0, 1, 0)
private val dy = intArrayOf(0, -1, 0, 1)

fun main() {
  val n = i()
  val a = Array(n) { BooleanArray(n) }
  var hCnt = 0
  repeat(n) { i ->
    repeat(n) { j ->
      val h = b()
      a[i][j] = h
      if (h) hCnt++
    }
  }

  val apt = PriorityQueue<Int>()
  val mainQ = IntArray(n * n)
  val subQ = IntArray(n * n)
  var mqL = 0
  var mqR = 0
  mainQ[mqR++] = 0
  val ch = Array(n) { BooleanArray(n) }
  ch[0][0] = true

  fun getAptCnt(v: Int) {
    var l = 0
    var r = 0
    subQ[r++] = v
    while (l < r) {
      val v = subQ[l++]
      val i = v / SEP
      val j = v % SEP
      repeat(4) { k ->
        val ni = i + dx[k]
        val nj = j + dy[k]
        if (ni in 0 until n && nj in 0 until n && !ch[ni][nj]) {
          ch[ni][nj] = true
          val nv = ni * SEP + nj
          if (a[ni][nj]) subQ[r++] = nv
          else mainQ[mqR++] = nv
        }
      }
    }
    apt.add(r)
  }

  while (mqL < mqR) {
    val v = mainQ[mqL++]
    val i = v / SEP
    val j = v % SEP
    val h = a[i][j]
    if (h) getAptCnt(v)
    else {
      repeat(4) { k ->
        val ni = i + dx[k]
        val nj = j + dy[k]
        if (ni in 0 until n && nj in 0 until n && !ch[ni][nj]) {
          ch[ni][nj] = true
          if (!a[ni][nj]) mainQ[mqR++] = ni * SEP + nj
          else getAptCnt(ni * SEP + nj)
        }
      }
    }
  }

  w(apt.size)
  while (apt.isNotEmpty()) w(apt.poll())
  O.flush()
}