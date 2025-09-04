package 백준.Silver.no1427

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val n = br.readLine()

  println(solution(n))
}

/**
 * @param n 숫자
 * @return 각 자리의 수를 내림차순으로 정렬한 수
 */
fun solution(n: String): Int {
  val nums = n.map { it.digitToInt() }.toMutableList()

  for (i in 1 until nums.size) {
    val v = nums[i]

    for (j in 0 until i) {

      if (nums[j] < v) {
        var k = i
        while (k > j) {
          nums[k] = nums[k - 1]
          k--
        }

        nums[j] = v

        break
      }
    }
  }

  return nums.joinToString("").toInt()
}
