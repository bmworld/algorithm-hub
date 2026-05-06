package 프로그래머스.입문.Day11.주사위의개수

class Solution {

  fun solution(box: IntArray, n: Int): Int {
    val wc = box[0] / n
    val dc = box[1] / n
    val hc = box[2] / n
    return wc * dc * hc
  }
}

fun main() {
  val s = Solution()
  check(s.solution(intArrayOf(1, 1, 1), 1) == 1)
  check(s.solution(intArrayOf(10, 8, 6), 3) == 12)
  check(s.solution(intArrayOf(5, 6, 6), 3) == 4)
}
