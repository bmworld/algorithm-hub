import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 10_000
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

private const val WS = 10
private val WB = ByteArray(WS)
private fun w(
  num: Int,
) {
  var v = num
  if (v < 0) {
    O.write('-'.code)
    v = -v
  }
  var end = WS - 1
  do {
    WB[end--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  val stt = end + 1
  O.write(WB, stt, -stt + WS)
}

private const val RIPE = 1
private const val UNRIPE = 0
private const val EMPTY = -1
private const val SEP = 1000
private val dr = intArrayOf(1, 0, -1, 0)
private val dc = intArrayOf(0, 1, 0, -1)
fun main() {
  val rs = i()
  val cs = i()
  val box = Array(rs) { IntArray(cs) }
  val ripes = IntArray(rs * cs)
  var ripeCnt = 0
  var unripeCnt = 0
  repeat(cs) { c ->
    repeat(rs) { r ->
      val v = i()
      box[r][c] = v
      if (v == UNRIPE) unripeCnt++
      if (v == RIPE) ripes[ripeCnt++] = r * SEP + c
    }
  }

  if (unripeCnt == 0) {
    w(0)
    O.flush()
    return
  }

  var totalDays = 0
  val q = IntArray(ripeCnt + unripeCnt)
  var qh = 0
  var qt = 0
  repeat(ripeCnt) {
    q[qt++] = ripes[it]
  }

  while (qh < qt) {
    val rc = q[qh++]
    val r = rc / SEP
    val c = rc % SEP
    val day = box[r][c]
    repeat(4) { j ->
      val nr = r + dr[j]
      val nc = c + dc[j]
      if (nr in 0 until rs && nc in 0 until cs && box[nr][nc] == UNRIPE) {
        box[nr][nc] = day + 1
        q[qt++] = nr * SEP + nc
        unripeCnt--
        if (totalDays < day) totalDays = day
      }
    }
  }

  w(
    when {
      unripeCnt > 0 -> -1
      else -> totalDays
    }
  )
  O.flush()
}