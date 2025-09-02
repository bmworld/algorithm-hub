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

  var cnt5 = w / 5
  var cnt3 = 0
  var balance = w % 5
  if (balance == 0) return cnt5

  while (cnt5 >= 0 && balance > 0) {

    while (balance >= 3) {
      cnt3++
      balance -= 3
    }

    if (balance == 0) return cnt5 + cnt3

    cnt5--
    balance += 5
  }

  return -1
}
