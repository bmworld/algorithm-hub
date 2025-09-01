package 백준.Silver.no1436

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val n = br.readLine().toInt()

  println(solution(n))
}

/**
 * @return N번째 종말의 수 (666이 적어도 3개 이상 연속으로 들어가는 수)
 * @N 순서
 */
fun solution(order: Int): Int {
  var cnt = 0
  var x = 666
  while (true) {
    if (x.toString().contains("666")) {
      cnt++
      if (cnt == order) return x
    }
    x++
  }
}
