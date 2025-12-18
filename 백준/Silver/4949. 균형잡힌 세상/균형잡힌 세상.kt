import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val IN = BufferedInputStream(System.`in`, 1 shl 18)
private val OUT = BufferedOutputStream(System.`out`, 1 shl 18)
private val YES = "yes\n".toByteArray()
private val NO = "no\n".toByteArray()

fun main() {
  while (true) {
    val r = readLine()
    when (r) {
      1 -> OUT.write(YES)
      0 -> OUT.write(NO)
      else -> break
    }
  }

  OUT.flush()
}

private val ch = ByteArray(100)

private fun readByte(): Int = IN.read()

private fun readLine(): Int {
  var foundErr = false
  var r = -1
  var i = 0
  var c = readByte()
  var len = 0
  while (true) {
    if (foundErr) {
      when (c) {
        46 -> return r
        else -> c = readByte()
      }
    } else {
      when (c) {
        32, // ' '
        in 65..90, // 대
        in 97..122, // 소
        -> {
          len++
          r = 1
          c = readByte()
        }
        40, // (
        91, // [
        -> {
          len++
          r = 0
          val cb = c.toByte()
          ch[i++] = cb
          c = readByte()
        }
        41, // )
        93, // ]
        -> {
          len++
          r =
              if (i == 0) {
                foundErr = true
                0
              } else {
                val v = ch[--i]
                if ((c == 41 && v == 40.toByte()) || (c == 93 && v == 91.toByte())) {
                  1
                } else {
                  foundErr = true
                  0
                }
              }
          c = readByte()
        }
        46 -> return if (len <= 0) -1 else if (i == 0) 1 else 0
        else -> c = readByte()
      }
    }
  }
}