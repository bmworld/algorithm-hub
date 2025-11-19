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

  val n = i()

  w(
    when {
      n > 3 -> {
        val maxSqrt = sqrt(n.toDouble()).toInt()
        if (n == maxSqrt * maxSqrt) 1  // STEP 1
        else {
          var tmp = n
          while (tmp % 4 == 0) tmp /= 4
          var minCnt = if (tmp % 8 == 7) 4 else 3 // STEP 2
          if (minCnt == 3) {
            for (i in maxSqrt downTo 2) {
              val iRem = n - i * i
              for (j in sqrt(iRem.toDouble()).toInt() downTo 1) {
                if (iRem == j * j) {
                  minCnt = 2 // STEP 3
                  break
                }
              }
            }
          }

          minCnt
        }
      }

      else -> n
    }
  )
  OUT.flush()
}