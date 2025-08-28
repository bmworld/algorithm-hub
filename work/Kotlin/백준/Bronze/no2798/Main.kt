package 백준.Bronze.no2798

import java.util.*

fun main() {

  val sc = Scanner(System.`in`)
  val count = sc.nextInt()
  val m = sc.nextInt()

  val arr = Array(count) { sc.nextInt() }
  println(solution(arr, m))
}

/**
 * @return 최대값에 근접한 카드 3장의 합
 * @arr '카드이 적힌 수' 목록 @m 최대값
 */
fun solution(arr: Array<Int>, max: Int): Int {

  var answer = 0

  BooleanArray(arr.size)

  fun dfs(start: Int, depth: Int, acc: Int) {

    if (acc > max) return
    if (depth == 3) {
      if (acc > answer) answer = acc
      return
    } else {

      for (i in start until arr.size) {
        dfs(i + 1, depth + 1, acc + arr[i])
      }
    }
  }

  dfs(0, 0, 0)

  return answer
}
