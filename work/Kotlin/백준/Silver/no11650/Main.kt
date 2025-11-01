package 백준.Silver.no11650

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val MAX_NUM_LEN = 6 // 100_000
private const val BYTE_NUM_LEN = (MAX_NUM_LEN + 1) * 2 // [부호+6자리, 부호+6자리]
private const val CODE_MINUS = '-'.code
private const val CODE_SPACE = ' '.code
private const val CODE_NL = '\n'.code
private const val CODE_0 = '0'.code
private const val CODE_9 = '9'.code

fun main() {
  val n = readInt()

  val arr = mutableListOf<ByteArray>() // [ 100_000 | 100_000 ]
  repeat(n) {
    val x = readXYAsByte(0)
    arr.add(x)
  }

  arr.sortWith { a, b ->
    val aSize = a.size
    val bSize = b.size
    val len = if (aSize < bSize) aSize else bSize
    for (i in 0 until len) {
      val diff = (a[i].toInt() and 0xFF) - (b[i].toInt() and 0xFF)
      if (diff != 0) return@sortWith diff
    }
    aSize - b.size // ASC
  }

  // Sort
  for (b in arr) {
    // X
    if (b[0].toInt() == 0) OUT.write(CODE_MINUS)
    var i = 1
    while (i < 6 && b[i].toInt() == 0) i++
    while (i <= 6) {
      val d = b[i++].toInt() and 0xFF
      OUT.write(d + CODE_0)
    }

    OUT.write(CODE_SPACE)

    // Y
    if (b[7].toInt() == 0) OUT.write(CODE_MINUS)
    i = 8
    while (i < 13 && b[i].toInt() == 0) i++
    while (i <= 13) {
      val d = b[i++].toInt() and 0xFF
      OUT.write(d + CODE_0)
    }
    OUT.write(CODE_NL)
  }

  OUT.flush()
}

val IN = BufferedInputStream(System.`in`, 1 shl 20)
val OUT = BufferedOutputStream(System.`out`, 1 shl 20)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = IN.read()
  }
  return n
}

private val inBuf = ByteArray(BYTE_NUM_LEN) // -100_000-100_000

private fun readXYAsByte(sttIdx: Int): ByteArray {
  if (sttIdx > BYTE_NUM_LEN) return inBuf.copyOf()
  var i = sttIdx
  val endIdx = sttIdx + MAX_NUM_LEN
  val maxI = endIdx + 1 // 6자리 꽉 찬 후, idx
  var c = IN.read()

  // 부호
  inBuf[i] = 1 // +
  if (c == CODE_MINUS) {
    inBuf[i] = 0 // -
    c = IN.read()
  }

  // 숫자
  i++
  var numLen = 0
  while (c in CODE_0..CODE_9) {
    inBuf[i++] = (c - CODE_0).toByte()
    numLen++
    c = IN.read()
  }

  fun numAlignRight() {
    if (i != maxI) {
      val diff = maxI - i
      var e = endIdx
      while (numLen > 0) {
        inBuf[e] = inBuf[e - diff]
        inBuf[e - diff] = 0
        e--
        numLen--
      }
    }
  }

  if (c == CODE_SPACE) { // 다음 숫자
    numAlignRight()
    readXYAsByte(7)
  } else if (c == CODE_NL) {
    numAlignRight()
    return inBuf.copyOf()
  }
  return inBuf.copyOf()
}
