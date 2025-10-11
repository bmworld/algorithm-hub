import java.io.*

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      val t = nextInt()
      BufferedWriter(OutputStreamWriter(System.out)).use { bw ->
        repeat(t) {
          val n = nextInt()
          solveTo(n, bw)
        }
      }
    }

val c =
    LongArray(101).apply {
      this[1] = 1
      this[2] = 1
      this[3] = 1
      this[4] = 2
      this[5] = 2
      for (i in 6..100) {
        this[i] = this[i - 1] + this[i - 5]
      }
    }

fun solveTo(n: Int, out: Appendable) {
  out.append(c[n].toString()).append("\n")
}