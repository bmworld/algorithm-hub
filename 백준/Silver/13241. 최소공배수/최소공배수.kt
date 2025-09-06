
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max
import kotlin.math.min

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val st = StringTokenizer(br.readLine(), " ")

  println(solution(st.nextToken().toLong(), st.nextToken().toLong()))
}

/**
 * @param a 정수
 * @param b 정수
 * @return 최소공배수
 */
fun solution(a: Long, b: Long): Long {

  val n = min(a, b)
  val m = max(a, b)

  var answer = n * m

  for (i in n until n * m step n) {
    if (i % m == 0L) {
      answer = min(answer, i)
      break
    }
  }

  return answer
}
