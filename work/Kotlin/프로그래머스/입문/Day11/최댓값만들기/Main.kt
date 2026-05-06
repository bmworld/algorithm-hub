package 프로그래머스.입문.Day11.최댓값만들기

class Solution {

  fun solution(arr: IntArray): Int {
    var r1 = 0
    var r2 = 0

    repeat(arr.size) {
      val n = arr[it]
      if (n > r1) {
        r2 = r1
        r1 = n
      } else if (n > r2) {
        r2 = n
      }
    }
    return r1 * r2
  }
}

fun main() {
  val s = Solution()
  check(s.solution(intArrayOf(1, 2, 3, 4, 5)) == 20)
  check(s.solution(intArrayOf(0, 31, 24, 10, 1, 9)) == 744)
}
