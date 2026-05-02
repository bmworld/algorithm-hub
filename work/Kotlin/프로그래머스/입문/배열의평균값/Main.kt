package 프로그래머스.입문.배열의평균값

class Solution {

  fun solution(arr: IntArray): Double = arr.sum().toDouble() / arr.size
}

fun main() {
  val s = Solution()
  check(s.solution(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)) == 5.5)
  check(s.solution(intArrayOf(89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99)) == 94.0)
}
