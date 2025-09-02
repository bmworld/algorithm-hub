package 백준.Silver.no2839

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val w = br.readLine().toInt()

  println(solution(w))
}

/**
 * @param w 설탕 무게
 * @return 봉지 최소개수 (규격: 3kg, 5kg) -> 정확하게 맞출 수 없는 경우, -1
 */
fun solution(w: Int): Int {

  var cntOf5 = w / 5
  var cntOf3 = 0
  var balance = w % 5
  println("w = ${w}")


  while (balance >= 0) {
    cntOf3++
    balance -= 3

    if (balance == 0) return cntOf5 + cntOf3

    if (cntOf5 > 0) {
      cntOf5--
      balance += 5
    }
  }

  return -1
}
