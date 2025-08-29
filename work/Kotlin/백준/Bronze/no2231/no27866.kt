package 백준.Bronze.no2231

import java.util.*
import kotlin.math.min

fun main() {

  val sc = Scanner(System.`in`)

  val n = sc.nextLine().toInt()
  println(solution(n))
}

fun solution(n: Int): Int {

  var m = Int.MAX_VALUE

  // N = M + (M 의 각 자리수의 합)
  // M: N의 생성자

  // N
  for (num in 1 until n) {

    // 자리수의 합
    val sum = num.toString().map { it.digitToInt() }.toIntArray().sum()

    // 분해합
    val sumOfDisassembly = num + sum

    if (sumOfDisassembly == n) {
      m = min(m, num)
    }
  }

  return if (m != Int.MAX_VALUE) m else 0
}
