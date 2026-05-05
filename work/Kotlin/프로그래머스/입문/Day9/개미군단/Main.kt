package 프로그래머스.입문.Day9.개미군단

class Solution {

  val G = 5
  val S = 3
  val W = 1
  fun solution(hp: Int): Int {
    var v = hp

    var ans = 0
    if (v >= G) {
      ans += v / G
      v %= G
    }
    if (v >= S) {
      ans += v / S
      v %= S
    }

    if (v >= W) {
      ans += v / W
    }

    return ans
  }
}

fun main() {
  val s = Solution()
  check(s.solution(23) == 5)
  check(s.solution(24) == 6)
  check(s.solution(999) == 201)
}
