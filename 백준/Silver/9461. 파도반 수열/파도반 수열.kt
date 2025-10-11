import java.io.StreamTokenizer

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }
      val t = nextInt()
      val sb = StringBuilder(t * 14)
      repeat(t) {
        val n = nextInt()
        sb.append(c[n]).append("\n")
      }
      print(sb)
    }

val c =
    LongArray(101).also { a ->
      a[1] = 1
      a[2] = 1
      a[3] = 1
      for (i in 4..100) {
        a[i] = a[i - 2] + a[i - 3]
      }
    }