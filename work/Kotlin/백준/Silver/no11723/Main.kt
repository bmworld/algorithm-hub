package 백준.Silver.no11723

import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 16)
private val B: ByteArray = System.`in`.readBytes()
private var Bi = 0
private const val EOF = -1

private fun read(): Int = if (Bi < B.size) (B[Bi++].toInt() and 0xFF) else EOF

private fun readInt(): Int {
  var c = read()
  while (c != EOF && c <= 32) c = read()

  var neg = false
  if (c == '-'.code) {
    neg = true
    c = read()
  }
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = read()
  }
  return if (neg) -n else n
}

private const val ADD = 1
private const val REMOVE = 2
private const val CHECK = 3
private const val TOGGLE = 4
private const val ALL = 5
private const val EMPTY = 6

private val CODE =
    IntArray(256).also {
      it[97] = 153 // add Or all
      it[114] = REMOVE
      it[99] = CHECK
      it[116] = TOGGLE
      it[101] = EMPTY
    }

fun readWordAsCode(): Int {
  var c = read()
  val code = CODE[c]
  if (code == 153) {
    val c2 = read()
    c = read()
    while (!SEP[c]) c = read()
    return if (c2 == 100) ADD else ALL
  }
  c = read()
  while (!SEP[c]) c = read()
  return code
}

private val SEP =
    BooleanArray(256).also {
      it[10] = true
      it[32] = true
    }

private const val ON: Byte = 1
private const val OFF: Byte = 0
private const val SIZE = 20
private val YES =
    ByteArray(2).also {
      it[0] = '1'.code.toByte()
      it[1] = '\n'.code.toByte()
    }

private val NO =
    ByteArray(2).also {
      it[0] = '0'.code.toByte()
      it[1] = '\n'.code.toByte()
    }

fun main() {
  val n = readInt()
  val s = ByteArray(SIZE)
  repeat(n) {
    when (readWordAsCode()) {
      ADD -> {
        val v = readInt()
        s[v - 1] = ON
      }
      REMOVE -> {
        val v = readInt()
        s[v - 1] = OFF
      }
      CHECK -> {
        val v = readInt()
        OUT.write(if (s[v - 1] == ON) YES else NO)
      }
      TOGGLE -> {
        val v = readInt()
        s[v - 1] = if (s[v - 1] == ON) OFF else ON
      }
      ALL -> {
        for (i in 0 until SIZE) s[i] = ON
      }
      EMPTY -> {
        for (i in 0 until SIZE) s[i] = OFF
      }
    }
  }
  OUT.flush()
}
