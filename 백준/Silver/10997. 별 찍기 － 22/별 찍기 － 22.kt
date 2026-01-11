import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 8
private const val OBS = 1 shl 18
private val O = BufferedOutputStream(System.out, OBS)
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

private const val NL: Byte = 10
private const val STAR: Byte = 42
private const val SPACE: Byte = 32
private const val DIR_CAP = 4
private const val DOWN = 0
private const val LEFT = 1
private const val UP = 2
private const val RIGHT = 3
private const val INIT_SEQ = 1
private val dirs = intArrayOf(DOWN, LEFT, UP, RIGHT)
private val STARS = byteArrayOf(
  STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR,
  STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR,
  STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR,
  STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR,
  STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR, STAR,
)

fun main() {
  val n = i()
  if (n == 1) {
    O.write(42)
    O.flush()
    return
  }

  val LEN = (n - 1) * 4 + 1
  O.write(STARS, 0, LEN)
  O.write(10)
  O.write(STAR.toInt())
  O.write(10)

  val CAP = LEN + 1
  val board = ByteArray(LEN * CAP) {
    val EOF = it % CAP == CAP - 1
    if (EOF) NL else STAR
  }

  fun punchHole(
    r: Int,
    c: Int,
    dir: Int,
    seq: Int,
    dist: Int,
  ) {
    repeat(dist) {
      val nr = r + if (dir == DOWN) it else if (dir == UP) -it else 0
      val nc = c + if (dir == RIGHT) it else if (dir == LEFT) -it else 0
      if (inRange(nr, nc, LEN)) board[encodePos(nr, nc, CAP)] = SPACE
      if (it + 1 == dist && inRange(nr, nc, LEN)) punchHole(nr, nc, nextDir(dir), seq + 1, dist + if (seq > INIT_SEQ && seq % 2 == 0) 2 else 0)
    }
  }

  val initR = 2 * (n - 2) + 1
  val initC = initR + 2
  punchHole(initR, initC, DOWN, INIT_SEQ, 3)

  O.write(board)
  O.flush()
}

private fun encodePos(
  r: Int,
  c: Int,
  CAP: Int,
): Int = r * CAP + c

private fun nextDir(dir: Int): Int = (dir + 1) % DIR_CAP

private fun inRange(
  nr: Int,
  nc: Int,
  size: Int,
) = nr in 0 until size && nc in 0 until size