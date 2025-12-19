import java.io.DataInputStream

private const val IBS = 36
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

fun main() {
  val a = i().toLong()
  val b = i()
  val c = i()

  fun pow(
    base: Long,
    exp: Int,
    mod: Int,
  ): Long {
    return when (exp) {
      0 -> 1L
      1 -> base

      else -> {
        val nb = (base * base) % mod
        val nc = exp / 2
        pow(nb, nc, mod) * if (exp % 2 == 1) base else 1
      }
    } % mod
  }
  print(pow(a, b, c))
}