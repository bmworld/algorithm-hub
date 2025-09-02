package 백준.Bronze.no19532

import java.util.*

fun main() {

  val v = StringTokenizer(readln())
  val arr = IntArray(6) { v.nextToken().toInt() }

  println(solution(arr))
}

fun solution(arr: IntArray): String {
  val a = arr[0]
  val b = arr[1]
  val c = arr[2]
  val d = arr[3]
  val e = arr[4]
  val f = arr[5]

  val x = (c * e - b * f) / (a * e - b * d)
  val y = (c * d - a * f) / (b * d - a * e)

  //  for (x in -999..999) {
  //    for (y in -999..999) {
  //
  //      if (a * x + b * y == c && d * x + e * y == f) {
  //        return "$x $y"
  //      }
  //    }
  //  }

  return "$x $y"
}
