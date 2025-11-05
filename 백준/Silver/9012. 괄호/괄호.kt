import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val IN = BufferedInputStream(System.`in`, 1 shl 18)
private val OUT = BufferedOutputStream(System.`out`, 1 shl 18)

private val Y = "YES\n".toByteArray()
private val N = "NO\n".toByteArray()

fun main() {
  val n = readInt()
  repeat(n) { OUT.write(if (readLine()) Y else N) }

  OUT.flush()
}

private val ch = ByteArray(100)

private fun readInt(): Int {
  var n = 0
  var sign = 1
  var c = IN.read()
  while (true) {
    when (c) {
      in 48..57 -> {
        n = n * 10 + (c - 48)
        c = IN.read()
      }
      45 -> {
        sign = -1
        c = IN.read()
      }
      10,
      32 -> return n * sign
      else -> c = IN.read()
    }
  }
}

private fun readByte(): Int = IN.read()

private fun readLine(): Boolean {
  var foundErr = false
  var r = true
  var i = 0
  var c = readByte()
  while (true) {
    if (foundErr) {
      when (c) {
        10,
        13 -> return r
        else -> c = readByte()
      }
    } else {
      when (c) {
        40, // (
        -> {
          r = false
          val cb = c.toByte()
          ch[i++] = cb
          c = readByte()
        }
        41, // )
        -> {
          r =
              if (i == 0) {
                foundErr = true
                false
              } else {
                val v = ch[--i]
                if (v == 40.toByte()) {
                  true
                } else {
                  foundErr = true
                  false
                }
              }
          c = readByte()
        }
        10,
        13 -> return i == 0
        else -> c = readByte()
      }
    }
  }
}