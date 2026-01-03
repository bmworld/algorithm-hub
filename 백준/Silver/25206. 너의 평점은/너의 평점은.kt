import java.io.BufferedInputStream

private const val IBS = 1 shl 11
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

private const val SPACE: Byte = 32
private const val NL: Byte = 10
private const val POINT: Byte = 46
private const val PLUS: Byte = 43
private const val ZERO: Byte = 48
private const val A: Byte = 65
private const val D: Byte = 68
private const val F: Byte = 70
private const val P: Byte = 80

private fun clearClassName() {
  var c: Byte
  while (r().also { c = it } >= NL) if (c == SPACE) break
}

private val NUM = 48..57
private fun getScore(): Double {
  var v = 0.0
  var c: Byte
  var done = false
  while (r().also { c = it } >= NL) {
    when (c) {
      in NUM -> if (!done) v = v * 10 + c - 48
      POINT -> done = true
      else -> break
    }
  }
  return v
}

private fun getGrade(): Double {
  var v = 0.0
  var c: Byte
  while (r().also { c = it } >= NL) {
    when (c) {
      in A..D -> v = (69 - c).toDouble()
      PLUS -> v += 0.5
      F, ZERO -> continue
      P -> v = -1.0
      else -> break
    }
  }
  return v
}

fun main() {
  var totalSXG = 0.0
  var totalS = 0.0
  repeat(20) {
    clearClassName()
    val score = getScore()
    val grade = getGrade()
    val pass = grade < 0
    if (pass) return@repeat
    totalS += score
    totalSXG += score * grade
  }
  print(totalSXG / totalS)
}