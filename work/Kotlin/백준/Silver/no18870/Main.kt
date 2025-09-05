package 백준.Silver.no18870

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val size = br.readLine().toInt()

  val v = StringTokenizer(br.readLine(), " ")
  val arr = IntArray(size) { v.nextToken().toInt() }

  println(solution(arr))
}

/**
 * @param arr 좌표 목록 (X1, X2.., Xn) / -10^9 <= Xi <= 10^9 / 1 <= N <= 1,000,000
 * @return 압축한 좌표목록 (X`1, X`2, ..., X`n)
 */
fun solution(arr: IntArray): String {

  // 중복제거 후, 좌표압축값(=index) 체크
  val copy = arr.toSet().sorted()
  val map = mutableMapOf<Int, Int>()
  for ((index, value) in copy.withIndex()) {
    map[value] = index
  }

  return arr.joinToString(" ") { map[it].toString() }
}
