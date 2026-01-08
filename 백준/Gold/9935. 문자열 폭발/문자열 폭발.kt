import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 17
private const val OBS = 1 shl 15
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

private const val NL: Byte = 10
private const val BOMB_MAX = 36
private const val STR_MAX = 1_000_000
private val FRULA = byteArrayOf(70, 82, 85, 76, 65)

fun main() {
  var b: Byte
  val str = ByteArray(STR_MAX)
  var strLen = 0
  while (r().also { b = it } >= NL) {
    if (b == NL) break
    str[strLen++] = b
  }

  val bomb = ByteArray(BOMB_MAX)
  var bombLen = 0
  while (r().also { b = it } >= NL) {
    if (b == NL) break
    bomb[bombLen++] = b
  }

  val result = ByteArray(strLen)
  var ri = 0

  val clue = bomb[bombLen - 1]
  repeat(strLen) { i ->
    val char = str[i]
    result[ri++] = char
    if (char == clue) {
      var bj = bombLen - 1
      var sj = ri - 1
      while (bj >= 0 && sj >= 0) {
        if (bomb[bj] != result[sj]) break
        if (bj == 0) {
          ri -= bombLen
          break
        }
        sj--
        bj--
      }
    }
  }

  if (ri == 0) O.write(FRULA)
  else O.write(result, 0, ri)
  O.flush()
}