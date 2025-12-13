import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.util.*

private const val IBS = 65_000
private const val OBS = 2_000
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

private fun op(): Byte {
  var c: Byte
  while (r().also { c = it } <= 32) {
  }
  r()
  return c
}

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = ' '.code.toByte() }
private val INT_MIN = "-2147483648".toByteArray()
private fun w(
  num: Int,
) {
  var v = if (num >= 0) num
  else if (num == Int.MIN_VALUE) {
    O.write(INT_MIN)
    O.write(' '.code)
    return
  } else {
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

private const val INSERT = 73.toByte()
private val EMPTY = byteArrayOf(69, 77, 80, 84, 89)

fun main() {
  val a = TreeMap<Int, Int>()
  repeat(i()) {
    a.clear()
    repeat(i()) {
      val op = op()
      val v = i()
      if (op == INSERT) {
        val cnt = a.put(v, 1)
        if (cnt != null) a[v] = cnt + 1
      } else if (a.isNotEmpty()) {
        if (v == 1) {
          val etr = a.lastEntry()
          val cnt = etr.value
          if (cnt == 1) a.pollLastEntry() else a[etr.key] = cnt - 1
        } else {
          val etr = a.firstEntry()
          val cnt = etr.value
          if (cnt == 1) a.pollFirstEntry() else a[etr.key] = cnt - 1
        }
      }
    }

    if (a.isEmpty()) O.write(EMPTY)
    else {
      w(a.lastKey())
      w(a.firstKey())
    }
    O.write('\n'.code)
  }
  O.flush()
}