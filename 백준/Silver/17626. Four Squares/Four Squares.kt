import java.io.BufferedOutputStream
import kotlin.math.sqrt

private val OUT = BufferedOutputStream(System.`out`, 1 shl 11)

private val IN: ByteArray = System.`in`.readBytes()
private var inPos = 0
private const val EOF = -1

private fun r(): Int = if (inPos < IN.size) (IN[inPos++].toInt() and 0xFF) else EOF

private fun i(): Int {
  var n = 0
  var c = r()
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = r()
  }
  return n
}

private val outBuf = ByteArray(1)

private fun w(num: Int) {
  outBuf[0] = (num + 48).toByte()
  OUT.write(
    outBuf, 0, 1
  )
}


fun main() {
  var minCnt = 4
  val n = i()
  var found = false

  w(
    when {
      n > 3 -> {
        val maxSqrt = sqrt(n.toDouble()).toInt()
        for (i in maxSqrt downTo 1) {
          val iSq = i * i
          if (found) break
          if (n == iSq) {
            minCnt = 1
            break
          }
          val iRem = n - iSq
          for (j in i downTo 1) {
            val jSq = j * j
            if (iRem == jSq) {
              minCnt = 2
              found = true
              break
            }
            val jRem = iRem - jSq
            for (k in j downTo 1) {
              val kSq = k * k
              if (jRem == kSq) {
                if (minCnt > 3) minCnt = 3
                break
              }
            }
          }
        }
        minCnt
      }

      else -> n
    }
  )

  OUT.flush()
}