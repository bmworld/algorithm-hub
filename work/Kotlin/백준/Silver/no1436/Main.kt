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

  val endNum = 666

  val ch = Array(order + 1) { 0 }

  fun nextNum(n: Int): Int {
    if (ch[n] != 0) return ch[n]
    var result: Int
    if (n == 1) {
      result = endNum
    } else {

      var next = nextNum(n - 1) + 1
      while (!next.toString().contains(endNum.toString())) {
        next++
      }

      result = next
    }

    ch[n] = result

    return result
  }

  return nextNum(order)
}
