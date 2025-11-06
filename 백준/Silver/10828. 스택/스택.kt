import java.io.BufferedOutputStream

private val B: ByteArray = System.`in`.readBytes()
private var Bi = 0

private fun read(): Int = if (Bi < B.size) (B[Bi++].toInt() and 0xFF) else 10 // EOF -> '\n'

private val OUT = BufferedOutputStream(System.`out`, 1 shl 15)
private const val MAX_NUM_LEN = 6
private val buf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = '\n'.code.toByte() }

private const val PUSH = 1
private const val POP = 2
private const val TOP = 3
private const val SIZE = 4
private const val EMPTY = 5

private val CODES =
    IntArray(256).also {
      it[116] = TOP
      it[115] = SIZE
      it[101] = EMPTY
      it[112] = 153 // push vs pop
    }
private val SEP =
    BooleanArray(256).also {
      it[10] = true
      it[32] = true
    }

fun main() {
  val n = readInt()
  val arr = IntArray(n)
  var i = 0
  repeat(n) {
    when (readWordAsCode()) {
      PUSH -> arr[i++] = readInt()
      POP -> writeln(if (i == 0) -1 else arr[--i])
      TOP -> writeln(if (i == 0) -1 else arr[i - 1])
      SIZE -> writeln(i)
      EMPTY -> writeln(if (i == 0) 1 else 0)
    }
  }
  OUT.flush()
}

private fun writeln(num: Int) {
  var x = num
  if (x < 0) {
    x = -x
    OUT.write(45)
  }
  var end = MAX_NUM_LEN - 1
  do {
    buf[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  val len = MAX_NUM_LEN - stt + 1 // 개행 포함
  OUT.write(buf, stt, len)
}

private fun readInt(): Int {
  var c = read()
  while (SEP[c]) c = read()
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = read()
  }
  return n
}

fun readWordAsCode(): Int {
  var c = read()
  val code = CODES[c]
  if (code == 153) {
    val c2 = read()
    c = read()
    while (!SEP[c]) c = read()
    return if (c2 == 117) PUSH else POP
  }
  c = read()
  while (!SEP[c]) c = read()
  return code
}