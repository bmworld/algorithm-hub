package 백준.Silver.no13241

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max
import kotlin.math.min

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val st = StringTokenizer(br.readLine(), " ")

  println(lcm(st.nextToken().toLong(), st.nextToken().toLong()))
}

/**
 * 유클리드 호제법 <br> 원리: a * b = gcd(a, b) * lcm(a, b)
 *
 * @return 최소공배수
 */
fun lcm(a: Long, b: Long) = (a * b) / gcd(a, b)

/** @return 최대 공약수 */
tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

/**
 * 그냥 풀기 <br>
 *
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
