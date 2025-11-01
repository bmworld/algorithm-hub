package 백준.Silver.no11650

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val MAX_NUM_LEN = 6 // 100_000
private const val BYTE_NUM_LEN = (MAX_NUM_LEN + 1) * 2
private const val CODE_MINUS = '-'.code
private const val CODE_SPACE = ' '.code
private const val CODE_NL = '\n'.code
private const val CODE_0 = '0'.code
private const val CODE_9 = '9'.code

fun main() {
  val n = readInt()

  /**
   * - X: num[0..6]
   * - num[0] = 부호 (1=양수, 0=음수)
   * - num[1..6] = 0..9
   * - Y: num[7..]
   * - num[7] = 부호 (1=양수, 0=음수)
   * - num[8..13] = 0..9
   */
  val nums = mutableListOf<ByteArray>()
  repeat(n) {
    val x = readXYAsByte()
    nums.add(x)
  }

  nums.sortWith { a, b ->
    val r = compare(a, b, 0) // X
    if (r != 0) r else compare(a, b, 7) // Y
  }

  for (b in nums) {
    // X
    if (b[0].toInt() == 0) OUT.write(CODE_MINUS)
    var i = 1
    while (i < 6 && b[i].toInt() == 0) i++
    while (i <= 6) OUT.write((b[i++].toInt() and 0xFF) + CODE_0)

    OUT.write(CODE_SPACE)

    // Y
    if (b[7].toInt() == 0) OUT.write(CODE_MINUS)
    i = 8
    while (i < 13 && b[i].toInt() == 0) i++
    while (i <= 13) OUT.write((b[i++].toInt() and 0xFF) + CODE_0)
    OUT.write(CODE_NL)
  }

  OUT.flush()
}

fun compare(a: ByteArray, b: ByteArray, stt: Int): Int {
  // + / -
  val sa = a[stt].toInt() and 0xFF
  val sb = b[stt].toInt() and 0xFF
  if (sa != sb) return sa - sb // 음수 먼저

  // 동일 부호
  for (i in 1..MAX_NUM_LEN) {
    val diff = (a[stt + i].toInt() and 0xFF) - (b[stt + i].toInt() and 0xFF)
    if (diff != 0)
        return if (sa == 1) diff // +
        else -diff // -
  }

  return 0
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

private val inBuf = ByteArray(BYTE_NUM_LEN)

private fun readXYAsByte(): ByteArray {
  var c = IN.read()

  // 1) 필터 선행 공백/개행
  while (c == CODE_SPACE || c == '\t'.code || c == '\r'.code || c == CODE_NL) c = IN.read()

  // 2) 값
  val sttIdx = 0
  c = readNum(sttIdx, c)

  // 3) 필터
  while (c == ' '.code || c == '\t'.code || c == '\r'.code) c = IN.read()

  // 4) 같은 줄: 다음 값 읽기, 개행 -> 종료
  return if (c == CODE_NL || c == -1) {
    inBuf.copyOf()
  } else {
    c = readNum(7, c)
    // 필터 (라인 끝까지)
    while (c != CODE_NL && c != -1) c = IN.read()
    inBuf.copyOf()
  }
}

private fun readNum(sttIdx: Int, firstChar: Int): Int {
  val signIdx = sttIdx
  val startIdx = sttIdx + 1
  val endIdx = sttIdx + MAX_NUM_LEN

  // 필터
  var z = startIdx
  while (z <= endIdx) inBuf[z++] = 0

  var c = firstChar

  // 부호
  inBuf[signIdx] = 1 // +
  if (c == CODE_MINUS) {
    inBuf[signIdx] = 0 // -
    c = IN.read()
  }

  // 숫자
  val tmp = ByteArray(MAX_NUM_LEN)
  var len = 0
  while (c in CODE_0..CODE_9 && len < MAX_NUM_LEN) {
    tmp[len++] = (c - CODE_0).toByte() // 0..9
    c = IN.read()
  }

  // 우측정렬
  var write = endIdx
  var i = len - 1
  while (i >= 0) {
    inBuf[write--] = tmp[i]
    i--
  }

  return c
}
