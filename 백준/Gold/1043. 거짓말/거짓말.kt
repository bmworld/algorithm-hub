import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 5_000
private const val OBS = 512
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
  O.write(WB, ++pos, WS - pos)
}

private const val EMPTY = 0
fun main() {

  val N = i()
  val M = i()
  val g = IntArray(N + 1) { it }
  var ROOT = EMPTY
  repeat(i()) {
    val v = i()
    var r = findRoot(v, g)
    if (ROOT != EMPTY) r = merge(r, ROOT, g)
    ROOT = r
  }

  w(
    if (ROOT == EMPTY) M else {
      
    val partyRoots = IntArray(M)
    repeat(M) { i ->
      var R = EMPTY
      repeat(i()) {
        val v = i()
        var r = findRoot(v, g)
        if (R != EMPTY && r != R) r = merge(r, R, g)
        R = r
      }
      partyRoots[i] = R
    }

    var cnt = 0
    for (i in 0 until M) if (g[findRoot(partyRoots[i], g)] != findRoot(ROOT, g)) cnt++
    cnt
  })

  O.flush()
}

fun merge(
  a: Int,
  b: Int,
  g: IntArray,
): Int {
  val r1 = findRoot(a, g)
  val r2 = findRoot(b, g)
  return when {
    r1 <= r2 -> {
      g[r2] = r1
      r1
    }

    else -> {
      g[r1] = r2
      r2
    }
  }
}

fun findRoot(
  v: Int,
  graph: IntArray,
): Int {
  val r = graph[v]
  return if (r == v) v
  else {
    val nr = findRoot(r, graph)
    graph[v] = nr
    nr
  }
}