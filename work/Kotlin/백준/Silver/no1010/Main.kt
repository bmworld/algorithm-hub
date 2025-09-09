package 백준.Silver.no1010

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

val cache = Array(30) { IntArray(30) { 0 } }

fun main() {

  val sc = BufferedReader(InputStreamReader(System.`in`))
  var size = sc.readLine().trim().toInt()

  while (size > 0) {
    val st = StringTokenizer(sc.readLine(), " ")
    val m = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    println(solution(n, m))
    size--
  }
}

/**
 * @param n 전체항목 수 (0 < N ≤ M < 30)
 * @param m 선택할 항목 수 (0 < N ≤ M < 30)
 * @return 경우의 수
 */
fun solution(n: Int, m: Int): Int {
  if (cache[n][m] > 0) return cache[n][m]
  if (n == m || m == 0) return 1
  else {
    cache[n][m] = solution(n - 1, m - 1) + solution(n - 1, m)
    return cache[n][m]
  }
}
