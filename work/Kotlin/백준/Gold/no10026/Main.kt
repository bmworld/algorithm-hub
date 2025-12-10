package 백준.Gold.no10026

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 10_000
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

private const val SEP = 100
private val dr = intArrayOf(0, 1, 0, -1)
private val dc = intArrayOf(1, 0, -1, 0)
private const val R = 0
private const val G = 1
private const val B = 2
private val depMapper = Array(3) { IntArray(3) }.also {
  it[R][R] = 2
  it[R][G] = 1
  it[R][B] = 0

  it[G][R] = 1
  it[G][G] = 2
  it[G][B] = 0

  it[B][R] = 0
  it[B][G] = 0
  it[B][B] = 2
}

fun main() {
  val n = i()
  val maxLen = n * n
  val a = Array(n) { IntArray(n) }
  repeat(n) { r ->
    repeat(n) { c ->
      val v = b()
      a[r][c] = v
    }
  }

  var rCnt = 0
  var gCnt = 0
  var rgCnt = 0
  var bCnt = 0

  val usedDep = Array(n) { IntArray(n) { -1 } }
  val depQ = Array(3) { IntArray(maxLen) }
  val depPos = Array(3) {
    intArrayOf(
      0, // head
      0  // tail
    )
  }
  depQ[depPos[0][0]][depPos[0][1]++] = 0


  fun depCounter(
    r: Int,
    c: Int,
    dep: Int,
  ) {
    if (usedDep[r][c] >= dep) return
    val rgb = a[r][c]
    when (dep) {
      2 -> when (rgb) {
        R -> rCnt++
        G -> gCnt++
        B -> bCnt++
      }

      1 -> when (rgb) {
        R, G -> rgCnt++
      }
    }

    val q = depQ[dep]
    q[depPos[dep][1]++] = r * SEP + c
    usedDep[r][c] = dep

    while (depPos[dep][0] < depPos[dep][1]) {
      val v = q[depPos[dep][0]++]
      val r = v / SEP
      val c = v % SEP
      when (dep) {
        2 -> {
          repeat(4) { i ->
            val nr = r + dr[i]
            val nc = c + dc[i]
            if (nr !in 0 until n || nc !in 0 until n) return@repeat
            val nextDep = depMapper[a[r][c]][a[nr][nc]]
            if (usedDep[nr][nc] >= nextDep) return@repeat
            usedDep[nr][nc] = nextDep
            depQ[nextDep][depPos[nextDep][1]++] = nr * SEP + nc
          }
        }

        1 -> depCounter(r, c, dep + 1)

      }
    }
  }

  while (depPos[0][0] < depPos[0][1]) {
    val v = depQ[0][depPos[0][0]++]
    val r = v / SEP
    val c = v % SEP
    depCounter(
      r, c, when (a[r][c]) {
        R, G -> 1
        else -> 2
      }
    )
  }

  w(bCnt + rCnt + gCnt)
  w(bCnt + rgCnt)
  O.flush()
}
