package 백준.Silver.no2667

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 14
private const val OBS = 1 shl 2
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
private val dx = intArrayOf(0, 0, -1, 1)
private val dy = intArrayOf(-1, 1, 0, 0)

fun main() {
  val n = i()
  val a = Array(n) { BooleanArray(n) }
  var hCnt = 0
  repeat(n) { i ->
    repeat(n) { j ->
      val isH = b()
      a[i][j] = isH
      if (isH) hCnt++
    }
  }

  val aptCnt = IntArray(hCnt)
  var aptI = 0
  val q = IntArray(n * n + 1)
  var head = 0
  var tail = 0
  q[tail++] = 0
  val ch = Array(n) { BooleanArray(n) }
  ch[0][0] = true

  var aptMode = a[0][0]


  while (head < tail) {
    val v = q[head++]
    val i = v / SEP
    val j = v % SEP
    val apt = a[i][j]
    if (aptMode && !apt) aptI++
    if (apt) aptCnt[aptI]++

    repeat(4) { k ->
      val ni = i + dx[k]
      val nj = j + dy[k]
      if (ni in 0 until n && nj in 0 until n && !ch[ni][nj]) {
        val isApt = a[ni][nj]
        ch[ni][nj] = true
        val nv = ni * SEP + nj
        when {
          isApt -> {
            var pos = head
            while (pos <= tail) {
              val tv = q[pos++]
              val ti = tv / SEP
              val tj = tv % SEP
              if (!a[ti][tj]) break
            }
            q[tail++] = q[--pos]
            q[pos] = nv
          }

          else -> q[tail++] = nv
        }
      }
    }
    aptMode = apt
  }
  
  aptCnt.sortDescending()
  w(aptI)
  repeat(aptI) { i ->
    w(aptCnt[aptI - i - 1])
  }
  O.flush()
}
