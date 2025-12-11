import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 5_000
private const val OBS = 10
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


private const val SEP = 100
private const val GOAL = 100
fun main() {
  val n = i()
  val m = i()
  val a = IntArray(GOAL + 1) { it }
  val cnts = IntArray(GOAL + 1) { GOAL }

  fun findRoot(v: Int): Int {
    val r = a[v]
    return if (r == v) v
    else {
      val nr = findRoot(r)
      a[v] = nr
      nr
    }
  }

  fun merge(
    v1: Int,
    v2: Int,
  ) {
    val r1 = findRoot(v1)
    val r2 = findRoot(v2)
    when {
      r1 < r2 -> a[r1] = r2
      r1 > r2 -> a[r2] = r1
    }
  }

  repeat(n) {
    merge(i(), i())
  }

  repeat(m) {
    a[i()] = i()
  }

  val q = IntArray(GOAL)
  var qh = 0
  var qt = 0
  q[qt++] = 1 * SEP + 0

  while (qh < qt) {
    val v = q[qh++]
    val pos = v / SEP
    val cnt = v % SEP
    if (pos == GOAL) continue
    val p = a[pos]
    val nc = cnt + 1
    repeat(6) {
      val jump = p + (it + 1)
      if (jump > GOAL) return@repeat
      val np = a[jump]
      if (cnts[np] <= nc || a[np] < np) return@repeat
      cnts[np] = nc
      q[qt++] = np * SEP + nc
    }
  }

  w(cnts[GOAL])
  O.flush()
}