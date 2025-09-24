package 백준.Gold.no2447

import java.io.*
import kotlin.math.pow

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      val n = nextInt()

      BufferedWriter(OutputStreamWriter(System.out)).use { bw -> solveTo(n, bw) }
    }

fun solveTo(n: Int, out: Appendable) {
  val p = Array(n) { CharArray(n) { ' ' } }

  fun fill(x: Int, y: Int, size: Int) {
    if (size == 1) {
      p[y][x] = '*'
      return
    }

    val t = size / 3
    var dy = 0
    while (dy < 3) {
      var dx = 0
      while (dx < 3) {
        val isNotCenter = !(dx == 1 && dy == 1)
        if (isNotCenter) {
          fill(x + dx * t, y + dy * t, t)
        }

        dx++
      }

      dy++
    }
  }

  fill(0, 0, n)

  var i = 0
  while (i < n) {
    out.append(String(p[i]))
    if (i != n - 1) out.append("\n")
    i++
  }
}

/**
 * @param n 한 변 너비 (3^k 1<=k<8)
 * @return n x n 패턴 [중앙공백 너비 = (n/3)x(n/3)]
 */
fun solveTo_OLD(n: Int, out: Appendable) {

  val pattern =
      StringBuilder(
          n * n // Pattern
          + n // 줄바꿈
      )

  fun recursion(dep: Int) {
    val rows = pattern.split("\n")
    val curLen = rows.size
    val nextLen = 3.0.pow(dep)

    // Make Pattern
    if (curLen < nextLen) {
      // 패턴 가져옴
      val p = if (rows[0].isEmpty()) "*" else pattern.toString() // 복사
      // 초기화
      pattern.clear()

      for (y in 0 until curLen * 3 step 1) {
        for (x in 0 until curLen * 3 step curLen) {
          val isMiddle =
              x >= nextLen / 3 && x < nextLen * 2 / 3 && y >= nextLen / 3 && y < nextLen * 2 / 3
          var curPattern: String
          if (isMiddle) {
            val empty = StringBuilder(curLen)
            repeat(curLen) { empty.append(" ") }
            curPattern = empty.toString()
          } else {
            val split = p.split("\n")
            curPattern = if (split.size <= 1) "*" else split[y % curLen]
          }

          pattern.append(curPattern)
        }
        val beforeLast = y < curLen * 3 - 1
        if (beforeLast) pattern.append("\n")
      }
    }

    // Check Recursion
    if (nextLen < n) {
      recursion(dep + 1)
    }
  }

  recursion(1)

  out.append(pattern.trim())
}

/** 테스트 */
fun solution(n: Int): String {
  val sb = StringBuilder()
  solveTo(n, sb)
  return sb.trimEnd().toString()
}
