import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 15
private const val OBS = 1 shl 3
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private const val EOF = -1
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

private fun s(): Byte {
  var c: Byte
  while (r().also { c = it } <= 32) {
  }
  return c
}

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = ' '.code.toByte() }

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

private const val WALL = 88.toByte()
private const val EMPTY = 79.toByte()
private const val USER = 73.toByte()
private const val PERSON = 80.toByte()
private val dx = intArrayOf(0, 0, -1, 1)
private val dy = intArrayOf(-1, 1, 0, 0)
private const val xySEP = 1000

fun main() {
  val n = i()
  val m = i()
  val a = Array(n) { ByteArray(m) }
  var DHxy = 0
  repeat(n) { x ->
    repeat(m) { y ->
      val v = s()
      a[x][y] = v
      if (v == USER) DHxy = x * xySEP + y
    }
  }

  var cnt = 0
  val q = IntArray(n * m)
  val used = Array(n) { BooleanArray(m) }
  var head = 0
  var tail = 0
  q[tail++] = DHxy

  while (head < tail) {
    val xy = q[head++]
    val x = xy / xySEP
    val y = xy % xySEP
    when (a[x][y]) {
      WALL -> continue
      PERSON -> cnt++
    }
    a[x][y] = WALL
    used[x][y] = true
    for (i in 0..3) {
      val nx = x + dx[i]
      val ny = y + dy[i]
      if (nx in 0 until n && ny in 0 until m && a[nx][ny] != WALL && !used[nx][ny]) {
        q[tail++] = nx * xySEP + ny
        used[nx][ny] = true
      }
    }
  }

  when {
    cnt > 0 -> w(cnt)

    else -> {
      O.write('T'.code)
      O.write('T'.code)
    }
  }

  O.flush()
}