import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 2_600
private const val OBS = 1 shl 10
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

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
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
  O.write(WB, ++pos, WS - pos + 1)
}

private const val A: Byte = 65
private const val NL: Byte = 10
private const val ALPHABET_CNT = 26

private fun getChar(): Int {
  var b: Byte
  var char = 0
  while (r().also { b = it } >= NL) {
    if (b == NL) break
    else if (b >= A) {
      char = b - A + 1
      break
    }
  }
  return char
}

private fun clearLine() {
  while (r() != 10.toByte()) {
  }
}

fun main() {
  val N = i()
  val Q = i()
  val acc = IntArray(N + 1)
  val str = IntArray(N + 1)

  repeat(N) {
    val char = getChar()
    val i = it + 1
    str[i] = char
    acc[i] = acc[i - 1] + if (str[i - 1] != char) 1 else 0
  }

  clearLine()

  repeat(Q) {
    val op = i()
    val l = i()
    val r = i()
    when (op) {
      1 -> w(acc[r] - acc[l] + 1)

      else -> {
        val lAmount = getChangedAmount(str[l - 1], str[l])
        val rAmount = getChangedAmount(if (r + 1 <= N) str[r + 1] else -1, str[r])

        for (i in l..r) {
          str[i] = nextChar(str[i])
          acc[i] += lAmount
        }

        for (i in r + 1..N) acc[i] += lAmount + rAmount
      }
    }
  }

  O.flush()
}

private fun nextChar(char: Int): Int = if (char + 1 > ALPHABET_CNT) 1 else char + 1

private const val MERGED = -1
private const val DETACHED = 1
private const val UNCHANGED = 0
private fun getChangedAmount(
  fixed: Int,
  changeable: Int,
): Int {
  val changed = nextChar(changeable)
  return when {
    fixed != changeable && fixed == changed -> MERGED
    fixed == changeable && fixed != changed -> DETACHED
    else -> UNCHANGED
  }
}