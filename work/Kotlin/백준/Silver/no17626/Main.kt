package 백준.Silver.no17626

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
  var minCnt = 5
  val n = i()
  var found = false
  val ch = IntArray(4)
  fun dfs(
    acc: Int,
    maxSqrt: Int,
    cnt: Int,
  ) {
    if (acc == n) {
      found = true
      minCnt = cnt
      return
    } else if (found || acc > n || cnt >= 4 || cnt > 1 && ch[cnt - 2] < ch[cnt - 1]) return

    for (sqrt in maxSqrt downTo 1) {
      val x = sqrt * sqrt
      val nextAcc = acc + x
      if (nextAcc > n) continue
      ch[cnt] = x
      dfs(nextAcc, sqrt, cnt + 1)
    }
  }

  w(
    when {
      n > 3 -> {
        dfs(0, sqrt(n.toDouble()).toInt(), 0)
        minCnt
      }

      else -> n
    }
  )

  OUT.flush()
}
