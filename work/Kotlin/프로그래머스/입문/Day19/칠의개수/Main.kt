package 프로그래머스.입문.Day19.칠의개수


class Solution {

  fun solution(a: IntArray): Int {
    var ans = 0
    for (i in 0 until a.size) {
      var x = a[i]
      while (x > 0) {
        val r = x % 10
        if (r == 7) ans++
        x /= 10
      }
    }
    return ans
  }
}

fun main() {
  val s = Solution()
  check(s.solution(intArrayOf(7, 77, 17)) == 4)
  check(s.solution(intArrayOf(0, 10000)) == 0)
}
