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
