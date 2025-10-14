package 백준.Bronze.no1000

import java.io.StreamTokenizer

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      val a = nextInt()
      val b = nextInt()

      println(a + b)
    }
