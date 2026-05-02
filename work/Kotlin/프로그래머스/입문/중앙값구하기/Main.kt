package 프로그래머스.입문.중앙값구하기

class Solution {

  fun solution(array: IntArray): Int {
    array.sort()
    return array[array.size / 2]
  }
}

fun main() {
  val s = Solution()
  check(s.solution(intArrayOf(9, -9, 0)) == 0)
  check(s.solution(intArrayOf(5, 3, 1, 8, 9)) == 5)
}
