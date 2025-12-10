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
private const val RG = -1
private const val EMPTY = 0
private const val R = 1
private const val G = 2
private const val B = 3

fun main() {
  val n = i()
  val maxLen = n * n
  val q = IntArray(maxLen)
  val a = Array(n) { IntArray(n) }
  repeat(n) { r ->
    repeat(n) { c ->
      a[r][c] = b()
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
    val t = a[row][col]
    if (t == EMPTY || rgbMode && t < EMPTY) return

    when (rgbMode) {
      true -> when (t) {
        R -> rZoneCnt++
        G -> gZoneCnt++
        B -> bZoneCnt++
      }

      else -> rgZoneCnt++
    }

    a[row][col] = when (t) {
      R, G -> RG
      else -> EMPTY
    }

    var qh = 0
    var qt = 0
    q[qt++] = row * SEP + col

    while (qh < qt) {
      val v = q[qh++]
      val r = v / SEP
      val c = v % SEP
      repeat(4) { i ->
        val nr = r + dr[i]
        val nc = c + dc[i]
        if (nr !in 0 until n || nc !in 0 until n) return@repeat
        val nt = a[nr][nc]
        if (t != nt) return@repeat
        a[nr][nc] = when (nt) {
          R, G -> RG
          else -> EMPTY
        }
        q[qt++] = nr * SEP + nc
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