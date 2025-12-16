import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 20_000
private const val OBS = 6_000
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

private const val CAP_D = 86.toByte()
private const val D = 68.toByte()
private const val S = 83.toByte()
private const val L = 76.toByte()
private const val R = 82.toByte()
private val ops = byteArrayOf(D, S, L, R, CAP_D)
private const val CAP = 10_000
private const val SEP = CAP
fun main() {
  val q = IntArray(CAP)
  repeat(i()) {
    val cnts = IntArray(CAP) { CAP }
    val from = i()
    val to = i()
    var qh = 0
    var qt = 0
    cnts[from] = 0
    q[qt++] = from
    bfs@ while (qh < qt) {
      val t = q[qh++]
      val v = t % SEP
      val c = t / SEP
      for (i in 0..3) {
        val nv = fwd(v, ops[i])
        val nc = c + 1
        if (nc < cnts[nv]) {
          cnts[nv] = nc
          if (nv == to) break@bfs
          q[qt++] = nc * SEP + nv
        }
      }
    }

    var toCnt = cnts[to]
    if (toCnt == CAP) return@repeat
    var traced = to
    val tracedOps = ByteArray(toCnt)

    repeat(toCnt) {
      toCnt--
      for (i in 0..4) {
        val op = ops[i]
        val isOdd = traced % 2 != 0
        if (isOdd && (op == D || op == CAP_D)) continue
        val v = bwd(traced, op)
        val c = cnts[v]
        if (c == toCnt) {
          tracedOps[toCnt] = if (op == CAP_D) D else op
          traced = v
          break
        }
      }
    }
    O.write(tracedOps)
    O.write('\n'.code)
  }
  O.flush()
}

private fun fwd(
  v: Int,
  op: Byte,
): Int {
  return when (op) {
    D -> v shl 1

    S -> v - 1 + CAP

    L -> v * 10 + v / 1000

    R -> (v % 10) * 1000 + v / 10

    else -> throw Exception()
  } % CAP
}

private fun bwd(
  x: Int,
  op: Byte,
): Int {
  return when (op) {
    CAP_D -> (x + CAP) shr 1

    D -> x shr 1

    S -> x + 1

    L -> (x % 10) * 1000 + x / 10

    R -> x * 10 + x / 1000

    else -> throw Exception()
  } % CAP
}