package 프로그래머스.입문.Day16.가장큰수찾기


class Solution {

  fun solution(array: IntArray): IntArray {
    var max = 0
    var pos = 0
    for (i in array.indices) {
      val x = array[i]
      if (x > max) {
        max = x
        pos = i
      }
    }
    return intArrayOf(max, pos)
  }
}

fun main() {
  val s = Solution()

  val act = s.solution(intArrayOf(1, 8, 3))
  val exp = intArrayOf(8, 1)
  repeat(exp.size) {
    check(act[it] == exp[it])
  }

}
