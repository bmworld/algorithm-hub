package 백준.Bronze.no27433

import java.io.StreamTokenizer

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      print(FACT[nextInt()])
    }

val FACT =
    LongArray(21).apply {
      this[0] = 1
      for (i in 1..20) this[i] = this[i - 1] * i
    }
