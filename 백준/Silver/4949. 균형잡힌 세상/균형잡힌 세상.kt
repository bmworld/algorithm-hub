import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val IN = BufferedInputStream(System.`in`, 1 shl 20)
private val OUT = BufferedOutputStream(System.`out`, 1 shl 20)
private val YES = "yes\n".toByteArray()
private val NO = "no\n".toByteArray()

fun main() {
  while (true) {
    val r = readLine()
    when (r) {
      1 -> OUT.write(YES)
      0 -> OUT.write(NO)
      else -> {
        OUT.flush()
        return
      }
    }
  }
}

private val ch = ByteArray(100)

private fun readLine(): Int {
  var checkDone = false
  var result = -1
  var i = 0
  var c = IN.read()
  var len = 0
  while (true) {
    if (checkDone) {
      when (c) {
        46 -> return result
        else -> c = IN.read()
      }
    } else {
      when (c) {
        32, // ' '
        in 65..90, // 대
        in 97..122, // 소
        -> {
          len++
          result = 1
          c = IN.read()
        }
        40, // (
        91, // [
        -> {
          len++
          result = 0
          ch[i++] = c.toByte()
          c = IN.read()
        }
        41, // )
        93, // ]
        -> {
          len++
          result =
              if (i == 0) {
                checkDone = true
                0
              } else {
                val v = ch[--i]
                if ((c == 41 && v == 40.toByte()) || (c == 93 && v == 91.toByte())) {
                  1
                } else {
                  checkDone = true
                  0
                }
              }
          c = IN.read()
        }
        46 -> return if (len <= 0) -1 else if (i == 0) 1 else 0
        else -> c = IN.read()
      }
    }
  }
}