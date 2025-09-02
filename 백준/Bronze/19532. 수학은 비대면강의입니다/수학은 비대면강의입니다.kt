
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val v = StringTokenizer(br.readLine(), " ")
  val arr = IntArray(6) { v.nextToken().toInt() }

  println(solution(arr))
}

/**
 * @return N번째 종말의 수 (666이 적어도 3개 이상 연속으로 들어가는 수)
 * @N 순서
 */
fun solution(arr: IntArray): String {
  for (x in -999..999) {
    for (y in -999..999) {
      if (arr[0] * x + arr[1] * y == arr[2] && arr[3] * x + arr[4] * y == arr[5]) {
        return "$x $y"
      }
    }
  }

  return ""
}