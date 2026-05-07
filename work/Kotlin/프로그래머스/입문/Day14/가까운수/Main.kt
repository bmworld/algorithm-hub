package 프로그래머스.입문.Day14.가까운수

class Solution {

  fun solution(a: IntArray, n: Int): Int {
    var ans: Int = a[0]
    var diff = abs(n - ans)

    for (i in 1 until a.size) {
      val v = a[i]
      val d = abs(n - v)
      if (d < diff || d == diff && v < ans) {
        ans = v
        diff = d
      }
    }
    return ans
  }

  fun abs(v: Int): Int = if (v < 0) -v else v
}

fun main() {
  val s = Solution()
  check(s.solution(intArrayOf(3, 10, 28), 20) == 28)
  check(s.solution(intArrayOf(10, 11, 12), 13) == 12)
  check(s.solution(intArrayOf(10, 12), 11) == 10)
  check(s.solution(intArrayOf(1, 3), 2) == 1)
  check(s.solution(intArrayOf(1, 4), 2) == 1)
  check(s.solution(intArrayOf(1, 4), 3) == 4)
  check(s.solution(intArrayOf(1), 3) == 1)
  check(s.solution(intArrayOf(100, 1), 10) == 1)
  check(s.solution(intArrayOf(99, 1, 2, 98, 3, 97), 50) == 3)
}
