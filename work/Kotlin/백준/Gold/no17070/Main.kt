package 백준.Gold.no17070

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.util.*

private const val IBS = 256
private const val OBS = 64
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

private const val ZERO = 48
private fun b(): Int {
  var b: Byte
  while (r().also { b = it } >= 10) if (b == ZERO.toByte() || b == (ZERO + 1).toByte()) break
  return b - ZERO
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

private const val H = 0
private const val V = 1
private const val D = 2
private const val EMPTY = 0
private const val C_SEP = 100
private const val R_SEP = C_SEP * C_SEP

fun main() {

  val N = i()
  val a = Array(N) { IntArray(N) { b() } }
  if (a[0][0] == 1 || a[0][1] == 1 || a[0][2] == 1) {
    w(0)
    O.flush()
    return
  }

  var cnt = 0
  val q = PriorityQueue<Int>()
  q.add(0 * R_SEP + 1 * C_SEP + H)


  fun move(
    r: Int,
    c: Int,
    dir: Int,
  ) {
    val done = r == N - 1 && c == N - 1
    if (done) cnt++
    else q.add(r * R_SEP + c * C_SEP + dir)
  }

  while (q.isNotEmpty()) {
    val rcd = q.poll()
    val r = rcd / R_SEP
    val cd = rcd % R_SEP
    val c = cd / C_SEP
    val dir = cd % C_SEP

    val nc = c + 1
    val nr = r + 1
    val movableH = nc in 0 until N && a[r][nc] == EMPTY
    val movableV = nr in 0 until N && a[nr][c] == EMPTY
    val movableD = movableH && movableV && a[nr][nc] == EMPTY

    println(
      "--- $r, $c, ${
        when (dir) {
          H -> "H"
          V -> "V"
          D -> "D"
          else -> dir
        }
      } $nr, $nc"
    )
    when (dir) {
      H -> if (movableH) move(r, nc, H)
      V -> if (movableV) move(nr, c, V)

      D -> {
        if (movableH) move(r, nc, H)
        if (movableV) move(nr, c, V)
      }
    }
    if (movableD) move(nr, nc, D)
  }

  w(cnt)
  O.flush()
}
